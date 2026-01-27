@RestController
@RequestMapping("/api/message")
public class MessageController {

	private final MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@PostMapping("/save")
	public Message saveIncomingMessage(@RequestBody Message message) {
		return messageService.saveIncomingMessage(message);
	}
	
	@GetMapping("/all")
}
