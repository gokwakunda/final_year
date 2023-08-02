package com.example.cervical_cancer;

import com.google.gson.annotations.SerializedName;

public class RiskResponse {
    @SerializedName("riskScore")
    private String riskScore;

    public String getRiskScore() {
        return riskScore;
    }
}
