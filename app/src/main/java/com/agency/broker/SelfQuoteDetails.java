package com.agency.broker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.NestedScrollView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class SelfQuoteDetails extends AppCompatActivity {


    private GridLayout gridLayout;
    private CardView cardView,cardView2,cardView3,cardView4;
    private LinearLayout linearLayout,l2,l3,l4;
    private TextView t0,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_quote_details);

        gridLayout=(GridLayout)findViewById(R.id.myselfgrid);
        gridLayout.setColumnCount(1);
        gridLayout.setRowCount(3);
        gridLayout.setMinimumHeight(1000);
        setMyLayouts();



    }
    private void setMyLayouts(){
        cardView=generateCard();
        linearLayout=generateLayout();
        l2=generateInternalLayout();
        l3=generateInternalLayout();
        l4=generateInternalLayout();
        t1=generateTextCenter("Britam Motor Private");
        t0=generateTextLeft("Comprehensive Cover");
        t2=generateTextLeft("Premium");
        t3=generateTextLeft("Ksh.20500");
        t4=generateDrawable();
        t5=generateTextView();
        t6=generateDrawable();
        t7=generateTextViewNext();
        t8=generateDrawable();
        t9=generateTextViewAfter();
        l2.addView(t4);
        l2.addView(t5);
        l3.addView(t6);
        l3.addView(t7);
        l4.addView(t8);
        l4.addView(t9);
        linearLayout.addView(t1);
        linearLayout.addView(t0);
        linearLayout.addView(t2);
        linearLayout.addView(t3);
        linearLayout.addView(l2);
        linearLayout.addView(l3);
        linearLayout.addView(l4);
        cardView.addView(linearLayout);
        gridLayout.addView(cardView);
    }

    private TextView generateTextCenter(String text){
        TextView textView=new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(text);
        textView.setTextColor(getColor(R.color.teal));
        Typeface typeface = ResourcesCompat.getFont(this, R.font.berkshire_swash);
        textView.setTypeface(typeface);
        textView.setPadding(40,40,40,40);
        textView.setTextSize(20);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        return textView;
    }
    private TextView generateTextLeft(String text){
        TextView textView=new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(text);
        textView.setTextColor(getColor(R.color.teal));
        Typeface typeface = ResourcesCompat.getFont(this, R.font.berkshire_swash);
        textView.setTypeface(typeface);
        textView.setPadding(40,40,40,40);
        textView.setTextSize(14);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        return textView;
    }
    private CardView generateCard(){

       CardView cardView=new CardView(this);
        cardView.setRadius(20);
        cardView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        cardView.setFocusable(true);
        cardView.setCardElevation(20);
        cardView.setPadding(5,5,5,5);

        return cardView;
    }
    private TextView generateTextView(){

        TextView textView=new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        textView.setText("Coveres you for injuries arising from reckless driving and accidents inflicted");
        textView.setTextColor(getColor(R.color.teal));
        Typeface typeface3 = ResourcesCompat.getFont(this, R.font.berkshire_swash);

        textView.setTypeface(typeface3);
        textView.setPadding(20,20,20,20);
        textView.setTextSize(14);
        return textView;
    }
    private TextView generateTextViewNext(){

        TextView textView=new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        textView.setText("Cover for fires caused by vehicle but not the vehicle itself");
        textView.setTextColor(getColor(R.color.teal));
        Typeface typeface3 = ResourcesCompat.getFont(this, R.font.berkshire_swash);

        textView.setTypeface(typeface3);
        textView.setPadding(20,20,20,20);
        textView.setTextSize(14);
        return textView;
    }

    private TextView generateTextViewAfter(){

        TextView textView=new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        textView.setText("Cover for towing incase of an accident");
        textView.setTextColor(getColor(R.color.teal));
        Typeface typeface3 = ResourcesCompat.getFont(this, R.font.berkshire_swash);

        textView.setTypeface(typeface3);
        textView.setPadding(20,20,20,20);
        textView.setTextSize(14);
        return textView;
    }

    private TextView generateDrawable(){

        TextView textView=new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.covered,0,0,0);
        Typeface typeface3 = ResourcesCompat.getFont(this, R.font.berkshire_swash);
        textView.setTypeface(typeface3);
        textView.setPadding(20,20,20,20);
        textView.setTextSize(10);
        return textView;
    }
    private LinearLayout generateLayout(){

      LinearLayout  linearLayout=new LinearLayout(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(5,5,5,5);
        return linearLayout;
    }
    private LinearLayout generateInternalLayout(){

       LinearLayout layout=new LinearLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setPadding(5,20,5,5);

         return layout;
    }
}