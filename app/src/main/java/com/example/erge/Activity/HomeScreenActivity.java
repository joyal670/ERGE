package com.example.erge.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erge.ApiClient.ApiClient;
import com.example.erge.ApiInterface.ApiInterface;
import com.example.erge.ApiModel.LoadUserProfileModel;
import com.example.erge.ApiModel.currentOrderModel;
import com.example.erge.Fragments.Faq_Fragment;
import com.example.erge.Fragments.Home_Fragment;
import com.example.erge.Fragments.Logout_Fragment;
import com.example.erge.Fragments.Mycart_Fragment;
import com.example.erge.Fragments.Myorders_Fragment;
import com.example.erge.Fragments.Notification_Fragment;
import com.example.erge.Fragments.ProfileFragment;
import com.example.erge.Fragments.Settings_Fragment;
import com.example.erge.Fragments.TermsandConditions_Fragment;
import com.example.erge.Interface.refreshProfileImage;
import com.example.erge.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, refreshProfileImage {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToogle;
    private static final int CAMERA_REQUEST_CODE = 200;
    String cameraPermission[];
    ImageButton imgbtn;
    CircleImageView profileimg;

    public static refreshProfileImage refreshProfileImage;

    TextView headerName,headerEmail;

    String currentFragment = "other";
    String user_token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        refreshProfileImage = this;

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerToogle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);

//        imgbtn = findViewById(R.id.imgbtn);
        cameraPermission = new String[]{Manifest.permission.CAMERA};

        mDrawerLayout.addDrawerListener(mDrawerToogle);
        mDrawerToogle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        profileimg = navigationView.getHeaderView(0).findViewById(R.id.circularImg);

        navigationView.setNavigationItemSelectedListener(this);

        final Home_Fragment fragment= new Home_Fragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment,"Home");
        fragmentTransaction.commit();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.blue));
        }

        headerName = navigationView.getHeaderView(0).findViewById(R.id.header_name);
        headerEmail = navigationView.getHeaderView(0).findViewById(R.id.header_email);

        SharedPreferences sharedPreferences = getSharedPreferences("user_token", 0);
//        String tempname,tempemail,tempimage;
//        tempemail = sharedPreferences.getString("username","");
//        tempname = sharedPreferences.getString("name","");
//        tempimage = sharedPreferences.getString("image","");
        user_token = sharedPreferences.getString("user_token","");

        loadUserProfileApi();

//        headerEmail.setText(tempname);
//        headerName.setText(tempemail);
//        Picasso.get().load(tempimage).into(profileimg);
        profileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                currentFragment = "other";
                ProfileFragment profileFragment = new ProfileFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout,profileFragment,"Profile");
                fragmentTransaction.commit();
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
                {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    private void loadUserProfileApi()
    {
        ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
        Call<LoadUserProfileModel> loadProfiler = apiInterface.loadProfile(user_token);
        loadProfiler.enqueue(new Callback<LoadUserProfileModel>() {
            @Override
            public void onResponse(Call<LoadUserProfileModel> call, Response<LoadUserProfileModel> response) {
                if(response.body().isStatus())
                {
                    headerName.setText(response.body().getData().getFirst_name());
                    headerEmail.setText(response.body().getData().getEmail());
                    Picasso.get().load(response.body().getData().getProfile_pic()).into(profileimg);
                }
            }

            @Override
            public void onFailure(Call<LoadUserProfileModel> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        int id = menuItem.getItemId();
        if(id == R.id.home)
        {
            currentFragment = "home";
            Home_Fragment fragment = new Home_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout,fragment,"Home");
            fragmentTransaction.commit();
        }
        else if (id == R.id.myorders)
        {
            currentFragment = "other";
            Myorders_Fragment fragment= new Myorders_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout,fragment,"My Orders");
            fragmentTransaction.commit();
        }
        else if(id == R.id.mycart)
        {
            currentFragment = "other";
            Mycart_Fragment fragment= new Mycart_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout,fragment,"My Cart");
            fragmentTransaction.commit();
        }
        else if(id == R.id.notification)
        {
            currentFragment = "other";
            Notification_Fragment fragment= new Notification_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout,fragment,"Notification");
            fragmentTransaction.commit();
        }
        else if(id == R.id.termsandconditions)
        {
            currentFragment = "other";
            TermsandConditions_Fragment fragment= new TermsandConditions_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout,fragment,"Terms & Conditions");
            fragmentTransaction.commit();
        }
        else if(id == R.id.faq)
        {
            currentFragment = "other";
            Faq_Fragment fragment= new Faq_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout,fragment,"FAQ");
            fragmentTransaction.commit();
        }
        else if(id == R.id.settings)
        {
            currentFragment = "other";
            Settings_Fragment fragment= new Settings_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout,fragment,"Settings");
            fragmentTransaction.commit();
        }
        else if(id == R.id.logout)
        {
            currentFragment = "other";
            Logout_Fragment fragment= new Logout_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout,fragment,"Logout");
            fragmentTransaction.commit();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {

            if(currentFragment != "home")
            {
                Home_Fragment fragment= new Home_Fragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout,fragment,"Home");
                fragmentTransaction.commit();
            }
            else {
                super.onBackPressed();
            }
        }
    }

//    public void fnimageclick(View view)
//    {
//        String[] items = {" Camera", " Gallery"};
//        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
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
//
//    private void pickCamera()
//    {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent,1001);
//    }
//
//    private void pickGallery()
//    {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent,1000);
//    }
//
//    private boolean checkCameraPermission()
//    {
//        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
//        return result;
//    }
//
//    private void requestCameraPermission()
//    {
//        ActivityCompat.requestPermissions(this,cameraPermission,CAMERA_REQUEST_CODE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
//    {
//        switch (requestCode)
//        {
//            case  CAMERA_REQUEST_CODE:
//                if (grantResults.length > 0)
//                {
//                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    if (!cameraAccepted)
//                    {
//                        Toast.makeText(this,"Permissions Denied",Toast.LENGTH_SHORT).show();
//                    }
//                }
//                break;
//        }
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if( requestCode == 1001)
//        {
//            if( resultCode == RESULT_OK)
//            {
//                Bitmap photo = (Bitmap) data.getExtras().get("data");
//                profileimg.setImageBitmap(photo);
//            }
//        }
//        else if (requestCode == 1000)
//        {
//            if( resultCode == RESULT_OK)
//            {
//
//                Uri uri = data.getData();
//                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
//                    profileimg.setImageBitmap(bitmap);
//                }catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item_notification:
                Notification_Fragment fragmentNotification = new Notification_Fragment();
                FragmentTransaction fragmentTransactionnotification = getSupportFragmentManager().beginTransaction();
                fragmentTransactionnotification.replace(R.id.frame_layout,fragmentNotification,"Notification");
                fragmentTransactionnotification.commit();
                return true;
            case R.id.item_cart:
                Mycart_Fragment fragmentcart = new Mycart_Fragment();
                FragmentTransaction fragmentTransactioncart = getSupportFragmentManager().beginTransaction();
                fragmentTransactioncart.replace(R.id.frame_layout,fragmentcart,"My Cart");
                fragmentTransactioncart.commit();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onResume()
    {
        loadUserProfileApi();
        super.onResume();
    }

    @Override
    public void onRestart()
    {
        loadUserProfileApi();
        super.onRestart();
    }

    @Override
    public void refresh() {
        loadUserProfileApi();
    }
}
