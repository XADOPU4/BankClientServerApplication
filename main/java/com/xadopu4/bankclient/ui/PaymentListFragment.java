package com.xadopu4.bankclient.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xadopu4.bankclient.MainActivityViewModel;
import com.xadopu4.bankclient.R;
import com.xadopu4.bankclient.entities.Card;
import com.xadopu4.bankclient.entities.Payment;
import com.xadopu4.bankclient.repository.Repository;
import com.xadopu4.bankclient.ui.adapter.PaymentRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class PaymentListFragment extends Fragment {

    private static final String TAG = "PaymentListFragment";


    private Repository repository;

    private FloatingActionButton fab;
    private FloatingActionButton fabRefresh;


    private RecyclerView paymentListRecyclerView;
    private PaymentRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_payment_list, container, false);

        repository = Repository.getInstance();

        adapter = new PaymentRecyclerViewAdapter(new ArrayList<>());

        findViews(view);
        getData();


        fab.show();
        fabRefresh.show();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.hide();
                fabRefresh.hide();
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(PaymentListFragmentDirections.actionPaymentListFragmentToAddPayment());
            }
        });


        fabRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });


        adapter.setClickListener(new PaymentRecyclerViewAdapter.OnClickListener() {
            @Override
            public void OnClick(int position, View view) {
                PaymentListFragmentDirections.ActionPaymentListFragmentToPaymentInfoFragment action = PaymentListFragmentDirections.actionPaymentListFragmentToPaymentInfoFragment(position);
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(action);
            }
        });

        paymentListRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        paymentListRecyclerView.setHasFixedSize(true);
        paymentListRecyclerView.setAdapter(adapter);


        return view;
    }

    private void findViews(View view) {
        paymentListRecyclerView = view.findViewById(R.id.payment_list_recyclerView);
        fab = view.findViewById(R.id.fab);
        fabRefresh = view.findViewById(R.id.fab_refresh);
    }

    private void getData() {
        updateData();

    }

    private void updateData() {
        repository.getAllPayments().observe(requireActivity(), new Observer<List<Payment>>() {
            @Override
            public void onChanged(List<Payment> payments) {
                adapter.setPaymentList(payments);
            }
        });

        repository.getAllCards().observe(requireActivity(), new Observer<List<Card>>() {
            @Override
            public void onChanged(List<Card> cards) {
                StringBuilder builder = new StringBuilder();
                for (Card card : cards) {
                    builder.append("Card: ").append(card.getCardNumber()).append(" Balance: ").append(card.getBalance().toString()).append("\n");
                }
                Toast.makeText(requireActivity(), builder.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}