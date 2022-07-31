package com.gientech.practice.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Coffee implements Serializable {
	
    private static final long serialVersionUID = -8957798433920224124L;
    
	private Long id;
	
    private String name;
    
    private BigDecimal price;
    
    private Date createTime;
    
    private Date updateTime;
}
