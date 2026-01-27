package controller;


@Controller("/api")
public class FeedbackController {
	
	@Autowired
	public LlmService llmService;
	
	@GetMapping("/feedback")
	public Feedback getAllFeedback(){
		 return llmService.getFeedback;
	}
	
	
	
	
	
	

}
