package controller;

import java.util.List;
import java.util.UUID;

import DTO.GuestRequest;
import DTO.GuestResponse;
import service.GuestService;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
	private final GuestService guestService;
	
	public GuestController(GuestService guestService){
		this.guestService = guestService;
		
	}

	@PostMapping("/add")
	public GuestResponse create(@RequestBody GuestRequest request){
		
		return guestService.createGuest(request);
	}
	
	@GetMapping("/allGuests")
	public List<GuestResponse> getAllGuests(){
		
		return guestService.getAllGuests();
	}
	
	@GetMapping("/{id}")
	public GuestResponse getGuest(@PathVariable UUID id) {
		
		return guestService.getGuest(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteGuest(@PathVariable UUID id) {
		 
		guestService.deleteGuest(id);
	}
}
