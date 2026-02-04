package service;

import java.util.List;

import entity.CrmChunk;

public class ChunkingService {
	
	private final EmbeddingService embeddingService;
	private final OpenAiClient openAiClient;
	
	public ChunkingService( EmbeddingService embeddingService, OpenAiClient openAiClient) {
		
		this.embeddingService = embeddingService;
		this.openAiClient = openAiClient;
	}
	
	public List<String> chunkText(String text, int maxChars, int overlap){
		
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
		
		System.out.println("successfully ingested " + chunks.size() + "chunks for source: " + sourceId );
		
		return chunks.size();
	}

}
