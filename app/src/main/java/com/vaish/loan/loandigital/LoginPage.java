package com.hackersdigital.loan.loandigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    EditText email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Button bt=(Button) findViewById(R.id.login);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=(EditText) findViewById(R.id.Emailid);
                final String e1=email.getText().toString();
                pass=(EditText) findViewById(R.id.password);
                final String admin=new String("admin");
                final String p1=pass.getText().toString();
                final String pa=new String("admin");
                if(e1.equals(admin) && p1.equals(pa))
                {
                    Toast.makeText(LoginPage.this,"Logged in as Admin",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoginPage.this,HomePage.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(LoginPage.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    emptyInputEditText();
                }
            }
        });
        Button reg=(Button) findViewById(R.id.Register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void emptyInputEditText() {
        email.setText(null);
        pass.setText(null);
    }
}
