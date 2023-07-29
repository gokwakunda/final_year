package com.example.cervical_cancer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    private boolean isMediaManagerInitialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the app is launched for the first time (coming from SplashActivity)
        SharedPreferences preferences = getSharedPreferences(SplashActivity.PREFS_NAME, MODE_PRIVATE);
        boolean isFirstLaunch = preferences.getBoolean(SplashActivity.KEY_FIRST_LAUNCH, true);
        if (isFirstLaunch) {
            // If it's the first launch, start the SplashActivity and finish this activity
            startActivity(new Intent(this, SplashActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.video);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_profile) {
            // Replace the current fragment with the SettingFragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            SettingFragment settingFragment = new SettingFragment();
            fragmentTransaction.replace(R.id.flFragment, settingFragment); // Replace R.id.flFragment with your container ID
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation item selection for the BottomNavigationView
        switch (item.getItemId()) {
            case R.id.videos:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new BlankFragment()).commit();
                return true;
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new HomeFragment()).commit();
                return true;
            case R.id.createissue:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new CreatePostFragment()).commit();
                return true;
            case R.id.risk:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new RiskAssessmentFragment()).commit();
                return true;
            case R.id.users:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new FriendsFragment()).commit();
                return true;
        }
        return false;
    }
}

//package com.example.cervical_cancer;
//
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.firebase.FirebaseApp;
//
//public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
//
//    BottomNavigationView bottomNavigationView;
//    private boolean isMediaManagerInitialized = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Initialize Firebase
//        FirebaseApp.initializeApp(this);
//
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setSelectedItemId(R.id.video);
//        bottomNavigationView.setSelectedItemId(R.id.home);
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.profile, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.menu_item_profile) {
//            // Replace the current fragment with the SettingFragment
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//            SettingFragment settingFragment = new SettingFragment();
//            fragmentTransaction.replace(R.id.flFragment, settingFragment); // Replace R.id.flFragment with your container ID
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        // Handle navigation item selection for the BottomNavigationView
//        switch (item.getItemId()) {
//            case R.id.videos:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new BlankFragment()).commit();
//                return true;
//            case R.id.home:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new HomeFragment()).commit();
//                return true;
//            case R.id.createissue:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new CreatePostFragment()).commit();
//                return true;
//            case R.id.risk:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new RiskAssessmentFragment()).commit();
//                return true;
//            case R.id.users:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new FriendsFragment()).commit();
//                return true;
//
//        }
//        return false;
//    }
//}

//import android.os.Bundle;
//import android.view.MenuItem;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.firebase.FirebaseApp;
//
//public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
//    BottomNavigationView bottomNavigationView;
//    private boolean isMediaManagerInitialized = false;
////    private RiskAssessment riskAssessment;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
////        FragmentManager fragmentManager = getSupportFragmentManager();
//        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        RiskAssessmentFragment riskAssessmentFragment = new RiskAssessmentFragment();
//       // fragmentTransaction.add(R.id.risk, riskAssessmentFragment);  // Replace R.id.fragment_container with your container ID
//       // fragmentTransaction.commit();
//
//
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setSelectedItemId(R.id.video);
//        bottomNavigationView.setSelectedItemId(R.id.home);
//
//// Add this code in your MainActivity or Application class
//        FirebaseApp.initializeApp(this);
//
////
////
////        View recyclerView = findViewById(R.id.recyclerViewVideos);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////
////        // Fetch videos from your backend API and pass the list to the adapter
////        List<Video> videos = // Your list of Video objects obtained from the API
////                videoAdapter = new VideoAdapter(videos);
////        recyclerView.setAdapter(videoAdapter);
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
////            case R.id.welcome:
////                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new WelcomeFragment() ).commit();
//            case R.id.videos:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new BlankFragment() ).commit();
//                return true;
//            case R.id.home:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new HomeFragment() ).commit();
//                return true;
//            case R.id.createissue:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new CreatePostFragment()).commit();
//                return true;
//            case R.id.risk:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new RiskAssessmentFragment()).commit();
//                return true;
//            case R.id.users:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new FriendsFragment()).commit();
//                return true;
////            case R.id.pageCard6:
////                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new RiskAssessmentFragment() ).commit();
////                return true;
//        }
//        return false;
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }
//
//
//
//    }
//
