package dev.pkj.productservice.services;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import dev.pkj.productservice.dtos.InitiatePaymentRequestDto;
import dev.pkj.productservice.dtos.PaymentResponse;

public interface PaymentService {

    String doPayment(Long orderId, String name, int amount, String phoneno) throws RazorpayException, StripeException;
}
