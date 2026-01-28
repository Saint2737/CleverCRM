
public interface TaskRepository extends JpaRepository<Task, Integer>{
	List<Task> findByStatus(Task.Status status);
	List<Task> findByPriority(String priority);
	List<Task> findByServiceType(String serviceType);
	List<Task> findByAssignedTo(String assignedTo);
	List<Task> findByRoomNumber(String roomNumber);
	List<Task> findByGuestId(UUID guestId);

}
