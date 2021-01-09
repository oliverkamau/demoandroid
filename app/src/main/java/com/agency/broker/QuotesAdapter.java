package com.agency.broker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.ViewHolder>{

    Context context;
    List<QuotesBean> quotesBeanList;
    private final OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(QuotesBean item);
        }

    public QuotesAdapter(Context context, List<QuotesBean> quotesBeanList, OnItemClickListener listener) {
        this.context = context;
        this.quotesBeanList = quotesBeanList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.quote_items,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesAdapter.ViewHolder holder, int position) {
        if( quotesBeanList!=null && quotesBeanList.size()>0){
            holder.bind(quotesBeanList.get(position), listener);
            QuotesBean quotesBean=quotesBeanList.get(position);
            holder.prod.setText(quotesBean.getProduct());
            holder.quote.setText(quotesBean.getQuoteNo());
           // holder.status.setText(quotesBean.getStatus());

        }
        else{
            return;
        }
    }

    @Override
    public int getItemCount() {
        return quotesBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView quote,prod,status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prod=itemView.findViewById(R.id.qprod);
            quote=itemView.findViewById(R.id.quoNo);
          //  status=itemView.findViewById(R.id.qstatus);

        }
        public void bind(final QuotesBean item, final QuotesAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
