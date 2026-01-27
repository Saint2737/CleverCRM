import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/Response")
public class ResponseGeneratorController {

	private final ResponseGenerator responseGenerator;
	
	public ResponseGeneratorController(ResponseGenerator responseGenerator) {
		this.responseGenerator = responseGenerator;
	}
	
	@PostMapping("/generate")
	public ResponseEntity<String> generateResponse(@RequestBody Feedback feedback){
		return ResponseEntity.ok(responseGenerator.getResponseForFeedback(feedback));
	}
	
	@PostMapping("/postProcess")
	public ResponseEntity<String> cleanResponse(@RequestParam String s){
		return ResponseEntity.ok(responseGenerator.postProcess(s));
	}
	
	
}
