package com.community.rest.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Merchant implements Serializable {
    @Id
    @Column
    private Long id;

    @Column
    private String merchantName;

    @Column
    private Date regDate;
    
	@Column
    private String serviceType;

    @Column
    private String statusType;

    @Column
    private String address;

    @Column
    private String addressDetail;

    @Column
    private Double xPos;

    @Column
    private Double yPos;

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Date getRegDate() {
		return regDate;
	}

	public Double getxPos() {
		return xPos;
	}

	public void setxPos(Double xPos) {
		this.xPos = xPos;
	}

	public Double getyPos() {
		return yPos;
	}

	public void setyPos(Double yPos) {
		this.yPos = yPos;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Long getId() {
        return id;
    }


	public Merchant(Long id, String merchantName, Date regDate, String serviceType, String statusType, String address,
			String addressDetail, double xPos, double yPos) {
		super();
		this.id = id;
		this.merchantName = merchantName;
		this.regDate = regDate;
		this.serviceType = serviceType;
		this.statusType = statusType;
		this.address = address;
		this.addressDetail = addressDetail;
		this.xPos = xPos;
		this.yPos = yPos;
	}

    public boolean isEmptyCoords() {
    	try {
    		return (this.xPos.equals(null) || this.yPos.equals(null));
    	}catch(NullPointerException e){  
    		return true;
    	}
    }
    
	@Override
	public String toString() {
		return "Merchant [id=" + id + ", merchantName=" + merchantName + ", regDate=" + regDate + ", serviceType="
				+ serviceType + ", statusType=" + statusType + ", address=" + address + ", addressDetail="
				+ addressDetail + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((addressDetail == null) ? 0 : addressDetail.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((merchantName == null) ? 0 : merchantName.hashCode());
		result = prime * result + ((regDate == null) ? 0 : regDate.hashCode());
		result = prime * result + ((serviceType == null) ? 0 : serviceType.hashCode());
		result = prime * result + ((statusType == null) ? 0 : statusType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(xPos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yPos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

    
    
}
