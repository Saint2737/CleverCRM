package Interface;
 pubic interface VectorStoreAdapter{
	 List<VectorSearchResult>querySimilar(float[]vector, int topK);
	 String insertVector(float[]vector);  
 }