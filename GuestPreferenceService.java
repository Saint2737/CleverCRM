package service;

import DTO.GuestPreferencesRequest;
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
    	Guest guest = guestRepo.findById(request.guestID()).orElseThrow(() -> new RuntimeException("Guest not found"));
    	GuestPreferences g = guestPrefRepo.findById(request.guestID());
    	
    	if(g==null) g = new GuestPreferences();
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
    	
    	return guestPrefRepo.findById(guestId);
    }
    
    
	
	

}
