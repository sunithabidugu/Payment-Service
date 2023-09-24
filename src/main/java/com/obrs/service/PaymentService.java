package com.obrs.service;

import java.util.List;

import com.obrs.dto.PaymentDto;
import com.obrs.entity.Payment;
import com.obrs.exceptions.BookingIdNotFoundException;
import com.obrs.exceptions.CustomerIdNotFoundException;
import com.obrs.exceptions.PaymentNotFoundException;

public interface PaymentService {
	public List<PaymentDto> getAllPayment()throws PaymentNotFoundException;
	public PaymentDto addPayment(PaymentDto paymentDto)throws PaymentNotFoundException;
	//public PaymentDto updatePayment(PaymentDto paymentDto)throws PaymentNotFoundException;
	public PaymentDto deletePayment(int paymentId)throws PaymentNotFoundException;
	public Payment findPaymentById(int paymentId)throws PaymentNotFoundException;
	public List<Payment> getPaymentsBycustomerId(int customerId)throws CustomerIdNotFoundException;
	public List<Payment> getPaymentsByBookingId(int bookingId) throws BookingIdNotFoundException;
	PaymentDto updatePayment(int paymentId, PaymentDto paymentDto);
}
