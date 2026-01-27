@RestController
@RequestMapping("api/Settings")
public class SettingsController {

	private final SettingsService settingsService;
	
	public SettingsController(SettingsService settingsService) {
		this.settingsService = settingsService;
	}
	
	@GetMapping("/key")
	public String getSettingsKey(String key) {
		return settingsService.getSettingsKey(key);
	}
	
	@GetMapping("/value")
	public String getSettingsValue(String value) {
		return settingsService.getSettingsValue(value);
	}
	
	@GetMapping("/description")
	public String getSettingsDescription(String description) {
		return settingsService.getSettingsDescription(description);
	}
	
}
