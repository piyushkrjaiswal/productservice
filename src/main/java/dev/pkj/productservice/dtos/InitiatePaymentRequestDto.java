package dev.pkj.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentRequestDto {
    private String name;
    private String phoneNumber;
    private int amount;
    private Long orderId;
}
