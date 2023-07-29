//package com.example.cervical_cancer;
package com.example.cervical_cancer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    public static final String KEY_FIRST_LAUNCH = "isFirstLaunch";
    public static final String PREFS_NAME = "MyPrefsFile";
    private static final long SPLASH_SCREEN_DELAY = 2000;
//    private static final String PREFS_NAME = "MyPrefsFile";
//    private static final String KEY_FIRST_LAUNCH = "isFirstLaunch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Check if the app is launched for the first time
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isFirstLaunch = preferences.getBoolean(KEY_FIRST_LAUNCH, true);

        if (isFirstLaunch) {
            // If it's the first launch, set isFirstLaunch to false and save it in shared preferences
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(KEY_FIRST_LAUNCH, false);
            editor.apply();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start your main activity here
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Close the splash activity to prevent going back to it
            }
        }, SPLASH_SCREEN_DELAY);
    }
}

//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class SplashActivity extends AppCompatActivity {
//
//    private static final long SPLASH_SCREEN_DELAY = 2000;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Start your main activity here
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish(); // Close the splash activity to prevent going back to it
//            }
//
//        }, SPLASH_SCREEN_DELAY);
//    }
//}