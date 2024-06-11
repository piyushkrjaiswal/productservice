package dev.pkj.productservice.controllers;

import dev.pkj.productservice.dtos.InitiatePaymentRequestDto;
import dev.pkj.productservice.dtos.PaymentResponse;
import dev.pkj.productservice.services.PaymentGatewaySelectionStrategy;
import dev.pkj.productservice.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto paymentRequestDto) throws Exception {
        String payment = null;
        try {
            return paymentService.doPayment(
                    paymentRequestDto.getOrderId(),
                    paymentRequestDto.getName(),
                    paymentRequestDto.getAmount(),
                    paymentRequestDto.getPhoneNumber()
            );
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return payment;

    }
}
