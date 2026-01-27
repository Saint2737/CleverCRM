import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/GuestPreferences")
public class GuestPreferencesController {

	private final GuestPreferencesService guestPreferencesService;
	
	public GuestPreferencesController(GuestPreferencesService guestPreferencesService) {
		this.guestPreferencesService = guestPreferencesService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<GuestPreferences> createGuestPreference(@RequestBody GuestPreferencesRequest request){
		return ResponseEntity.ok(guestPreferencesService.savePreferences(request));
	}
	
	@GetMapping("/preference")
	public ResponseEntity<GuestPreferences> getGuestPreference(@RequestBody UUID guestId){
		return ResponseEntity.ok(guestPreferenceService.getGuestPreference(guestId));
	}
}
