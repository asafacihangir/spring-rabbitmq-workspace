package com.phoenix.producer.event;

public class InvoiceCancelledMessage {

	private String cancelDate;
	
	private String invoiceNumber;
	
	private String reason;


	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "InvoiceCancelledMessage{" +
				"cancelDate='" + cancelDate + '\'' +
				", invoiceNumber='" + invoiceNumber + '\'' +
				", reason='" + reason + '\'' +
				'}';
	}
}
