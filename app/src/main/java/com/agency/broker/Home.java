package com.agency.broker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class  Home extends AppCompatActivity {
     TextView name;
      CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent=getIntent();
        name=findViewById(R.id.userrealname);
        if(intent.getExtras()!=null){
         String loggedUser=intent.getStringExtra("name");
         name.setText(loggedUser);
        }
        cardView=findViewById(R.id.policycard);
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
    }
}