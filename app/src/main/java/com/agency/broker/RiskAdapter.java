package com.agency.broker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RiskAdapter extends RecyclerView.Adapter<RiskAdapter.ViewHolder>{

    Context context;
    List<RiskBean> riskBeanList;

    public RiskAdapter(Context context, List<RiskBean> riskBeanList) {
        this.context = context;
        this.riskBeanList = riskBeanList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.risk_items,parent,false);

        return new ViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull RiskAdapter.ViewHolder holder, int position) {
        if( riskBeanList!=null && riskBeanList.size()>0){
            RiskBean riskBean=riskBeanList.get(position);
            holder.riskid.setText(riskBean.getRiskDesc());
            holder.desc.setText(riskBean.getDescription());
            holder.prem.setText(riskBean.getPremium());
            holder.sum.setText(riskBean.getSumInsured());

        }
        else{
            holder.sum.setText("No Risks");
        }
    }

    @Override
    public int getItemCount() {
        return riskBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prem,riskid,desc,sum;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            sum=itemView.findViewById(R.id.sum_insured);
            riskid=itemView.findViewById(R.id.risk_id);
            desc=itemView.findViewById(R.id.desc_value);
            prem=itemView.findViewById(R.id.prem_value);
        }
    }
}
