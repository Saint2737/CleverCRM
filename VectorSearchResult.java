package DTO;
public class VectorSearchResult{
	
	private final String id;
	private final Double similarity;
	private final Map<String,String> metadata;
	
	
	public VectorSearchResult(String id, Double similarity, Map<String,String> metadata ){
		this.id = id;
		this.similarity = similarity;
		this.metadata = metadata;
	}
	
}