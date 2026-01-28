
public class Feedback{
	private Int id;
	private Int customerId;
	private String feedbackText;
	private LocalDateTime createdAt = LocalDateTime.now();
	private String sentiment;
	private Double[] embedding;
	private String channel;
	private Int productId;
	private String status;
	private String responseText;
	
	public Feedback( Int id, Int customerId, String feedbackText, LocalDateTime createdAt, String sentiment, Double[] embedding, String channel, Int productId, String status, String responseText) {
		this.id = id
		this.customerId = customerId;
		this.feedbackText = feedbackText;
		this.createdAt = createdAt;
		this.sentiment = sentiment;
		this.embedding = embedding;
		this.channel = channel;
		this.productId = productId;
		this.status = status;
		this.responseText = responseText;
	}
	
	public Int getId() {
		return id;
	}
	public void setId(Int id) {
		this.id = id;
	}
	
	public Int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Int customerId) {
		this.customerId = customerId;
	}
	
	public String getFeedbackText() {
		return feedbackText;
	}
	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	
	public Double[] getEmbeddings() {
		return embedding;
	}
	public void setEmbeddings(Double[] embedding) {
		this.embedding = embedding;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	
	}
	
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	
}