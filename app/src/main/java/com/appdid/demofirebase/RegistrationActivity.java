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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    EditText mEdtEmailRegister,mEdtPasswordRegister,mEdtConfirmPassRegister;
    Button mBtnRegister;
    TextView mTxtLogin;
    FirebaseAuth mFireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mEdtEmailRegister = findViewById(R.id.edt_email_register);
        mEdtPasswordRegister = findViewById(R.id.edt_password_register);
        mEdtConfirmPassRegister = findViewById(R.id.edt_password_confirm);
        mBtnRegister = findViewById(R.id.btn_register);
        mTxtLogin = findViewById(R.id.txt_login);
        mFireAuth = FirebaseAuth.getInstance();

        mTxtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i = new Intent(RegistrationActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(mEdtEmailRegister.getText().toString()))
                {
                    mEdtEmailRegister.setError("Enter Email id..");
                    return;
                }

                if(TextUtils.isEmpty(mEdtPasswordRegister.getText().toString()))
                {
                    mEdtPasswordRegister.setError("Enter password here..");
                    return;
                }

                if(TextUtils.isEmpty(mEdtConfirmPassRegister.getText().toString()))
                {
                    mEdtConfirmPassRegister.setError("Enter password to confirm here..");
                    return;

                }

                if(mEdtPasswordRegister.getText().toString() == mEdtConfirmPassRegister.getText().toString())
                {
                    Toast.makeText(RegistrationActivity.this,"Password Matched",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(RegistrationActivity.this,"Password Not Matched",Toast.LENGTH_LONG).show();

                }


                String mStrEmail = mEdtEmailRegister.getText().toString();
                String mStrPassword = mEdtPasswordRegister.getText().toString();

                mFireAuth.createUserWithEmailAndPassword(mStrEmail,mStrPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            finish();
                        }
                        else
                        {
                            Toast.makeText(RegistrationActivity.this,"Registration Unsuccessful",Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });


    }
}
