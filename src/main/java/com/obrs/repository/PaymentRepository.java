package com.obrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obrs.dto.PaymentDto;
import com.obrs.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment , Integer> {



}
