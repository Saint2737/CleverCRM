package repository;

import java.util.List;
import java.util.UUID;

import entity.GuestHistory;

public interface GuestHistoryRepository extends JpaRepository<GuestHistory, UUID> {
	
	List<GuestHistory> findByGuestId(UUID guestId);

}
