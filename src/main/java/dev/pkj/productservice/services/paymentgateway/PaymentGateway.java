package dev.pkj.productservice.services.paymentgateway;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String generatePaymentLink(Long orderId, String name, int amount, String phoneNumber) throws RazorpayException, StripeException;

    boolean validatePayment(Long orderId);
}
