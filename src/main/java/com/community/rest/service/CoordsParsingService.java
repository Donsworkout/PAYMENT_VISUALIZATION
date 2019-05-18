package com.community.rest.service;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.community.rest.domain.Merchant;
import com.community.rest.repository.MerchantRepository;

@Service
public class CoordsParsingService {
	@Autowired
	MerchantRepository merchantRepository;
	
	private static final Logger LOGGER = LogManager.getLogger(CoordsParsingService.class);
	
	public void setMerchantsCoords (List<Merchant> coordsNotSetted) {
		for (Merchant merchant : coordsNotSetted) {
			Point2D coord = getCoordsByAddress(merchant.address);
			if(coord != null) {
				merchant.xPos = coord.getX();
				merchant.yPos = coord.getY();				
			}else {
				merchant.xPos = null;
				merchant.yPos = null;			
			}
			merchantRepository.save(merchant);
		}
	}
	
	public Point2D getCoordsByAddress(String address) {
		Point2D.Double coords = new Point2D.Double();
		String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + address;
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.add("X-NCP-APIGW-API-KEY-ID", "f0mv9gc9op");
	    httpHeaders.add("X-NCP-APIGW-API-KEY", "3sDoTfxMpWljOVEzY53O4qa85hriJgENBC98aSiE");
	    HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);

	    ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Map.class);
	    
        if(!response.getStatusCode().is2xxSuccessful() || !response.getBody().get("status").equals("OK")) {
	    	LOGGER.warn("Geocoder BAD RESPONSE , http status : {}, API status : {}", response.getStatusCodeValue(), response.getBody().get("status"));
		    return null;
	    }
	    
	    List addressList = (List) response.getBody().get("addresses");
	    Map<String, String> addresses = (Map<String, String>) addressList.get(0);
	    String latitude = addresses.entrySet().stream().filter(entry -> "x".equalsIgnoreCase(entry.getKey())).map(Map.Entry::getValue).findFirst().orElse(null);
	    String longitude = addresses.entrySet().stream().filter(entry -> "y".equalsIgnoreCase(entry.getKey())).map(Map.Entry::getValue).findFirst().orElse(null);
	    coords.setLocation(Double.parseDouble(latitude), Double.parseDouble(longitude));
	    return coords;
	}
}
