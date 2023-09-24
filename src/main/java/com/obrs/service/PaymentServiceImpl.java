package com.obrs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.obrs.dto.PaymentDto;
import com.obrs.entity.Payment;
import com.obrs.exceptions.BookingIdNotFoundException;
import com.obrs.exceptions.CustomerIdNotFoundException;
import com.obrs.exceptions.PaymentNotFoundException;
import com.obrs.repository.PaymentRepository;
@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public List<PaymentDto> getAllPayment()  {
		List<Payment> payments =paymentRepository.findAll();
		List<PaymentDto> d=new ArrayList<>();
		for(Payment p:payments) {
			PaymentDto pp = new PaymentDto();
			BeanUtils.copyProperties(p, pp);
			d.add(pp);
		}
		return d;
	}

	@Override
	public PaymentDto addPayment(PaymentDto paymentDto) throws PaymentNotFoundException {
		
		Optional<Payment> payments=paymentRepository.findById(paymentDto.getPaymentId());
		if(payments.isPresent()) {
			throw new PaymentNotFoundException("Payment Id already exists");
		}
		else {
			Payment pp=new Payment();
			BeanUtils.copyProperties(paymentDto, pp);
			paymentRepository.save(pp);	
			return paymentDto;
		}
		
	}

	
	
	@Override
	public PaymentDto deletePayment(int paymentId) throws PaymentNotFoundException {
		  if(paymentRepository.existsById(paymentId)) {
		    	Payment p=paymentRepository.findById(paymentId).get();
		    	paymentRepository.deleteById(paymentId);
		    	PaymentDto d=new PaymentDto();
		    	BeanUtils.copyProperties(p, d);
		    	return d;
		    	
		    }
		    else {
		    	throw new PaymentNotFoundException("payment not found with id"+" "+paymentId);
		    }
	}

	

	@Override
	public List<Payment> getPaymentsBycustomerId(int customerId) throws CustomerIdNotFoundException {
		List<Payment> payments=paymentRepository.findAll();
		List<Payment> d=new ArrayList<>();
			for(Payment p:payments) {
				if(p.getCustomerId()==customerId) {
					d.add(p);
					return d;
					
				}
				else
				{
					throw new CustomerIdNotFoundException("customer have no payments");	
					
				
			}
	
			
		}
			return null;
			
	}

	@Override
	public List<Payment> getPaymentsByBookingId(int bookingId) throws BookingIdNotFoundException {
		List<Payment> payments=paymentRepository.findAll();
		List<Payment> d=new ArrayList<>();
		for(Payment p:payments) {
			if(p.getBookingId()==bookingId) {
				d.add(p);
				return d;
			}
			else
			{
				throw new CustomerIdNotFoundException(" no payments exists by this bookingId");	
				
		}

		
	}
	return null;
}

	
	public PaymentDto updatePayment(int paymentId, PaymentDto paymentDto) {

		
			// Find the payment by ID
	        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);

	        if (optionalPayment.isPresent()) {
	            // Payment exists, update it
	            Payment existingPayment = optionalPayment.get();
	            BeanUtils.copyProperties(paymentDto, existingPayment);
	            paymentRepository.save(existingPayment);
	            return paymentDto; // You may return the updated DTO or some response indicating success
	        } else {
	            // Payment with the given ID does not exist
	            // You can handle this case as per your application's requirements (e.g., return an error response)
	            return null; // Or return an appropriate response, like HttpStatus.NOT_FOUND
	        }
		}

	@Override
	public Payment findPaymentById(int paymentId) throws PaymentNotFoundException {
		if(paymentRepository.existsById(paymentId)) {
			Payment p=paymentRepository.findById(paymentId).get();
			
			return p;
			
		}
		else {
			throw new PaymentNotFoundException("payment with"+" "+paymentId+" "+"not found");
		}
	}

	
}
