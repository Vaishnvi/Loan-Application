package com.hackersdigital.loan.dailycollector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class CollectCustomerPaymentPage extends AppCompatActivity {

    Button collectTab,notCollectTab;
    TextView t1,t2;
    EditText amountcollected;
    DatePicker datePicker;
    SimpleDateFormat dateFormatter ;
    Date d ;
    String entered_dob ;

    DatabaseReference databaseLendInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_customer_payment_page);

        datePicker = findViewById(R.id.dateTextView);

        t1 = findViewById(R.id.accNoTextView);

        t2 = findViewById(R.id.dailyAmountTextView);

        amountcollected = findViewById(R.id.amountCollectedTextedit);

        Bundle bundle = getIntent().getExtras();

        Long accNo = (Long) bundle.get("accNo");
        Long dailyAmount = (Long) bundle.get("dailyAmount");

        t1.setText(accNo.toString());
        t2.setText(dailyAmount.toString());

        collectTab = findViewById(R.id.collectTabButton);
        notCollectTab = findViewById(R.id.notCollectTabButton);

        collectTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = getIntent().getExtras();

                String lendInfoId = bundle.getString("lendInfoId");

                Long dailyAmount = (Long) bundle.get("dailyAmount");

                Long balance  = (Long) bundle.get("balance");

                Long due = (Long) bundle.get("due");

                Long collected = Long.parseLong(amountcollected.getText().toString());

                balance = balance - collected;
                due = dailyAmount - collected;
                dailyAmount = dailyAmount + due;

                String day = String.valueOf(datePicker.getDayOfMonth());
                String month = String.valueOf(datePicker.getMonth() + 1);
                String year = String.valueOf(datePicker.getYear());

                if(day.equals("1") || day.equals("2") || day.equals("3") || day.equals("4") || day.equals("5") || day.equals("6") || day.equals("7") || day.equals("8") ||
                        day.equals("9")  ){
                    day = "0"+day;
                }


                if(month.equals("1") || month.equals("2") || month.equals("3") || month.equals("4") || month.equals("5") || month.equals("6") || month.equals("7") || month.equals("8") ||
                        month.equals("9")  ){
                    month = "0"+month;
                }

                entered_dob = year+"/"+month+"/"+day;

/*              d = new Date(year, month, day);
                dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
                entered_dob = dateFormatter.format(d);
*/
                databaseLendInfo = FirebaseDatabase.getInstance().getReference("LendMoneyInfo").child(lendInfoId);

                databaseLendInfo.child("balance").setValue(balance);
                databaseLendInfo.child("due").setValue(due);
                databaseLendInfo.child("dailyAmount").setValue(dailyAmount);
                databaseLendInfo.child("date").setValue(entered_dob);
                databaseLendInfo.child("amount_collected").setValue(collected);
                databaseLendInfo.child("status").setValue("paid");

            }
        });

        notCollectTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();

                String lendInfoId = bundle.getString("lendInfoId");

                Long dailyAmount = (Long) bundle.get("dailyAmount");

                Long balance  = (Long) bundle.get("balance");

                Long due = (Long) bundle.get("due");

                Long collected = Long.parseLong(amountcollected.getText().toString());

                balance = balance - collected;
                due = dailyAmount - collected;
                dailyAmount = dailyAmount + due;

                String day = String.valueOf(datePicker.getDayOfMonth());
                String month = String.valueOf(datePicker.getMonth() + 1);
                String year = String.valueOf(datePicker.getYear());

                if(day.equals("1") || day.equals("2") || day.equals("3") || day.equals("4") || day.equals("5") || day.equals("6") || day.equals("7") || day.equals("8") ||
                        day.equals("9")  ){
                    day = "0"+day;
                }


                if(month.equals("1") || month.equals("2") || month.equals("3") || month.equals("4") || month.equals("5") || month.equals("6") || month.equals("7") || month.equals("8") ||
                        month.equals("9")  ){
                    month = "0"+month;
                }

                entered_dob = year+"/"+month+"/"+day;

           /*     d = new Date(year, month, day);
                dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
                entered_dob = dateFormatter.format(d);*/

                databaseLendInfo = FirebaseDatabase.getInstance().getReference("LendMoneyInfo").child(lendInfoId);

                databaseLendInfo.child("balance").setValue(balance);
                databaseLendInfo.child("due").setValue(due);
                databaseLendInfo.child("dailyAmount").setValue(dailyAmount);
                databaseLendInfo.child("date").setValue(entered_dob);
                databaseLendInfo.child("amount_collected").setValue(collected);
                databaseLendInfo.child("status").setValue("unpaid");

            }
        });

    }
}
