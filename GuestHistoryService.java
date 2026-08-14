package service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import DTO.GuestHistoryRequest;
import com.cleverCRM.exception.ResourceNotFoundException;
import entity.Guest;
import entity.GuestHistory;
import repository.GuestHistoryRepository;
import repository.GuestRepository;

@Service
public class GuestHistoryService {
	
	private final GuestRepository guestRepository;
	private final GuestHistoryRepository historyRepo;
	
	public GuestHistoryService(GuestRepository guestRepository, GuestHistoryRepository historyRepo) {
		this.guestRepository = guestRepository;
		this.historyRepo = historyRepo;
	}
	
	public GuestHistory addHistory(GuestHistoryRequest request) {
		
		if (request == null || request.guestId() == null) {
			throw new IllegalArgumentException("guestId is required");
		}
		Guest guest = guestRepository.findById(request.guestId())
				.orElseThrow(() -> new ResourceNotFoundException("Guest", request.guestId()));
		GuestHistory h = new GuestHistory();
		h.setGuest(guest);
		h.setCheckInDate(LocalDate.now());
		h.setRoomUsed(request.roomUsed());
		h.setPurpose(request.purpose());
		h.setNotes(request.notes());
		
		return historyRepo.save(h);
		
	}
	
    
    
    public List<GuestHistory> getGuestHistory(UUID guestId) {
    	if (guestId == null) {
    		throw new IllegalArgumentException("guestId is required");
    	}
    	if (!guestRepository.existsById(guestId)) {
    		throw new ResourceNotFoundException("Guest", guestId);
    	}
    	return historyRepo.findByGuestId(guestId);
    }

}
