package com.xadopu4shelepushka.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.xadopu4shelepushka.practice.entities.Card;
import com.xadopu4shelepushka.practice.entities.Payment;
import com.xadopu4shelepushka.practice.repository.MyDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("card")
public class CardController {

    private final MyDataSource dataSource = MyDataSource.getInstance();
    private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Card>> getAll() {
        return new ResponseEntity<>(dataSource.getCardList(), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> getById(@PathVariable int id) {

        Card card = null;
        for (var item : dataSource.getCardList()) {
            if (item.getId() == id) {
                card = item;
            }
        }

        if (card == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(card, HttpStatus.OK);
    }
}
