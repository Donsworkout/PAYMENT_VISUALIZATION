package com.community.rest.domain;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Trade implements Serializable {

    @Id
    @Column
    private Long id;

    @Column
    private String tradeDate;

    @Column
    private String tradeType;

    @Column
    private String amount;

    @Column
    private String fee;

    @ManyToOne(targetEntity = Merchant.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "merchantId")
    private Merchant merchantId;

    @Column
    private String serviceType;


    @Column
    private String tradeAccess;


    @Builder
    public Trade(Long id, String tradeDate, String tradeType,
                 String amount, String fee, Merchant merchantId, String serviceType, String tradeAccess) {
        this.id = id;
        this.tradeDate = tradeDate;
        this.tradeType = tradeType;
        this.amount = amount;
        this.fee = fee;
        this.merchantId = merchantId;
        this.serviceType = serviceType;
        this.tradeAccess = tradeAccess;
    }
}