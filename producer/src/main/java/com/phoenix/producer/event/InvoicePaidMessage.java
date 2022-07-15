package com.phoenix.producer.event;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InvoicePaidMessage {

	private String invoiceNumber;

	private String paidDate;

	private String paymentNumber;


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
