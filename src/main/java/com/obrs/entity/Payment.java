package com.obrs.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name="Payment")
public class Payment {
	@Id
	@NotNull(message = "payment id cannot be null")
	private int paymentId;
	@CreationTimestamp
	LocalDate paymentDate;
	@NotNull(message = "payment status cannot be blank")
	private String paymentStatus;
	@NotNull(message = "amount ")
	private int amount;
	@NotNull(message = "customerId cannot be null")
	private int customerId;
	@NotNull(message="bookingId cannot be null")
	private int bookingId;

	public Payment() {
		super();
	}


	


	public Payment(@NotNull(message = "payment id cannot be null") int paymentId, LocalDate paymentDate,
			@NotBlank(message = "payment status cannot be blank") String paymentStatus,
			@NotNull(message = "amount ") int amount, @NotNull(message = "customerId cannot be null") int customerId,
			@NotNull(message = "bookingId cannot be null") int bookingId) {
		super();
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.amount = amount;
		this.customerId = customerId;
		this.bookingId = bookingId;
	}





	public int getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}


	


	public LocalDate getPaymentDate() {
		return paymentDate;
	}





	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}





	public String getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	

	public int getBookingId() {
		return bookingId;
	}


	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	
}
