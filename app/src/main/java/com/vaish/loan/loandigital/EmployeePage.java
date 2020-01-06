package com.hackersdigital.loan.loandigital;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EmployeePage extends AppCompatActivity {

    TabHost tabHost;
    TextView textFile;
    private Uri mImageUri;
    GMailSender sender;

    List<Employees> employeeList;

    EditText e1, e2, e3, e4, e5, e6, e7, e8;
    Button add;

    TextView t0,t1,t2,t3,t4,t5,t6,t7,t8,t11,t12,t13,t14,t15,t16,t17,t18;

    DatabaseReference databaseEmployees;

    private static final int PICKFILE_RESULT_CODE = 1;

    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page);

        Button b;

        employeeList = new ArrayList<>();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        databaseEmployees = FirebaseDatabase.getInstance().getReference("EmployeesInfo");

        tabHost = (TabHost) findViewById(R.id.tabhost1);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("Add");
        tab1.setContent(R.id.Add);
        tab1.setIndicator("ADD");
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("Modify");
        tab2.setContent(R.id.Modify);
        tab2.setIndicator("MODIFY");
        tabHost.addTab(tab2);

        TabHost.TabSpec tab3 = tabHost.newTabSpec("Delete");
        tab3.setContent(R.id.Delete);
        tab3.setIndicator("DELETE");
        tabHost.addTab(tab3);


        e1 = (EditText) findViewById(R.id.etName);
        e2 = (EditText) findViewById(R.id.etEmail);
        e3 = (EditText) findViewById(R.id.etPhone);
        e4 = (EditText) findViewById(R.id.etFlatNo);
        e5 = (EditText) findViewById(R.id.etStreet);
        e6 = (EditText) findViewById(R.id.etState);
        e7 = (EditText) findViewById(R.id.etCity);
        e8 = (EditText) findViewById(R.id.etPincode);
        add = (Button) findViewById(R.id.addEmpButton);

        t0 = (TextView) findViewById(R.id.txt0);

        t1 = (TextView) findViewById(R.id.txt1);
        t2 = (TextView) findViewById(R.id.txt2);
        t3 = (TextView) findViewById(R.id.txt3);
        t4 = (TextView) findViewById(R.id.txt4);
        t5 = (TextView) findViewById(R.id.txt5);
        t6 = (TextView) findViewById(R.id.txt6);
        t7 = (TextView) findViewById(R.id.txt7);
        t8 = (TextView) findViewById(R.id.txt8);

        t11 = (TextView) findViewById(R.id.txtname);
        t12 = (TextView) findViewById(R.id.txtemail);
        t13 = (TextView) findViewById(R.id.txtphone);
        t14 = (TextView) findViewById(R.id.txtflatno);
        t15 = (TextView) findViewById(R.id.txtcity);
        t16 = (TextView) findViewById(R.id.txtstreet);
        t17 = (TextView) findViewById(R.id.txtstate);
        t18 = (TextView) findViewById(R.id.txtpincode);

        Button buttonPick = (Button) findViewById(R.id.buttonpick);
        textFile = (TextView) findViewById(R.id.textfile);

        buttonPick.setOnClickListener(new Button.OnClickListener() {


            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("file/*");
                startActivityForResult(intent, PICKFILE_RESULT_CODE);

            }
        });

    }

    private  String ans1(String ans){return ans;}

    private void addEmployee(String img){
        String name = e1.getText().toString();
        String email = e2.getText().toString();
        String phone = e3.getText().toString();
        String flatno = e4.getText().toString();
        String street = e5.getText().toString();
        String state = e6.getText().toString();
        String city = e7.getText().toString();
        String pincode = e8.getText().toString();

        String empId = "",password="";

        sender = new GMailSender("vkhindkar@gmail.com", "$HIVKRISHNA");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.

                Builder().permitAll().build();



        StrictMode.setThreadPolicy(policy);

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(flatno) && !TextUtils.isEmpty(street) && !TextUtils.isEmpty(state) && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(pincode))
        {

            empId = databaseEmployees.push().getKey();

            password = databaseEmployees.push().getKey();

            Employees employees = new Employees(empId,password,name,email,phone,img,flatno,street,state,city,pincode);

            databaseEmployees.child(empId).setValue(employees);

            Toast.makeText(this,"Employee added",Toast.LENGTH_LONG).show();

        }

        else{
            Toast.makeText(this,"You should enter the text",Toast.LENGTH_LONG).show();
        }

        try {
            sender.sendMail("Login Credentials", "Hi ,\n\nWe appreciate your interest for work ! Here are you login credentials -\n\nEmployee ID : "+empId +"\nPassword : "+password +
            "\n\nThank you for joining us ! \n\n\n - Team Loan Digital :)"        , "vkhindkar@gmail.com", email);
            Toast.makeText(this, "Email sent", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Exception", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


        /*try {

            new MyAsyncClass().execute();



        } catch (Exception ex) {

            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();

        }*/

    }



    class MyAsyncClass extends AsyncTask<Void, Void, Void> {



        ProgressDialog pDialog;



        @Override

        protected void onPreExecute() {

            super.onPreExecute();



            pDialog = new ProgressDialog(EmployeePage.this);

            pDialog.setMessage("Please wait...");

            pDialog.show();



        }


        @Override

        protected Void doInBackground(Void... mApi) {

            try {

                // Add subject, Body, your mail Id, and receiver mail Id.

                sender.sendMail("Login Credentials", "Booking Confirmed", "vkhindkar@gmail.com", "vkhindkar@gmail.com");





            }



            catch (Exception ex) {



            }

            return null;

        }



        @Override

        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

            pDialog.cancel();

            Toast.makeText(getApplicationContext(), "Email sent", Toast.LENGTH_SHORT).show();

        }

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch(requestCode){
            case PICKFILE_RESULT_CODE:
                if(resultCode==RESULT_OK){
                    String FilePath = data.getData().getPath();
                    textFile.setText(FilePath);
                    mImageUri=data.getData();
                    uploadImage();
                    // Picasso.with(this).load(mImageUri).into(mImageView);

                }
                break;

        }
    }

    private void uploadImage()
    {
        String fin;
        if(mImageUri!=null){
            StorageReference ref=storageReference.child("images/"+ UUID.randomUUID().toString());

            ref.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(EmployeePage.this,"upload succesfull",Toast.LENGTH_LONG).show();
                    //new String( taskSnapshot.getDownloadUrl().toString());
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            addEmployee(taskSnapshot.getDownloadUrl().toString());

                        }
                    });
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EmployeePage.this,e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    });
        }
    }

    public void deleteDialog(final String e){

        Button b = (Button) findViewById(R.id.delEmp);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("EmployeesInfo").child(e);

                databaseRef.removeValue();

                Toast.makeText(EmployeePage.this, "Employee " + e + " deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showUpdateDialog(final String category, String empId){

        final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("EmployeesInfo").child(empId).child(category);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog,null);

        dialogBuilder.setView(dialogView);

        final EditText editTextValue = dialogView.findViewById(R.id.editTxt);
        final Button buttonUpdate = dialogView.findViewById(R.id.buttonUpdate);


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String finalValue = editTextValue.getText().toString().trim();

                databaseRef.setValue(finalValue);

                Toast.makeText(EmployeePage.this, "Employee " + category + " updated successfully to " + finalValue , Toast.LENGTH_SHORT).show();

            }
        });

        dialogBuilder.setTitle("Updating Employee "+category);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }


    public void updateVisibleAndDialog(final String empID){


        final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("EmployeesInfo").child(empID);


        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //  Toast.makeText(CustomerPage.this, "here "+dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();

                for(DataSnapshot valueSnapshot : dataSnapshot.getChildren()){
                    if(valueSnapshot.getKey().equals("name")) {

                        t11.setText(String.valueOf(valueSnapshot.getValue()));
                    }
                    else if(valueSnapshot.getKey().equals("email")) {

                        t12.setText(String.valueOf(valueSnapshot.getValue()));
                    }
                    else if(valueSnapshot.getKey().equals("phone")) {

                        t13.setText(String.valueOf(valueSnapshot.getValue()));
                    }
                    else if(valueSnapshot.getKey().equals("flatno")) {

                        t14.setText(String.valueOf(valueSnapshot.getValue()));
                    }
                    else if(valueSnapshot.getKey().equals("city")) {

                        t15.setText(String.valueOf(valueSnapshot.getValue()));
                    }
                    else if(valueSnapshot.getKey().equals("street")) {

                        t16.setText(String.valueOf(valueSnapshot.getValue()));
                    }
                    else if(valueSnapshot.getKey().equals("state")) {

                        t17.setText(String.valueOf(valueSnapshot.getValue()));
                    }
                    else if(valueSnapshot.getKey().equals("pincode")) {

                        t18.setText(String.valueOf(valueSnapshot.getValue()));
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        t0.setVisibility(View.VISIBLE); t1.setVisibility(View.VISIBLE); t2.setVisibility(View.VISIBLE); t3.setVisibility(View.VISIBLE); t4.setVisibility(View.VISIBLE);
        t5.setVisibility(View.VISIBLE); t6.setVisibility(View.VISIBLE); t7.setVisibility(View.VISIBLE); t8.setVisibility(View.VISIBLE); t11.setVisibility(View.VISIBLE);
        t12.setVisibility(View.VISIBLE); t13.setVisibility(View.VISIBLE); t14.setVisibility(View.VISIBLE); t15.setVisibility(View.VISIBLE); t16.setVisibility(View.VISIBLE);
        t17.setVisibility(View.VISIBLE); t18.setVisibility(View.VISIBLE);


        t11.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("name",empID);
                return false;
            }
        });

        t12.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("email",empID);
                return false;
            }
        });


        t13.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("phone",empID);
                return false;
            }
        });


        t14.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("flatno",empID);
                return false;
            }
        });


        t15.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("city",empID);
                return false;
            }
        });


        t16.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("street",empID);
                return false;
            }
        });


        t17.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("state",empID);
                return false;
            }
        });


        t18.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("pincode",empID);
                return false;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        final ArrayAdapter<String> autoComplete2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        final HashMap<String,String> hashMap = new HashMap<>();

        databaseEmployees.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                employeeList.clear();

                for(DataSnapshot employeeSnapshot : dataSnapshot.getChildren()){
                    Employees employees = employeeSnapshot.getValue(Employees.class);
                    employeeList.add(employees);
                    autoComplete2.add(employees.getName());
                    //     autoCompleteCustIds.add(customer.getCustId());
                    hashMap.put(employees.getName(),employees.getEmpId());
//                    System.out.println(customer.getName());
                }

                AutoCompleteTextView ACTV = findViewById(R.id.autotxtview);
                ACTV.setAdapter(autoComplete2);
                ACTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String selected_emp_name = parent.getItemAtPosition(position).toString() ;

                        //        String selected_cust_Id = autoCompleteCustIds.getItem(autoComplete.getPosition(selected_cust_name));

                        Toast.makeText(EmployeePage.this, "Selected Item is: \t" + hashMap.get(selected_emp_name), Toast.LENGTH_LONG).show();

                        updateVisibleAndDialog(hashMap.get(selected_emp_name));

                    }
                });

                AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autotxtview1);
                autoCompleteTextView.setAdapter(autoComplete2); ;
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String selected_emp_name = parent.getItemAtPosition(position).toString() ;

                        //        String selected_cust_Id = autoCompleteCustIds.getItem(autoComplete.getPosition(selected_cust_name));

                        Toast.makeText(EmployeePage.this, "Selected Item is: \t" + hashMap.get(selected_emp_name), Toast.LENGTH_LONG).show();

                        deleteDialog(hashMap.get(selected_emp_name));
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
