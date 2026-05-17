package com.cleverCRM.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cleverCRM.service.ChunkingService;

@RestController
@RequestMapping("/api/chunking")
public class ChunkingController {

	private final ChunkingService chunkingService;
	
	public ChunkingController(ChunkingService chunkingService) {
		this.chunkingService = chunkingService;
	}
	
	@PostMapping("/chunk")
	public List<String> chunkText(@RequestBody String text, @RequestParam int maxChars, @RequestParam int overlap){
		return chunkingService.chunkText(text,maxChars,overlap);
	}
	
	@PostMapping("/ingest")
	public int ingestDocument(@RequestParam String sourceType, @RequestParam String sourceId, @RequestBody String content) {
		return chunkingService.ingestDocument(sourceType,sourceId,content);
	}
	
}
