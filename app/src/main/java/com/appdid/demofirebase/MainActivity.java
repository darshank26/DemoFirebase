package com.appdid.demofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText mEdtEmail,mEditPassword;
    Button mBtnLogin;
    TextView mTxtRegister;
    FirebaseAuth mFireAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtEmail = findViewById(R.id.edt_email);
        mEditPassword = findViewById(R.id.edt_password);
        mBtnLogin = findViewById(R.id.btn_login);
        mTxtRegister = findViewById(R.id.txt_register);
        mFireAuth = FirebaseAuth.getInstance();

        if(mFireAuth.getCurrentUser() != null)
        {
            Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this,MainPage.class);
            startActivity(i);
            finish();
        }






        mTxtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(i);
                finish();
            }
        });


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(mEdtEmail.getText().toString()))
                {
                    mEdtEmail.setError("Please Enter email..");
                    return;
                }

                if(TextUtils.isEmpty(mEditPassword.getText().toString()))
                {
                    mEditPassword.setError("Please Enter password..");
                    return;
                }

                String mStrEmail = mEdtEmail.getText().toString();
                String mStrPassword = mEditPassword.getText().toString();


                mFireAuth.signInWithEmailAndPassword(mStrEmail,mStrPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this,MainPage.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Login unsuccessful",Toast.LENGTH_LONG).show();

                        }

                    }
                });


            }
        });




    }
}
