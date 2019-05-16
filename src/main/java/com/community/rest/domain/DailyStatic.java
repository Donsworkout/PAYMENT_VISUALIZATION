package com.community.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table
public class DailyStatic implements Serializable {
    @Id
    @Column
    private Long id;

    @Column
    private Date tradeDate;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = Merchant.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "merchantId")
    private Merchant merchantId;

    @Column
    private Integer amount;

    @Column
    private Integer frequency;

    public DailyStatic(Long id, Date tradeDate, Merchant merchantId, Integer amount, Integer frequency) {
        this.id = id;
        this.tradeDate = tradeDate;
        this.merchantId = merchantId;
        this.amount = amount;
        this.frequency = frequency;
    }
}
