package service;

import java.util.UUID;

import DTO.GuestPreferencesRequest;
import com.cleverCRM.exception.ResourceNotFoundException;
import entity.Guest;
import entity.GuestPreferences;
import repository.GuestPreferenceRepository;
import repository.GuestRepository;

public class GuestPreferenceService {
	
	private final GuestRepository guestRepo;
	private final GuestPreferenceRepository guestPrefRepo;
	
    public GuestPreferenceService(GuestPreferenceRepository guestPrefRepo, GuestRepository guestRepo) {
    	
    	this.guestPrefRepo = guestPrefRepo;
    	this.guestRepo = guestRepo;
    }
    
    public GuestPreferences savePreferences(GuestPreferencesRequest request) {
    	if (request == null || request.guestID() == null) {
    		throw new IllegalArgumentException("guestID is required");
    	}
    	Guest guest = guestRepo.findById(request.guestID())
    			.orElseThrow(() -> new ResourceNotFoundException("Guest", request.guestID()));
    	GuestPreferences g = guestPrefRepo.findById(request.guestID())
    			.orElseGet(GuestPreferences::new);
    	
    	g.setBedTypePreferences(request.bedType());
    	g.setGuest(guest);
    	g.setAllergies(request.allergies());  
    	g.setPrefersSeaView(null);
    	g.setLikesQuietRooms(null);
    	g.setMealPreferences(request.mealPreferences());
    	g.setRoomAllocation(request.roomAllocation);
    	
    	return guestPrefRepo.save(g);
    	
    	
    }
    
    public GuestPreferences getGuestPreference(UUID guestId) {
    	if (guestId == null) {
    		throw new IllegalArgumentException("guestId is required");
    	}
    	return guestPrefRepo.findById(guestId)
    			.orElseThrow(() -> new ResourceNotFoundException("GuestPreferences", guestId));
    }
    
    
	
	

}
