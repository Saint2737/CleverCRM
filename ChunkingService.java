package service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cleverCRM.exception.ExternalServiceException;

import entity.CrmChunk;

public class ChunkingService {
	
	private static final Logger log = LoggerFactory.getLogger(ChunkingService.class);
	
	private final EmbeddingService embeddingService;
	private final OpenAiClient openAiClient;
	private final CrmChunkRepository crmChunkRepository;
	
	public ChunkingService( EmbeddingService embeddingService, OpenAiClient openAiClient,
			CrmChunkRepository crmChunkRepository) {
		
		this.embeddingService = embeddingService;
		this.openAiClient = openAiClient;
		this.crmChunkRepository = crmChunkRepository;
	}
	
	public List<String> chunkText(String text, int maxChars, int overlap){
		
		if(text == null || text.isBlank())
			throw new IllegalArgumentException("text must not be blank");
		if(maxChars <= 0)
			throw new IllegalArgumentException("maxChars must be positive");
		if(overlap < 0 || overlap >= maxChars)
			throw new IllegalArgumentException("overlap must be between 0 and maxChars");
		
		List<String> chunks = new ArrayList<>();
		String[] paras = text.split("\\n\\n+");
		StringBuilder cur = new StringBuilder();
		for(String p : paras) {
			if(cur.length()+ p.length() > maxChars) {
				chunks.add(cur.toString().trim());
				int start = Math.max(0, cur.length() -overlap);
				cur = new StringBuilder(cur.substring(start));
			}
			cur.append(p).append("\n\n");
		}
		if(cur.length()>0)
			chunks.add(cur.toString().trim());
		
		return chunks;
	}
	
	
	public int ingestDocument(String sourceType, String sourceId, String content) {
		
		if(sourceId == null || sourceId.isBlank())
			throw new IllegalArgumentException("sourceId must not be blank");
		
		// split the full text into manageable chunks .......each chunk is upto 2000 characters, with 200 characters overlapping
		List<String> chunks = chunkText(content,2000, 200);
		
		//loop through each chunk, generate an embedding and store them
		for(int i = 0 ; i < chunks.size(); i++ ) {
			
			String chunk = chunks.get(i);
			
			float[] vectorArr;
			try {
				vectorArr = openAiClient.createEmbedding(chunk);
			} catch (ExternalServiceException ex) {
				// fail the whole ingestion: a partially embedded document would be
				// silently unsearchable
				throw new ExternalServiceException(
						"Embedding failed for chunk " + i + " of source " + sourceId, ex);
			}
			
			CrmChunk crmChunk = new CrmChunk();
			crmChunk.setSourceType(sourceType);
			crmChunk.setSourceId(sourceId);
			crmChunk.setChunkIndex(i);
			crmChunk.setContent(chunk);
			crmChunk.setEmbedding(vectorArr);
			
			crmChunkRepository.save(crmChunk);
			
		}
		
		log.info("Ingested {} chunks for source {}", chunks.size(), sourceId);
		
		return chunks.size();
	}

}
