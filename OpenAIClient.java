package component;

import java.util.List;
import java.util.Map;

import util.VectorUtils;

@component
public class OpenAiClient{
	private static final String CHAT_MODEL = "gpt-4o-mini";
	private static final String EMBEDDING_MODEL = "text-embedding-small";
	
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
		Map<String, Object> body = Map.of("model", EMBEDDING_MODEL, "input", text);
		
		Map<String, Object> response = post("/embeddings", body);
		
		//response structure
		List<Map<String, Object>> data = (List<Map<String, Object>>)response.get("data");
		List<Number> embeddingList = (List<Number>)data.get(0).get("embedding");
		
		return VectorUtils.toFloatArray(embeddingList);
			
	}
	
	
	//call chat completion to generate response
	public String createChatCompletions(String systemPrompt, List<Map<String, String>> messages, double temperature) {
		Map<String, Object> body = Map.of("model", CHAT_MODEL, "messages", messages, "temperature", temperature);
		
		return firstChoiceContent(post("/chat/completions", body));
	}
	
	public String analyzeFeedback(String feedbackText) {
		String sentiment = prompt(
				"You are a sentiment analyzer. Classify the following feedback as positive,negative or neutral",
				feedbackText).trim().toLowerCase();
		
		if(sentiment.contains("positive")) return "positive";
		if(sentiment.contains("negative")) return "negative";
		if(sentiment.contains("neutral")) return "neutral";
		
		return "unknown";
	}
	
	public String classifyMessage(String content) {
		return prompt(
				"You are a message classifier. classify the message text considering the intent and objective of the text and recommend necessary action to follow.",
				content);
	}
	
	//single system + user turn, the shape every prompt in this client uses
	private String prompt(String systemPrompt, String userContent) {
		return createChatCompletions(
				systemPrompt,
				List.of(
						Map.of("role", "system", "content", systemPrompt),
						Map.of("role", "user", "content", userContent)),
				0);
	}
	
	private Map<String, Object> post(String uri, Map<String, Object> body) {
		return webClient.post()
				.uri(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(body))
				.retrieve()
				.bodyToMono(Map.class)
				.block();
	}
	
	private String firstChoiceContent(Map<String, Object> response) {
		if(response == null) return "";
		
		List<Map<String, Object>> choices = (List<Map<String, Object>>)response.get("choices");
		if(choices == null || choices.isEmpty()) return "";
		
		Map<String, Object> message = (Map<String, Object>)choices.get(0).get("message");
		if(message == null) return "";
		
		return (String)message.get("content");
	}
	
}
