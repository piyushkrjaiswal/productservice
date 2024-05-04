package dev.pkj.productservice.services;

import com.razorpay.RazorpayException;
import dev.pkj.productservice.dtos.InitiatePaymentRequestDto;
import dev.pkj.productservice.dtos.PaymentResponse;

public interface PaymentService {
    PaymentResponse doPayment(InitiatePaymentRequestDto paymentRequestDto) throws RazorpayException;
}
