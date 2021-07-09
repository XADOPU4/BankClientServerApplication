package com.xadopu4.bankclient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xadopu4.bankclient.entities.Card;
import com.xadopu4.bankclient.entities.Payment;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Payment>> livePaymentList;
    private MutableLiveData<List<Card>> liveCardList;

    private List<Payment> paymentList;

    public MutableLiveData<List<Payment>> getLivePaymentList() {
        return livePaymentList;
    }

    public MutableLiveData<List<Card>> getLiveCardList() {
        return liveCardList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }
}
