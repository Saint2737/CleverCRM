package repository;

public interface GuestPreferenceRepository extends JpaRepositories <GuestPreferences, UUID> {
	
	GuestPreferences findById(UUId id);
	GuestPreferences save(GuestPreferences g);

}
