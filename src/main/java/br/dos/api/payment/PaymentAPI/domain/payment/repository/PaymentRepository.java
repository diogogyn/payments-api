package br.dos.api.payment.PaymentAPI.domain.payment.repository;

import br.dos.api.payment.PaymentAPI.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
