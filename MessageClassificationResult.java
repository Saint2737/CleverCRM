package DTO;

import java.util.Map;

public class MessageClassificationResult {
	
	private String category;
	private double confidence;
	private String sentiment;
	private SuggestedAction suggestedAction;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public double getConfidence() {
		return confidence;
	}
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	
	public SuggestedAction getSuggestedAction() {
		return suggestedAction;
	}
	public void setSuggestedAction(SuggestedAction suggestedAction) {
		this.suggestedAction = suggestedAction;
	}
	public static class SuggestedAction{
		private String type;
		private String module;
		private String description;
		private String priority;
		private Map<String,Object> payload;
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
		public String getModule() {
			return module;
		}
		public void setModule(String module) {
			this.module = module;
		}
		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		public String getPriority() {
			return priority;
		}
		public void setPriority(String priority) {
			this.priority = priority;
		}
		
		public Map<String,Object> getPayload(){
			return payload;
		}
		public void setPayload(Map<String,Object> payload) {
			this.payload = payload;
		}
		
	}

}
