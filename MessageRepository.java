package repository;

import java.util.List;
import java.util.UUID;

import entity.Guest;
import entity.Message;

public interface MessageRepository extends JpaRepository<Message, UUID>{
	

	List<Message> findAllByGuestIdOrderByTimestampDesc(UUID guestId);

	List<Message> findAllByIsReadFalse();
	
	List<Message> findAllByGuestIdAndIsReadFalseOrderByTimestampDesc(UUID guestId);

	List<Message> findAllByChannel(String channel);

	List<Message> findAllByCategoryIsNullOrderByTimestampDesc(String category);
	
	@Query("""
			SELECT m
			FROM Message m
			WHERE LOWER(m.content) LIKE LOWER(CONCAT('%',:keyword, '%'))
			""")
	List<Message> searchMessages(@Param("keyword") String keyword);
	

}
