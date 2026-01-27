import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/GuestHistory")
public class GuestHistoryController {
	
	private final GuestHistoryService guestHistoryService;
	
	public GuestHistoryController(GuestHistoryService guestHistoryService) {
		this.guestHistoryService = guestHistoryService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<GuestHistory> createGuestHistory(@RequestBody GuestHistoryRequest request){
		GuestHistory guestHistory = guestHistoryService.addHistory(request);
		return new ResponseEntity<>(guestHistory, HttpStatus.CREATED);
	}
	
	@PostMapping("/history")
	public ResponseEntity<GuestHistory> getGuestHistory(@RequestBody UUID guestId){
		return guestHistoryService.getGuestHistory(guestId);
	}
	
	
	

}
