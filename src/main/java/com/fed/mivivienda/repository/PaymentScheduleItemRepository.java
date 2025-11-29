package com.fed.mivivienda.repository;

import com.fed.mivivienda.entity.PaymentScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentScheduleItemRepository extends JpaRepository<PaymentScheduleItem, Long> {
    List<PaymentScheduleItem> findByLoanIdOrderByNumeroCuotaAsc(Long loanId);
}
