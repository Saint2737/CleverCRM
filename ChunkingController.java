
@RestController
@RequestMapping("/api/Chunking")
public class ChunkingController {

	private final ChunkimgService chunkingService;
	
	public ChunkingController(ChunkingService chunkingService) {
		this.chunkingService = chunkingService;
	}
	
	@PostMapping("/chunk")
	public List<String> chunkText(@RequestBody String text, int maxChars, int overlap){
		return chunkingService.chunkText(text,maxChars,overlap);
	}
	
	@PostMapping("/ingest")
	public int ingestDocument(@RequestBody String sourceType, String sourceId, String content) {
		return chunkingService.ingestDocument(sourceType,sourceId,content);
	}
	
}
