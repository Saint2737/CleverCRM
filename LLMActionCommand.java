package DTO;

public class LLMActionCommand {
	
	private String type;
	//create_task, notify_staff, no_action
	private String module;
	//housekeeping, maintenance, reservation
	private String description;
	private String priority;
	private Map<String, Object> payload;
	
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
	
	public Map<String, Object> getPayload(){
		return payload;
	}
	public void setPayload(Map<String, Object> payload) {
		this.payload = payload;
	}
	
	

}
