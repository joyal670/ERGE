package com.example.erge.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.erge.Activity.LoginActivity;
import com.example.erge.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Logout_Fragment extends Fragment {


    public Logout_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logout_, container, false);

        SharedPreferences sharedPreferences =getActivity().getSharedPreferences("user_token", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();


        return view;
    }

}
