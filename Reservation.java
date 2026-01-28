package entity;


@Entity
@Table(name = "reservations")
public class Reservation {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name = "guest_id")
	private Guest guest;
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
	
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	@Enumerated(EnumType.STRING)
	private ReservationStatus reservationStatus;
	
	private String bookingSource;
	private int numberOfGuests;
	private BigDecimal totalPrice;
	
	@Column(columnDefinition = "TEXT")
	private String specialRequests;
	
	private LocalDateTime createdAt;
	

}
