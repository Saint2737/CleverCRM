@RestController
@RequestMapping("/api/hotelAssistant")
public class HotelAssistantController {

	private final HotelAssistant hotelAssistant;
	
	public HotelAssistantController(HotelAssistant hotelAssistant) {
		this.hotelAssistant = hotelAssistant;
	}
	
	@PostMapping("/respond")
	public String getCustomerFeedback(@RequestBody Feedback feedback) {
		return hotelAssistant.handleCustomerFeedback(feedback);
	}
}
