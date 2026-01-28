package repository;

public interface CrmChunkRepository extends JpaRepository<CrmChunk, UUID>{
	
	@Query(value= """
			SELECT id, source_type, source_id,chunk_index, content, embedding FROM crm_chunk
				ORDER BY embedding <->  				
				CAST (:queryEmbedding AS vector) 
				LIMIT :k """, nativeQuery = true)
	List< CrmChunk> findNearest(@Param("queryEmbedding") String queryEmbedding, @Param("k") int k);
	
	CrmChunk save(CrmChunk crmChunk);
			

}
