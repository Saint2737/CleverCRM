package repository;

public interface GuestHistoryRepository extends JpaRepositories<GuestHistory, UUID> {
	
	GuestHistory findById(UUID guestId);

}
