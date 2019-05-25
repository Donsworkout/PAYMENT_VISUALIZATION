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
@Document(collection="daily_statics")
public class DailyStatic{
	
    @Id
    @GeneratedValue
    private Long id;

    private Date tradeDate;

    private Merchant merchantId;

    private Integer amount;

    private Long frequency;
}
