package br.dos.api.payment.PaymentAPI.domain.payment.service;


import br.dos.api.payment.PaymentAPI.domain.payment.Payment;
import br.dos.api.payment.PaymentAPI.domain.payment.Status;
import br.dos.api.payment.PaymentAPI.domain.payment.record.PaymentListRecord;
import br.dos.api.payment.PaymentAPI.domain.payment.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    public Page<PaymentListRecord> findAll(Pageable paginacao) {
        return paymentRepository
                .findAll(paginacao).map(PaymentListRecord::new);
    }

    public PaymentListRecord findById(Long id) {
        Payment pagamento = paymentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return new PaymentListRecord(pagamento);
    }

    public PaymentListRecord createPayment(PaymentListRecord record) {
        Payment pagamento = new Payment(record);
        pagamento.setStatus(Status.CREATED);
        paymentRepository.save(pagamento);

        return new PaymentListRecord(pagamento);
    }
    public PaymentListRecord updatePayment(Long id, PaymentListRecord record) {
        Payment pagamento = new Payment(record);
        pagamento.setId(id);
        pagamento = paymentRepository.save(pagamento);
        return new PaymentListRecord(pagamento);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
