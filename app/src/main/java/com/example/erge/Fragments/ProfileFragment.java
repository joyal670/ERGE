package com.example.erge.Fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erge.Activity.HomeScreenActivity;
import com.example.erge.ApiClient.ApiClient;
import com.example.erge.ApiInterface.ApiInterface;
import com.example.erge.ApiModel.ChangeProfileNameModel;
import com.example.erge.ApiModel.LoadUserProfileModel;
import com.example.erge.ApiModel.UpdateProfilePicModel;
import com.example.erge.ApiModel.currentOrderModel;
import com.example.erge.GetFilePath;
import com.example.erge.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView name,email,phonenum;
    Button save;
    CircleImageView profileimg;
    String user_token;

    private static final int CAMERA_REQUEST_CODE = 200;
    private static final  int STORAGE_REQUEST_CODE = 400;
    private static final  int STORAGE_GALLERY_REQUEST_CODE = 401;
    private static final  int IMAGE_PICK_GALLERY_CODE = 1000;
    private static final  int IMAGE_PICK_CAMERA_CODE = 1001;

    String cameraPermission[];
    String storagePermission[];

    public ProgressDialog progressDialog;

    Bitmap photo;
    File imageFile;
    Map<String, RequestBody> map;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        name = view.findViewById(R.id.profile_name);
        email = view.findViewById(R.id.profile_email);
        phonenum = view.findViewById(R.id.profile_phonenumber);
        save = view.findViewById(R.id.profile_Save);
        profileimg = view.findViewById(R.id.profile_user_image);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_token", 0);
//        String tempname,tempemail,tempimage,tempphone;
//        tempemail = sharedPreferences.getString("username","");
//        tempname = sharedPreferences.getString("name","");
//        tempimage = sharedPreferences.getString("image","");
//        tempphone = sharedPreferences.getString("phone","");
        user_token = sharedPreferences.getString("user_token","");

//        name.setText(tempname);
//        email.setText(tempemail);
//        phonenum.setText(tempphone);
//        Picasso.get().load(tempimage).into(profileimg);
        loadUserprofileApi();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().equals(""))
                {
                    progressDialog.show();
                    ApiInterface apiInterface = ApiClient.getClient(getActivity()).create(ApiInterface.class);
                    Call<ChangeProfileNameModel> changeName = apiInterface.changeName(user_token,name.getText().toString());
                    changeName.enqueue(new Callback<ChangeProfileNameModel>() {
                        @Override
                        public void onResponse(Call<ChangeProfileNameModel> call, Response<ChangeProfileNameModel> response) {
                            progressDialog.dismiss();
                            if(response.body().isStatus())
                            {
                                loadUserprofileApi();
                                Toast.makeText(getContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ChangeProfileNameModel> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), ""+t, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(getContext(), "Enter name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        profileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String[] items = {" Camera", " Gallery"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Select Image");
                dialog.setItems(items, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if (which == 0)
                        {
                            if(!checkCameraPermission())
                            {
                                requestCameraPermission();
                            }
                            else
                            {
                                pickCamera();
                            }
                        }
                        if (which == 1)
                        {
                            if (!checkStoragePermission())
                            {
                                //If not storage permission is Enabled
                                requestGalleryStoragePermission();
                            }
                            else
                            {
                                pickGallery();
                            }
                        }
                    }
                });
                dialog.create().show();
            }
        });

        return view;
    }


