package com.example.cervical_cancer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("/send_email_to_all_users/")
    Call<ResponseBody> sendEmailToAllUsers(@Body EmailData emailData);
}
