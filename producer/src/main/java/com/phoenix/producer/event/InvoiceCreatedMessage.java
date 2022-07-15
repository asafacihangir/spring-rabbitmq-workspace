package com.phoenix.producer.event;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InvoiceCreatedMessage {

	private double amount;

	private String createdDate;

	private String currency;

	private String invoiceNumber;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Override
	public String toString() {
		return "InvoiceCreatedMessage{" +
				"amount=" + amount +
				", createdDate='" + createdDate + '\'' +
				", currency='" + currency + '\'' +
				", invoiceNumber='" + invoiceNumber + '\'' +
				'}';
	}
}
