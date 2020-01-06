package com.hackersdigital.loan.loandigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LendOrFirstPayPage extends AppCompatActivity {

    Button lend,pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lend_or_first_pay_page);

        lend = findViewById(R.id.lendMoneybutton);
        pay = findViewById(R.id.firstPayButton);

        lend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LendOrFirstPayPage.this,LendPage.class);
                startActivity(i);
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LendOrFirstPayPage.this,FirstPayPage.class);
                startActivity(i);
            }
        });

    }
}
