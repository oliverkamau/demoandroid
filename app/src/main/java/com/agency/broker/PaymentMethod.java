package com.agency.broker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.muddzdev.styleabletoast.StyleableToast;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentMethod extends AppCompatActivity {

    String phone;
    String premium;
    String premOriginal;
    LinearLayout linearLayout;
    AutoCompleteTextView autoCompleteTextView;
    TextInputLayout textInputLayout;
    TextInputLayout tPesaInput;
    TextInputLayout inputPesaLayout;
    TextInputLayout inputPesa;
    TextInputLayout tCardInput;
    TextInputLayout inputCardLayout;
    TextInputLayout inputCard;
    TextView tv,ip1,ip2,ip3,ip4,ip5,ip6;
    TextView tv1,ic1,ic2,ic3,ic4,ic5,ic6;
    TextView tv2;
    TextView tvP;
    TextView tv1P;
    TextView tv2P;
    MaterialCheckBox pesaCheck,cardCheck;
    MaterialButton buttonP;
    MaterialButton button;
    MaterialCardView mpesaC1,mpesaC2,mpesaC3;
    MaterialCardView cardC1,cardC2,cardC3;
    GridLayout mpesaGrid,cardGrid;
    LinearLayout pesal1,pesal2,pesal3,pesaCardL1,pesaCardL2,pesaCardL3;
    LinearLayout cardl1,cardl2,cardl3,cardCardL1,cardCardL2,cardCardL3;
    TextInputEditText inputTLayout1;
    TextInputEditText inputTLayout;
    BuyInsuranceBean buyInsuranceBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        autoCompleteTextView = findViewById(R.id.payopt);
        textInputLayout = findViewById(R.id.paymentopt);

        Intent i = getIntent();

        premium = i.getStringExtra("premium");

        premOriginal = i.getStringExtra("premium");

       String cover= i.getStringExtra("cover");
        String length=i.getStringExtra("period");
        String startDate=i.getStringExtra("startDate");
        String engine=i.getStringExtra("engine");
        String value=i.getStringExtra("value");
        String model=i.getStringExtra("model");
        String year=i.getStringExtra("year");
        String vehicle=i.getStringExtra("vehicle");
        String premium=i.getStringExtra("premium");
        String binder=i.getStringExtra("binder");
        String company=i.getStringExtra("company");
        String and=i.getStringExtra("android");
        buyInsuranceBean=new BuyInsuranceBean();
        buyInsuranceBean.setCompany(company);
        buyInsuranceBean.setBinder(binder);
        buyInsuranceBean.setPremium("");
        buyInsuranceBean.setModelYear(model);
        buyInsuranceBean.setStartDate(startDate);
        buyInsuranceBean.setVehicle(vehicle);
        buyInsuranceBean.setEngine(engine);
        buyInsuranceBean.setCoverLength(length);
        buyInsuranceBean.setAndroidPol(and);
        buyInsuranceBean.setCover(cover);
        buyInsuranceBean.setModelYear(year);
        buyInsuranceBean.setValue(value);


        String[] options = {"M-PESA", "CARD"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.payments_menu, options);
        autoCompleteTextView.setText(arrayAdapter.getItem(0), false);
        autoCompleteTextView.setAdapter(arrayAdapter);
        linearLayout = findViewById(R.id.linearpay);
        SharedPreferences sharedPreferences = getSharedPreferences("insurance", Context.MODE_PRIVATE);
        phone = sharedPreferences.getString("phone", "phone");
        getMpesaLayout();
        getCardLayout();
        setCardGone();
        cardCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cardPesaChecked();
                } else {
                    cardPesaUnchecked();
                }
            }
        });
        pesaCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pesaCardChecked();
                } else {
                    pesaCardUnchecked();
                }
            }
        });
        mpesaC1.setOnClickListener(v -> {

            if (ip2.getText().toString().contains("-")) {
                String ip = ip2.getText().toString();
                ip = ip.replaceAll("-", "");
                ip2.setText(ip);
                mpesaC1.setCardBackgroundColor(getColor(R.color.white));
                inputTLayout.setText(premOriginal);
            } else {
                String ip = ip2.getText().toString();
                if (ip.contains(".")) {
                    inputTLayout.setText(ip);
                } else {
                    inputTLayout.setText(ip + ".00");
                }
                ip2.setText("-" + ip);
                mpesaC1.setCardBackgroundColor(getColor(R.color.blowfish));

            }


/*
            String inst=ip2.getText().toString();
            inst=inst.substring(inst.lastIndexOf(" ") + 1);
            inst=inst.replaceAll(",","");

            double t=Double.parseDouble(inst);
            BigDecimal bg=BigDecimal.valueOf(t);

*/


        });
        ((AutoCompleteTextView) textInputLayout.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedValue = arrayAdapter.getItem(position);
                getLayout(selectedValue);
            }
        });
        cardC2.setOnClickListener(v -> {
            if (ic4.getText().toString().contains("-")) {
                String ip = ic4.getText().toString();
                ip = ip.replaceAll("-", "");
                ic4.setText(ip);
                cardC2.setCardBackgroundColor(getColor(R.color.white));
                inputTLayout1.setText(premOriginal);
            } else {
                String ip = ic4.getText().toString();
                if (ip.contains(".")) {
                    inputTLayout1.setText(ip);
                } else {
                    inputTLayout1.setText(ip + ".00");
                }
                ic4.setText("-" + ip);
                cardC2.setCardBackgroundColor(getColor(R.color.blowfish));

            }


        });
        cardC1.setOnClickListener(v -> {
            if (ic2.getText().toString().contains("-")) {
                String ip = ic2.getText().toString();
                ip = ip.replaceAll("-", "");
                ic2.setText(ip);
                cardC1.setCardBackgroundColor(getColor(R.color.white));
                inputTLayout1.setText(premOriginal);
            } else {
                String ip = ic2.getText().toString();
                if (ip.contains(".")) {
                    inputTLayout1.setText(ip);
                } else {
                    inputTLayout1.setText(ip + ".00");
                }
                ic2.setText("-" + ip);
                cardC1.setCardBackgroundColor(getColor(R.color.blowfish));

            }

        });
        cardC3.setOnClickListener(v -> {
            if (ic6.getText().toString().contains("-")) {
                String ip = ic6.getText().toString();
                ip = ip.replaceAll("-", "");
                ic6.setText(ip);
                cardC3.setCardBackgroundColor(getColor(R.color.white));
                inputTLayout1.setText(premOriginal);
            } else {
                String ip = ic6.getText().toString();
                if (ip.contains(".")) {
                    inputTLayout1.setText(ip);
                } else {
                    inputTLayout1.setText(ip + ".00");
                }
                ic6.setText("-" + ip);
                cardC3.setCardBackgroundColor(getColor(R.color.blowfish));

            }

        });
        mpesaC2.setOnClickListener(v -> {
            if (ip4.getText().toString().contains("-")) {
                String ip = ip4.getText().toString();
                ip = ip.replaceAll("-", "");
                ip4.setText(ip);
                mpesaC2.setCardBackgroundColor(getColor(R.color.white));
                inputTLayout.setText(premOriginal);
            } else {
                String ip = ip4.getText().toString();
                if (ip.contains(".")) {
                    inputTLayout.setText(ip);
                } else {
                    inputTLayout.setText(ip + ".00");
                }
                ip4.setText("-" + ip);
                mpesaC2.setCardBackgroundColor(getColor(R.color.blowfish));

            }
        });
        mpesaC3.setOnClickListener(v -> {
            if (ip6.getText().toString().contains("-")) {
                String ip = ip6.getText().toString();
                ip = ip.replaceAll("-", "");
                ip6.setText(ip);
                mpesaC3.setCardBackgroundColor(getColor(R.color.white));
                inputTLayout.setText(premOriginal);
            } else {
                String ip = ip6.getText().toString();
                if (ip.contains(".")) {
                    inputTLayout.setText(ip);
                } else {
                    inputTLayout.setText(ip + ".00");
                }
                ip6.setText("-" + ip);
                mpesaC3.setCardBackgroundColor(getColor(R.color.blowfish));

            }


        });
        buttonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SharedPreferences sharedPreferences = getSharedPreferences("insurance", Context.MODE_PRIVATE);
               // String token = sharedPreferences.getString("token", "token");
                Long clid = sharedPreferences.getLong("client", 0);
                MpesaCall mpesaCall = new MpesaCall();
               // String p=phone.substring(1);
                String amm=inputTLayout.getText().toString();
                amm=amm.substring(amm.lastIndexOf(" ") + 1);
                amm=amm.substring(0,amm.indexOf('.'));
                amm=amm.replaceAll(",","");
                mpesaCall.setPhone(phone);
                mpesaCall.setAmmount(amm);
                mpesaCall.setClientId(clid);

                //token = "Bearer " + token;
                Call<ResponseBean> mpesaResponse = ApiClient.getPolicyService()
                        .mpesaCall(mpesaCall);
                mpesaResponse.enqueue(new Callback<ResponseBean>() {
                                          @Override
                                          public void onResponse(@NotNull Call<ResponseBean> call, @NotNull Response<ResponseBean> response) {
                                              if (response.isSuccessful()) {
                                                 underwritePolicy(buyInsuranceBean);
                                              } else {
                                                  StyleableToast.makeText(PaymentMethod.this,"Policy payment Failed !",R.style.myCommontoast).show();
                                              }
                                          }

                                          @Override
                                          public void onFailure(Call<ResponseBean> call, Throwable t) {
                                              StyleableToast.makeText(PaymentMethod.this,"Policy payment Failed !",R.style.myCommontoast).show();

                                          }
                                      }
                );
            }
        });
    }

    private void underwritePolicy(BuyInsuranceBean buyInsuranceBean) {

         SharedPreferences sharedPreferences =getSharedPreferences("insurance", Context.MODE_PRIVATE);
            String token = sharedPreferences.getString("token", "token");
            Long clid = sharedPreferences.getLong("client", 0);
            buyInsuranceBean.setClientId(clid);
            buyInsuranceBean.setPremium(inputTLayout.getText().toString());
            token = "Bearer " + token;
            Call<ResponseBean> loginResponseCall=ApiClient.getPolicyService()
                    .selfMessage(token,buyInsuranceBean);
            loginResponseCall.enqueue(new Callback<ResponseBean>() {
                @Override
                public void onResponse(@NotNull Call<ResponseBean> call, @NotNull Response<ResponseBean> response) {
                    if (response.isSuccessful()) {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(PaymentMethod.this, Policies.class);
                                startActivity(i);
                            }
                        }, 100);

                    } else {
                        StyleableToast.makeText(PaymentMethod.this, "Policy processing failed contact the Agency!", R.style.myCommontoast).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBean> call, Throwable t) {
                    StyleableToast.makeText(PaymentMethod.this, "Policy processing failed contact the Agency", R.style.myCommontoast).show();

                }
            });

    }

    private void createCardInstallments() {

        premium=premium.substring(premium.lastIndexOf(" ") + 1);
        premium=premium.replaceAll(",","");

        double t=Double.parseDouble(premium);
        BigDecimal bg=BigDecimal.valueOf(t);

        BigDecimal fourty=BigDecimal.valueOf(40).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.CEILING);
        BigDecimal thirty=BigDecimal.valueOf(30).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.CEILING);

        fourty=fourty.multiply(bg).setScale(2,RoundingMode.CEILING);
        thirty=thirty.multiply(bg).setScale(2,RoundingMode.CEILING);

        String num="Ksh "+ NumberFormat.getNumberInstance(Locale.US).format(fourty);
        String num2="Ksh "+NumberFormat.getNumberInstance(Locale.US).format(thirty);

        ic1=getTextViewLayoutCenter("40%");
        ic2=getTextViewLayoutCenter(num);
        ic3=getTextViewLayoutCenter("30%");
        ic4=getTextViewLayoutCenter(num2);
        ic5=getTextViewLayoutCenter("30%");
        ic6=getTextViewLayoutCenter(num2);

        cardl1=generateLinear();
        cardl1.addView(ic1);
        cardl1.addView(ic2);
        cardl2=generateLinear();
        cardl2.addView(ic3);
        cardl2.addView(ic4);
        cardl3=generateLinear();
        cardl3.addView(ic5);
        cardl3.addView(ic6);

        cardC1=generateCard();
        cardC1.addView(cardl1);
        cardC2=generateCard();
        cardC2.addView(cardl2);
        cardC3=generateCard();
        cardC3.addView(cardl3);

        cardCardL1=generateLinear();
        cardCardL1.addView(cardC1);
        cardCardL2=generateLinear();
        cardCardL2.addView(cardC2);
        cardCardL3=generateLinear();
        cardCardL3.addView(cardC3);

        cardGrid=generateGridLayout();

        cardGrid.addView(cardCardL1,new GridLayout.LayoutParams(
                GridLayout.spec(0, 1f),
                GridLayout.spec(0, 1f)));
        cardGrid.addView(cardCardL2,new GridLayout.LayoutParams(
                GridLayout.spec(0, 1f),
                GridLayout.spec(1, 1f)));
        cardGrid.addView(cardCardL3,new GridLayout.LayoutParams(
                GridLayout.spec(0, 1f),
                GridLayout.spec(2, 1f)));



    }

    private LinearLayout generateLinear() {

        LinearLayout layout=new LinearLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setPadding(10,10,10,10);
        layout.setOrientation(LinearLayout.VERTICAL);
        return layout;

    }


    private void createMpesaInstallments() {
       // premium=premium.substring(premium.indexOf(" "));
        premium=premium.substring(premium.lastIndexOf(" ") + 1);
        premium=premium.replaceAll(",","");
        double t=Double.parseDouble(premium);
        BigDecimal bg=BigDecimal.valueOf(t);

        BigDecimal fourty=BigDecimal.valueOf(40).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.CEILING);
        BigDecimal thirty=BigDecimal.valueOf(30).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.CEILING);

        fourty=fourty.multiply(bg).setScale(2,RoundingMode.CEILING);
        thirty=thirty.multiply(bg).setScale(2,RoundingMode.CEILING);

        String num="Ksh "+ NumberFormat.getNumberInstance(Locale.US).format(fourty);
        String num2="Ksh "+NumberFormat.getNumberInstance(Locale.US).format(thirty);

        ip1=getTextViewLayoutCenter("40%");
        ip2=getTextViewLayoutCenter(num);
        ip3=getTextViewLayoutCenter("30%");
        ip4=getTextViewLayoutCenter(num2);
        ip5=getTextViewLayoutCenter("30%");
        ip6=getTextViewLayoutCenter(num2);

        pesal1=generateLinear();
        pesal1.addView(ip1);
        pesal1.addView(ip2);
        pesal2=generateLinear();
        pesal2.addView(ip3);
        pesal2.addView(ip4);
        pesal3=generateLinear();
        pesal3.addView(ip5);
        pesal3.addView(ip6);

        mpesaC1=generateCard();
        mpesaC1.addView(pesal1);
        mpesaC2=generateCard();
        mpesaC2.addView(pesal2);
        mpesaC3=generateCard();
        mpesaC3.addView(pesal3);

        pesaCardL1=generateLinear();
        pesaCardL1.addView(mpesaC1);
        pesaCardL2=generateLinear();
        pesaCardL2.addView(mpesaC2);
        pesaCardL3=generateLinear();
        pesaCardL3.addView(mpesaC3);

        mpesaGrid=generateGridLayout();

        mpesaGrid.addView(pesaCardL1,new GridLayout.LayoutParams(
                GridLayout.spec(0, 1f),
                GridLayout.spec(0, 1f)));
        mpesaGrid.addView(pesaCardL2,new GridLayout.LayoutParams(
                GridLayout.spec(0, 1f),
                GridLayout.spec(1, 1f)));
        mpesaGrid.addView(pesaCardL3,new GridLayout.LayoutParams(
                GridLayout.spec(0, 1f),
                GridLayout.spec(2, 1f)));
    }

    private void pesaCardUnchecked() {
       // Toast.makeText(this,"Inst Card UnChecked",Toast.LENGTH_LONG).show();
        mpesaGrid.setVisibility(View.GONE);
    }

    private void pesaCardChecked() {

      //  Toast.makeText(this,"Inst Pesa Checked",Toast.LENGTH_LONG).show();
        mpesaGrid.setVisibility(View.VISIBLE);
    }

    private void cardPesaUnchecked() {
       // Toast.makeText(this,"Inst Pesa UnChecked",Toast.LENGTH_LONG).show();
        cardGrid.setVisibility(View.GONE);
    }

    private void cardPesaChecked() {
       // Toast.makeText(this,"Inst Card Checked",Toast.LENGTH_LONG).show();
        cardGrid.setVisibility(View.VISIBLE);
    }

    private GridLayout generateGridLayout() {
        GridLayout gridLayout=new GridLayout(this);
        gridLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        gridLayout.setColumnCount(3);
        gridLayout.setRowCount(1);
        gridLayout.setPadding(10,10,10,30);
        gridLayout.setAlignmentMode(GridLayout.ALIGN_MARGINS);
        return gridLayout;
    }

    private MaterialCardView generateCard() {
        MaterialCardView cardView=new MaterialCardView(this);

        cardView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        // c1.setCardElevation(10);
        cardView.setContentPadding(10,10,10,10);
        cardView.setRadius(50);
        cardView.setFocusable(true);
        cardView.setClickable(true);
        // c1.setCardBackgroundColor(context.getColor(R.color.azure));
        cardView.setStrokeColor(getColor(R.color.blowfish));
        cardView.setStrokeWidth(5);

        return cardView;

    }

    private void setCardGone() {

        tv.setVisibility(View.GONE);
        tv1.setVisibility(View.GONE);
        tv2.setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        tCardInput.setVisibility(View.GONE);
        inputCardLayout.setVisibility(View.GONE);
        inputCard.setVisibility(View.GONE);
        cardCheck.setVisibility(View.GONE);

        tvP.setVisibility(View.VISIBLE);
        tv1P.setVisibility(View.VISIBLE);
        buttonP.setVisibility(View.VISIBLE);
        tPesaInput.setVisibility(View.VISIBLE);
        inputPesaLayout.setVisibility(View.VISIBLE);
        pesaCheck.setVisibility(View.VISIBLE);

    }
    private void setPesaGone() {

        tvP.setVisibility(View.GONE);
        tv1P.setVisibility(View.GONE);
        tv.setVisibility(View.VISIBLE);
        tv1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        buttonP.setVisibility(View.GONE);
        cardCheck.setVisibility(View.VISIBLE);

        tCardInput.setVisibility(View.VISIBLE);
        inputCardLayout.setVisibility(View.VISIBLE);
        inputCard.setVisibility(View.VISIBLE);
        pesaCheck.setVisibility(View.GONE);
        tPesaInput.setVisibility(View.GONE);
        inputPesaLayout.setVisibility(View.GONE);


    }

    private void getLayout(String selectedValue) {
        if(selectedValue.equalsIgnoreCase("M-PESA")){
            setCardGone();

        }
        else if(selectedValue.equalsIgnoreCase("CARD")){
                setPesaGone();
        }
        else{

        }

    }

    private void getCardLayout() {
    /*    if(tv1!=null) {
            ((ViewGroup) tv1.getParent()).removeView(tv1);
        }

        if(tv!=null) {
            ((ViewGroup) tv.getParent()).removeView(tv);
        }

        if(tCardInput!=null) {
            ((ViewGroup) tInput.getParent()).removeView(tInput);
        }
        if(button!=null) {
            ((ViewGroup) button.getParent()).removeView(button);
        }
        if(inputLayout!=null) {
            ((ViewGroup) inputLayout.getParent()).removeView(inputLayout);
        }
        if(tv2!=null) {
           ((ViewGroup) tv2.getParent()).removeView(tv2);
       }
        if(input!=null) {
            ((ViewGroup) input.getParent()).removeView(input);
        }


     */
        tCardInput=cardIcon();
        inputCardLayout=idCard();
        inputCard=getTextLayout2();


        TextInputEditText textInputEditText=getEditT(tCardInput);
        TextInputEditText inputTLayout=getEditT(inputCardLayout);
        inputTLayout1=getEditT(inputCard);
        inputTLayout1.setText(premOriginal);

         button=generateButton2();
        tCardInput.addView(textInputEditText);
        inputCardLayout.addView(inputTLayout);
        inputCard.addView(inputTLayout1);
        cardCheck=generateCheckBox();

         tv=getTextViewLayout("Card Number");
         tv1=getTextViewLayout("National ID");
         tv2=getTextViewLayout("Ammount To Pay");

        linearLayout.addView(tv);
        linearLayout.addView(tCardInput);
        linearLayout.addView(tv1);
        linearLayout.addView(inputCardLayout);

        linearLayout.addView(cardCheck);
        createCardInstallments();
        linearLayout.addView(cardGrid);
        cardGrid.setVisibility(View.GONE);
        linearLayout.addView(tv2);

        linearLayout.addView(inputCard);
        linearLayout.addView(button);


    }

    private void getMpesaLayout() {
/*
        if(tv1!=null) {
            ((ViewGroup) tv1.getParent()).removeView(tv1);
        }

        if(tv!=null) {
            ((ViewGroup) tv.getParent()).removeView(tv);
        }

        if(tInput!=null) {
            ((ViewGroup) tInput.getParent()).removeView(tInput);
        }
        if(button!=null) {
            ((ViewGroup) button.getParent()).removeView(button);
        }
        if(inputLayout!=null) {
            ((ViewGroup) inputLayout.getParent()).removeView(inputLayout);
        }
        if(tv2!=null) {
            ((ViewGroup) tv2.getParent()).removeView(tv2);
        }
        if(input!=null) {
            ((ViewGroup) input.getParent()).removeView(input);
        }

 */

      tPesaInput=getTextLayout();
      inputPesaLayout=getTextLayout2();

     TextInputEditText textInputEditText=getEditT(tPesaInput);
     textInputEditText.setText(phone);

     inputTLayout=getEditT(inputPesaLayout);
     inputTLayout.setText(premOriginal);

        buttonP=generateButton2();
        tPesaInput.addView(textInputEditText);
        inputPesaLayout.addView(inputTLayout);

    // tInput.setHintEnabled(false);
     //inputLayout.setHintEnabled(true);
        pesaCheck=generateCheckBox();
      tvP=getTextViewLayout("Mobile Number");
      tv1P=getTextViewLayout("Ammount To Pay");
     linearLayout.addView(tvP);
     linearLayout.addView(tPesaInput);
     linearLayout.addView(pesaCheck);
     createMpesaInstallments();
     linearLayout.addView(mpesaGrid);
     mpesaGrid.setVisibility(View.GONE);
     linearLayout.addView(tv1P);
     linearLayout.addView(inputPesaLayout);
     linearLayout.addView(buttonP);

    }

    private MaterialCheckBox generateCheckBox() {

        MaterialCheckBox m=new MaterialCheckBox(this);
        m.isUseMaterialThemeColors();
        m.setClickable(true);
        m.setText("Pay in installments?");
        m.setPadding(0,0,0,10);
        return m;

    }

    private MaterialButton generateButton2() {
        MaterialButton btn2=new MaterialButton(this);
        btn2.setText("Buy");
        btn2.setTop(30);
        btn2.setBackgroundColor(getColor(R.color.teal));
        btn2.setCornerRadius(100);

        btn2.setPadding(80,80,80,80);
        btn2.setStrokeColor(ColorStateList.valueOf(getColor(R.color.blowfish)));
        btn2.setStrokeWidth(2);
        btn2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btn2.setClickable(true);
        return btn2;
    }
    private TextInputEditText getEditT(TextInputLayout tInput){
        TextInputEditText tz=  new TextInputEditText(tInput.getContext());
        tz.setPadding(50,50,50,50);
        return tz;
    }
    private TextView getTextViewLayout(String h){
        TextView tView=new TextView(this);

        tView.setText(h);
        tView.setPadding(30,10,10,10);
        tView.setTextColor(getColor(R.color.majorBlue));
        tView.setTextSize(14);

        return tView;
    }

    private TextView getTextViewLayoutCenter(String h){
        TextView tc=new TextView(this);

        tc.setText(h);
        tc.setPadding(30,10,10,10);
        tc.setTextColor(getColor(R.color.majorBlue));
        tc.setTextSize(14);
        tc.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        return tc;
    }
    private TextInputLayout cardIcon(){
        TextInputLayout t=new TextInputLayout(this);
        t.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        t.setBoxCornerRadii(100,100,100,100);
        t.setBoxStrokeColor(getColor(R.color.mtrl_textinput_default_box_stroke_color));
        //t.setHint("07XXXXXXXX");
        t.setStartIconDrawable(getDrawable(R.drawable.card));
        t.setStartIconTintList(getColorStateList(R.color.mtrl_textinput_default_box_stroke_color));
        t.setBoxBackgroundColor(getColor(R.color.white));
        t.setPadding(10,30,10,20);
        t.setLayoutParams(new TextInputLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return t;
    }
    private TextInputLayout idCard(){
        TextInputLayout t=new TextInputLayout(this);
        t.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        t.setBoxCornerRadii(100,100,100,100);
        t.setBoxStrokeColor(getColor(R.color.mtrl_textinput_default_box_stroke_color));
        t.setStartIconDrawable(getDrawable(R.drawable.id));
        t.setStartIconTintList(getColorStateList(R.color.mtrl_textinput_default_box_stroke_color));
        t.setBoxBackgroundColor(getColor(R.color.white));
        t.setPadding(10,30,10,50);
        t.setLayoutParams(new TextInputLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return t;
    }

    private TextInputLayout getTextLayout(){
        TextInputLayout t=new TextInputLayout(this);
        t.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        t.setBoxCornerRadii(100,100,100,100);
        t.setBoxStrokeColor(getColor(R.color.mtrl_textinput_default_box_stroke_color));
        //t.setHint("07XXXXXXXX");
        t.setStartIconDrawable(getDrawable(R.drawable.call));
        t.setStartIconTintList(getColorStateList(R.color.mtrl_textinput_default_box_stroke_color));
        t.setBoxBackgroundColor(getColor(R.color.white));
        t.setPadding(10,30,10,20);
        t.setLayoutParams(new TextInputLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return t;
    }
    private TextInputLayout getTextLayout2(){
        TextInputLayout t=new TextInputLayout(this);
        t.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        t.setBoxCornerRadii(100,100,100,100);
        t.setBoxStrokeColor(getColor(R.color.mtrl_textinput_default_box_stroke_color));
        t.setStartIconDrawable(getDrawable(R.drawable.moneys));
        t.setStartIconTintList(getColorStateList(R.color.mtrl_textinput_default_box_stroke_color));
        t.setBoxBackgroundColor(getColor(R.color.white));
        t.setPadding(10,30,10,80);
        t.setLayoutParams(new TextInputLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return t;
    }
}