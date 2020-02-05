package com.larry.bean;

import java.io.Serializable;

import com.larry.entity.shop.ShopProduct;

import lombok.Data;

@Data
public class BuyerItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ShopProduct product;
	private Boolean isHave;
	private Integer ammount = 1;

	@Override
	public int hashCode() {
		int prime = 17;
		int result = 1;
		result = prime * result + ((this.product == null ? 0 : this.product.hashCode()));
		return result;
	}
	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		else if(this.product == o)
			return true;
		else if(this.getClass() != o.getClass())
			return false;
		else if(this.product == null && ((BuyerItem)o).getProduct() != null)
			return false;
		else if(this.product != null && ((BuyerItem)o).getProduct() == null)
			return false;
		else if(this.product.getId() ==((BuyerItem)o).getProduct().getId() )
			return true;
		else
			return false;
	
	}

}
