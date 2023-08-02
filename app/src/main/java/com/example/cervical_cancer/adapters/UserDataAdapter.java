package com.example.cervical_cancer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cervical_cancer.R;
import com.example.cervical_cancer.modals.UserData;

import java.util.List;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.UserDataViewHolder> {

    private List<UserData> userDataList;

    public UserDataAdapter(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }

    @NonNull
    @Override
    public UserDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_data, parent, false);
        return new UserDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDataViewHolder holder, int position) {
        UserData userData = userDataList.get(position);

        holder.textViewEmail.setText("Email: " + userData.getEmail());
        holder.textViewAge.setText("Age: " + userData.getAge());
        holder.textViewNumOfSexualPartners.setText("Number of Sexual Partners: " + userData.getNumberOfSexualPartners());
        holder.textViewFirstSexualIntercourse.setText("First Sexual Intercourse: " + userData.getFirstSexualIntercourse());
        holder.textViewNumOfPregnancies.setText("Number of Pregnancies: " + userData.getNumOfPregnancies());
        holder.textViewSmokes.setText("Smokes: " + userData.getSmokes());
        holder.textViewSmokesYears.setText("Smokes Years: " + userData.getSmokesYears());
        holder.textViewSmokesPacksYear.setText("Smokes Packs/Year: " + userData.getSmokesPacksYear());
        holder.textViewHormonalContraceptives.setText("Hormonal Contraceptives : " + userData.getHormonalContraceptives());
        holder.textViewHormonalContraceptivesYears.setText("Hormonal Contraceptives Years: " + userData.getHormonalContraceptivesYears());
        holder.textViewIUD.setText("IUD: " + userData.getIud());
        holder.textViewIUDYears.setText("IUD Years: " + userData.getIudYears());
        holder.textViewSTDs.setText("STDs: " + userData.getStds());
        holder.textViewSTDsNumber.setText("STDs Number: " + userData.getStdsNumber());
        holder.textViewSTDsCondylomatosis.setText("STDs Condylomatosis: " + userData.getStdsCondylomatosis());
        holder.textViewSTDsCervicalCondylomatosis.setText("STDs Cervical Condylomatosis: " + userData.getStdsCervicalCondylomatosis());
        holder.textViewSTDsVaginalCondylomatosis.setText("STDs Vaginal Condylomatosis: " + userData.getStdsVaginalCondylomatosis());
        holder.textViewSTDsVulvoPerinealCondylomatosis.setText("STDs Vulvo Perineal Condylomatosis: " + userData.getStdsVulvoPerinealCondylomatosis());
        holder.textViewSTDsSyphilis.setText("STDs Syphilis: " + userData.getStdsSyphilis());
        holder.textViewSTDsPelvicInflammatoryDisease.setText("STDs Pelvic Inflammatory Disease: " + userData.getStdsPelvicInflammatoryDisease());
        holder.textViewSTDsGenitalHerpes.setText("STDs Genital Herpes: " + userData.getStdsGenitalHerpes());
        holder.textViewSTDsMolluscumContagiosum.setText("STDs Molluscum Contagiosum: " + userData.getStdsMolluscumContagiosum());
        holder.textViewSTDsAIDS.setText("STDs AIDS: " + userData.getStdsAIDS());
        holder.textViewSTDsHIV.setText("STDs HIV: " + userData.getStdsHIV());
        holder.textViewSTDsHepatitisB.setText("STDs Hepatitis B: " + userData.getStdsHepatitisB());
        holder.textViewSTDsHPV.setText("STDs HPV: " + userData.getStdsHPV());
        holder.textViewSTDsNumberOfDiagnosis.setText("STDs Number of Diagnosis: " + userData.getStdsNumberOfDiagnosis());
        holder.textViewSTDsTimeSinceFirstDiagnosis.setText("STDs Time Since First Diagnosis: " + userData.getStdsTimeSinceFirstDiagnosis());
        holder.textViewSTDsTimeSinceLastDiagnosis.setText("STDs Time Since Last Diagnosis: " + userData.getStdsTimeSinceLastDiagnosis());

    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public static class UserDataViewHolder extends RecyclerView.ViewHolder {
        TextView textViewEmail;
        TextView textViewPrediction;
        TextView textViewAge;
        TextView textViewNumOfSexualPartners;
        TextView textViewFirstSexualIntercourse;
        TextView textViewNumOfPregnancies;
        TextView textViewSmokes;
        TextView textViewSmokesYears;
        TextView textViewSmokesPacksYear;
        TextView textViewHormonalContraceptives;
        TextView textViewHormonalContraceptivesYears;
        TextView textViewIUD;
        TextView textViewIUDYears;
        TextView textViewSTDs;
        TextView textViewSTDsNumber;
        TextView textViewSTDsCondylomatosis;
        TextView textViewSTDsCervicalCondylomatosis;
        TextView textViewSTDsVaginalCondylomatosis;
        TextView textViewSTDsVulvoPerinealCondylomatosis;
        TextView textViewSTDsSyphilis;
        TextView textViewSTDsPelvicInflammatoryDisease;
        TextView textViewSTDsGenitalHerpes;
        TextView textViewSTDsMolluscumContagiosum;
        TextView textViewSTDsAIDS;
        TextView textViewSTDsHIV;
        TextView textViewSTDsHepatitisB;
        TextView textViewSTDsHPV;
        TextView textViewSTDsNumberOfDiagnosis;
        TextView textViewSTDsTimeSinceFirstDiagnosis;
        TextView textViewSTDsTimeSinceLastDiagnosis;

        public UserDataViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewPrediction = itemView.findViewById(R.id.textViewPrediction);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            textViewNumOfSexualPartners = itemView.findViewById(R.id.textViewNumOfSexualPartners);
            textViewFirstSexualIntercourse = itemView.findViewById(R.id.textViewFirstSexualIntercourse);
            textViewNumOfPregnancies = itemView.findViewById(R.id.textViewNumOfPregnancies);
            textViewSmokes = itemView.findViewById(R.id.textViewSmokes);
            textViewSmokesYears = itemView.findViewById(R.id.textViewSmokesYears);
            textViewSmokesPacksYear = itemView.findViewById(R.id.textViewSmokesPacksYear);
            textViewHormonalContraceptives = itemView.findViewById(R.id.textViewHormonalContraceptives);
            textViewHormonalContraceptivesYears = itemView.findViewById(R.id.textViewHormonalContraceptivesYears);
            textViewIUD = itemView.findViewById(R.id.textViewIUD);
            textViewIUDYears = itemView.findViewById(R.id.textViewIUDYears);
            textViewSTDs = itemView.findViewById(R.id.textViewSTDs);
            textViewSTDsNumber = itemView.findViewById(R.id.textViewSTDsNumber);
            textViewSTDsCondylomatosis = itemView.findViewById(R.id.textViewSTDsCondylomatosis);
            textViewSTDsCervicalCondylomatosis = itemView.findViewById(R.id.textViewSTDsCervicalCondylomatosis);
            textViewSTDsVaginalCondylomatosis = itemView.findViewById(R.id.textViewSTDsVaginalCondylomatosis);
            textViewSTDsVulvoPerinealCondylomatosis = itemView.findViewById(R.id.textViewSTDsVulvoPerinealCondylomatosis);
            textViewSTDsSyphilis = itemView.findViewById(R.id.textViewSTDsSyphilis);
            textViewSTDsPelvicInflammatoryDisease = itemView.findViewById(R.id.textViewSTDsPelvicInflammatoryDisease);
            textViewSTDsGenitalHerpes = itemView.findViewById(R.id.textViewSTDsGenitalHerpes);
            textViewSTDsMolluscumContagiosum = itemView.findViewById(R.id.textViewSTDsMolluscumContagiosum);
            textViewSTDsAIDS = itemView.findViewById(R.id.textViewSTDsAIDS);
            textViewSTDsHIV = itemView.findViewById(R.id.textViewSTDsHIV);
            textViewSTDsHepatitisB = itemView.findViewById(R.id.textViewSTDsHepatitisB);
            textViewSTDsHPV = itemView.findViewById(R.id.textViewSTDsHPV);
            textViewSTDsNumberOfDiagnosis = itemView.findViewById(R.id.textViewSTDsNumberOfDiagnosis);
            textViewSTDsTimeSinceFirstDiagnosis = itemView.findViewById(R.id.textViewSTDsTimeSinceFirstDiagnosis);
            textViewSTDsTimeSinceLastDiagnosis = itemView.findViewById(R.id.textViewSTDsTimeSinceLastDiagnosis);
//
        }
    }
}



