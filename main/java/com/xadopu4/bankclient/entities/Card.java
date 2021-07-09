package com.xadopu4.bankclient.entities;



public class Card {
    private int id;
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;
    private int cvv;
    private String cardholderName;

    private Long balance;
    private boolean unlimited;

    public Card(int id, String cardNumber, int expiryMonth, int expiryYear, int cvv, String cardholderName, Long balance, boolean unlimited) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
        this.cardholderName = cardholderName;
        this.balance = balance;
        this.unlimited = unlimited;
    }

    public Card(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public boolean isUnlimited() {
        return unlimited;
    }

    public void setUnlimited(boolean unlimited) {
        this.unlimited = unlimited;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expiryMonth=" + expiryMonth +
                ", expiryYear=" + expiryYear +
                ", cvv=" + cvv +
                ", cardholderName='" + cardholderName + '\'' +
                ", balance=" + balance +
                ", unlimited=" + unlimited +
                '}';
    }
}
