package com.agency.broker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PolicyAdapter extends RecyclerView.Adapter<PolicyAdapter.ViewHolder> {

    Context context;
    List<PolicyBean> policyBeanList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(PolicyBean item);
    }

    public PolicyAdapter(Context context, List<PolicyBean> policyBeanList, OnItemClickListener listener) {
        this.context = context;
        this.policyBeanList = policyBeanList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      if( policyBeanList!=null && policyBeanList.size()>0){
          holder.bind(policyBeanList.get(position), listener);
       PolicyBean policyBean=policyBeanList.get(position);
       holder.prod.setText(policyBean.getProduct());
       holder.polNo.setText(policyBean.getPolicyNo());
     //  holder.status.setText(policyBean.getStatus());

      }
      else{
          return;
      }
    }

    @Override
    public int getItemCount() {

        return policyBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prod,polNo,status;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            prod=itemView.findViewById(R.id.prod);
            polNo=itemView.findViewById(R.id.polNo);
          //  status=itemView.findViewById(R.id.status);
        }

        public void bind(final PolicyBean item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

}
