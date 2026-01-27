import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/llm")
public class LlmController {

	private final LlmService llmService;
	
	public LlmController(LlmService llmService) {
		this.llmService = llmService;
	}
	
	@PostMapping("/saveFeedback")
	public ResponseEntity<Feedback> saveFeedback(@RequestParam int customerId, String feedbackText, LocalDateTime createdAt, String sentiment, Double[] embedding, String channel, int productId){
		return ResponseEntity.ok(llmService.captureFeedback(customerId, feedbackText, createdAt, sentiment, embedding, channel, productId));
	}
	
	@PostMapping("/feedback")
	public 
	
}
