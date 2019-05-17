package com.community.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Boundary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Date tradeDate;

    @Column
    private Point max;

    @Column
    private Point min;

    public Boundary(Long id, Date tradeDate, Point max, Point min) {
        this.id = id;
        this.tradeDate = tradeDate;
        this.max = max;
        this.min = min;
    }
}
