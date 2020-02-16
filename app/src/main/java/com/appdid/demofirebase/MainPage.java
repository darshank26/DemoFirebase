package com.appdid.demofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainPage extends AppCompatActivity {

    Button mBtnLogout;
    TextView mTxtUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        mBtnLogout = findViewById(R.id.btn_logout);
        mTxtUser = findViewById(R.id.txt_current_user);

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

        mTxtUser.setText(mUser.getEmail());

        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainPage.this,MainActivity.class);
                startActivity(i);

            }
        });
    }
}
