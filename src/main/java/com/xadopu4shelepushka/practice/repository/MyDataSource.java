package com.xadopu4shelepushka.practice.repository;

import com.xadopu4shelepushka.practice.entities.Card;
import com.xadopu4shelepushka.practice.entities.Payment;

import java.util.ArrayList;
import java.util.List;

public class MyDataSource {

    private static MyDataSource instance;

    private static List<Card> cardList;
    private static List<Payment> paymentList;

    public static MyDataSource getInstance() {
        if (instance == null) {
            instance = new MyDataSource();
        }
        return instance;
    }

    private MyDataSource() {
        cardList = new ArrayList<>() {{
            add(new Card(1, "1234 4321", 1, 23, 123, "Daniil", 1000000L, false));
            add(new Card(2, "4321 1234", 3, 21, 321, "Olga", 100L, true));
            add(new Card(3, "2606 2211", 11, 25, 422, "Xelepushka", 1000000L, true));
        }};

        paymentList = new ArrayList<>() {{
            add(new Payment(1L, "1234 4321", 1, 23, 123, "Daniil", 10000L, Payment.PAID));
            add(new Payment(2L, "1234 4321", 1, 23, 123, "Daniil", 999000L, Payment.PAID));
            add(new Payment(3L, "4321 1234", 3, 21, 321, "Olga", 999L, Payment.PAID));
            add(new Payment(4L, "1234 4321", 1, 23, 123, "Daniil", 10000L, Payment.PAID));
        }};
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

}
