package com.agency.broker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.List;


public class SelfQuoteAdapter extends RecyclerView.Adapter<SelfQuoteAdapter.ViewHolder>{

    int layout=0;
    GridLayout gridLayout;
    Context context;
    List<SelfQuoteBean> selfQuoteBeans;
    private final OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(SelfQuoteBean item);
    }

    public SelfQuoteAdapter(Context context, List<SelfQuoteBean> selfQuoteBeans, OnItemClickListener listener) {
        this.context = context;
        this.selfQuoteBeans = selfQuoteBeans;
        this.listener = listener;
    }
    @NonNull
    @Override
    public SelfQuoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.self_quote,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SelfQuoteAdapter.ViewHolder holder, int position) {
        if( selfQuoteBeans!=null && selfQuoteBeans.size()>0){

                holder.bind(selfQuoteBeans.get(position), listener);
                SelfQuoteBean selfQuoteBean = selfQuoteBeans.get(position);
                holder.mypriv.setText("PRIVATE");
                holder.insurance.setText(selfQuoteBean.getName());
                holder.underwriter.setText("Sum Insured");
                holder.sum.setText(selfQuoteBean.getSumInsured());
                holder.cover.setText("Cover");
                holder.coverval.setText(selfQuoteBean.getCover());
                holder.myprem.setText("Premium");
                holder.premium.setText(selfQuoteBean.getPremium());

        }
        else{
            return;
        }
    }

    @Override
    public int getItemCount() {
      return selfQuoteBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView insurance,sum,premium,mypriv,underwriter,cover,coverval,myprem;
           Button btn;
           MaterialButton btn2,btn3;
          MaterialCardView c1,c2,c3,c4;
        LinearLayout iconlinear,replacer,parentlinear,linearLayout,motherlayout,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
        TextView t,t0,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            insurance=itemView.findViewById(R.id.myunderwriter);
            sum=itemView.findViewById(R.id.mysumval);
            premium=itemView.findViewById(R.id.mypremval);
            mypriv=itemView.findViewById(R.id.mtprivate);
            underwriter=itemView.findViewById(R.id.mysuminsured);
            cover=itemView.findViewById(R.id.cov);
            coverval=itemView.findViewById(R.id.mycoverval);
            myprem=itemView.findViewById(R.id.mypremium);
            btn=itemView.findViewById(R.id.moreitems);
            motherlayout=itemView.findViewById(R.id.mymotherGrid);
            linearLayout=itemView.findViewById(R.id.mynewLinear);
            linearLayout.setPadding(10,10,10,10);
            motherlayout.setPadding(10,10,10,10);
            parentlinear=itemView.findViewById(R.id.parentlinear);
            iconlinear=itemView.findViewById(R.id.iconlinear);
            btn3=new MaterialButton(context);
            gridLayout=new GridLayout(context);
            t=new TextView(context);
            t0=new TextView(context);
            btn2=new MaterialButton(context);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    // Toast.makeText(context,"Clicked",Toast.LENGTH_LONG);

/*
                    GridLayout.LayoutParams doubleLayoutParams2 = new GridLayout.LayoutParams();
                    doubleLayoutParams.rowSpec = GridLayout.spec(1,1,1f);
                    doubleLayoutParams.columnSpec = GridLayout.spec(1,0,1f);

 */
                    //  GridLayout.spec(rowNumber,rowSpan);
                    ((ViewGroup)btn.getParent()).removeView(btn);
                    premium.setPadding(0,10,0,0);

                    //btn3=new MaterialButton(context);
                    btn3.setIcon(context.getDrawable(R.drawable.uparrow));
                    btn3.setBackgroundColor(context.getColor(R.color.white));
                    btn3.setIconTint(ColorStateList.valueOf(context.getColor(R.color.blowfish)));
                    btn3.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.white)));
                    btn3.setPadding(80,0,0,0);
                    btn3.setStateListAnimator(null);
                    iconlinear.addView(btn3);

                   // gridLayout=new GridLayout(context);



                    gridLayout.setLayoutParams(new ViewGroup.LayoutParams(
                           ViewGroup.LayoutParams.MATCH_PARENT,
                          ViewGroup.LayoutParams.MATCH_PARENT));

                    gridLayout.setColumnCount(2);
                    gridLayout.setRowCount(3);
                    gridLayout.setPadding(10,10,10,10);
                   gridLayout.setAlignmentMode(GridLayout.ALIGN_MARGINS);

                    c1=new MaterialCardView(context);
                    c1.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));

                    c1.setCardElevation(10);
                   c1.setPadding(30,30,30,30);
                    c1.setContentPadding(10,10,10,10);
                    c1.setRadius(50);
                    c1.setFocusable(true);
                    c1.setClickable(true);
                   // c1.setCardBackgroundColor(context.getColor(R.color.azure));
                    c1.setStrokeColor(context.getColor(R.color.blowfish));
                    c1.setStrokeWidth(5);


                    c2=new MaterialCardView(context);
                    c2.setLayoutParams(new MaterialCardView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));

                    c2.setCardElevation(10);
                    c2.setPadding(30,30,30,30);
                    c2.setContentPadding(10,10,10,10);
                    c2.setRadius(50);
                    c2.setFocusable(true);
                    c2.setClickable(true);
                 //   c2.setCardBackgroundColor(context.getColor(R.color.azure));
                    c2.setStrokeColor(context.getColor(R.color.blowfish));
                    c2.setStrokeWidth(5);

                    c3=new MaterialCardView(context);
                    c3.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    c3.setPadding(30,30,30,30);
                    c3.setContentPadding(10,10,10,10);
                    c3.setRadius(50);
                    c3.setFocusable(true);
                    c3.setClickable(true);
                  //  c3.setCardBackgroundColor(context.getColor(R.color.azure));
                    c3.setStrokeColor(context.getColor(R.color.blowfish));
                    c3.setStrokeWidth(5);


                    c4=new MaterialCardView(context);
                    c4.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    c4.setCardElevation(10);
                    c4.setPadding(30,30,30,30);
                    c4.setContentPadding(10,10,10,10);
                    c4.setRadius(50);
                    c4.setFocusable(true);
                    c4.setClickable(true);
                  //  c4.setCardBackgroundColor(context.getColor(R.color.azure));
                    c4.setStrokeColor(context.getColor(R.color.blowfish));
                    c4.setStrokeWidth(5);

                    replacer=new LinearLayout(context);

                    replacer.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    replacer.setPadding(10,10,10,10);

                    l1=new LinearLayout(context);

                    l1.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    l1.setPadding(10,10,10,10);
                    l1.setOrientation(LinearLayout.VERTICAL);

                    l2=new LinearLayout(context);
                    l2.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    l2.setPadding(10,10,10,10);
                    l2.setOrientation(LinearLayout.VERTICAL);

                    l3=new LinearLayout(context);
                    l3.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    l3.setPadding(10,10,10,10);
                    l3.setOrientation(LinearLayout.VERTICAL);

                    l4=new LinearLayout(context);
                    l4.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    l4.setPadding(10,10,10,10);
                    l4.setOrientation(LinearLayout.VERTICAL);

                    l5=new LinearLayout(context);
                    l5.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    l5.setPadding(20,20,20,20);
                    l5.setOrientation(LinearLayout.VERTICAL);

                    l6=new LinearLayout(context);
                    l6.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    l6.setPadding(20,20,20,20);
                    l6.setOrientation(LinearLayout.VERTICAL);

                    l7=new LinearLayout(context);
                    l7.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    l7.setPadding(20,20,20,20);
                    l7.setOrientation(LinearLayout.VERTICAL);

                    l8=new LinearLayout(context);
                    l8.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    l8.setPadding(20,20,20,20);
                    l8.setOrientation(LinearLayout.VERTICAL);

                    l9=new LinearLayout(context);
                    l9.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    l9.setPadding(20,20,20,20);

                    l10=new LinearLayout(context);
                    l10.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    l10.setPadding(20,20,20,20);

                    l11=new LinearLayout(context);
                    l11.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    l11.setPadding(20,20,20,20);


                    btn2.setText("Buy");
                    btn2.setBackgroundColor(context.getColor(R.color.teal));
                    btn2.setCornerRadius(20);
                    btn2.setPadding(100,10,100,10);
                    btn2.setStrokeColor(ColorStateList.valueOf(context.getColor(R.color.black)));
                    btn2.setStrokeWidth(2);
                    btn2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    btn2.setClickable(true);


                  //  t=new TextView(context);
                    t.setText("Add Ons");
                    t.setPadding(10,10,10,10);

                   // t0=new TextView(context);
                    t0.setText("Consider the following Add Ons at an extra fee to help insure you better");
                    t0.setPadding(10,10,10,10);

                    t1=new TextView(context);
                    t1.setText("Windscreen");
                    t1.setPadding(10,10,10,10);
                    t1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    t9=new TextView(context);
                    t9.setText("Total");
                    t9.setPadding(10,10,10,10);
                    t9.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    t9.setTextSize(16);
                    t9.setTextColor(context.getColor(R.color.blowfish));
                    t9.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));

                    t10=new TextView(context);
                    t10.setText(premium.getText());
                    t10.setPadding(10,10,10,10);
                    t10.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                    t10.setTextSize(16);
                    t10.setTextColor(context.getColor(R.color.blowfish));


                    t2=new TextView(context);
                    t2.setText("200 Ksh");
                    t2.setPadding(10,10,10,10);
                    t2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


                    t3=new TextView(context);
                    t3.setText("Radio & Cassette");
                    t3.setPadding(10,10,10,10);
                    t3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    t4=new TextView(context);
                    t4.setText("300 Ksh");
                    t4.setPadding(10,10,10,10);
                    t4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    t5=new TextView(context);
                    t5.setText("AA membership");
                    t5.setPadding(10,10,10,10);
                    t5.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


                    t6=new TextView(context);
                    t6.setText("450 Ksh");
                    t6.setPadding(10,10,10,10);
                    t6.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


                    t7=new TextView(context);
                    t7.setText("Fire & Theft");
                    t7.setPadding(10,10,10,10);
                    t7.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    t8=new TextView(context);
                    t8.setText("500 Ksh");
                    t8.setPadding(10,10,10,10);
                    t8.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    l1.addView(t1);
                    l1.addView(t2);

                    l2.addView(t3);
                    l2.addView(t4);

                    l3.addView(t5);
                    l3.addView(t6);

                    l4.addView(t7);
                    l4.addView(t8);

                    c1.addView(l1);
                    c2.addView(l2);
                    c3.addView(l3);
                    c4.addView(l4);

                    l5.addView(c1);
                    l6.addView(c2);
                    l7.addView(c3);
                    l8.addView(c4);

                    l9.addView(t9);
                    l10.addView(t10);
                   // l11.addView(btn2);

                    linearLayout.addView(t);
                    linearLayout.addView(t0);
                    gridLayout.addView(l5, new GridLayout.LayoutParams(
                            GridLayout.spec(0,1f),
                            GridLayout.spec(0, 1f)));

                    gridLayout.addView(l6,new GridLayout.LayoutParams(
                            GridLayout.spec(0, 1f),
                            GridLayout.spec(1, 1f)));

                    gridLayout.addView(l7,new GridLayout.LayoutParams(
                            GridLayout.spec(1, 1f),
                            GridLayout.spec(0, 1f)));

                    gridLayout.addView(l8,new GridLayout.LayoutParams(
                            GridLayout.spec(1, 1f),
                            GridLayout.spec(1, 1f)));

                    gridLayout.addView(l9,new GridLayout.LayoutParams(
                            GridLayout.spec(2, 1f),
                            GridLayout.spec(0, 1f)));

                    gridLayout.addView(l10,new GridLayout.LayoutParams(
                            GridLayout.spec(2, 1f),
                            GridLayout.spec(1, 1f)));

                    motherlayout.addView(gridLayout);
                    parentlinear.addView(btn2);
                    //linearLayout.addView(btn2);

                    /*
                    l1=new LinearLayout(context);

                    l1.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    l1.setPadding(2,2,2,2);
                    l1.setOrientation(LinearLayout.VERTICAL);

                    l2=new LinearLayout(context);
                    l2.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    l2.setPadding(2,2,2,2);
                    l2.setOrientation(LinearLayout.VERTICAL);

                    l3=new LinearLayout(context);
                    l3.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    l3.setPadding(2,2,2,2);
                    l3.setOrientation(LinearLayout.VERTICAL);

                    l4=new LinearLayout(context);
                    l4.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    l4.setPadding(2,2,2,2);
                    l4.setOrientation(LinearLayout.VERTICAL);

                    t1=new TextView(context);
                    t1.setText("Windscreen");
                    t1.setPadding(2,2,2,2);
                    t2=new TextView(context);
                    t2.setText("200 Ksh");
                    t2.setPadding(2,2,2,2);
                    t3=new TextView(context);
                    t3.setText("Radio");
                    t3.setPadding(2,2,2,2);
                    t4=new TextView(context);
                    t4.setText("300 Ksh");
                    t4.setPadding(2,2,2,2);

                    t5=new TextView(context);
                    t5.setText("AA membership");
                    t5.setPadding(2,2,2,2);
                    t6=new TextView(context);
                    t6.setText("450 Ksh");
                    t6.setPadding(2,2,2,2);

                    t7=new TextView(context);
                    t7.setText("Fire & Theft");
                    t7.setPadding(2,2,2,2);

                    t8=new TextView(context);
                    t8.setText("500 Ksh");
                    t8.setPadding(2,2,2,2);

                    c1=new MaterialCardView(context);
                    c1.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    c1.setCardElevation(10);
                    c1.setRadius(10);
                    c1.setFocusable(true);
                    c1.setClickable(true);

                    c2=new MaterialCardView(context);
                    c2.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    c2.setCardElevation(10);
                    c2.setRadius(10);
                    c2.setFocusable(true);
                    c2.setClickable(true);
                    c3=new MaterialCardView(context);
                    c3.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    c3.setCardElevation(10);
                    c3.setRadius(10);
                    c3.setFocusable(true);
                    c3.setClickable(true);
                    c4=new MaterialCardView(context);
                    c4.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    c4.setCardElevation(10);
                    c4.setRadius(10);
                    c4.setFocusable(true);
                    c4.setClickable(true);

                    l1.addView(t1);
                    l1.addView(t2);

                    l2.addView(t3);
                    l2.addView(t4);

                    l3.addView(t5);
                    l3.addView(t6);

                    l4.addView(t7);
                    l4.addView(t8);

                    c1.addView(l1);
                    c2.addView(l2);
                    c3.addView(l3);
                    c4.addView(l4);

                    gridLayout.addView(c1);
                    gridLayout.addView(c2);
                    gridLayout.addView(c3);
                    gridLayout.addView(c4);

                     */
                }
            });
            btn3.setOnClickListener(v1 -> {
                ((ViewGroup)gridLayout.getParent()).removeView(gridLayout);
                ((ViewGroup)t.getParent()).removeView(t);
                ((ViewGroup)t0.getParent()).removeView(t0);
                ((ViewGroup)btn2.getParent()).removeView(btn2);
                ((ViewGroup)btn3.getParent()).removeView(btn3);
                 btn=getButton();
                 iconlinear.addView(btn);

            });
        }

        private Button getButton() {
          MaterialButton btn3=new MaterialButton(context);
            btn3.setIcon(context.getDrawable(R.drawable.more));
            btn3.setBackgroundColor(context.getColor(R.color.white));
            btn3.setIconTint(ColorStateList.valueOf(context.getColor(R.color.blowfish)));
            btn3.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.white)));
            btn3.setPadding(100,0,0,0);
            btn3.setStateListAnimator(null);
            return btn3;
        }

        public void bind(SelfQuoteBean item, OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    layout=1;
                }
            });
        }
    }
}
