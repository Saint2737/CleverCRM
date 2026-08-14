package repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import entity.Guest;

public interface GuestRepository extends JpaRepository <Guest, UUID> {
	
	List<Guest> findByNameContainingIgnoreCase(String name);
	Optional<Guest> findByEmail(String email);

}
