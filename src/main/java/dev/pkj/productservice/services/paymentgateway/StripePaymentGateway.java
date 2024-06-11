package dev.pkj.productservice.services.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("stripepaymentgateway")
public class StripePaymentGateway implements PaymentGateway {
    @Value("${stripe.key.id}")
    private String stripePaymentId;
    @Override
    public String generatePaymentLink(Long orderId, String name, int amount, String phoneNumber) throws StripeException {
        Stripe.apiKey = stripePaymentId;

        Stripe.apiKey = "sk_test_51PQKv42LIGeJVmMYJfqdCqhXkIcexQARblg4FNkcyHNgITdJPiXKZ2qT2Ll1JnBODbf8y6pu5AjxW6PFVTshaIwx00tVsO1L8J";

        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setCurrency("usd")
                        .setUnitAmount(1000L)
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();

        Price price = Price.create(priceParams);

        PaymentLinkCreateParams paymentParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentParams);
        return paymentLink.toString();
    }

    @Override
    public boolean validatePayment(Long orderId) {
        return false;
    }
}
