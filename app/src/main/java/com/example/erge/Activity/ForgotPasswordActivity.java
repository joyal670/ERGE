package com.example.erge.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erge.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText fuseremail;
    Button forgotbtn;
    TextView cancelbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        fuseremail = findViewById(R.id.forgotemail);

        forgotbtn = findViewById(R.id.forgotbtn);
        cancelbtn = findViewById(R.id.cancelbnt);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.blue));
        }
    }

    public void forgotpassword(View view)
    {
        Toast.makeText(this, "Forgot Password", Toast.LENGTH_SHORT).show();
    }

    public void signin(View view) {
        Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }


}
