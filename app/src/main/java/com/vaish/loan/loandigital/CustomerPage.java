package com.hackersdigital.loan.loandigital;

import android.content.Intent;
import android.net.Uri;
import android.os.Message;
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


public class CustomerPage extends AppCompatActivity {

    String addCust;
    TabHost tabHost;
    TextView textFile;
    private Uri mImageUri;
    Long accNoStart = 599999999L ;
    Long accNoCount = 0L;

    List<Customers> customersList;

    EditText e1, e2, e3, e4, e5, e6, e7, e8, e9;
    Button add;

    TextView t0,t1,t2,t3,t4,t5,t6,t7,t8,t11,t12,t13,t14,t15,t16,t17,t18;

    DatabaseReference databaseCustomers;

    private static final int PICKFILE_RESULT_CODE = 1;

    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);

       // custNames();

        Button b;

        customersList = new ArrayList<>();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        databaseCustomers = FirebaseDatabase.getInstance().getReference("CustomersInfo");

        tabHost = (TabHost) findViewById(R.id.tab_host);
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
        e3 = (EditText) findViewById(R.id.etPassword);
        e4 = (EditText) findViewById(R.id.etPhone);
        e5 = (EditText) findViewById(R.id.etFlatNo);
        e6 = (EditText) findViewById(R.id.etStreet);
        e7 = (EditText) findViewById(R.id.etState);
        e8 = (EditText) findViewById(R.id.etCity);
        e9 = (EditText) findViewById(R.id.etPincode);
        add = (Button) findViewById(R.id.addCustButton);

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

     /*   b = (Button) findViewById(R.id.AddCust);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomerPage.this,AddCustPage.class);
                startActivity(i);
            }
        });*/

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //addCustomer();
            }
        });

        // addCustomer();

    }

/*
    private void addAddress(String id) {
        String flatno = e5.getText().toString();
        String street = e6.getText().toString();
        String state = e7.getText().toString();
        String city = e8.getText().toString();
        String pincode = e9.getText().toString();
        if(!TextUtils.isEmpty(e5.getText().toString()) && !TextUtils.isEmpty(street) && !TextUtils.isEmpty(state) && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(e9.getText().toString()))
        {
            databaseAddress=FirebaseDatabase.getInstance().getReference().child(id);
            String addrId = databaseAddress.push().getKey();
            CustAddress address=new CustAddress(addrId,flatno,street,state,city,pincode);
            databaseAddress.child(addrId).setValue(address);

            Toast.makeText(this,"Adress  added",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"You should enter the text",Toast.LENGTH_LONG).show();
        }

    }
    private String getFileExtension(Uri uri){
        ContentResolver cR=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }*/
    /*private void uploadFile()
    {
        if(mImageUri!=null) {
            StorageReference fileReference=storageReference.child("abc.png");
            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(MainActivity.this,"upload succesfull",Toast.LENGTH_LONG).show();
                     addCustomer("adhaarcard",taskSnapshot.getDownloadUrl().toString());
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    });
            }
        else
        {
            Toast.makeText(this,"You should select image",Toast.LENGTH_LONG).show();

        }
    }*/


    private  String ans1(String ans){return ans;}

    private void addCustomer(String img){
        String name = e1.getText().toString();
        String email = e2.getText().toString();
        String password = e3.getText().toString();
        String phone = e4.getText().toString();
        String flatno = e5.getText().toString();
        String street = e6.getText().toString();
        String state = e7.getText().toString();
        String city = e8.getText().toString();
        String pincode = e9.getText().toString();

        // String image=uploadImage();

        //storageReference= FirebaseStorage.getInstance().getReference();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(e4.getText().toString()) && !TextUtils.isEmpty(e5.getText().toString()) && !TextUtils.isEmpty(street) && !TextUtils.isEmpty(state) && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(e9.getText().toString()))
        {

            String custId = databaseCustomers.push().getKey();

            Customers customers = new Customers(custId,name,email,password,phone,img,flatno,street,state,city,pincode);


            databaseCustomers.child(custId).setValue(customers);


            Toast.makeText(this,"Customer added",Toast.LENGTH_LONG).show();
           // addAddress(custId);

        }

        else{
            Toast.makeText(this,"You should enter the text",Toast.LENGTH_LONG).show();
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
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(CustomerPage.this,"upload succesfull",Toast.LENGTH_LONG).show();
                    //new String( taskSnapshot.getDownloadUrl().toString());
                    addCustomer(taskSnapshot.getDownloadUrl().toString());

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CustomerPage.this,e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    });




        }
    }

    public void deleteDialog(final String c){

        Button b = (Button) findViewById(R.id.delCust);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("CustomersInfo").child(c);

                databaseRef.removeValue();

                Toast.makeText(CustomerPage.this, "Customer " + c + " deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showUpdateDialog(final String category, String custId){

        final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("CustomersInfo").child(custId).child(category);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog,null);

        dialogBuilder.setView(dialogView);

        final EditText editTextValue = dialogView.findViewById(R.id.editTxt);
        final Button buttonUpdate = dialogView.findViewById(R.id.buttonUpdate);


        //System.out.println("\n\n\n\n\n\n\n\n\n\\n\n"+finalValue);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String finalValue = editTextValue.getText().toString().trim();

                databaseRef.setValue(finalValue);

                Toast.makeText(CustomerPage.this, "Customer " + category + " updated successfully to " + finalValue , Toast.LENGTH_SHORT).show();

            }
        });

        dialogBuilder.setTitle("Updating Customer "+category);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }


    public void updateVisibleAndDialog(final String custID){


        final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("CustomersInfo").child(custID);


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

                showUpdateDialog("name",custID);
                return false;
            }
        });

        t12.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("email",custID);
                return false;
            }
        });


        t13.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("phone",custID);
                return false;
            }
        });


        t14.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("flatno",custID);
                return false;
            }
        });


        t15.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("city",custID);
                return false;
            }
        });


        t16.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("street",custID);
                return false;
            }
        });


        t17.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("state",custID);
                return false;
            }
        });


        t18.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showUpdateDialog("pincode",custID);
                return false;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        final ArrayAdapter<String> autoComplete = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        final HashMap<String,String> hashMap = new HashMap<>();


