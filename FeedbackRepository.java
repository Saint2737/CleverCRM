
public interface FeedbackRepository extends JpaRepository<Feedback,integer >{
	List<Feedback> findById(int id);
	Feedback save(Feedback feedback);
		
}
