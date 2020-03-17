package com.example.erge.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.erge.ApiClient.ApiClient;
import com.example.erge.ApiInterface.ApiInterface;
import com.example.erge.ApiModel.UserRegistrationModel;
import com.example.erge.BaseActivity.BaseActivity;
import com.example.erge.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity implements Validator.ValidationListener {

    @NotEmpty
    EditText signupusername;
    @Email
    EditText signupemail;
    @NotEmpty
    EditText signupphone;
    @Password(min = 4)
    EditText signuppassword;
    @ConfirmPassword
    EditText signupconpassword;
    Button signupbtn;
    Validator validator;
    String username,email,phone,password,empty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        signupusername = findViewById(R.id.signupusername);
        signupemail = findViewById(R.id.signupemail);
        signupphone = findViewById(R.id.signupphone);
        signuppassword = findViewById(R.id.signuppassword);
        signupconpassword = findViewById(R.id.signupconpassword);

        if(getIntent().getExtras()!=null) {
            //intent api from login activity
            String email = getIntent().getExtras().getString("email");
            String id = getIntent().getExtras().getString("id");
            String imgurl = getIntent().getExtras().getString("img_url");
            signupemail.setText(email);
        }

        validator= new Validator(this);
        validator.setValidationListener(this);

        signupbtn = findViewById(R.id.registersignupbtn);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.blue));
        }
    }


    public void signin(View view) {
        Intent intent = new Intent(RegisterActivity.this, HomeScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void onValidationSucceeded()
    {
        progressDialog.show();
//        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
//        startActivity(intent);

        username = signupusername.getText().toString();
        email = signupemail.getText().toString();
        phone = signupphone.getText().toString();
        password = signuppassword.getText().toString();
        empty = "";
        ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
        Call<UserRegistrationModel> userRegister = apiInterface.userRegister(username,email,password,phone,empty,empty,empty,empty,empty);
        userRegister.enqueue(new Callback<UserRegistrationModel>() {
            @Override
            public void onResponse(Call<UserRegistrationModel> call, Response<UserRegistrationModel> response) {
                progressDialog.dismiss();
                if(response.body().isStatus())
                {
                    String firstname = response.body().getUser_data().getFirst_name();
                    String email = response.body().getUser_data().getEmail();
                    String phone = response.body().getUser_data().getPhone();
                    String id = response.body().getUser_data().getId();

                    SharedPreferences shpre = getSharedPreferences("msg",0);
                    SharedPreferences.Editor editor = shpre.edit();
                    editor.putString("firstname",firstname);
                    editor.putString("email",email);
                    editor.putString("phone",phone);
                    editor.putString("id",id);
                    editor.commit();

                    String msg = response.body().getMessage();
                    showOneTimeToast(msg);

                    SharedPreferences sharedPreferences = getSharedPreferences("user_token", 0);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("user_token",response.body().getUser_token());
                    editor.putBoolean("is_logged",true);
                    edit.apply();

                    Intent intent = new Intent(RegisterActivity.this,HomeScreenActivity.class);
                    startActivity(intent);
                }else {
                    String msg =  response.body().getMessage();
                    showOneTimeToast(msg);
                }

            }

            @Override
            public void onFailure(Call<UserRegistrationModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors)
    {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
