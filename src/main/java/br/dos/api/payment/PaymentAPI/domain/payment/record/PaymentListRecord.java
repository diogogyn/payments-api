package br.dos.api.payment.PaymentAPI.domain.payment.record;

import br.dos.api.payment.PaymentAPI.domain.payment.Payment;
import br.dos.api.payment.PaymentAPI.domain.payment.Status;

import java.math.BigDecimal;

public record PaymentListRecord( Long id,
         BigDecimal valor,
         String nome,
         String numero,
         String expiracao,
         String codigo,
         Status status,
         Long formaDePagamentoId,
         Long pedidoId) {
    public PaymentListRecord(Payment payment) {
        this(payment.getId(), payment.getValor(), payment.getNome(), payment.getNumero(), payment.getExpiracao(), payment.getCodigo(), payment.getStatus(), payment.getFormaDePagamentoId(), payment.getPedidoId());
    }
}
