package com.example.cervical_cancer;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmailsActivity extends AppCompatActivity {
    private static final String BASE_URL = "YOUR_FAST_API_BASE_URL";
    private static final String TAG = "EmailsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emails);

        // Create the Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build()) // You may customize the client if needed
                .build();

        // Create the API interface
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        // Create the EmailData object with subject and body
        EmailData emailData = new EmailData("Cervical cancer Awareness", "Cervical cancer is one of he dangerous but curable diseases when still at early stages.know your cervical health through regular screening");

        // Make the API call to the FAST API endpoint
        Call<ResponseBody> call = apiInterface.sendEmailToAllUsers(emailData);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Emails sent successfully");
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e(TAG, "Failed to send emails: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error sending emails: " + t.getMessage());
            }
        });
    }
}
