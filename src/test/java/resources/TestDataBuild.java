package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.Place;

public class TestDataBuild {

	public Place addPlacePayload(String name, String language, String address) {
		
		Place p = new Place();
		p.setAccuracy(50);
		p.setName(name);
		p.setPhoneNumber("805-111-1111");
		p.setAddress(address);
		p.setLanguage(language);
		p.setWebsite("www.nonktube.com");
		List<String> types = new ArrayList<String>();
		types.add("The Trap");
		types.add("Goodies");
		p.setTypes(types);
		
		Location l = new Location();
		l.setLat(49.44);
		l.setLng(-55.11);
		p.setLocation(l);
		
		return p;
	}
	
	public String deletePlacePayload(String placeId) {
		
		return "{\r\n \"place_id\":\"" + placeId + "\"\r\n}";
	}
}

