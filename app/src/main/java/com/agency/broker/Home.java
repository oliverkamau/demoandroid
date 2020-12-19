package com.agency.broker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class  Home extends AppCompatActivity {
     TextView name,policies,quotes,expired;
      CardView cardView,cardView2,covers,serv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent=getIntent();
        //name=findViewById(R.id.userrealname);
        policies=findViewById(R.id.total);
        quotes=findViewById(R.id.expiredpol);
        expired=findViewById(R.id.quotesval);
        if(intent.getExtras()!=null){
         //String loggedUser=intent.getStringExtra("name");
         String total=intent.getStringExtra("policies");
         String expire=intent.getStringExtra("expired");
         String quote=intent.getStringExtra("quotes");

       //  name.setText(loggedUser);
         policies.setText(total);
         expired.setText(quote);
         quotes.setText(expire);
        }
        cardView=findViewById(R.id.policycard);
        cardView2=findViewById(R.id.quotescard);
        covers=findViewById(R.id.covers);
        serv=findViewById(R.id.serv);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Home.this,Policies.class));
                    }
                },1000);            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Home.this,Quotes.class));
                    }
                },1000);            }
        });
        covers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Home.this,InsuranceProducts.class));
                    }
                },1000);            }
        });
        serv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Home.this,SelfQuoteDetails.class));
                    }
                },1000);            }
        });
    }

}