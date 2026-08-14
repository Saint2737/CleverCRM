package component;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.cleverCRM.exception.ExternalServiceException;

@Component
public class OpenAiClient {

	private static final Logger log = LoggerFactory.getLogger(OpenAiClient.class);

	private final WebClient webClient;
	private final String apiKey;

	public OpenAiClient() {
		this.apiKey = System.getenv("OPENAI_API_KEY");
		if (this.apiKey == null || this.apiKey.isBlank()) {
			throw new IllegalStateException("OPENAI_API_KEY is not configured");
		}
		this.webClient = WebClient.builder()
				.baseUrl("https://api.openai.com/v1")
				.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + this.apiKey)
				.build();
	}

	public float[] createEmbedding(String text) {
		if (text == null || text.isBlank()) {
			throw new IllegalArgumentException("text must not be blank");
		}

		// minimal example body for embeddings endpoint
		Map<String, Object> body = Map.of("model", "text-embedding-3-small", "input", text);

		Map<String, Object> response = post("/embeddings", body);

		List<?> data = asList(response.get("data"), "data", "/embeddings");
		Map<?, ?> first = asMap(data.get(0), "data[0]", "/embeddings");
		List<?> embeddingList = asList(first.get("embedding"), "data[0].embedding", "/embeddings");

		float[] vector = new float[embeddingList.size()];
		for (int i = 0; i < embeddingList.size(); i++) {
			Object value = embeddingList.get(i);
			if (!(value instanceof Number number)) {
				throw new ExternalServiceException(
						"OpenAI returned a non-numeric embedding value at index " + i);
			}
			vector[i] = number.floatValue();
		}

		return vector;
	}

	// call chat completion to generate response
	public String createChatCompletions(String systemPrompt, List<Map<String, String>> messages, double temperature) {
		if (messages == null || messages.isEmpty()) {
			throw new IllegalArgumentException("messages must not be empty");
		}

		Map<String, Object> body = Map.of("model", "gpt-4o-mini", "messages", messages, "temperature", temperature);

		return extractMessageContent(post("/chat/completions", body), "/chat/completions");
	}

	public String analyzeFeedback(String feedbackText) {
		if (feedbackText == null || feedbackText.isBlank()) {
			throw new IllegalArgumentException("feedbackText must not be blank");
		}

		Map<String, Object> requestBody = Map.of("model", "gpt-4o-mini",
				"messages", List.of(
						Map.of("role", "system", "content", "You are a sentiment analyzer."
								+ " Classify the following feedback as positive,negative or neutral"),
						Map.of("role", "user", "content", feedbackText)),
				"temperature", 0);

		String sentiment = extractMessageContent(post("/chat/completions", requestBody), "/chat/completions")
				.trim()
				.toLowerCase();

		if (sentiment.contains("positive")) return "positive";
		if (sentiment.contains("negative")) return "negative";
		if (sentiment.contains("neutral")) return "neutral";

		throw new ExternalServiceException(
				"OpenAI returned an unrecognised sentiment classification: " + sentiment);
	}

	public String classifyMessage(String content) {
		if (content == null || content.isBlank()) {
			throw new IllegalArgumentException("content must not be blank");
		}

		Map<String, Object> requestBody = Map.of("model", "gpt-4o-mini",
				"messages", List.of(
						Map.of("role", "system", "content", "You are a message classifier."
								+ " classify the message text considering the intent and objective of the text"
								+ " and recommend necessary action to follow."),
						Map.of("role", "user", "content", content)),
				"temperature", 0);

		return extractMessageContent(post("/chat/completions", requestBody), "/chat/completions");
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> post(String uri, Map<String, Object> body) {
		Map<String, Object> response;
		try {
			response = webClient.post()
					.uri(uri)
					.contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(body))
					.retrieve()
					.bodyToMono(Map.class)
					.block();
		} catch (WebClientException ex) {
			throw new ExternalServiceException("OpenAI call to " + uri + " failed", ex);
		}

		if (response == null) {
			throw new ExternalServiceException("OpenAI returned an empty body for " + uri);
		}
		if (response.containsKey("error")) {
			throw new ExternalServiceException("OpenAI returned an error for " + uri + ": " + response.get("error"));
		}
		return response;
	}

	private String extractMessageContent(Map<String, Object> response, String uri) {
		List<?> choices = asList(response.get("choices"), "choices", uri);
		if (choices.isEmpty()) {
			throw new ExternalServiceException("OpenAI returned no choices for " + uri);
		}

		Map<?, ?> choice0 = asMap(choices.get(0), "choices[0]", uri);
		Map<?, ?> message = asMap(choice0.get("message"), "choices[0].message", uri);
		Object content = message.get("content");
		if (!(content instanceof String text) || text.isBlank()) {
			throw new ExternalServiceException("OpenAI returned no message content for " + uri);
		}

		log.debug("OpenAI call to {} returned {} characters", uri, text.length());
		return text;
	}

	private List<?> asList(Object value, String field, String uri) {
		if (!(value instanceof List<?> list)) {
			throw new ExternalServiceException(
					"OpenAI response for " + uri + " is missing list field '" + field + "'");
		}
		if (list.isEmpty()) {
			throw new ExternalServiceException("OpenAI response for " + uri + " has empty field '" + field + "'");
		}
		return list;
	}

	private Map<?, ?> asMap(Object value, String field, String uri) {
		if (!(value instanceof Map<?, ?> map)) {
			throw new ExternalServiceException(
					"OpenAI response for " + uri + " is missing object field '" + field + "'");
		}
		return map;
	}
}
