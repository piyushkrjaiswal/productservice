package dev.pkj.productservice.services;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import dev.pkj.productservice.services.paymentgateway.PaymentGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    PaymentGateway paymentGateway;

    public PaymentServiceImpl(@Qualifier("stripepaymentgateway")PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public String doPayment(Long orderId, String name, int amount, String phoneNumber) throws RazorpayException, StripeException {
        return paymentGateway.generatePaymentLink(
                orderId,
                name,
                amount,
                phoneNumber
        );
    }


    public boolean validatePayment(Long orderId) {
        return false;
    }
}
