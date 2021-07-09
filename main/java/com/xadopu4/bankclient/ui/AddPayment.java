package com.xadopu4.bankclient.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;
import com.xadopu4.bankclient.MainActivityViewModel;
import com.xadopu4.bankclient.R;
import com.xadopu4.bankclient.entities.Payment;
import com.xadopu4.bankclient.repository.Repository;


public class AddPayment extends Fragment {

    private EditText paymentOrderIDEditText;
    private EditText paymentCardNumberEditText;
    private EditText paymentExpiryMonthEditText;
    private EditText paymentExpiryYearEditText;
    private EditText paymentCVVEditText;
    private EditText paymentCardholderEditText;
    private EditText paymentAmountKopEditText;

    private MaterialButton payment1Card;
    private MaterialButton payment2Card;
    private MaterialButton paymentPay;

    private MainActivityViewModel mainActivityViewModel;

    private Repository repository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_payment, container, false);

        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        repository = Repository.getInstance();

        findViews(view);
        editTextAddPatterns();

        setOnClickListeners();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void findViews(View view) {
        paymentOrderIDEditText = view.findViewById(R.id.payment_id_editText);
        paymentCardNumberEditText = view.findViewById(R.id.payment_card_number_editText);
        paymentExpiryMonthEditText = view.findViewById(R.id.payment_expiry_month_editText);
        paymentExpiryYearEditText = view.findViewById(R.id.payment_expiry_year_editText);
        paymentCVVEditText = view.findViewById(R.id.payment_cvv_editText);
        paymentCardholderEditText = view.findViewById(R.id.payment_cardholder_editText);
        paymentAmountKopEditText = view.findViewById(R.id.payment_amountKop_editText);

        payment1Card = view.findViewById(R.id.payment_1_card_button);
        payment2Card = view.findViewById(R.id.payment_2_card_button);
        paymentPay = view.findViewById(R.id.payment_pay_button);
    }

    public void editTextAddPatterns() {
        paymentCardNumberEditText.addTextChangedListener(new PatternedTextWatcher("#### ####"));
        paymentCVVEditText.addTextChangedListener(new PatternedTextWatcher("###"));
        paymentExpiryMonthEditText.addTextChangedListener(new PatternedTextWatcher("##"));
        paymentExpiryYearEditText.addTextChangedListener(new PatternedTextWatcher("##"));

    }

    public void setOnClickListeners() {


        payment1Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentCardNumberEditText.setText("1234 4321");
                paymentExpiryMonthEditText.setText(String.valueOf(1));
                paymentExpiryYearEditText.setText(String.valueOf(23));
                paymentCVVEditText.setText(String.valueOf(123));
                paymentCardholderEditText.setText("Daniil");
            }
        });

        payment2Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentCardNumberEditText.setText("4321 1234");
                paymentExpiryMonthEditText.setText(String.valueOf(3));
                paymentExpiryYearEditText.setText(String.valueOf(21));
                paymentCVVEditText.setText(String.valueOf(321));
                paymentCardholderEditText.setText("Olga");
            }
        });

        paymentPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (paymentCardNumberEditText.getText().toString().isEmpty() ||
                        paymentOrderIDEditText.getText().toString().isEmpty() ||
                        paymentCVVEditText.getText().toString().isEmpty() ||
                        paymentExpiryYearEditText.getText().toString().isEmpty() ||
                        paymentExpiryMonthEditText.getText().toString().isEmpty() ||
                        paymentCardholderEditText.getText().toString().isEmpty() ||
                        paymentAmountKopEditText.getText().toString().isEmpty()) {

                    Toast.makeText(requireActivity(), "Check input fields", Toast.LENGTH_SHORT).show();
                } else {
                    Long id = Long.parseLong(paymentOrderIDEditText.getText().toString());
                    String number = paymentCardNumberEditText.getText().toString();
                    String cardholder = paymentCardholderEditText.getText().toString();
                    int month = Integer.parseInt(paymentExpiryMonthEditText.getText().toString());
                    int year = Integer.parseInt(paymentExpiryYearEditText.getText().toString());
                    int cvv = Integer.parseInt(paymentCVVEditText.getText().toString());
                    Long amountKop = Long.parseLong(paymentAmountKopEditText.getText().toString());


                    //TODO POST request
                    repository.addPayment(new Payment(id, number, month, year, cvv, cardholder, amountKop, Payment.PAID));
//                    Toast.makeText(requireActivity(), "Payment added!", Toast.LENGTH_SHORT).show();

//                    mainActivityViewModel.getPaymentList().add(new Payment(id, number, month, year, cvv, cardholder, amountKop, Payment.PAID));

                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_AddPayment_to_PaymentListFragment);
                }
            }
        });
    }
}