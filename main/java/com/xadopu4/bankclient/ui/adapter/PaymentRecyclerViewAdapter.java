package com.xadopu4.bankclient.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xadopu4.bankclient.entities.Payment;
import com.xadopu4.bankclient.R;


import java.util.ArrayList;
import java.util.List;


public class PaymentRecyclerViewAdapter extends RecyclerView.Adapter<PaymentRecyclerViewAdapter.PaymentViewHolder> {

    private List<Payment> paymentList;
    private OnClickListener clickListener;

    public PaymentRecyclerViewAdapter(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_payment, parent, false);
        return new PaymentViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        Payment payment = paymentList.get(position);

        holder.paymentAmountKop.setText(payment.getAmountKop().toString());
        holder.paymentOrderID.setText(payment.getOrderId().toString());

        String status;
        if (payment.getStatus() == 0){
            status = "Paid";
            holder.paymentStatusImageView.setImageResource(R.drawable.ic_baseline_check_circle_24);
        }
        else if (payment.getStatus() == 1) {
            status = "Refund";
            holder.paymentStatusImageView.setImageResource(R.drawable.ic_baseline_cancel_24);

        } else {
            status = "Error";
            holder.paymentStatusImageView.setImageResource(R.drawable.ic_baseline_warning_24);

        }

        holder.paymentStatus.setText(status);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.OnClick(position, v);
            }
        });

        holder.paymentCardNumber.setText(payment.getCardNumber() + " " + payment.getCardholderName());
    }


    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
        notifyDataSetChanged();
    }

    public void setClickListener(OnClickListener listener){
        clickListener = listener;
    }

    public static class PaymentViewHolder extends RecyclerView.ViewHolder {

        private TextView paymentAmountKop;
        private TextView paymentOrderID;
        private TextView paymentStatus;
        private TextView paymentCardNumber;

        private ImageView paymentStatusImageView;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);

            paymentAmountKop = itemView.findViewById(R.id.item_payment_amountKop_textView);
            paymentOrderID = itemView.findViewById(R.id.item_payment_id_textView);
            paymentStatus = itemView.findViewById(R.id.item_payment_status_textView);
            paymentStatusImageView = itemView.findViewById(R.id.item_payment_status_imageView);
            paymentCardNumber = itemView.findViewById(R.id.item_payment_card_number_textView);
        }
    }

    public interface OnClickListener{
        void OnClick(int position, View view);
    }
}
