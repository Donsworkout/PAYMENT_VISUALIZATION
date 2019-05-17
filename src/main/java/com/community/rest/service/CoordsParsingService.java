package com.community.rest.service;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	
	public void setMerchantsCoords (List<Merchant> coordsNotSetted) {
		for (Merchant merchant : coordsNotSetted) {
			Point2D coord = getCoordsByAddress(merchant.address);
			merchant.xPos = coord.getX();
			merchant.yPos = coord.getY();
			merchantRepository.save(merchant);
		}
	}
	
	public Point2D getCoordsByAddress(String address) {
		String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + address;
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.add("X-NCP-APIGW-API-KEY-ID", "f0mv9gc9op");
	    httpHeaders.add("X-NCP-APIGW-API-KEY", "3sDoTfxMpWljOVEzY53O4qa85hriJgENBC98aSiE");
	    HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);

	    ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Map.class);
	    List addressList = (List) response.getBody().get("addresses");
	    Map<String, String> addresses = (Map<String, String>) addressList.get(0);
	    
	    String latitude = addresses.entrySet().stream().filter(entry -> "x".equalsIgnoreCase(entry.getKey())).map(Map.Entry::getValue).findFirst().orElse(null);
	    String longitude = addresses.entrySet().stream().filter(entry -> "y".equalsIgnoreCase(entry.getKey())).map(Map.Entry::getValue).findFirst().orElse(null);
	    Point2D.Double coords = new Point2D.Double();
	    coords.setLocation(Double.parseDouble(latitude), Double.parseDouble(longitude));
	    return coords;
	}
}