//        final ArrayAdapter<String> autoCompleteCustIds = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        databaseCustomers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                customersList.clear();


                for(DataSnapshot customerSnapshot : dataSnapshot.getChildren()){
                    Customers customer = customerSnapshot.getValue(Customers.class);
                    customersList.add(customer);
                    autoComplete.add(customer.getName());
               //     autoCompleteCustIds.add(customer.getCustId());
                    hashMap.put(customer.getName(),customer.getCustId());
//                    System.out.println(customer.getName());
                }

                AutoCompleteTextView ACTV = findViewById(R.id.autotxtview);
                ACTV.setAdapter(autoComplete);
                ACTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String selected_cust_name = parent.getItemAtPosition(position).toString() ;

                //        String selected_cust_Id = autoCompleteCustIds.getItem(autoComplete.getPosition(selected_cust_name));

                        Toast.makeText(CustomerPage.this, "Selected Item is: \t" + hashMap.get(selected_cust_name), Toast.LENGTH_LONG).show();

                        updateVisibleAndDialog(hashMap.get(selected_cust_name));

                    }
                });

                AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autotxtview1);
                autoCompleteTextView.setAdapter(autoComplete); ;
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String selected_cust_name = parent.getItemAtPosition(position).toString() ;

                        //        String selected_cust_Id = autoCompleteCustIds.getItem(autoComplete.getPosition(selected_cust_name));

                        Toast.makeText(CustomerPage.this, "Selected Item is: \t" + hashMap.get(selected_cust_name), Toast.LENGTH_LONG).show();

                        deleteDialog(hashMap.get(selected_cust_name));
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    /*private void uploadImage()
    {
        if(mImageUri!=null){
            StorageReference ref=storageReference.child("images/"+ UUID.randomUUID().toString());

            ref.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(MainActivity.this,"upload succesfull",Toast.LENGTH_LONG).show();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    });




        }
    }*/

  /*  public void custNames(){

        //Create a new ArrayAdapter with your context and the simple layout for the dropdown menu provided by Android
       final ArrayAdapter<String> autoComplete = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        //Child the root before all the push() keys are found and add a ValueEventListener()
        databaseCustomers.child("CustomersInfo").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Basically, this says "For each DataSnapshot *Data* in dataSnapshot, do what's inside the method.
                for (DataSnapshot suggestionSnapshot : dataSnapshot.getChildren()){
                    //Get the suggestion by childing the key of the string you want to get.
                  //  String s = suggestionSnapshot.getChildren().toString();
                    String suggestion = suggestionSnapshot.child("name").getValue(String.class);
                     //Add the retrieved string to the list
                    autoComplete.add(suggestion);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        AutoCompleteTextView ACTV = findViewById(R.id.autotxtview);
        ACTV.setAdapter(autoComplete);


        final ArrayAdapter<String> customerList = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
   //     final List<String> customerList = new ArrayList<>();
        databaseCustomers.child("CustomersInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Customers customer = postSnapshot.getValue(Customers.class);
                    customerList.add(customer.name);
                    // here you can access to name property like university.name

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        AutoCompleteTextView ACTV = findViewById(R.id.autotxtview);
        ACTV.setAdapter(customerList);

    }*/
}

