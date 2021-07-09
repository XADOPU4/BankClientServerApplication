package com.xadopu4shelepushka.practice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Card {
    private int id;
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;
    private int cvv;
    private String cardholderName;

    private Long balance;
    private boolean unlimited;
}
