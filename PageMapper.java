import org.springframework.data.domain.Page;

public final class PageMapper {

	private PageMapper() {
	}

	public static <T> PaginatedResponse<T> toPaginatedResponse(Page<T> page) {
		return new PaginatedResponse<>(
				page.getContent(),
				page.getNumber(),
				page.getSize(),
				page.getTotalElements(),
				page.getTotalPages(),
				page.hasNext(),
				page.hasPrevious());
	}
}
