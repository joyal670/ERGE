package com.example.erge.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erge.ApiClient.ApiClient;
import com.example.erge.ApiInterface.ApiInterface;
import com.example.erge.ApiModel.SocialLoginCheck;
import com.example.erge.ApiModel.UserLoginModel;
import com.example.erge.ApiModel.UserRegistrationModel;
import com.example.erge.BaseActivity.BaseActivity;
import com.example.erge.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener, Validator.ValidationListener {

    @NotEmpty
    @Email
    EditText useremail;

    @Password(min = 4)
    EditText password;

    TextView forgot, signup;
    ImageView fb, google;
    Button login;
    private CallbackManager callbackManager;
    private GoogleApiClient googleApiClient;
    private static final int SIGN_IN = 1;
    Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        useremail = findViewById(R.id.Useremail);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.loginbtn);
        signup = findViewById(R.id.signupbtn);
        forgot = findViewById(R.id.forgotbtn);
        fb = findViewById(R.id.imgFB);
        google = findViewById(R.id.google_sign);

        validator = new Validator(this);
        validator.setValidationListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        }

        //google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN);

            }
        });

        //facebook
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Success", "Login");
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile"));
            }
        });

        //checkLoginStatus();
    }


    public void login(View view) {
        validator.validate();
    }

    public void signup(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void forgot(View view) {

        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        //google
        if (requestCode == SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {

//                startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
//                finish();

                GoogleSignInAccount account = result.getSignInAccount();
                String gname = account.getDisplayName();
                String gemail = account.getEmail();
                String gid = account.getId();

                String gimage_url = null;
                try {
                    gimage_url = account.getPhotoUrl().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    gimage_url="";
                }

                Auth.GoogleSignInApi.signOut(googleApiClient);

                checkStatus(gid,gemail,gimage_url);

            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //facebook
    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null) {
                Toast.makeText(LoginActivity.this, "User Logged out", Toast.LENGTH_SHORT).show();
            } else {
                loaduserProfile(currentAccessToken);
            }

        }
    };


    private void loaduserProfile(AccessToken newAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String femail = object.getString("email");
                    String fid = object.getString("id");
                    String fimage_url = "http://graph.facebook.com/" + fid + "/picture?type=normal";

                    //username.setText(email);
                    //Toast.makeText(LoginActivity.this, ""+femail, Toast.LENGTH_SHORT).show();

                    checkStatus(fid, femail, fimage_url);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void checkStatus(final String fid, final String femail, final String fimage_url) {

        final ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
        Call<SocialLoginCheck> checkStatus = apiInterface.checkStatus(fid, "facebook");
        checkStatus.enqueue(new Callback<SocialLoginCheck>() {
            @Override
            public void onResponse(Call<SocialLoginCheck> call, Response<SocialLoginCheck> response) {
                progressDialog.dismiss();
                if (response.body().isStatus()) {

                    Call<UserLoginModel> userLogin = apiInterface.userLogin(femail, "", "", "", "facebook", fid, fimage_url);
                    userLogin.enqueue(new Callback<UserLoginModel>() {
                        @Override
                        public void onResponse(Call<UserLoginModel> call, Response<UserLoginModel> response) {
                            if (response.body().isStatus()) {
                                String msg = response.body().getMessage();
                                showOneTimeToast(msg);
                                Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
                                startActivity(intent);
                            } else {
                                String msg = response.body().getMessage();
                                showOneTimeToast(msg);
                            }
                        }

                        @Override
                        public void onFailure(Call<UserLoginModel> call, Throwable t) {
                            progressDialog.dismiss();
                        }
                    });

                } else {
                                String msg = response.body().getMessage();
                                showOneTimeToast(msg);
                                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                                intent.putExtra("email",femail);
                                intent.putExtra("id",fid);
                                intent.putExtra("img_url",fimage_url);
                                startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<SocialLoginCheck> call, Throwable t) {

            }
        });

    }

    private void checkLoginStatus(String fid) {
        if (AccessToken.getCurrentAccessToken() != null) {
            loaduserProfile(AccessToken.getCurrentAccessToken());
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure want to Exit?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }


    @Override
    public void onValidationSucceeded() {
        progressDialog.show();
        final String email = useremail.getText().toString();
        final String password1 = password.getText().toString();
        ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
        Call<UserLoginModel> userLogin = apiInterface.userLogin(email, password1, "", "", "", "", "");
        userLogin.enqueue(new Callback<UserLoginModel>() {
            @Override
            public void onResponse(Call<UserLoginModel> call, Response<UserLoginModel> response) {
                progressDialog.dismiss();
                if (response.body().isStatus())
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("user_token", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username",email);
                    editor.putString("password",password1);
                    editor.putString("user_token",response.body().getUser_token());
                    editor.putString("user_id",response.body().getUser_data().getId());
                    editor.putString("name",response.body().getUser_data().getFirst_name());
                    editor.putString("image",response.body().getUser_data().getProfile_pic());
                    editor.putString("phone",response.body().getUser_data().getPhone());
                    editor.putBoolean("is_logged",true);
                    editor.apply();

                    String msg = response.body().getMessage();
                    showOneTimeToast(msg);
                    Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
                    startActivity(intent);
                } else {
                    String msg = response.body().getMessage();
                    showOneTimeToast(msg);
                }
            }

            @Override
            public void onFailure(Call<UserLoginModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
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
    @Override
    protected void onResume()
    {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("user_token", 0);
        String defaultVal = "";
        String use_email = sharedPreferences.getString("username", defaultVal);
        String use_pass = sharedPreferences.getString("password", defaultVal);
        useremail.setText(use_email);
        password.setText(use_pass);

        boolean logg = sharedPreferences.getBoolean("is_logged",false);
        if(logg == true)
        {
            Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
            startActivity(intent);
        }
    }
}
