package com.web.dazu.model;

public class Amount {
	private Integer total, tax_free, vat, point, discount;
	
	public Amount() {
		// TODO Auto-generated constructor stub
	}

	public Amount(Integer total, Integer tax_free, Integer vat, Integer point, Integer discount) {
		super();
		this.total = total;
		this.tax_free = tax_free;
		this.vat = vat;
		this.point = point;
		this.discount = discount;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTax_free() {
		return tax_free;
	}

	public void setTax_free(Integer tax_free) {
		this.tax_free = tax_free;
	}

	public Integer getVat() {
		return vat;
	}

	public void setVat(Integer vat) {
		this.vat = vat;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
    
}
