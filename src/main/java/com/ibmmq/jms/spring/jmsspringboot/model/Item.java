package com.ibmmq.jms.spring.jmsspringboot.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Item {
	
	private String itemsId;
	private String itemName;
	/**
	 * @return the itemsId
	 */
	public String getItemsId() {
		return itemsId;
	}
	/**
	 * @param itemsId the itemsId to set
	 */
	public void setItemsId(String itemsId) {
		this.itemsId = itemsId;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
}
