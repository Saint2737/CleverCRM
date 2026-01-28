package entity;

import java.time.Instant;
import java.util.UUID;

@entity
@Table(name="crm_chunk")
public class CrmChunk {
	
	private UUID id = UUID.randomUUID();
	private String sourceType;
	private String sourceId;
	private int chunkIndex;
	
	@Column(columnDefinition="Text")
	private String content;
	private float[]embedding;
	private Instant createdAt = Instant.now();
	
	public CrmChunk(UUID id, String sourceType, String sourceId, int chunkIndex, String content, float[] embedding, Instant createdAt ) {
		
		this.id = id;
		this.sourceType = sourceType;
		this.sourceId = sourceId;
		this.chunkIndex = chunkIndex;
		this.content = content;
		this.embedding = embedding;
		this.createdAt = createdAt;
		
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
		
	}
	
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	public int getChunkIndex() {
		return chunkIndex;
	}
	public void setChunkIndex(int chunkIndex) {
		this.chunkIndex = chunkIndex;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public float[] getEmbedding() {
		return embedding;
	}
	public void setEmbedding(float[] embedding) {
		this.embedding = embedding;
	}
	
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	
}
