package dev.pkj.productservice.services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import dev.pkj.productservice.dtos.InitiatePaymentRequestDto;
import dev.pkj.productservice.dtos.PaymentResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpayPaymentService")
public class RazorpayPaymentService implements PaymentService{
    RazorpayClient razorpayClient;

    public RazorpayPaymentService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }
    @Override
    public PaymentResponse doPayment(InitiatePaymentRequestDto paymentRequestDto) throws RazorpayException {
        try {
            JSONObject paymentRequestOptions = new JSONObject();
            paymentRequestOptions.put("amount", paymentRequestDto.getAmount());
            paymentRequestOptions.put("currency", "INR");
            paymentRequestOptions.put("receipt", paymentRequestDto.getOrderId());

            JSONObject notes = new JSONObject();
            notes.put("notes_key_1", "Demo Razorpay order payment");
            paymentRequestOptions.put("notes", notes);

            JSONObject customer = new JSONObject();
            customer.put("email", paymentRequestDto.getEmail());
            customer.put("contact", paymentRequestDto.getPhoneNumber());
            paymentRequestOptions.put("customer", customer);

            JSONObject notify = new JSONObject();
            customer.put("sms", true);
            customer.put("email", true);
            paymentRequestOptions.put("notify", notify);

            paymentRequestOptions.put("callback_url", "http:localhost:81/razorpayWebhook");
            paymentRequestOptions.put("callback_method", "get");

            PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentRequestOptions);
            PaymentResponse paymentResponse = new PaymentResponse();

            return paymentResponse;
        } catch (RazorpayException e) {
            e.printStackTrace();
        }
        return null;
    }
}
