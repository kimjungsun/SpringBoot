package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>, PaymentRepositoryInterface{
    @Override
    List<Payment> findByAccount(Account account);
}
