import java.util.List;

import com.cleverCRM.exception.ExternalServiceException;

public class EmbeddingService{
	private final OpenAiClient openAiClient;
	private final VectorStoreAdapter vectorStore;
	
	public EmbeddingService(OpenAiClient openAiClient, VectorStoreAdapter vectorStore){
		this.openAiClient = openAiClient;
		this.vectorStore = vectorStore;	
	}
	
	public String createEmbeddingAndStore(String text) {
		float[] embedding = openAiClient.createEmbedding(text);
		
		String vectorId = vectorStore.insertVector(embedding);
		if (vectorId == null || vectorId.isBlank()) {
			throw new ExternalServiceException("Vector store did not return an id for the inserted vector");
		}
		
		return vectorId;
	}
	
	public List<VectorSearchResult> querySimilar(String text, int topK){
		if (topK <= 0) {
			throw new IllegalArgumentException("topK must be positive");
		}
		float[] embedding = openAiClient.createEmbedding(text);
		
		List<VectorSearchResult> results = vectorStore.querySimilar(embedding,topK);
		if (results == null) {
			throw new ExternalServiceException("Vector store returned no result set for the similarity query");
		}
		
		return results;
	}
	
	
}
