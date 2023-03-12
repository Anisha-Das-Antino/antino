package com.antino.dto;

public class DashBoardGraphSaleDTO {

	Integer date;
	
	Double sales;

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "DashBoardGraphSaleDTO [date=" + date + ", sales=" + sales + "]";
	}
	
	
	
}
