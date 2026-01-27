import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/MessageClassifier")
public class MessageClassifierController {

	private final MessageClassifierService messageClassifierService;
	
	public MessageClassifierController(MessageClassifierService messageClassifierService) {
		this.messageClassifierService = messageClassifierService;
	}
	
	@PostMapping("/classify")
	public ResponseEntity<MessageClassificationResult> classifyMessage(@RequestBody Message message){
		return ResponseEntity.ok(messageClassifierService.classify(message));
	}
	
	@PostMapping
	public ResponseEntity<void> SuggestedAction(@RequestBody MessageClassificationResult.SuggestedAction action, @RequestBody Message message){
		messageClassifierService.enactSuggestedAction(action, message);
	}
}
