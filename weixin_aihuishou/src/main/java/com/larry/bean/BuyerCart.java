package com.larry.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class BuyerCart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<BuyerItem> items = new ArrayList<BuyerItem>();
	public void addIterm(BuyerItem item) {
		if (items.contains(item)) {
			for (BuyerItem i : items) {
				if (i.equals(item)) {
					i.setAmmount(i.getAmmount()+item.getAmmount());
				}
			}
		} else {
			items.add(item);
		}
	}
	public void removeItem(BuyerItem item) {
		if (items.contains(item)) {
			items.remove(item);
		}
	}


	@JsonIgnore
	public int getTotalPrice() {
		int result = 0;
		for (BuyerItem i : items) {
			result = result + i.getAmmount() * new Integer(i.getProduct().getPoint_price()).intValue();
		}
		return result;
	}

}
