package repository;

import java.util.UUID;

import entity.Guest;

public interface GuestRepository extends JpaRepository <Guest, UUID> {
	
	List<Guest> findByNameContainingIgnoreCase(String name);
	Guest findByEmail(String email);
	void save(Guest g);
	Object findAll();
	void deleteById(UUID id);
	Guest findById(UUID id);

}
