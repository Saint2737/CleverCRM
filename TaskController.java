import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/Tasks")
public class TaskController {
	
	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@GetMapping("/AllTasks")
	public List<Task> getAllTasks(){
		return taskService.getAllTasks();
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Task> getTask(@PathVariable int id) {
		Task task = taskService.getTaskById(id);
		return ResponseEntity.ok(task);
	}
	
	@PostMapping("/{id}/update")
	public ResponseEntity<Task> updateTask(@PathVariable int id,  @RequestBody Task updateData){
		return taskService.updateTask(id,updateData);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Task> saveTask(@RequestBody Task task){
		return taskService.createTask(task);
	}
	
	@PostMapping("/{id}/status")
	public ResponseEntity<Task> updateStatus(@PathVariable int id, @RequestBody Task.Status status){
		return taskService.changeStatus(id, status);
	} 
	
	@PostMapping("/status")
	public List<Task> getTaskStatus(@RequestBody Task.Status status){
		return taskService.getTaskStatus(status);
	}
	
	@PostMapping("/serviceType")
	public List<Task> getTasksByServiceType(@RequestParam String serviceType){
		return taskService.getTasksByServiceType(serviceType);
	}
	
	@PostMapping("/priority")
	public List<Task> getTasksByPriority(@RequestParam String priority){
		return taskService.getTasksByPriority(status);
	}
	
	@PostMapping("/guestId")
	public List<Task> getByGuestId(UUID guestId){
		return taskService.getTasksByGuestId(guestId);
	}
	
	@PostMapping("/assignedTo")
	public ResponseEntity<Task> getStaffAssignedTask(){
		
	}
		
	
	

}
