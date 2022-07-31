package com.gientech.practice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coffee implements Serializable {
	
    private static final long serialVersionUID = -6263074749591348392L;

	private Long id;
    
    private String name;
    
    private Money price;
    
    private Date createTime;
    
    private Date updateTime;
}
