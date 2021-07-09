package com.xadopu4.bankclient.bankApi;

import com.xadopu4.bankclient.entities.Card;
import com.xadopu4.bankclient.entities.Payment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BankPaymentApi {

    @GET("payment")
    Call<List<Payment>> getAllPayments();

    @GET("card")
    Call<List<Card>> getAllCards();

    @POST("payment")
    Call<Payment> addPayment(@Body Payment payment);

    @PUT("payment/{id}")
    Call<Payment> putPayment(@Path("id") Long id, @Body Payment payment);
}
