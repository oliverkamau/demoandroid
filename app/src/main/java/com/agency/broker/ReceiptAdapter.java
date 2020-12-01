package com.agency.broker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ViewHolder>{

    Context context;
    List<ReceiptBean> receiptBeans;

    public ReceiptAdapter(Context context, List<ReceiptBean> receiptBeans) {
        this.context = context;
        this.receiptBeans = receiptBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.receipt_items,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if( receiptBeans!=null && receiptBeans.size()>0){
            ReceiptBean receiptBean=receiptBeans.get(position);
            holder.receipt.setText(receiptBean.getReceiptNo());
            holder.ref.setText(receiptBean.getRefNo());
            holder.ammount.setText(receiptBean.getAmmount());
            holder.date.setText(receiptBean.getDatePaid());
            holder.dc.setText(receiptBean.getDebitCredit());

        }
        else{
            holder.ammount.setText("No Receipts");

        }
    }

    @Override
    public int getItemCount() {
        return receiptBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView receipt,ref,ammount,date,dc;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            receipt=itemView.findViewById(R.id.receipt_no);
            ref=itemView.findViewById(R.id.reference_no);
            ammount=itemView.findViewById(R.id.ammount_val);
            date=itemView.findViewById(R.id.date_val);
            dc=itemView.findViewById(R.id.dc_value);
        }
    }
    }

