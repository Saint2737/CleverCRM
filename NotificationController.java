
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

	private NotificationService notificationService;
	
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
	@PostMapping("/{id}/status")
	public ResponseEntity<Notification> changeNotificationStatus(@PathVariable int id, @RequestBody Status status){
		return notificationService.changeStatus(id,status);
	}
	
	@GetMapping("/category")
	public List<Notification> getNotificationsByCategory(String category){
		return notificationService.getNotificationByCategory(category);
	}
	
	@GetMapping("/channel")
	public List<Notification> getNotificationByChannel(String channel){
		return notificationService.getNotificationByChannel(channel);
	}
	
	@GetMapping("/{staffId}")
	public List<Notification> getNotificationByStaffId(@PathVariable int staffId){
		return notificationService.getNotificationByStaffId(staffId);
	}
	
}
