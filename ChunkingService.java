package service;

import java.util.List;

import entity.CrmChunk;

public class ChunkingService {
	
	private static final int MAX_CHARS_LIMIT = 20000;
	private static final int MAX_CONTENT_LENGTH = 2_000_000;
	
	private final EmbeddingService embeddingService;
	private final OpenAiClient openAiClient;
	
	public ChunkingService( EmbeddingService embeddingService, OpenAiClient openAiClient) {
		
		this.embeddingService = embeddingService;
		this.openAiClient = openAiClient;
	}
	
	public List<String> chunkText(String text, int maxChars, int overlap){
		
		if(text == null || text.isBlank()) {
			throw new IllegalArgumentException("text must not be blank");
		}
		if(text.length() > MAX_CONTENT_LENGTH) {
			throw new IllegalArgumentException("text must not exceed " + MAX_CONTENT_LENGTH + " characters");
		}
		if(maxChars < 1 || maxChars > MAX_CHARS_LIMIT) {
			throw new IllegalArgumentException("maxChars must be between 1 and " + MAX_CHARS_LIMIT);
		}
		if(overlap < 0 || overlap >= maxChars) {
			throw new IllegalArgumentException("overlap must be between 0 and maxChars - 1");
		}
		
		List<CrmChunk> chunks = new ArrayList<>();
		String[] paras = text.split("\\n\\n+");
		StringBuilder cur = new StringBuilder();
		for(String p : paras) {
			if(cur.length()+ p.length() > maxChars) {
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
		
		if(sourceType == null || sourceType.isBlank() || sourceId == null || sourceId.isBlank()) {
			throw new IllegalArgumentException("sourceType and sourceId must not be blank");
		}
		
		// split the full text into manageable chunks .......each chunk is upto 2000 characters, with 200 characters overlapping
		List<String> chunks = chunkText(content,2000, 200);
		
		//loop through each chunk, generate an embedding and store them
		for(int i = 0 ; i < chunks.size(); i++ ) {
			
			String chunk = chunks.get(i);
			
			EmbeddingsRequest req = EmbeddingsRequest.builder()
					.model("text-embedding-3-large")
					.input(List.of(chunk))
					.build();
			
			EmbeddingsResponse resp = openAiClient.createEmbedding(req);
			
			List<Float> vector = resp.getData().get(0).getEmbedding();
			
			//convert List<float> to a primitive float array 
			float[] vectorArr = new float[vector.size()];
			for( int j = 0; j < vector.size(); j++) {
				vectorArr[j] = vector.get(j);
			}
			
			CrmChunk crmChunk = new CrmChunk();
			crmChunk.setSourceType(sourceType);
			crmChunk.setSourceId(sourceId);
			crmChunk.setChunkIndex(i);
			crmChunk.setContent(chunk);
			crmChunk.setEmbedding(vectorArr);
			
			crmChunkRepository.save(crmChunk);
			
		}
		

		
		return chunks.size();
	}

}
