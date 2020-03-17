package com.example.erge.Fragments;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erge.ApiClient.ApiClient;
import com.example.erge.ApiInterface.ApiInterface;
import com.example.erge.ApiModel.UpdatePasswordModel;
import com.example.erge.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Settings_Fragment extends Fragment  {

    TextView passwordBtn;
    EditText oldpassword;
    EditText newPass;
    EditText confirmPass;

    public ProgressDialog progressDialog;

    Button cancel,save;
    String user_token;

    String oldpas,newpas,conpas;

    public Settings_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_settings_, container, false);

        SharedPreferences sha = this.getActivity().getSharedPreferences("user_token",0);
        String def = "can't get user token";
        user_token = sha.getString("user_token",def);

        passwordBtn = view.findViewById(R.id.settings_changePassword);
        passwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
        return view;
    }

    private void changePassword()
    {
        LayoutInflater li = LayoutInflater.from(getContext());
        View newPassword = li.inflate(R.layout.changepassword,null);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
        alertBuilder.setView(newPassword);
        oldpassword = newPassword.findViewById(R.id.changepasswordOldPass);
        newPass = newPassword.findViewById(R.id.changepasswordNewPass);
        confirmPass = newPassword.findViewById(R.id.changepasswordOldPass1);
        save = newPassword.findViewById(R.id.changepasswordSave);
        cancel = newPassword.findViewById(R.id.changepasswordCancel);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        final AlertDialog alertDialog = alertBuilder.create();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpas,newpas,conpas;
                oldpas = oldpassword.getText().toString();
                newpas = newPass.getText().toString();
                conpas = confirmPass.getText().toString();
                if (oldpas.isEmpty() || newpas.isEmpty() || conpas.isEmpty())
                {
                    Toast.makeText(getContext(), "All Fileds are Required", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(newpas.equalsIgnoreCase(conpas)) {
                        oldpas = oldpassword.getText().toString();
                        newpas = newPass.getText().toString();
                        conpas = confirmPass.getText().toString();
                        if (newpas.trim().length() < 4 || conpas.trim().length() < 4) {
                            newPass.setError("Minimun 4 characters");
                            confirmPass.setError("Minimun 4 characters");
                        } else {
                            progressDialog.show();
                            ApiInterface apiInterface = ApiClient.getClient(getActivity()).create(ApiInterface.class);
                            Call<UpdatePasswordModel> updatePassword = apiInterface.updatepassword(user_token, oldpas, conpas);
                            updatePassword.enqueue(new Callback<UpdatePasswordModel>() {
                                @Override
                                public void onResponse(Call<UpdatePasswordModel> call, Response<UpdatePasswordModel> response) {
                                    progressDialog.dismiss();
                                    if (response.body().isStatus()) {
                                        Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        alertDialog.cancel();
                                    } else {
                                        Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<UpdatePasswordModel> call, Throwable t) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "" + t, Toast.LENGTH_SHORT).show();

                                }
                            });

                        }
                    }
                    else {
                        confirmPass.setError("Password Not Matched");
                    }

                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }
}
