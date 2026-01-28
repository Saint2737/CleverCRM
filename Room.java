package entity;


@Entity
@Table(name = "room")
public class Room {

	@Id
	@GeneratedValue
	private UUID id;
	
	private String roomNumber;
	private String roomType;
	private BigDecimal basePrice;
	private String status; // available, occupied, cleaning
	private int floor;
	
	@Column(ColumnDefinition = "TEXT")
	private String amenities;
}
