package repository;

import java.time.LocalDate;

import entity.Guest;

public interface ReservationRepository extends JpaRepositories<Reservation, UUID>  {
	
	List<Reservation> findByReservationStatus(Reservation.ReservationStatus);
	List<Reservation> findByCheckInBetween(LocalDate checkinDate, LocalDate checkOutDate);
	List<Reservation> findByGuestName( Guest guest);
	List<Reservation> findByRoom(String roomNumber);
	

	


}