//    public void fnimageclick()
//    {
//        String[] items = {" Camera", " Gallery"};
//        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
//        dialog.setTitle("Select Image");
//        dialog.setItems(items, new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//                if (which == 0)
//                {
//                    if(!checkCameraPermission())
//                    {
//                        requestCameraPermission();
//                    }
//                    else
//                    {
//                        pickCamera();
//                    }
//                }
//                if (which == 1)
//                {
//                    if(!checkCameraPermission())
//                    {
//                        requestCameraPermission();
//                    }
//                    else
//                    {
//                        pickGallery();
//                    }
//                }
//            }
//        });
//        dialog.create().show();
//    }

    private void pickCamera()
    {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent,1001);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "img1.jpg");
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);
    }

    private void pickGallery()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_GALLERY_CODE);
    }

    private boolean checkCameraPermission()
    {
        boolean result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
//        boolean result1 = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestCameraPermission()
    {
        requestPermissions(cameraPermission,CAMERA_REQUEST_CODE);
    }

    private boolean checkStoragePermission()
    {
        //Checking for Storage Permission
        boolean result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestStoragePermission()
    {
        //Requesting for CameraPermission
        requestPermissions(storagePermission,STORAGE_REQUEST_CODE);
    }

    private void requestGalleryStoragePermission()
    {
        //Requesting for CameraPermission
        requestPermissions(storagePermission,STORAGE_GALLERY_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case  CAMERA_REQUEST_CODE:
                if (grantResults.length > 0)
                {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (!cameraAccepted)
                    {
                        Toast.makeText(getContext(),"Permissions Denied",Toast.LENGTH_SHORT).show();
                    }else{
                        requestStoragePermission();

                    }
                }
            case STORAGE_REQUEST_CODE:
                if (grantResults.length > 0)
                {
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (!writeStorageAccepted)
                    {
                        Toast.makeText(getContext(),"Permissions Denied",Toast.LENGTH_SHORT).show();
                    }else{
                        pickCamera();
                    }
                }
                break;
            case STORAGE_GALLERY_REQUEST_CODE:
                if (grantResults.length > 0)
                {
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (!writeStorageAccepted)
                    {
                        Toast.makeText(getContext(),"Permissions Denied",Toast.LENGTH_SHORT).show();
                    }else{
                        pickGallery();
                    }
                }
                break;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == IMAGE_PICK_CAMERA_CODE)
        {
//            if( resultCode == IMAGE_PICK_CAMERA_CODE)
//            {
                imageFile = new File(Environment.getExternalStorageDirectory() + File.separator + "img1.jpg");
//                profileimg.setImageBitmap(photo);
                Picasso.get().load(imageFile).into(profileimg);
                updateProfilePic();
//            }
        }
        else if (requestCode == IMAGE_PICK_GALLERY_CODE)
        {
//            if( resultCode == RESULT_OK)
//            {
                Uri uri = data.getData();
                try {
                    Uri selectedImageUri = data.getData();
                    GetFilePath getFilePath = new GetFilePath(getContext());
                    String selectedImagePath = getFilePath.getPath(selectedImageUri);
                    try {

                       imageFile = new File(selectedImagePath);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    photo = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
                    profileimg.setImageBitmap(photo);
                    updateProfilePic();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
//            }
        }
    }

    private void updateProfilePic()
    {
            map = new HashMap<>();
            if (imageFile != null) {
                RequestBody file = RequestBody.create(MediaType.parse("image/*"), imageFile);
                map.put("files\"; filename=\"LIJOGAL"  + ".jpg\" ", file);
            } else {
                RequestBody jobseekerImage = RequestBody.create(MediaType.parse("text/plain"), "");
                map.put("files", jobseekerImage);
            }

            callApi();
    }

    private void callApi()
    {
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getClient(getActivity()).create(ApiInterface.class);
        Call<UpdateProfilePicModel> updateProfile = apiInterface.updatePic(map, user_token);
        updateProfile.enqueue(new Callback<UpdateProfilePicModel>() {
            @Override
            public void onResponse(Call<UpdateProfilePicModel> call, Response<UpdateProfilePicModel> response) {
                progressDialog.dismiss();
                if (response.body().isStatus()) {
                    Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    HomeScreenActivity.refreshProfileImage.refresh();
                }
            }

            @Override
            public void onFailure(Call<UpdateProfilePicModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileName() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/TestFloder") ;
        if(!file.exists())
        {
            file.mkdirs();
        }
        String uriString = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriString;
    }

    private void loadUserprofileApi() {
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getClient(getActivity()).create(ApiInterface.class);
        Call<LoadUserProfileModel> loadProfiler = apiInterface.loadProfile(user_token);
        loadProfiler.enqueue(new Callback<LoadUserProfileModel>() {
            @Override
            public void onResponse(Call<LoadUserProfileModel> call, Response<LoadUserProfileModel> response) {
                progressDialog.dismiss();
                if(response.body().isStatus())
                {
                    name.setText(response.body().getData().getFirst_name());
                    email.setText(response.body().getData().getEmail());
                    phonenum.setText(response.body().getData().getPhone());
                    Picasso.get().load(response.body().getData().getProfile_pic()).into(profileimg);
                }
            }

            @Override
            public void onFailure(Call<LoadUserProfileModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}
