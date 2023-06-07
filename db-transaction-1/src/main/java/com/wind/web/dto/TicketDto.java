package com.wind.web.dto;

public class TicketDto {
	
	private String consumerId;
	private String amount;
	
	public String getConsumerId() {
		return consumerId;
	}
	public String getAmount() {
		return amount;
	}
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
