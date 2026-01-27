package controller;

import service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
	
	private final ReservationService service;
	
	public ReservationController(ReservationService service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	public ReservationResponse create(@Requestbody ReservationRequest request) {
		
		return service.createReservation(request);
	}
	
	@GetMapping("/{id}")
	public List<ReservationResponse> getById(@PathVariable UUID id){
		
		return service.getById(id);
	}
	
	@PutMapping("/{id}/update")
	public ReservationResponse updateStatus(@PathVariable UUID id, @RequestParam String status) {
		
		return service.updateStatus(id, status);
		
	}
	
	@DeleteMapping("/{id}                                                                                                                                                     ")
	public void delete(@PathVariable UUID id) {
		
		service.delete(id);
	}
	
	
	
	
	

}
