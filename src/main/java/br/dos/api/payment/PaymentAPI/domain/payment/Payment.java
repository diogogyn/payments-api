package br.dos.api.payment.PaymentAPI.domain.payment;

import br.dos.api.payment.PaymentAPI.domain.payment.record.PaymentDetailsRecord;
import br.dos.api.payment.PaymentAPI.domain.payment.record.PaymentListRecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotBlank
    @Size(max = 100)
    private String nome;
    @NotBlank
    @Size(max = 19)
    private String numero;
    @Size(max = 7)
    private String expiracao;
    @NotBlank
    @Size(min = 3, max = 3)
    private String codigo;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotNull
    private Long pedidoId;
    @NotNull
    private Long formaDePagamentoId;

    public Payment(PaymentDetailsRecord record) {
        if(record.id() != null) this.id = record.id();
        this.valor = record.valor();
        this.nome = record.nome();
        this.numero = record.numero();
        this.expiracao = record.expiracao();
        this.codigo = record.codigo();
        this.status = record.status();
        this.pedidoId = record.pedidoId();
        this.formaDePagamentoId = record.formaDePagamentoId();
    }
    public Payment(PaymentListRecord record) {
        if(record.id() != null) this.id = record.id();
        this.valor = record.valor();
        this.nome = record.nome();
        this.numero = record.numero();
        this.expiracao = record.expiracao();
        this.codigo = record.codigo();
        this.status = record.status();
        this.pedidoId = record.pedidoId();
        this.formaDePagamentoId = record.formaDePagamentoId();
    }
}
