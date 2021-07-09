 package com.xadopu4.bankclient.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;
import com.xadopu4.bankclient.MainActivityViewModel;
import com.xadopu4.bankclient.R;
import com.xadopu4.bankclient.entities.Payment;
import com.xadopu4.bankclient.repository.Repository;

public class PaymentInfoFragment extends Fragment {

    private TextView infoCardNumber;
    private TextView infoAmountKop;
    private TextView infoPaymentId;
    private TextView infoStatus;
    private MaterialButton infoRefundButton;

    private ImageView infoImageView;

    private MainActivityViewModel mainActivityViewModel;
    private Repository repository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_info, container, false);

        findViews(view);
//        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        repository = Repository.getInstance();

        Integer index = PaymentInfoFragmentArgs.fromBundle(getArguments()).getPaymentIndex();

        if (index != null) {
            Payment payment = repository.getPaymentList().getValue().get(index);
            infoPaymentId.setText(payment.getOrderId().toString());
            infoAmountKop.setText(payment.getAmountKop().toString());
            infoCardNumber.setText(payment.getCardNumber());



            String status;
            if (payment.getStatus() == 0){
                status = "Paid";
                infoImageView.setImageResource(R.drawable.ic_baseline_check_circle_24);
            }
            else if (payment.getStatus() == 1) {
                status = "Refund";
                infoImageView.setImageResource(R.drawable.ic_baseline_cancel_24);
            } else {
                status = "Error";
                infoImageView.setImageResource(R.drawable.ic_baseline_warning_24);
            }

            infoStatus.setText(status);

            infoRefundButton.setEnabled(payment.getStatus() != Payment.REFUND);
        }

        infoRefundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Payment payment = repository.getPaymentList().getValue().get(index);
                payment.setStatus(Payment.REFUND);
                repository.changePayment(payment.getOrderId(), payment);

                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_PaymentInfoFragment_to_PaymentListFragment);
            }
        });

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void findViews(View view) {
        infoCardNumber = view.findViewById(R.id.info_card_number_textView);
        infoAmountKop = view.findViewById(R.id.info_amountKop_textView);
        infoStatus = view.findViewById(R.id.info_status_textView);
        infoPaymentId = view.findViewById(R.id.info_payment_id_textView);
        infoRefundButton = view.findViewById(R.id.info_refund_button);

        infoImageView = view.findViewById(R.id.infoImageView);
    }
}