
public record PaginatedResponse<T>(
		List<T> data,
		int page,
		int size,
		long totalElements,
		int totalPages,
		Boolean hasNext,
		Boolean hasPrevious
		) {

}
