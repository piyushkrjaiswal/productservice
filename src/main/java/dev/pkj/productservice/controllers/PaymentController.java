package dev.pkj.productservice.controllers;

import dev.pkj.productservice.dtos.InitiatePaymentRequestDto;
import dev.pkj.productservice.dtos.PaymentResponse;
import dev.pkj.productservice.services.PaymentGatewaySelectionStrategy;
import dev.pkj.productservice.services.PaymentService;
import dev.pkj.productservice.services.RazorpayPaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService razorpayPaymentService;
    private PaymentService stripePaymentService;
    private PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy;

    public PaymentController(@Qualifier("razorpayPaymentService") PaymentService razorpayPaymentService,
                             @Qualifier("stripePaymentService") PaymentService stripePaymentService,
                             PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy) {
        this.razorpayPaymentService = razorpayPaymentService;
        this.stripePaymentService = stripePaymentService;
        this.paymentGatewaySelectionStrategy = paymentGatewaySelectionStrategy;
    }

    @PostMapping("/")
    public ResponseEntity<PaymentResponse> initiatePayment(@RequestBody InitiatePaymentRequestDto paymentRequestDto) throws Exception {
        PaymentResponse paymentResponse;
        switch (paymentGatewaySelectionStrategy.paymentGatewaySelection()) {
            case 1:
               paymentResponse = razorpayPaymentService.doPayment(paymentRequestDto);
               return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
            case 2:
              paymentResponse = stripePaymentService.doPayment(paymentRequestDto);
              return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
        }

        return null;


    }
}
