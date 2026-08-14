package service;

import java.time.LocalDate;

import DTO.GuestHistoryRequest;
import entity.GuestHistory;
import repository.GuestHistoryRepository;
import util.EntityLookup;

@Service
public class GuestHistoryService {
	
	private final GuestRepository guestRepository;
	private final GuestHistoryRepository historyRepo;
	
	public GuestHistoryService(GuestRepository guestRepository, GuestHistoryRepository historyRepo) {
		this.guestRepository = guestRepository;
		this.historyRepo = historyRepo;
	}
	
	public GuestHistory addHistory(GuestHistoryRequest request) {
		
		Guest guest = EntityLookup.orThrow(guestRepository.findById(request.guestId()), "Guest", request.guestId());
		GuestHistory h = new GuestHistory();
		h.setGuest(guest);
		h.setCheckInDate(LocalDate.now());
		h.setRoomUsed(request.roomUsed());
		h.setPurpose(request.purpose());
		h.setNotes(request.notes());
		
		return historyRepo.save(h);
		
	}
	
    
    
    public GuestHistory getGuestHistory(UUID guestId) {
    	return historyRepo.findAllById(guestId);
    }

}
