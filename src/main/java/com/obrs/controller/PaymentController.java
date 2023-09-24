package com.obrs.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.converters.Auto;
import com.obrs.dto.PaymentDto;
import com.obrs.entity.Payment;
import com.obrs.exceptions.BookingIdNotFoundException;
import com.obrs.exceptions.CustomerIdNotFoundException;
import com.obrs.exceptions.PaymentNotFoundException;
import com.obrs.repository.PaymentRepository;
import com.obrs.service.PaymentService;

@RestController
@RequestMapping("/payments")
@CrossOrigin("*")//origins = "http://localhost:60718/")
public class PaymentController {
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PaymentService paymentServcie;
	
	@GetMapping("/allpayments")
	public ResponseEntity<List<PaymentDto>>  getAllPayment() throws PaymentNotFoundException {
		
		return new ResponseEntity<>(paymentServcie.getAllPayment(),HttpStatus.OK);
	}
	@PostMapping("/addpayment")
	public ResponseEntity<PaymentDto>  addPayment(@RequestBody PaymentDto paymentDto) throws PaymentNotFoundException {
		
		return new ResponseEntity<>(paymentServcie.addPayment(paymentDto),HttpStatus.CREATED);
	}

	@PutMapping("/updatepayment/{paymentId}")
	public ResponseEntity<PaymentDto>  updatePayment(@PathVariable int paymentId,@RequestBody PaymentDto paymentDto)  {
		// Find the payment by ID
		Optional<Payment> optionalPayment = paymentRepository.findById(paymentDto.getPaymentId());

        if (optionalPayment.isPresent()) {
            // Payment exists, update it
            Payment existingPayment = optionalPayment.get();
            BeanUtils.copyProperties(paymentDto, existingPayment);
            paymentRepository.save(existingPayment);
            return ResponseEntity.ok(paymentDto); // You may return the updated DTO or some response indicating success
        } else {
            // Payment with the given ID does not exist
            // You can handle this case as per your application's requirements (e.g., return an error response)
            return null; // Or return an appropriate response, like HttpStatus.NOT_FOUND
        }
    }
	
	

    @DeleteMapping("deletepayment/{paymentId}")
	public ResponseEntity<PaymentDto>  deletePayment(@PathVariable int paymentId) throws PaymentNotFoundException {
		
		return new ResponseEntity<>(paymentServcie.deletePayment(paymentId),HttpStatus.ACCEPTED);
	}
    

    @GetMapping("/getpaymentbycustomerid/{customerId}")
	public ResponseEntity<List<Payment>> getPaymentsBycustomerId(@PathVariable int customerId)  throws CustomerIdNotFoundException{
		
	    return new ResponseEntity<>(paymentServcie.getPaymentsBycustomerId(customerId),HttpStatus.OK);
	}
    
    @GetMapping("/getpaymentbybookingid/{bookingId}")
   	public ResponseEntity<List<Payment>> getPaymentsBybookingId(@PathVariable int bookingId) {
   		
   	    return new ResponseEntity<>(paymentServcie.getPaymentsByBookingId(bookingId),HttpStatus.OK);
   	}
    
    @GetMapping("getpaymentbyid/{paymentId}")
	public ResponseEntity<Payment> findpaymentById(@PathVariable int paymentId)throws PaymentNotFoundException{
		return new ResponseEntity<>(paymentServcie.findPaymentById(paymentId),HttpStatus.OK);
	}

}
