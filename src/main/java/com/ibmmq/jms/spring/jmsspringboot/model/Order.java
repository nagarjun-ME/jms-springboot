package com.ibmmq.jms.spring.jmsspringboot.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Order {

	private String orgerId;
	private String orderInfo;
	
}
