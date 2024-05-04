package dev.pkj.productservice.services;

import dev.pkj.productservice.dtos.InitiatePaymentRequestDto;
import dev.pkj.productservice.dtos.PaymentResponse;
import org.springframework.stereotype.Service;

@Service("stripePaymentService")
public class StripePaymentService implements PaymentService{
    @Override
    public PaymentResponse doPayment(InitiatePaymentRequestDto paymentRequestDto) {
        return null;
    }
}
