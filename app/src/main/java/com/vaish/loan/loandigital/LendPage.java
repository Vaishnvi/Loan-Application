package com.hackersdigital.loan.loandigital;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class LendPage extends AppCompatActivity  {

    TextView amtpay1,totalint1;
    TextView amttext1,inttext1;
    EditText day,roi,principal;
    Button addLendInfo,cal;

    Long accNoStart = 599999999L ;
    Long accNoCount = 0L;

    DatabaseReference databaseLendInfo,databaseCustomers;

    List<Customers> customersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lend_page);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autotxtview);
        amtpay1=(TextView) findViewById(R.id.amtpay);
        totalint1=(TextView) findViewById(R.id.totalint);
        amttext1=(TextView)findViewById(R.id.amttext);
        inttext1=(TextView)findViewById(R.id.interesttext);
        roi=(EditText)findViewById(R.id.interest);
        day=(EditText)findViewById(R.id.days);
        principal=(EditText)findViewById(R.id.amount);

        customersList = new ArrayList<>();

        databaseLendInfo = FirebaseDatabase.getInstance().getReference("LendMoneyInfo");
        databaseCustomers = FirebaseDatabase.getInstance().getReference("CustomersInfo");

        // Spinner click listener
/*        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amtpay1.setVisibility(View.VISIBLE);
                totalint1.setVisibility(View.VISIBLE);
                int val,prin,r,n;
                prin=Integer.parseInt(principal.getText().toString());
                r=Integer.parseInt(roi.getText().toString());
                n=Integer.parseInt(day.getText().toString());
                val=(prin*r*n)/100;
                int tv=(prin+val)/n;
                amttext1.setText(Integer.toString(tv));
                inttext1.setText(Integer.toString(val));
            }
        });*/


    }

    public void addLendInfo(final String custID){

        addLendInfo = (Button) findViewById(R.id.addLendInfo);
        cal = (Button) findViewById(R.id.calcu);


        addLendInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Long totalAmount = Long.parseLong(principal.getText().toString());
                Long number_of_days = Long.parseLong(day.getText().toString());
                Double rate_of_interest = Double.parseDouble(roi.getText().toString());
                Long dailyAmount;
                Long interest_amount_to_be_collected;

                interest_amount_to_be_collected = Math.round((totalAmount*((rate_of_interest/100) + 1))-totalAmount);
                dailyAmount = totalAmount/number_of_days;
                System.out.println("Before rounding : "+dailyAmount);
                Math.round(dailyAmount);
                System.out.println("After rounding : "+dailyAmount);

                String lendInfoID;

           /*     Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("yyyy/MM/dd");
                String date = mdformat.format(calendar.getTime());

*/

                if(!TextUtils.isEmpty(totalAmount.toString()) && !TextUtils.isEmpty(number_of_days.toString()) && !TextUtils.isEmpty(rate_of_interest.toString()))
                {

                    lendInfoID = databaseLendInfo.push().getKey();

                    LendMoneyInfo lendMoneyInfo = new LendMoneyInfo(custID,lendInfoID,accNoStart+ accNoCount + 1,totalAmount,number_of_days,rate_of_interest,dailyAmount,interest_amount_to_be_collected,totalAmount,0L,"unpaid",0L,"0");

                    databaseLendInfo.child(lendInfoID).setValue(lendMoneyInfo);

                    Toast.makeText(LendPage.this,"Lend Info added",Toast.LENGTH_LONG).show();

                }

                else{
                    Toast.makeText(LendPage.this,"You should enter the text",Toast.LENGTH_LONG).show();
                }
            }
        });

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Long totalAmount = Long.parseLong(principal.getText().toString());
                Long number_of_days = Long.parseLong(day.getText().toString());
                Double rate_of_interest = Double.parseDouble(roi.getText().toString());
                Long dailyAmount;
                Long interest_amount_to_be_collected;

                interest_amount_to_be_collected = Math.round((totalAmount*((rate_of_interest/100) + 1))-totalAmount);
                dailyAmount = totalAmount/number_of_days;
                System.out.println("Before rounding : "+dailyAmount);
                Math.round(dailyAmount);
                System.out.println("After rounding : "+dailyAmount);

                amttext1.setText(Long.toString(dailyAmount));
                inttext1.setText(Long.toString(interest_amount_to_be_collected));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        final ArrayAdapter<String> autoComplete1 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        final HashMap<String,String> hashMap = new HashMap<>();


        databaseCustomers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                customersList.clear();

                for(DataSnapshot customerSnapshot : dataSnapshot.getChildren()){
                    Customers customer = customerSnapshot.getValue(Customers.class);
                    customersList.add(customer);
                    autoComplete1.add(customer.getName());
                    //     autoCompleteCustIds.add(customer.getCustId());
                    hashMap.put(customer.getName(),customer.getCustId());
//                    System.out.println(customer.getName());
                }

                AutoCompleteTextView ACTV = findViewById(R.id.autotxtview);
                ACTV.setAdapter(autoComplete1);
                ACTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String selected_cust_name = parent.getItemAtPosition(position).toString() ;

                        //        String selected_cust_Id = autoCompleteCustIds.getItem(autoComplete.getPosition(selected_cust_name));

                        Toast.makeText(LendPage.this, "Selected Item is: \t" + hashMap.get(selected_cust_name), Toast.LENGTH_LONG).show();
                        System.out.println(hashMap.get(selected_cust_name));
                        addLendInfo(hashMap.get(selected_cust_name));

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        databaseLendInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                accNoCount = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}