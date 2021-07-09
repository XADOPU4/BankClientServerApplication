package com.xadopu4shelepushka.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.xadopu4shelepushka.practice.entities.Payment;
import com.xadopu4shelepushka.practice.repository.MyDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("payment")
public class PaymentController {

    private final MyDataSource dataSource = MyDataSource.getInstance();
    private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Payment>> getAll() {
        return new ResponseEntity<>(dataSource.getPaymentList(), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> getById(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Payment payment = null;
        for (var item : dataSource.getPaymentList()) {
            if (item.getOrderId().equals(id)) {
                payment = item;
            }
        }

        if (payment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> create(@RequestBody Payment payment) {

        if (payment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //Поиск карты по введенным данным
        for (var item : dataSource.getCardList()) {
            if (item.getCardNumber().equals(payment.getCardNumber())
                    && item.getExpiryMonth() == payment.getExpiryMonth()
                    && item.getExpiryYear() == payment.getExpiryYear()
                    && item.getCvv() == payment.getCvv()
                    && item.getCardholderName().equals(payment.getCardholderName())) {

                //Если карта нашлась, проверка баланса
                if (item.isUnlimited()) {
                    payment.setId(Payment.IDGenerator++);
                    payment.setStatus(Payment.PAID);
                    dataSource.getPaymentList().add(payment);
                    return new ResponseEntity<>(payment, HttpStatus.CREATED);
                } else if (item.getBalance() - payment.getAmountKop() >= 0) {
                    item.setBalance(item.getBalance() - payment.getAmountKop());
                    payment.setId(Payment.IDGenerator++);
                    payment.setStatus(Payment.PAID);
                    dataSource.getPaymentList().add(payment);
                    return new ResponseEntity<>(payment, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> update(@PathVariable("id") Long id, @RequestBody Payment payment) {

        if (payment == null || id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (payment.getStatus() == Payment.REFUND) {

            for (var item : dataSource.getPaymentList()) {
                //Изменение покупки на покупку со статусом возврат
                if(item.getOrderId().equals(id)){
                    item.setStatus(payment.getStatus());
                    break;
                }
            }

            for (var item : dataSource.getCardList()) {
                //Пополнение баланса за возврат
                if (item.getCardNumber().equals(payment.getCardNumber())) {
                    item.setBalance(item.getBalance() + payment.getAmountKop());
                    return new ResponseEntity<>(payment, HttpStatus.OK);
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
