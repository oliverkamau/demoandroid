package com.agency.broker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClaimAdapter extends RecyclerView.Adapter<ClaimAdapter.ViewHolder>{

    Context context;
    List<ClaimsBean> claimsBeans;

    public ClaimAdapter(Context context, List<ClaimsBean> claimsBeans) {
        this.context = context;
        this.claimsBeans = claimsBeans;
    }

    @NonNull
    @Override
    public ClaimAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.claim_items,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClaimAdapter.ViewHolder holder, int position) {

        if( claimsBeans!=null && claimsBeans.size()>0){
            ClaimsBean claimsBean=claimsBeans.get(position);
            holder.claim.setText(claimsBean.getClaimNo());
            holder.loss.setText(claimsBean.getLossDate());
            holder.date.setText(claimsBean.getClaimDate());
            holder.peril.setText(claimsBean.getPeril());
            holder.status.setText(claimsBean.getStatus());

        }
        else{
            holder.date.setText(R.string.no_claim);
        }

    }

    @Override
    public int getItemCount() {
        return claimsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView claim,loss,date,peril,status;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            claim=itemView.findViewById(R.id.clmno);
            loss=itemView.findViewById(R.id.loss_date);
            date=itemView.findViewById(R.id.claim_date);
            peril=itemView.findViewById(R.id.peril_val);
            status=itemView.findViewById(R.id.status_val);

        }
    }
}
