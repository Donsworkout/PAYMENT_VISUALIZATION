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
public class Merchant implements Serializable {
    @Id
    @Column
    private Long id;

    @Column
    private String merchantName;

    @Column
    private String regDate;

    @Column
    private String serviceType;

    @Column
    private String statusType;

    @Column
    private String address;

    @Column
    private String addressDetail;

    public Long getId() {
        return id;
    }

    @Builder
    public Merchant(Long id, String merchantName, String regDate,
                    String serviceType, String statusType, String address, String addressDetail) {
        this.id = id;
        this.merchantName = merchantName;
        this.regDate = regDate;
        this.serviceType = serviceType;
        this.statusType = statusType;
        this.address = address;
        this.addressDetail = addressDetail;
    }
}
