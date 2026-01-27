@RestController
@RequestMapping("api/Embedding")
public class EmbeddingController {

	private final EmbeddingService embeddingService;
	
	public EmbeddingController(EmbeddingService embeddingService){
		this.embeddingService = embeddingService;
	}
	
	@PostMapping("/create")
	public String createEmbedding(@RequestParam String text){
		return embeddingService.createAndStoreEmbedding(text);
	}
	
	@PostMapping("/checkSimilar")
	public List<VectorSearchResult> getSimilarVector(@RequestParam String text, int topK){
		return embeddingService.querySimilar(text,topK);
	}
	
	
}
