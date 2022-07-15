package com.phoenix.consumer.event;

public class InvoicePaidMessage {

	private String invoiceNumber;

	private String paidDate;

	private String paymentNumber;

	public InvoicePaidMessage() {
	}

	public InvoicePaidMessage(String invoiceNumber, String paidDate, String paymentNumber) {
		super();
		this.invoiceNumber = invoiceNumber;
		this.paidDate = paidDate;
		this.paymentNumber = paymentNumber;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}

	public String getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	@Override
	public String toString() {
		return "InvoicePaidMessage{" +
				"invoiceNumber='" + invoiceNumber + '\'' +
				", paidDate='" + paidDate + '\'' +
				", paymentNumber='" + paymentNumber + '\'' +
				'}';
	}
}
