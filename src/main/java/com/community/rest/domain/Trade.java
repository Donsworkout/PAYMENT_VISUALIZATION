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
@Document(collection="trades")
public class Trade{

    @Id
    @GeneratedValue
    public Long id;

    public Date tradeDate;

    public String tradeType;

    public int amount;

    public int fee;

    public Merchant merchantId;

    public String serviceType;

    public String tradeAccess;

}