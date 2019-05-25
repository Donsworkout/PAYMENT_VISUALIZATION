package com.community.rest.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="merchants")
public class Merchant {
	
    @Id
    @GeneratedValue
    public Long id;

    public String merchantName;

    public Date regDate;

    public String serviceType;

    public String statusType;

    public String address;

    public String addressDetail;

    public Double xPos;

    public Double yPos;

	public boolean isEmptyCoords() {
    	try {
    		return (this.xPos.equals(null) || this.yPos.equals(null));
    	}catch(NullPointerException e){  
    		return true;
    	}
    } 
    
}
