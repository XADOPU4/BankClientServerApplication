package com.xadopu4.bankclient.entities;


public class Payment {
    public static final int PAID = 0;
    public static final int ERROR = -1;
    public static final int REFUND = 1;

    public static int IDGenerator = 0;

    private int id;
    private Long orderId;
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;
    private int cvv;
    private String cardholderName;
    private Long amountKop;
    private int status;

    public Payment(Long orderId, String cardNumber, int expiryMonth, int expiryYear, int cvv, String cardholderName, Long amountKop, int status) {
        this.id = IDGenerator++;
        this.orderId = orderId;
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
        this.cardholderName = cardholderName;
        this.amountKop = amountKop;
        this.status = status;
    }

    public Payment(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Long getAmountKop() {
        return amountKop;
    }

    public void setAmountKop(Long amountKop) {
        this.amountKop = amountKop;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryMonth=" + expiryMonth +
                ", expiryYear=" + expiryYear +
                ", cvv=" + cvv +
                ", cardholderName='" + cardholderName + '\'' +
                ", amountKop=" + amountKop +
                ", status=" + status +
                '}';
    }
}
