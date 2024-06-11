package dev.pkj.productservice.services.paymentgateway;

import com.razorpay.PaymentLink;
import dev.pkj.productservice.configs.RazorpayClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service("razorpaymentgateway")
public class RazorpayPaymentGateway implements PaymentGateway {
    private RazorpayClient razorpay;

    public RazorpayPaymentGateway(RazorpayClient razorpay) {
        this.razorpay = razorpay;
    }
    @Override
    public String generatePaymentLink(Long orderId, String name, int amount, String phoneNumber) throws RazorpayException {
        System.out.println(amount);
//        RazorpayClient razorpay = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000);
        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",1718079098);
        paymentLinkRequest.put("reference_id","TS1989");
        paymentLinkRequest.put("description","Payment for Scaler 10-Jun");
        JSONObject customer = new JSONObject();
        customer.put("name","+919000090000");
        customer.put("contact","piyush jaiswal");
        customer.put("email","piyushsince1988@gmail.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Some random policy");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://scalar.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
        return payment.toString();
    }

    @Override
    public boolean validatePayment(Long orderId) {
        return false;
    }
}
