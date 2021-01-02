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

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;


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
                String sum=selfQuoteBean.getSumInsured().substring(0, selfQuoteBean.getSumInsured().indexOf('.'));
                Integer s=Integer.parseInt(sum);
                holder.sum.setText(String.format("%s.00 Ksh", NumberFormat.getNumberInstance(Locale.US).format(s)));
                holder.cover.setText("Cover");
                holder.coverval.setText(selfQuoteBean.getCover());
                holder.myprem.setText("Premium");
                String prem=selfQuoteBean.getPremium().substring(0, selfQuoteBean.getPremium().indexOf('.'));
                Integer p=Integer.parseInt(prem);
                holder.premium.setText(String.format("%s.00 Ksh", NumberFormat.getNumberInstance(Locale.US).format(p)));

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
            c1=new MaterialCardView(context);
            c2=new MaterialCardView(context);
            c3=new MaterialCardView(context);
            c4=new MaterialCardView(context);
            t2=new TextView(context);


            c1.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    String addOn=t2.getText().toString();
                    addOn=addOn.substring(0, addOn.indexOf(' '));
                    String premium=t10.getText().toString();

                    premium=premium.substring(0, premium.indexOf('.'));
                   premium=premium.replaceAll(",","");
                    BigDecimal prem=BigDecimal.valueOf(Long.parseLong(premium));
                    BigDecimal a=BigDecimal.valueOf(Long.parseLong(addOn));
                    prem=prem.add(a);
                    if(t2.getText().toString().contains("-")){

                        t10.setText(String.format("%s.00 Ksh", NumberFormat.getNumberInstance(Locale.US).format(prem)));
                        t2.setText(String.format("200 Ksh"));
                        c1.setCardBackgroundColor(context.getColor(R.color.white));
                        t2.setTextColor(context.getColor(R.color.silver));
                    }
                    else{
                        t10.setText(String.format("%s.00 Ksh", NumberFormat.getNumberInstance(Locale.US).format(prem)));
                        t2.setText(String.format("-200 Ksh"));
                        c1.setCardBackgroundColor(context.getColor(R.color.azure));
                        t2.setTextColor(context.getColor(R.color.honeyDew));
                    }


                }
            });
            c4.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    String addOn=t8.getText().toString();
                    addOn=addOn.substring(0, addOn.indexOf(' '));
                    String premium=t10.getText().toString();
                    premium=premium.substring(0, premium.indexOf('.'));
                    premium=premium.replaceAll(",","");

                    BigDecimal prem=BigDecimal.valueOf(Long.parseLong(premium));
                    BigDecimal a=BigDecimal.valueOf(Long.parseLong(addOn));
                    prem=prem.add(a);
                    if(t8.getText().toString().contains("-")){
                        t10.setText(String.format("%s.00 Ksh", NumberFormat.getNumberInstance(Locale.US).format(prem)));
                        t8.setText(String.format("500 Ksh"));
                        c4.setCardBackgroundColor(context.getColor(R.color.white));
                        t8.setTextColor(context.getColor(R.color.silver));
                    }
                    else{
                        t10.setText(String.format("%s.00 Ksh", NumberFormat.getNumberInstance(Locale.US).format(prem)));
                        t8.setText(String.format("-500 Ksh"));
                        c4.setCardBackgroundColor(context.getColor(R.color.azure));
                        t8.setTextColor(context.getColor(R.color.honeyDew));
                    }


                }
            });
            c2.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    String addOn=t4.getText().toString();
                    addOn=addOn.substring(0, addOn.indexOf(' '));
                    String premium=t10.getText().toString();
                    premium=premium.substring(0, premium.indexOf('.'));
                    premium=premium.replaceAll(",","");
                    BigDecimal prem=BigDecimal.valueOf(Long.parseLong(premium));
                    BigDecimal a=BigDecimal.valueOf(Long.parseLong(addOn));
                    prem=prem.add(a);
                    if(t4.getText().toString().contains("-")){
                        t10.setText(String.format("%s.00 Ksh", NumberFormat.getNumberInstance(Locale.US).format(prem)));
                        t4.setText(String.format("300 Ksh"));
                        c2.setCardBackgroundColor(context.getColor(R.color.white));
                        t4.setTextColor(context.getColor(R.color.silver));
                    }
                    else{
                        t10.setText(String.format("%s.00 Ksh", NumberFormat.getNumberInstance(Locale.US).format(prem)));
                        t4.setText(String.format("-300 Ksh"));
                        c2.setCardBackgroundColor(context.getColor(R.color.azure));
                        t4.setTextColor(context.getColor(R.color.honeyDew));
                    }


                }
            });
            c3.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    String addOn=t6.getText().toString();
                    addOn=addOn.substring(0, addOn.indexOf(' '));
                    String premium=t10.getText().toString();
                    premium=premium.substring(0, premium.indexOf('.'));
                    premium=premium.replaceAll(",","");

                    BigDecimal prem=BigDecimal.valueOf(Long.parseLong(premium));
                    BigDecimal a=BigDecimal.valueOf(Long.parseLong(addOn));
                    prem=prem.add(a);
                    if(t6.getText().toString().contains("-")){
                        t10.setText(String.format("%s.00 Ksh", NumberFormat.getNumberInstance(Locale.US).format(prem)));
                        t6.setText(String.format("450 Ksh"));
                        c3.setCardBackgroundColor(context.getColor(R.color.white));
                        t6.setTextColor(context.getColor(R.color.silver));
                    }
                    else{
                        t10.setText(String.format("%s.00 Ksh", NumberFormat.getNumberInstance(Locale.US).format(prem)));
                        t6.setText(String.format("-450 Ksh"));
                        c3.setCardBackgroundColor(context.getColor(R.color.azure));
                        t6.setTextColor(context.getColor(R.color.honeyDew));
                    }


                }
            });

            btn.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    getButtonClick();


                }
            });
            btn3.setOnClickListener(v1 -> {
                gridLayout.removeAllViews();
                ((ViewGroup)gridLayout.getParent()).removeView(gridLayout);
                // gridLayout.setVisibility(View.GONE);
                t.setVisibility(View.GONE);
                t0.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
               // ((ViewGroup)btn2.getParent()).removeView(btn2);

                btn3.setVisibility(View.GONE);
                btn.setVisibility(View.VISIBLE);
               // ((ViewGroup)gridLayout.getParent()).removeView(gridLayout);
               // ((ViewGroup)t.getParent()).removeView(t);
               // ((ViewGroup)t0.getParent()).removeView(t0);
                //((ViewGroup)btn2.getParent()).removeView(btn2);
                //((ViewGroup)btn3.getParent()).removeView(btn3);
                // btn=getButton();

               // btn.setOnClickListener(new View.OnClickListener() {
                //    @Override public void onClick(View v) {
                   //     getButtonClick();


                  //  }
               // });
               //  iconlinear.addView(btn);

            });
        }

        private void getButtonClick() {

            // Toast.makeText(context,"Clicked",Toast.LENGTH_LONG);

/*
                    GridLayout.LayoutParams doubleLayoutParams2 = new GridLayout.LayoutParams();
                    doubleLayoutParams.rowSpec = GridLayout.spec(1,1,1f);
                    doubleLayoutParams.columnSpec = GridLayout.spec(1,0,1f);

 */
            //  GridLayout.spec(rowNumber,rowSpan);
           /* if(gridLayout.getChildCount()!=0){
                ((ViewGroup)gridLayout.getParent()).removeView(gridLayout);
                ((ViewGroup)btn2.getParent()).removeView(btn2);
               /* ((ViewGroup)t1.getParent()).removeView(t1);
                ((ViewGroup)t2.getParent()).removeView(t2);
                ((ViewGroup)l1.getParent()).removeView(l1);
                ((ViewGroup)c1.getParent()).removeView(c1);
                ((ViewGroup)t3.getParent()).removeView(t3);
                ((ViewGroup)t4.getParent()).removeView(t4);
                ((ViewGroup)l2.getParent()).removeView(l2);
                ((ViewGroup)c2.getParent()).removeView(c2);
                ((ViewGroup)t5.getParent()).removeView(t5);
                ((ViewGroup)t6.getParent()).removeView(t6);
                ((ViewGroup)l3.getParent()).removeView(l3);
                ((ViewGroup)c3.getParent()).removeView(c3);
                ((ViewGroup)t7.getParent()).removeView(t7);
                ((ViewGroup)t8.getParent()).removeView(t8);
                ((ViewGroup)l4.getParent()).removeView(l4);
                ((ViewGroup)c4.getParent()).removeView(c4);



            }

            */


            btn.setVisibility(View.GONE);
            generateButton3();

             gridLayout.removeAllViews();
            premium.setPadding(0,10,0,0);
            //btn3=new MaterialButton(context);
            if(btn3.getParent() != null) {
               ((ViewGroup)btn3.getParent()).removeView(btn3);
                generateButton3();
                btn3.setVisibility(View.VISIBLE);
            }
            iconlinear.addView(btn3);

            // gridLayout=new GridLayout(context);



            generateGridLayout();

            generateCard1();

            generateCard2();

            generateCard3();

            generateCard4();


            replacer=generateReplacer();



            l1=generateL1();



            l2=generateL2();


            l3=generateL3();

            l4=generateL4();


            l5=generateL5();


            l6=generateL6();


            l7=generateL7();


            l8=generateL8();


            l9=generateL9();


            l10=generateL9();

            l11=generateL9();

            generateButton2();

            //  t=new TextView(context);
            generateText();

            // t0=new TextView(context);
            generateT0();

            t1=generateT1();


            t9=generateT9();

            t10=generateT10();

            generateT2();

            t3=generateT3();

            t4=generateT4();

            t5=generateT5();

            t6=generateT6();

            t7=generateT7();

            t8=generateT8();

            if(t1.getParent() != null) {
                ((ViewGroup)t1.getParent()).removeView(t1);
                generateT1();
            }
            addT1();
            if(t2.getParent() != null) {
                ((ViewGroup)t2.getParent()).removeView(t2);
                generateT2();
            }
            addT2();

            if(t3.getParent() != null) {
                ((ViewGroup)t3.getParent()).removeView(t3);
                generateT3();
            }
            addT3();

            if(t4.getParent() != null) {
                ((ViewGroup)t4.getParent()).removeView(t4);
                generateT4();
            }
            addT4();

            if(t5.getParent() != null) {
                ((ViewGroup)t5.getParent()).removeView(t5);
                generateT5();
            }
            addT5();

            if(t6.getParent() != null) {
                ((ViewGroup)t6.getParent()).removeView(t6);
                generateT6();
            }
            addT6();
            if(t7.getParent() != null) {
                ((ViewGroup)t7.getParent()).removeView(t7);
                generateT7();
            }
            addT7();

            if(t8.getParent() != null) {
                ((ViewGroup)t8.getParent()).removeView(t8);
                generateT8();
            }
            addT8();

            if(l1.getParent() != null) {
                ((ViewGroup)l1.getParent()).removeView(l1);
                generateL1();
            }
            addL1();

            if(l2.getParent() != null) {
                ((ViewGroup)l2.getParent()).removeView(l2);
                generateL2();
            }
            addL2();

            if(l3.getParent() != null) {
                ((ViewGroup)l3.getParent()).removeView(l3);
                generateL3();
            }
            addL3();

            if(l4.getParent() != null) {
                ((ViewGroup)l4.getParent()).removeView(l4);
                generateL4();
            }
            addL4();

            if(c1.getParent() != null) {
                ((ViewGroup)c1.getParent()).removeView(c1);
                generateCard1();
            }
            addC1();
            if(c2.getParent() != null) {
                ((ViewGroup)c2.getParent()).removeView(c2);
                generateCard2();
            }
            addC2();
            if(c3.getParent() != null) {
                ((ViewGroup)c3.getParent()).removeView(c3);
                generateCard3();
            }
            addC3();
            if(c4.getParent() != null) {
                ((ViewGroup)c4.getParent()).removeView(c4);
                generateCard4();
            }
            addC4();

            if(t9.getParent() != null) {
                ((ViewGroup)t9.getParent()).removeView(t9);
                generateT9();
            }
            addT9();

            if(t10.getParent() != null) {
                ((ViewGroup)t10.getParent()).removeView(t10);
                generateT10();
            }
            addT10();
            // l11.addView(btn2);
            if(t.getParent() != null) {
                ((ViewGroup)t.getParent()).removeView(t);
                generateText();
            }
            addT();
            if(t0.getParent() != null) {
                ((ViewGroup)t0.getParent()).removeView(t0);
                generateT0();
            }
            addT0();

            if(l5.getParent() != null) {
                ((ViewGroup)l5.getParent()).removeView(l5);
                generateL5();
            }
            addL5();

            if(l6.getParent() != null) {
                ((ViewGroup)l6.getParent()).removeView(l6);
                generateL6();
            }
            addL6();

            if(l7.getParent() != null) {
                ((ViewGroup)l7.getParent()).removeView(l7);
                generateL7();
            }
            addL7();

            if(l8.getParent() != null) {
                ((ViewGroup)l8.getParent()).removeView(l8);
                generateL8();
            }
            addL8();

            if(l9.getParent() != null) {
                ((ViewGroup)l9.getParent()).removeView(l9);
                generateL9();
            }
            addL9();

            if(l10.getParent() != null) {
                ((ViewGroup)l10.getParent()).removeView(l10);
                generateL9();
            }
            addL10();


           // if(gridLayout.getParent() != null) {
            //    ((ViewGroup)gridLayout.getParent()).removeView(gridLayout);
           // }
            if(gridLayout.getParent() != null) {
                ((ViewGroup)gridLayout.getParent()).removeView(gridLayout);
                generateGridLayout();
            }
            addGridLayout();

            if(btn2.getParent() != null) {
                ((ViewGroup)btn2.getParent()).removeView(btn2);
                generateButton2();
                btn2.setVisibility(View.VISIBLE);
            }
            addBtn2();

        }

        private void addBtn2() {
            parentlinear.addView(btn2);

        }

        private void addGridLayout() {
            motherlayout.addView(gridLayout);

        }

        private void addL10() {
            gridLayout.addView(l10,new GridLayout.LayoutParams(
                    GridLayout.spec(2, 1f),
                    GridLayout.spec(1, 1f)));
        }

        private void addL9() {
            gridLayout.addView(l9,new GridLayout.LayoutParams(
                    GridLayout.spec(2, 1f),
                    GridLayout.spec(0, 1f)));
        }

        private void addL8() {
            gridLayout.addView(l8,new GridLayout.LayoutParams(
                    GridLayout.spec(1, 1f),
                    GridLayout.spec(1, 1f)));

        }

        private void addL7() {
            gridLayout.addView(l7,new GridLayout.LayoutParams(
                    GridLayout.spec(1, 1f),
                    GridLayout.spec(0, 1f)));
        }

        private void addL6() {
            gridLayout.addView(l6,new GridLayout.LayoutParams(
                    GridLayout.spec(0, 1f),
                    GridLayout.spec(1, 1f)));
        }

        private void addL5() {

            gridLayout.addView(l5, new GridLayout.LayoutParams(
                    GridLayout.spec(0,1f),
                    GridLayout.spec(0, 1f)));
        }

        private void addT0() {

            linearLayout.addView(t0);
        }

        private void addT() {

            linearLayout.addView(t);
        }

        private void addT10() {
            l10.addView(t10);

        }

        private void addT9() {
            l9.addView(t9);

        }

        private void addC4() {
            l8.addView(c4);


        }

        private void addC3() {
            l7.addView(c3);
        }

        private void addC2() {
            l6.addView(c2);

        }

        private void addC1() {
            l5.addView(c1);

        }

        private void addL4() {
            c4.addView(l4);

        }

        private void addL3() {
            c3.addView(l3);

        }

        private void addL2() {
            c2.addView(l2);

        }

        private void addL1() {
            c1.addView(l1);
        }

        private void addT8() {
            l4.addView(t8);
        }

        private void addT7() {
            l4.addView(t7);
        }

        private void addT6() {
            l3.addView(t6);
        }

        private void addT5() {
            l3.addView(t5);

        }

        private void addT4() {
            l2.addView(t4);
        }

        private void addT3() {
            l2.addView(t3);
        }

        private void addT2() {
            l1.addView(t2);
        }

        private void addT1() {
            l1.addView(t1);

        }

        private TextView generateT8() {
            TextView t8=new TextView(context);
            t8.setText("500 Ksh");
            t8.setPadding(5,5,5,5);
            t8.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t8.setTextSize(11);
            return t8;
        }

        private TextView generateT7() {
            TextView t7=new TextView(context);
            t7.setText("Fire & Theft");
            t7.setPadding(5,5,5,5);
            t7.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t7.setTextSize(11);
            return t7;
        }

        private TextView generateT6() {
            TextView t6=new TextView(context);
            t6.setText("450 Ksh");
            t6.setPadding(5,5,5,5);
            t6.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t6.setTextSize(11);
            return t6;

        }

        private TextView generateT5() {
            TextView t5=new TextView(context);
            t5.setText("AA membership");
            t5.setPadding(5,5,5,5);
            t5.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t5.setTextSize(11);
            return t5;
        }

        private TextView generateT4() {
            TextView t4=new TextView(context);
            t4.setText("300 Ksh");
            t4.setPadding(5,5,5,5);
            t4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t4.setTextSize(11);
            return t4;
        }

        private TextView generateT3() {
            TextView t3=new TextView(context);
            t3.setText("Radio & Cassette");
            t3.setPadding(5,5,5,5);
            t3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t3.setTextSize(11);
            return t3;
        }

        private void generateT2() {
            t2.setText("200 Ksh");
            t2.setPadding(5,5,5,5);
            t2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t2.setTextSize(11);

        }

        private TextView generateT10() {
            TextView t10=new TextView(context);
            t10.setText(premium.getText());
            t10.setPadding(10,10,10,10);
            t10.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            t10.setTextSize(13);
            t10.setTextColor(context.getColor(R.color.blowfish));
            return t10;

        }

        private TextView generateT9() {

            TextView t9=new TextView(context);
            t9.setText("Total");
            t9.setPadding(5,5,5,5);
            t9.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t9.setTextSize(13);
            t9.setTextColor(context.getColor(R.color.blowfish));
            t9.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return t9;

        }

        private TextView generateT1() {
            TextView t1=new TextView(context);
            t1.setText("Windscreen");
            t1.setPadding(5,5,5,5);
            t1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t1.setTextSize(11);
            return t1;
        }

        private void generateT0() {
            t0.setText("Consider the following Add Ons at an extra fee to help insure you better");
            t0.setPadding(10,10,10,10);
            t0.setVisibility(View.VISIBLE);

        }

        private void generateText() {
            t.setText("Add Ons");
            t.setPadding(10,10,10,10);
            t.setVisibility(View.VISIBLE);

        }

        private void generateButton2() {
            btn2.setText("Buy");
            btn2.setBackgroundColor(context.getColor(R.color.teal));
            btn2.setCornerRadius(20);
            btn2.setPadding(100,10,100,10);
            btn2.setStrokeColor(ColorStateList.valueOf(context.getColor(R.color.black)));
            btn2.setStrokeWidth(2);
            btn2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            btn2.setClickable(true);
        }

        private LinearLayout generateL9() {
            LinearLayout l9=new LinearLayout(context);
            l9.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            l9.setPadding(20,20,20,20);
            return l9;
        }

        private LinearLayout generateL8() {
            LinearLayout l8=new LinearLayout(context);
            l8.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            l8.setPadding(20,20,20,20);
            l8.setOrientation(LinearLayout.VERTICAL);
            return l8;
        }

        private LinearLayout generateL7() {
            LinearLayout l7=new LinearLayout(context);
            l7.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            l7.setPadding(20,20,20,20);
            l7.setOrientation(LinearLayout.VERTICAL);
            return l7;
        }

        private LinearLayout generateL6() {
          LinearLayout l6=new LinearLayout(context);
            l6.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            l6.setPadding(20,20,20,20);
            l6.setOrientation(LinearLayout.VERTICAL);
            return l6;
        }

        private LinearLayout generateL5() {
            LinearLayout l5=new LinearLayout(context);
            l5.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            l5.setPadding(20,20,20,20);
            l5.setOrientation(LinearLayout.VERTICAL);
            return l5;
        }

        private LinearLayout generateReplacer() {
            LinearLayout replacer=new LinearLayout(context);
            replacer.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            replacer.setPadding(10,10,10,10);
            return replacer;
        }

        private LinearLayout generateL4() {
            LinearLayout l4=new LinearLayout(context);
            l4.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            l4.setPadding(10,10,10,10);
            l4.setOrientation(LinearLayout.VERTICAL);
            return l4;
        }

        private LinearLayout generateL3() {
            LinearLayout l3=new LinearLayout(context);
            l3.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            l3.setPadding(10,10,10,10);
            l3.setOrientation(LinearLayout.VERTICAL);
            return l3;
        }

        private LinearLayout generateL2() {

            LinearLayout l2=new LinearLayout(context);
            l2.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            l2.setPadding(10,10,10,10);
            l2.setOrientation(LinearLayout.VERTICAL);
            return l2;

        }

        private LinearLayout generateL1() {
            LinearLayout l1=new LinearLayout(context);
            l1.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            l1.setPadding(10,10,10,10);
            l1.setOrientation(LinearLayout.VERTICAL);
            return l1;
        }

        private void generateCard4() {

            c4.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
           // c4.setCardElevation(10);
            c4.setPadding(30,30,30,30);
            c4.setContentPadding(5,5,5,5);
            c4.setRadius(40);
            c4.setFocusable(true);
            c4.setClickable(true);
            //  c4.setCardBackgroundColor(context.getColor(R.color.azure));
            c4.setStrokeColor(context.getColor(R.color.azure));

            c4.setStrokeWidth(5);

        }

        private void generateCard3() {

            c3.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            c3.setPadding(10,10,10,10);
            c3.setContentPadding(5,5,5,5);
            c3.setRadius(40);
            c3.setFocusable(true);
            c3.setClickable(true);
            //  c3.setCardBackgroundColor(context.getColor(R.color.azure));
            c3.setStrokeColor(context.getColor(R.color.azure));
            c3.setStrokeWidth(5);

        }

        private void generateCard2() {

            c2.setLayoutParams(new MaterialCardView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            //c2.setCardElevation(10);
            c2.setPadding(10,10,10,10);
            c2.setContentPadding(5,5,5,5);
            c2.setRadius(40);
            c2.setFocusable(true);
            c2.setClickable(true);
            //   c2.setCardBackgroundColor(context.getColor(R.color.azure));
            c2.setStrokeColor(context.getColor(R.color.azure));
            c2.setStrokeWidth(5);

        }

        private void generateCard1() {

            c1.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

           // c1.setCardElevation(10);
            c1.setPadding(10,10,10,10);
            c1.setContentPadding(5,5,5,5);
            c1.setRadius(40);
            c1.setFocusable(true);
            c1.setClickable(true);
            // c1.setCardBackgroundColor(context.getColor(R.color.azure));
            c1.setStrokeColor(context.getColor(R.color.azure));
            c1.setStrokeWidth(5);

        }

        private void generateGridLayout() {
            gridLayout.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            gridLayout.setColumnCount(2);
            gridLayout.setRowCount(3);
            gridLayout.setPadding(10,10,10,10);
            gridLayout.setAlignmentMode(GridLayout.ALIGN_MARGINS);
        }

        private void generateButton3() {
            btn3.setIcon(context.getDrawable(R.drawable.uparrow));
            btn3.setBackgroundColor(context.getColor(R.color.white));
            btn3.setIconTint(ColorStateList.valueOf(context.getColor(R.color.blowfish)));
            btn3.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.white)));
            btn3.setPadding(80,0,0,0);
            btn3.setStateListAnimator(null);
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
