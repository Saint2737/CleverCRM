package component;

import java.util.List;
import java.util.Map;

import service.Feedback;

@component
public class OpenAiClient{
	private final WebClient webClient;
	private final String apiKey;
	
	public OpenAiClient{
		this.apiKey = System.getenv().getOrDefault("OPENAI_API_KEY","MY_API_KEY");
		this.webClient = WebClient.builder()
				.baseUrl("https://api.openai.com/v1")
				.defaultHeader(HtttpHeaders.AUTHORIZATION, "Bearer" + this.apiKey)
				.build();
						
	}
	
	public float[] createEmbedding(String text) {
		
		//minimal example body for embeddings endpoint
		Map<String, Object> body = Map.of("model", "text-embedding-small", "input", text);
		
		Map response = webClient.post()
				.uri("/embeddings");
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(body))
				.retrieve()
				.bodyToMono(map.class)
				.block();
				
		//response structure
		List data = (List)response.get("data");
		Map first = (Map)data.get(0);
		List embeddingList = (List)first.get("embedding");
		float[] vector = new float[embeddingList.size()];
		for(int i = 0, i < embeddingList.size(); i++) 
			vector[i] = ((Number)embeddingList.get(i)).floatValue();
		
		
		return  vector;
			
	}
	
	
	//call chat completion to generate response
	public String createChatCompletions(String systemPrompt, List<Map<String, String>> messages, double temperature) {
		Map<String, Object> body = Map.of("model", "gpt-4o-mini", "messages", messages, "temperature", temperature);
		Map resp = webClient.post()
				.uri("/chat/completions")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(body))
				.retrieve()
				.bodyToMono(Map.class)
				.block();
		
		List choices = (List)resp.get("choices");
		if(choices == null || choices.isEmpty()) return "";
		Map choice0 = (Map)choices.get(0);
		Map message = (Map)choice0.get("message");
		
		return (String)message.get("content");
		
	}	
	

	
	public String analyzeFeedback(String feedbackText) {
		
		Map<String, Object> requestBody = Map.of("model", "gpt-4o-mini",
				"messages", List.of(Map.of("role","system","content","You are a sentiment analyzer." +" " + "Classify the following feedback as positive,negative or neutral"), 
						Map.of("role","user","content", feedbackText)), "temperature",0);
		
		Map response = webClient.post()
				.uri("/chat/completions")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(requestBody)
				.retrieve()
				.bodyToMono(Map.class)
				.block();
		
		if(response == null) {
			return "unknown";
		}
		
		List<Map<String,Object>> choices = (List<Map<String,Object>>) response.get("choices");
		if(choices == null || choices.isEmpty()) return "unknown";
		
		Map<String,Object> message = (Map<String, Object>) choices.get(0).get("message");
		
		String sentiment = ((String)message.get("content")).trim().toLowerCase();
		if(sentiment.contains("positive") ) return "positive";
		if(sentiment.contains("negative")) return "negative";
		if(sentiment.contains("neutral")) return "neutral";
		
	}
	




	public String classifyMessage(String content) {
		
		Map<String, Object> requestBody = Map.of("model","gpt-4o-mini", "messages", List.of(Map.of("role", "system","content","You are a message classifier." + " " + "classify the message text considering the intent and objective of the text and recommend necessary action to follow."),
				Map.of("role", "user", "content", content)), "temperature", 0);
		
		Map response = webClient.post()
				.uri("/chat/classify")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(requestBody)
				.retrieve()
				.bodyToMono(Map.class)
				.block();
		
		if(response == null) {
			return "unknown";
		}
		
		List<Map>
		// TODO Auto-generated method stub
		return null;
	}}