package com.obrs;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.obrs.dto.PaymentDto;
import com.obrs.entity.Payment;
import com.obrs.exceptions.BookingIdNotFoundException;
import com.obrs.exceptions.CustomerIdNotFoundException;
import com.obrs.exceptions.PaymentNotFoundException;
import com.obrs.repository.PaymentRepository;
import com.obrs.service.PaymentServiceImpl;

public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPayment() {
        List<Payment> mockPaymentList = new ArrayList<>();
        Payment payment1 = new Payment();
        payment1.setPaymentId(1);
        Payment payment2 = new Payment();
        payment2.setPaymentId(2);
        mockPaymentList.add(payment1);
        mockPaymentList.add(payment2);

        when(paymentRepository.findAll()).thenReturn(mockPaymentList);

        List<PaymentDto> paymentDtoList = paymentService.getAllPayment();

        assertNotNull(paymentDtoList);
        assertEquals(2, paymentDtoList.size());
    }

    @Test
    public void testAddPayment() throws PaymentNotFoundException {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId(1);

        when(paymentRepository.findById(1)).thenReturn(Optional.empty());

        PaymentDto addedPaymentDto = paymentService.addPayment(paymentDto);

        assertNotNull(addedPaymentDto);
    }

    @Test
    public void testAddPaymentAlreadyExists() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId(1);

        Payment existingPayment = new Payment();
        when(paymentRepository.findById(1)).thenReturn(Optional.of(existingPayment));

        assertThrows(PaymentNotFoundException.class, () -> paymentService.addPayment(paymentDto));
    }

    @Test
    public void testDeletePayment() throws PaymentNotFoundException {
        Payment existingPayment = new Payment();
        existingPayment.setPaymentId(1);
        when(paymentRepository.existsById(1)).thenReturn(true);
        when(paymentRepository.findById(1)).thenReturn(Optional.of(existingPayment));

        PaymentDto deletedPaymentDto = paymentService.deletePayment(1);

        assertNotNull(deletedPaymentDto);
        assertEquals(1, deletedPaymentDto.getPaymentId());
    }

    @Test
    public void testDeletePaymentNotFound() {
        when(paymentRepository.existsById(1)).thenReturn(false);

        assertThrows(PaymentNotFoundException.class, () -> paymentService.deletePayment(1));
    }

//    @Test
//    public void testGetPaymentsByCustomerId() throws CustomerIdNotFoundException {
//        List<Payment> mockPaymentList = new ArrayList<>();
//        Payment payment1 = new Payment();
//        payment1.setCustomerId(1);
//        Payment payment2 = new Payment();
//        payment2.setCustomerId(1);
//        mockPaymentList.add(payment1);
//        mockPaymentList.add(payment2);
//
//        when(paymentRepository.findAll()).thenReturn(mockPaymentList);
//
//        List<Payment> paymentsByCustomerId = paymentService.getPaymentsBycustomerId(1);
//
//        assertNotNull(paymentsByCustomerId);
//        assertEquals(2, paymentsByCustomerId.size());
//    }

//    @Test
//    public void testGetPaymentsByCustomerIdNotFound() {
//        when(paymentRepository.findAll()).thenReturn(new ArrayList<>());
//
//        assertThrows(CustomerIdNotFoundException.class, () -> paymentService.getPaymentsBycustomerId(1));
//    }

//    @Test
//    public void testGetPaymentsByBookingId() throws BookingIdNotFoundException {
//        List<Payment> mockPaymentList = new ArrayList<>();
//        Payment payment1 = new Payment();
//        payment1.setBookingId(1);
//        Payment payment2 = new Payment();
//        payment2.setBookingId(1);
//        mockPaymentList.add(payment1);
//        mockPaymentList.add(payment2);
//
//        when(paymentRepository.findAll()).thenReturn(mockPaymentList);
//
//        List<Payment> paymentsByBookingId = paymentService.getPaymentsByBookingId(1);
//
//        assertNotNull(paymentsByBookingId);
//        assertEquals(2, paymentsByBookingId.size());
//    }
//
//    @Test
//    public void testGetPaymentsByBookingIdNotFound() {
//        when(paymentRepository.findAll()).thenReturn(new ArrayList<>());
//
//        assertThrows(BookingIdNotFoundException.class, () -> paymentService.getPaymentsByBookingId(1));
//    }

    @Test
    public void testUpdatePayment() {
        Payment existingPayment = new Payment();
        existingPayment.setPaymentId(1);
        when(paymentRepository.findById(1)).thenReturn(Optional.of(existingPayment));

        PaymentDto updatedPaymentDto = new PaymentDto();
        updatedPaymentDto.setPaymentId(1);
        updatedPaymentDto.setAmount(100);

        PaymentDto updatedDto = paymentService.updatePayment(1, updatedPaymentDto);

        assertNotNull(updatedDto);
        assertEquals(100.0, updatedDto.getAmount());
    }

    @Test
    public void testUpdatePaymentNotFound() {
        when(paymentRepository.findById(1)).thenReturn(Optional.empty());

        PaymentDto updatedPaymentDto = new PaymentDto();
        updatedPaymentDto.setPaymentId(1);
        updatedPaymentDto.setAmount(100);

        assertNull(paymentService.updatePayment(1, updatedPaymentDto));
    }
}
