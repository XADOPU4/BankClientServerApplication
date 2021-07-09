package com.xadopu4.bankclient.repository;

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.xadopu4.bankclient.bankApi.BankPaymentApi;
import com.xadopu4.bankclient.entities.Card;
import com.xadopu4.bankclient.entities.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Repository {

    private static final String TAG = "Repository";
    private static Repository instance;

    private MutableLiveData<Pair<Integer, String>> responseCodeMessage;
    private MutableLiveData<List<Payment>> paymentList;
    private MutableLiveData<List<Card>> cardList;

    private BankPaymentApi bankPaymentApi;
    private Retrofit retrofitPayment;
    private final String homeUrl = "http://10.0.2.2:8080/";
    private final String localUrl = "http://web.xadopu4.keenetic.name:8080/";

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    private Repository() {
        retrofitPayment = new Retrofit.Builder()
                .baseUrl(homeUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        bankPaymentApi = retrofitPayment.create(BankPaymentApi.class);


        responseCodeMessage = new MutableLiveData<>();
        paymentList = new MutableLiveData<>(new ArrayList<>());
        cardList = new MutableLiveData<>(new ArrayList<>());
    }

    public MutableLiveData<List<Payment>> getAllPayments() {

        Call<List<Payment>> listCall = bankPaymentApi.getAllPayments();



        listCall.enqueue(new Callback<List<Payment>>() {
            @Override
            public void onResponse(Call<List<Payment>> call, Response<List<Payment>> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, response.code() + " " + response.message() + "Received successfully!" + response.body());
                    paymentList.setValue(response.body());
                } else {
                    Log.d(TAG, "Received with error! " + response.code());
                    paymentList.setValue(new ArrayList<>());
                }

                responseCodeMessage.setValue(new Pair<>(response.code(), response.message()));
            }

            @Override
            public void onFailure(Call<List<Payment>> call, Throwable t) {
                Log.d(TAG, "Error getting payment list! " + t.getMessage());
                paymentList.setValue(new ArrayList<>());

            }
        });

        return paymentList;
    }

    public void addPayment(Payment payment){
        Call<Payment> call = bankPaymentApi.addPayment(payment);

        call.enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, response.code() + " " + response.message() + "Received successfully!" + response.body());
                    paymentList.getValue().add(response.body());
                } else {
                    Log.d(TAG, "Received with error! " + response.code());
                }
                responseCodeMessage.setValue(new Pair<>(response.code(), response.message()));

            }

            @Override
            public void onFailure(Call<Payment> call, Throwable t) {
                Log.d(TAG, "Error getting payment list! " + t.getMessage());
            }
        });
    }

    public void changePayment(Long id, Payment payment){
        Call<Payment> call = bankPaymentApi.putPayment(id, payment);

        call.enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, response.code() + " " + response.message() + "Received successfully!" + response.body());
                } else {
                    Log.d(TAG, "Received with error! " + response.code());
                }

                responseCodeMessage.setValue(new Pair<>(response.code(), response.message()));

            }

            @Override
            public void onFailure(Call<Payment> call, Throwable t) {
                Log.d(TAG, "Error putting payment! " + t.getMessage());
            }
        });
    }

    public MutableLiveData<List<Card>> getAllCards(){

        Call<List<Card>> call = bankPaymentApi.getAllCards();
        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, response.code() + " " + response.message() + "Received successfully!" + response.body());
                    cardList.setValue(response.body());
                } else {
                    Log.d(TAG, "Received with error! " + response.code());
                }

                cardList.setValue(new ArrayList<>());
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                Log.d(TAG, "Error getting card list!" + t.getMessage());
                cardList.setValue(new ArrayList<>());
            }
        });

        return cardList;
    }

    public MutableLiveData<Pair<Integer, String>> getResponseCodeMessage() {
        return responseCodeMessage;
    }

    public void setResponseCodeMessage(MutableLiveData<Pair<Integer, String>> responseCodeMessage) {
        this.responseCodeMessage = responseCodeMessage;
    }

    public MutableLiveData<List<Payment>> getPaymentList() {
        return paymentList;
    }

    public MutableLiveData<List<Card>> getCardList() {
        return cardList;
    }

    public BankPaymentApi getBankPaymentApi() {
        return bankPaymentApi;
    }

    public Retrofit getRetrofitPayment() {
        return retrofitPayment;
    }
}
