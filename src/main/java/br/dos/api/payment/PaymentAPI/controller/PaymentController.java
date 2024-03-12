package br.dos.api.payment.PaymentAPI.controller;


import br.dos.api.payment.PaymentAPI.domain.payment.record.PaymentListRecord;
import br.dos.api.payment.PaymentAPI.domain.payment.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public Page<PaymentListRecord> listAll(@PageableDefault(size = 10) Pageable paginacao) {
        return paymentService.findAll(paginacao);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaymentListRecord> detail(@PathVariable @NotNull Long id) {
        PaymentListRecord dto = paymentService.findById(id);
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<PaymentListRecord> register(@RequestBody @Valid PaymentListRecord dto, UriComponentsBuilder uriBuilder) {
        PaymentListRecord pagamento = paymentService.createPayment(dto);
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.id()).toUri();

        return ResponseEntity.created(endereco).body(pagamento);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PaymentListRecord> update(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentListRecord dto) {
        PaymentListRecord atualizado = paymentService.updatePayment(id, dto);
        return ResponseEntity.ok(atualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentListRecord> delete(@PathVariable @NotNull Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/port")
    public ResponseEntity<String> getPort(@Value("${local.server.port") String port){
        return ResponseEntity.ok("Requisição respondida pela instancia pela porta: " + port);
    }
}
