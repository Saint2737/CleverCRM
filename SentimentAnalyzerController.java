@RestController
@RequestMapping("/api/sentiment")
public class SentimentAnalyzerController {

	private final SentimentAnalyzer sentimentAnalyzer;
	
	public SentimentAnalyzerController(SentimentAnalyzer sentimentAnalyzer) {
		this.sentimentAnalyzer = sentimentAnalyzer;
	}
	
	@PostMapping("/analyze")
	public String getSentiment(@RequestParam String text) {
		return sentimentAnalyzer.getSentiment(text);
	}
	
}

