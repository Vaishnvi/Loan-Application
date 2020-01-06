package com.hackersdigital.loan.dailycollector;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PayeesCollectionPage extends AppCompatActivity {

    DatabaseReference databaseCustomers,databaseLendMoneyInfo;

    ListView listViewPayees;

    List<LendMoneyInfo> lendMoneyInfoList;
    List<Customers> customersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payees_collection_page);

        databaseLendMoneyInfo = FirebaseDatabase.getInstance().getReference("LendMoneyInfo");

        databaseCustomers = FirebaseDatabase.getInstance().getReference("CustomersInfo");

        listViewPayees = findViewById(R.id.listPayee);

        lendMoneyInfoList = new ArrayList<>();
        customersList = new ArrayList<>();


    }

    private void getCustNameAndAddress(final String custid, final Long totalamount, final Long accNo,final String lendInfoId,final Long dailyAmount,final Long balance, final Long due){

        final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("CustomersInfo").child(custid);

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name="";
                String address = "" ;
                String flatno = "", street="" , city="", state ="",pincode="";
                for(DataSnapshot valueSnapshot : dataSnapshot.getChildren()) {
                    if (valueSnapshot.getKey().equals("name")) {

                        name = String.valueOf(valueSnapshot.getValue());
                    }
                    else if(valueSnapshot.getKey().equals("flatno")){

                        flatno = String.valueOf(valueSnapshot.getValue());
                    }
                    else if(valueSnapshot.getKey().equals("street")){

                        street = String.valueOf(valueSnapshot.getValue());
                    }
                    else if(valueSnapshot.getKey().equals("city")){

                        city = String.valueOf(valueSnapshot.getValue());
                    }
                    else if(valueSnapshot.getKey().equals("state")){

                        state = String.valueOf(valueSnapshot.getValue());
                    }
                    else if(valueSnapshot.getKey().equals("pincode")){

                        pincode = String.valueOf(valueSnapshot.getValue());
                    }
                }
                address = flatno + " , " + street + " " + city + " " + state + " - " + pincode;

                showCollectPayeeInfo(custid,name,address,totalamount,accNo,lendInfoId,dailyAmount,balance,due);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void showCollectPayeeInfo(final String custId, String custName, String custAddress, final Long totalAmount,final Long accNo,final String lendInfoId,final Long dailyAmount,final Long balance,final Long due){


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.collect_tab,null);

        dialogBuilder.setView(dialogView);

        TextView name = dialogView.findViewById(R.id.custNameTextview);
        TextView address = dialogView.findViewById(R.id.addressTextView);
        TextView amount = dialogView.findViewById(R.id.amountTextView);
        Button collectPayment = dialogView.findViewById(R.id.buttonCollectPayment);

        name.setText("Customer Name : " + custName);
        address.setText("Address : " + custAddress);
        amount.setText("Total Amount to be repaid : " + totalAmount.toString());

        collectPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(PayeesCollectionPage.this,CollectCustomerPaymentPage.class);
                i.putExtra("custId",custId);
                i.putExtra("totalAmount",totalAmount);
                i.putExtra("accNo",accNo);
                i.putExtra("lendInfoId",lendInfoId);
                i.putExtra("dailyAmount",dailyAmount);
                i.putExtra("balance",balance);
                i.putExtra("due",due);

                startActivity(i);

                /*      String finalValue = editTextValue.getText().toString().trim();

                databaseRef.setValue(finalValue);

                Toast.makeText(CustomerPage.this, "Customer " + category + " updated successfully to " + finalValue , Toast.LENGTH_SHORT).show();
                */

            }
        });

        dialogBuilder.setTitle("Customer Payment Details");

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();


        databaseLendMoneyInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                System.out.println(dataSnapshot.getChildrenCount());

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                //    String id = dataSnapshot1.getKey();

//                    System.out.println(id);

                    LendMoneyInfo lendMoneyInfo = dataSnapshot1.getValue(LendMoneyInfo.class);

                    System.out.println(lendMoneyInfo.getStatus());

                    if(lendMoneyInfo.getBalance() > 0) {

                        lendMoneyInfoList.add(lendMoneyInfo);
                    }
   /*                 DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("LendMoneyInfo").child(id);


                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

*/
                 //   Customers customers = dataSnapshot1.getValue(Customers.class);

                //    customersList.add(customers);


                }

                LendInfoList adapter = new LendInfoList(PayeesCollectionPage.this,lendMoneyInfoList);
                listViewPayees.setAdapter(adapter);


      /*          listViewPayees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        LendMoneyInfo lendMoneyInfo = lendMoneyInfoList.get(position);

                        getCustNameAndAddress(lendMoneyInfo.custId,lendMoneyInfo.getTotalAmount());

                    }
                });*/


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listViewPayees.setLongClickable(true);


        listViewPayees.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                LendMoneyInfo lendMoneyInfo = lendMoneyInfoList.get(position);

                getCustNameAndAddress(lendMoneyInfo.custId,lendMoneyInfo.getTotalAmount(),lendMoneyInfo.getAccNo(),lendMoneyInfo.getLendInfoID(),lendMoneyInfo.getDailyAmount(),lendMoneyInfo.getBalance(),lendMoneyInfo.getDue());


                return true;
            }
        });
    }
}
