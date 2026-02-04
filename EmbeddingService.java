import java.util.List;

import entity.CrmChunk;

public class EmbeddingService{
	private final OpenAiClient openAiClient;
	private final VectorStoreAdapter vectorStore;
	
	public EmbeddingService(OpenAiClient openAiClient, VectorStoreAdapter vectorStore){
		this.openAiClient = openAiClient;
		this.vectorStore = vectorStore;	
	}
	
	public String CreateEmbeddingAndStore(String text) {
		float[] embedding = openAiClient.createEmbedding(text);
		
		return vectorStore.insertVector(embedding);
	}
	
	public List<VectorSearchResult> querySimilar(String text, int topK){
		float[] embedding = openAiClient.createEmbedding(text);
		
		return vectorStore.querySimilar(embedding,topK);
	}
	
	
}
