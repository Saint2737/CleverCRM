

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
	
	List<Notification> findByChannel(String channel);
	List<Notification> findByCategory(String category);
	List<Notification> findByStatus(Notification.Status status);
	List<Notification> findByStaffIdAndStatus(int staffId, Notification.Status status);
	List<Notification> findByStaffId(int staffId);
	

}