//package com.example.cervical_cancer.adapters;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.cervical_cancer.R;
//import com.example.cervical_cancer.modals.UserData;
//
//import java.util.List;
//
//public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.UserDataViewHolder> {
//
//    private List<UserData> userDataList;
//
//    public UserDataAdapter(List<UserData> userDataList) {
//        this.userDataList = userDataList;
//    }
//
//    @NonNull
//    @Override
//    public UserDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_data, parent, false);
//        return new UserDataViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull UserDataViewHolder holder, int position) {
//        UserData userData = userDataList.get(position);
//
//        holder.textViewEmail.setText("Email: " + userData.getEmail());
//        holder.textViewPrediction.setText("Prediction: " + userData.getPrediction());
//        holder.textViewAge.setText("Age: " + String.valueOf(userData.getAge()));
//        holder.textViewNumOfSexualPartners.setText("Number of Sexual Partners: " + String.valueOf(userData.getNumberOfSexualPartners()));
//        holder.textViewFirstSexualIntercourse.setText("First Sexual Intercourse: " + userData.getFirstSexualIntercourse());
//        holder.textViewNumOfPregnancies.setText("Number of Pregnancies: " + String.valueOf(userData.getNumOfPregnancies()));
//        holder.textViewSmokes.setText("Smokes: " + String.valueOf(userData.getSmokes()));
//        holder.textViewSmokesYears.setText("Smokes Years: " + String.valueOf(userData.getSmokesYears()));
//        holder.textViewSmokesPacksYear.setText("Smokes Packs/Year: " + String.valueOf(userData.getSmokesPacksYear()));
//        holder.textViewHormonalContraceptives.setText("Hormonal Contraceptives: " + String.valueOf(userData.getHormonalContraceptives()));
//        holder.textViewHormonalContraceptivesYears.setText("Hormonal Contraceptives Years: " + String.valueOf(userData.getHormonalContraceptivesYears()));
//        holder.textViewIUD.setText("IUD: " + String.valueOf(userData.getIud()));
//        holder.textViewIUDYears.setText("IUD Years: " + String.valueOf(userData.getIudYears()));
//        holder.textViewSTDs.setText("STDs: " + String.valueOf(userData.getStds()));
//        holder.textViewSTDsNumber.setText("STDs Number: " + String.valueOf(userData.getStdsNumber()));
//        holder.textViewSTDsCondylomatosis.setText("STDs Condylomatosis: " + String.valueOf(userData.getStdsCondylomatosis()));
//        holder.textViewSTDsCervicalCondylomatosis.setText("STDs Cervical Condylomatosis: " + String.valueOf(userData.getStdsCervicalCondylomatosis()));
//        holder.textViewSTDsVaginalCondylomatosis.setText("STDs Vaginal Condylomatosis: " + String.valueOf(userData.getStdsVaginalCondylomatosis()));
//        holder.textViewSTDsVulvoPerinealCondylomatosis.setText("STDs Vulvo Perineal Condylomatosis: " + String.valueOf(userData.getStdsVulvoPerinealCondylomatosis()));
//        holder.textViewSTDsSyphilis.setText("STDs Syphilis: " + String.valueOf(userData.getStdsSyphilis()));
//        holder.textViewSTDsPelvicInflammatoryDisease.setText("STDs Pelvic Inflammatory Disease: " + String.valueOf(userData.getStdsPelvicInflammatoryDisease()));
//        holder.textViewSTDsGenitalHerpes.setText("STDs Genital Herpes: " + String.valueOf(userData.getStdsGenitalHerpes()));
//        holder.textViewSTDsMolluscumContagiosum.setText("STDs Molluscum Contagiosum: " + String.valueOf(userData.getStdsMolluscumContagiosum()));
//        holder.textViewSTDsAIDS.setText("STDs AIDS: " + String.valueOf(userData.getStdsAIDS()));
//        holder.textViewSTDsHIV.setText("STDs HIV: " + String.valueOf(userData.getStdsHIV()));
//        holder.textViewSTDsHepatitisB.setText("STDs Hepatitis B: " + String.valueOf(userData.getStdsHepatitisB()));
//        holder.textViewSTDsHPV.setText("STDs HPV: " + String.valueOf(userData.getStdsHPV()));
//        holder.textViewSTDsNumberOfDiagnosis.setText("STDs Number of Diagnosis: " + String.valueOf(userData.getStdsNumberOfDiagnosis()));
//        holder.textViewSTDsTimeSinceFirstDiagnosis.setText("STDs Time Since First Diagnosis: " + String.valueOf(userData.getStdsTimeSinceFirstDiagnosis()));
//        holder.textViewSTDsTimeSinceLastDiagnosis.setText("STDs Time Since Last Diagnosis: " + String.valueOf(userData.getStdsTimeSinceLastDiagnosis()));
//    }
//
//    @Override
//    public int getItemCount() {
//        return userDataList.size();
//    }
//
//    public static class UserDataViewHolder extends RecyclerView.ViewHolder {
//        TextView textViewEmail;
//        TextView textViewPrediction;
//        TextView textViewAge;
//        TextView textViewNumOfSexualPartners;
//        TextView textViewFirstSexualIntercourse;
//        TextView textViewNumOfPregnancies;
//        TextView textViewSmokes;
//        TextView textViewSmokesYears;
//        TextView textViewSmokesPacksYear;
//        TextView textViewHormonalContraceptives;
//        TextView textViewHormonalContraceptivesYears;
//        TextView textViewIUD;
//        TextView textViewIUDYears;
//        TextView textViewSTDs;
//        TextView textViewSTDsNumber;
//        TextView textViewSTDsCondylomatosis;
//        TextView textViewSTDsCervicalCondylomatosis;
//        TextView textViewSTDsVaginalCondylomatosis;
//        TextView textViewSTDsVulvoPerinealCondylomatosis;
//        TextView textViewSTDsSyphilis;
//        TextView textViewSTDsPelvicInflammatoryDisease;
//        TextView textViewSTDsGenitalHerpes;
//        TextView textViewSTDsMolluscumContagiosum;
//        TextView textViewSTDsAIDS;
//        TextView textViewSTDsHIV;
//        TextView textViewSTDsHepatitisB;
//        TextView textViewSTDsHPV;
//        TextView textViewSTDsNumberOfDiagnosis;
//        TextView textViewSTDsTimeSinceFirstDiagnosis;
//        TextView textViewSTDsTimeSinceLastDiagnosis;
//
//        public UserDataViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            textViewEmail = itemView.findViewById(R.id.textViewEmail);
//            textViewPrediction = itemView.findViewById(R.id.textViewPrediction);
//            textViewAge = itemView.findViewById(R.id.textViewAge);
//            textViewNumOfSexualPartners = itemView.findViewById(R.id.textViewNumOfSexualPartners);
//            textViewFirstSexualIntercourse = itemView.findViewById(R.id.textViewFirstSexualIntercourse);
//            textViewNumOfPregnancies = itemView.findViewById(R.id.textViewNumOfPregnancies);
//            textViewSmokes = itemView.findViewById(R.id.textViewSmokes);
//            textViewSmokesYears = itemView.findViewById(R.id.textViewSmokesYears);
//            textViewSmokesPacksYear = itemView.findViewById(R.id.textViewSmokesPacksYear);
//            textViewHormonalContraceptives = itemView.findViewById(R.id.textViewHormonalContraceptives);
//            textViewHormonalContraceptivesYears = itemView.findViewById(R.id.textViewHormonalContraceptivesYears);
//            textViewIUD = itemView.findViewById(R.id.textViewIUD);
//            textViewIUDYears = itemView.findViewById(R.id.textViewIUDYears);
//            textViewSTDs = itemView.findViewById(R.id.textViewSTDs);
//            textViewSTDsNumber = itemView.findViewById(R.id.textViewSTDsNumber);
//            textViewSTDsCondylomatosis = itemView.findViewById(R.id.textViewSTDsCondylomatosis);
//            textViewSTDsCervicalCondylomatosis = itemView.findViewById(R.id.textViewSTDsCervicalCondylomatosis);
//            textViewSTDsVaginalCondylomatosis = itemView.findViewById(R.id.textViewSTDsVaginalCondylomatosis);
//            textViewSTDsVulvoPerinealCondylomatosis = itemView.findViewById(R.id.textViewSTDsVulvoPerinealCondylomatosis);
//            textViewSTDsSyphilis = itemView.findViewById(R.id.textViewSTDsSyphilis);
//            textViewSTDsPelvicInflammatoryDisease = itemView.findViewById(R.id.textViewSTDsPelvicInflammatoryDisease);
//            textViewSTDsGenitalHerpes = itemView.findViewById(R.id.textViewSTDsGenitalHerpes);
//            textViewSTDsMolluscumContagiosum = itemView.findViewById(R.id.textViewSTDsMolluscumContagiosum);
//            textViewSTDsAIDS = itemView.findViewById(R.id.textViewSTDsAIDS);
//            textViewSTDsHIV = itemView.findViewById(R.id.textViewSTDsHIV);
//            textViewSTDsHepatitisB = itemView.findViewById(R.id.textViewSTDsHepatitisB);
//            textViewSTDsHPV = itemView.findViewById(R.id.textViewSTDsHPV);
//            textViewSTDsNumberOfDiagnosis = itemView.findViewById(R.id.textViewSTDsNumberOfDiagnosis);
//            textViewSTDsTimeSinceFirstDiagnosis = itemView.findViewById(R.id.textViewSTDsTimeSinceFirstDiagnosis);
//            textViewSTDsTimeSinceLastDiagnosis = itemView.findViewById(R.id.textViewSTDsTimeSinceLastDiagnosis);
//        }
//    }
//}

