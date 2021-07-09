package com.xadopu4shelepushka.practice.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.xadopu4shelepushka.practice.entities.Payment;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class BankRESTClient {

    private static HttpClient client;

    private static ArrayList<Payment> paymentList;
    private static Payment payment;



    @SneakyThrows({IOException.class, InterruptedException.class})
    public static void main(String[] args) {

        String GETUri = "http://localhost:8080/payment";
        String POSTUri = "http://localhost:8080/payment";
        ObjectMapper mapper = new ObjectMapper();

        client = HttpClient.newBuilder().build();

        HttpRequest requestGET = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(GETUri))
                .build();

        HttpRequest requestPOST = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(new Payment())))
                .header("accept", "application/json")
                .uri(URI.create(POSTUri))
                .build();

        HttpRequest requestPUT = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(new Payment())))
                .header("accept", "application/json")
                .uri(URI.create(POSTUri))
                .build();


        var responseGET = client.send(requestGET, HttpResponse.BodyHandlers.ofString());
        var responsePOST = client.send(requestPOST, HttpResponse.BodyHandlers.ofString());
        var responsePUT = client.send(requestPOST, HttpResponse.BodyHandlers.ofString());



        paymentList = mapper.readValue(responseGET.body(), mapper.getTypeFactory().constructCollectionType(List.class, Payment.class));
        payment = mapper.readValue(responsePOST.body(), Payment.class);




    }
}
