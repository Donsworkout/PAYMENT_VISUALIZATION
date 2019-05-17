package com.community.rest.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Trade implements Serializable {

    @Id
    @Column
    public Long id;

    @Column
    public Date tradeDate;

    @Column
    public String tradeType;

    @Column
    public int amount;

    @Column
    public int fee;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = Merchant.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "merchantId")
    public Merchant merchantId;

    @Column
    public String serviceType;


    @Column
    public String tradeAccess;


	@Override
	public String toString() {
		return "Trade [id=" + id + ", tradeDate=" + tradeDate + ", tradeType=" + tradeType + ", amount=" + amount
				+ ", fee=" + fee + ", merchantId=" + merchantId + ", serviceType=" + serviceType + ", tradeAccess="
				+ tradeAccess + "]";
	}


	public Trade(Long id, Date tradeDate, String tradeType, int amount, int fee, Merchant merchantId,
			String serviceType, String tradeAccess) {
		super();
		this.id = id;
		this.tradeDate = tradeDate;
		this.tradeType = tradeType;
		this.amount = amount;
		this.fee = fee;
		this.merchantId = merchantId;
		this.serviceType = serviceType;
		this.tradeAccess = tradeAccess;
	}


	public Trade() {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getTradeDate() {
		return tradeDate;
	}


	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}


	public String getTradeType() {
		return tradeType;
	}


	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getFee() {
		return fee;
	}


	public void setFee(int fee) {
		this.fee = fee;
	}


	public Merchant getMerchantId() {
		return merchantId;
	}


	public void setMerchantId(Merchant merchantId) {
		this.merchantId = merchantId;
	}


	public String getServiceType() {
		return serviceType;
	}


	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}


	public String getTradeAccess() {
		return tradeAccess;
	}


	public void setTradeAccess(String tradeAccess) {
		this.tradeAccess = tradeAccess;
	}


}