//package com.example.cervical_cancer.adapters;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.cervical_cancer.R;
//import com.example.cervical_cancer.modals.UserData;
//
//import java.util.List;
//
//public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.UserDataViewHolder> {
//
//    private List<UserData> userDataList;
//
//    public UserDataAdapter(List<UserData> userDataList) {
//        this.userDataList = userDataList;
//    }
//
//    @NonNull
//    @Override
//    public UserDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_data, parent, false);
//        return new UserDataViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull UserDataViewHolder holder, int position) {
//        UserData userData = userDataList.get(position);
//
//        holder.textViewEmail.setText("Email: " + userData.getEmail());
//        holder.textViewPrediction.setText("Prediction: " + userData.getPrediction());
//        holder.textViewAge.setText("Age: " + userData.getAge());
//        holder.textViewNumOfSexualPartners.setText("Number of Sexual Partners: " + userData.getNumberOfSexualPartners());
//        holder.textViewFirstSexualIntercourse.setText("First Sexual Intercourse: " + userData.getFirstSexualIntercourse());
//        holder.textViewNumOfPregnancies.setText("Number of Pregnancies: " + userData.getNumOfPregnancies());
//        holder.textViewSmokes.setText("Smokes: " + userData.getSmokes());
//        holder.textViewSmokesYears.setText("Smokes Years: " + userData.getSmokesYears());
//        holder.textViewSmokesPacksYear.setText("Smokes Packs/Year: " + userData.getSmokesPacksYear());
//        holder.textViewHormonalContraceptives.setText("Hormonal Contraceptives: " + userData.getHormonalContraceptives());
//        holder.textViewHormonalContraceptivesYears.setText("Hormonal Contraceptives Years: " + userData.getHormonalContraceptivesYears());
//        holder.textViewIUD.setText("IUD: " + userData.getIud());
//        holder.textViewIUDYears.setText("IUD Years: " + userData.getIudYears());
//        holder.textViewSTDs.setText("STDs: " + userData.getStds());
//        holder.textViewSTDsNumber.setText("STDs Number: " + userData.getStdsNumber());
//        holder.textViewSTDsCondylomatosis.setText("STDs Condylomatosis: " + userData.getStdsCondylomatosis());
//        holder.textViewSTDsCervicalCondylomatosis.setText("STDs Cervical Condylomatosis: " + userData.getStdsCervicalCondylomatosis());
//        holder.textViewSTDsVaginalCondylomatosis.setText("STDs Vaginal Condylomatosis: " + userData.getStdsVaginalCondylomatosis());
//        holder.textViewSTDsVulvoPerinealCondylomatosis.setText("STDs Vulvo Perineal Condylomatosis: " + userData.getStdsVulvoPerinealCondylomatosis());
//        holder.textViewSTDsSyphilis.setText("STDs Syphilis: " + userData.getStdsSyphilis());
//        holder.textViewSTDsPelvicInflammatoryDisease.setText("STDs Pelvic Inflammatory Disease: " + userData.getStdsPelvicInflammatoryDisease());
//        holder.textViewSTDsGenitalHerpes.setText("STDs Genital Herpes: " + userData.getStdsGenitalHerpes());
//        holder.textViewSTDsMolluscumContagiosum.setText("STDs Molluscum Contagiosum: " + userData.getStdsMolluscumContagiosum());
//        holder.textViewSTDsAIDS.setText("STDs AIDS: " + userData.getStdsAIDS());
//        holder.textViewSTDsHIV.setText("STDs HIV: " + userData.getStdsHIV());
//        holder.textViewSTDsHepatitisB.setText("STDs Hepatitis B: " + userData.getStdsHepatitisB());
//        holder.textViewSTDsHPV.setText("STDs HPV: " + userData.getStdsHPV());
//        holder.textViewSTDsNumberOfDiagnosis.setText("STDs Number of Diagnosis: " + userData.getStdsNumberOfDiagnosis());
//        holder.textViewSTDsTimeSinceFirstDiagnosis.setText("STDs Time Since First Diagnosis: " + userData.getStdsTimeSinceFirstDiagnosis());
//        holder.textViewSTDsTimeSinceLastDiagnosis.setText("STDs Time Since Last Diagnosis: " + userData.getStdsTimeSinceLastDiagnosis());
//    }
//
//    @Override
//    public int getItemCount() {
//        return userDataList.size();
//    }
//
//    public static class UserDataViewHolder extends RecyclerView.ViewHolder {
//        TextView textViewEmail;
//        TextView textViewPrediction;
//        TextView textViewAge;
//        TextView textViewNumOfSexualPartners;
//        TextView textViewFirstSexualIntercourse;
//        TextView textViewNumOfPregnancies;
//        TextView textViewSmokes;
//        TextView textViewSmokesYears;
//        TextView textViewSmokesPacksYear;
//        TextView textViewHormonalContraceptives;
//        TextView textViewHormonalContraceptivesYears;
//        TextView textViewIUD;
//        TextView textViewIUDYears;
//        TextView textViewSTDs;
//        TextView textViewSTDsNumber;
//        TextView textViewSTDsCondylomatosis;
//        TextView textViewSTDsCervicalCondylomatosis;
//        TextView textViewSTDsVaginalCondylomatosis;
//        TextView textViewSTDsVulvoPerinealCondylomatosis;
//        TextView textViewSTDsSyphilis;
//        TextView textViewSTDsPelvicInflammatoryDisease;
//        TextView textViewSTDsGenitalHerpes;
//        TextView textViewSTDsMolluscumContagiosum;
//        TextView textViewSTDsAIDS;
//        TextView textViewSTDsHIV;
//        TextView textViewSTDsHepatitisB;
//        TextView textViewSTDsHPV;
//        TextView textViewSTDsNumberOfDiagnosis;
//        TextView textViewSTDsTimeSinceFirstDiagnosis;
//        TextView textViewSTDsTimeSinceLastDiagnosis;
//
//        public UserDataViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            textViewEmail = itemView.findViewById(R.id.textViewEmail);
//            textViewPrediction = itemView.findViewById(R.id.textViewPrediction);
//            textViewAge = itemView.findViewById(R.id.textViewAge);
//            textViewNumOfSexualPartners = itemView.findViewById(R.id.textViewNumOfSexualPartners);
//            textViewFirstSexualIntercourse = itemView.findViewById(R.id.textViewFirstSexualIntercourse);
//            textViewNumOfPregnancies = itemView.findViewById(R.id.textViewNumOfPregnancies);
//            textViewSmokes = itemView.findViewById(R.id.textViewSmokes);
//            textViewSmokesYears = itemView.findViewById(R.id.textViewSmokesYears);
//            textViewSmokesPacksYear = itemView.findViewById(R.id.textViewSmokesPacksYear);
//            textViewHormonalContraceptives = itemView.findViewById(R.id.textViewHormonalContraceptives);
//            textViewHormonalContraceptivesYears = itemView.findViewById(R.id.textViewHormonalContraceptivesYears);
//            textViewIUD = itemView.findViewById(R.id.textViewIUD);
//            textViewIUDYears = itemView.findViewById(R.id.textViewIUDYears);
//            textViewSTDs = itemView.findViewById(R.id.textViewSTDs);
//            textViewSTDsNumber = itemView.findViewById(R.id.textViewSTDsNumber);
//            textViewSTDsCondylomatosis = itemView.findViewById(R.id.textViewSTDsCondylomatosis);
//            textViewSTDsCervicalCondylomatosis = itemView.findViewById(R.id.textViewSTDsCervicalCondylomatosis);
//            textViewSTDsVaginalCondylomatosis = itemView.findViewById(R.id.textViewSTDsVaginalCondylomatosis);
//            textViewSTDsVulvoPerinealCondylomatosis = itemView.findViewById(R.id.textViewSTDsVulvoPerinealCondylomatosis);
//            textViewSTDsSyphilis = itemView.findViewById(R.id.textViewSTDsSyphilis);
//            textViewSTDsPelvicInflammatoryDisease = itemView.findViewById(R.id.textViewSTDsPelvicInflammatoryDisease);
//            textViewSTDsGenitalHerpes = itemView.findViewById(R.id.textViewSTDsGenitalHerpes);
//            textViewSTDsMolluscumContagiosum = itemView.findViewById(R.id.textViewSTDsMolluscumContagiosum);
//            textViewSTDsAIDS = itemView.findViewById(R.id.textViewSTDsAIDS);
//            textViewSTDsHIV = itemView.findViewById(R.id.textViewSTDsHIV);
//            textViewSTDsHepatitisB = itemView.findViewById(R.id.textViewSTDsHepatitisB);
//            textViewSTDsHPV = itemView.findViewById(R.id.textViewSTDsHPV);
//            textViewSTDsNumberOfDiagnosis = itemView.findViewById(R.id.textViewSTDsNumberOfDiagnosis);
//            textViewSTDsTimeSinceFirstDiagnosis = itemView.findViewById(R.id.textViewSTDsTimeSinceFirstDiagnosis);
//            textViewSTDsTimeSinceLastDiagnosis = itemView.findViewById(R.id.textViewSTDsTimeSinceLastDiagnosis);
//        }
//    }
//}
