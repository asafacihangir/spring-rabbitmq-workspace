package com.phoenix.consumer.event;

public class InvoiceCreatedMessage {

	private double amount;

	private String createdDate;

	private String currency;

	private String invoiceNumber;

	public InvoiceCreatedMessage() {
	}

	public InvoiceCreatedMessage(double amount, String createdDate, String currency,
			String invoiceNumber) {
		super();
		this.amount = amount;
		this.createdDate = createdDate;
		this.currency = currency;
		this.invoiceNumber = invoiceNumber;
	}

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
