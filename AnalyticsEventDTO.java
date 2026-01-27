
public record AnalyticsEventDTO(
		private String eventType,
		private String module,
		private String referenceId,
		private String referenceType,
		Instant timeStamp) {}
