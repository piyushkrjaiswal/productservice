package dev.pkj.productservice.services;

import org.springframework.stereotype.Service;

@Service
public class PaymentGatewaySelectionStrategy {
    public int paymentGatewaySelection() {
        return 1;
    }
}
