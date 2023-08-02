package com.example.cervical_cancer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cervical_cancer.modals.InputData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RiskAssessmentFragment extends Fragment {
    private ProgressDialog progressDialog;
    private ApiService apiService;
    private Spinner spinnerAge;
    private Spinner spinnerNumPartners;
    private Spinner spinnerFirstIntercourse;
    private Spinner spinnerNumPregnancies;
    private Spinner spinnerSmokes;
    private Spinner spinnerSmokesYears;
    private Spinner spinnerSmokesPacksYear;
    private Spinner spinnerHormonalContraceptives;
    private Spinner spinnerHormonalContraceptivesYears;
    private Spinner spinnerIUD;
    private Spinner spinnerIUDYears;
    private Spinner spinnerSTDs;
    private Spinner spinnerSTDsNumber;
    private Spinner spinnerSTDsCondylomatosis;
    private Spinner spinnerSTDsCervicalCondylomatosis;
    private Spinner spinnerSTDsVaginalCondylomatosis;
    private Spinner spinnerSTDsVulvoPerinealCondylomatosis;
    private Spinner spinnerSTDsSyphilis;
    private Spinner spinnerSTDsPelvicInflammatoryDisease;
    private Spinner spinnerSTDsGenitalHerpes;
    private Spinner spinnerSTDsMolluscumContagiosum;
    private Spinner spinnerSTDsAIDS;
    private Spinner spinnerSTDsHIV;
    private Spinner spinnerSTDsHepatitisB;
    private Spinner spinnerSTDsHPV;
    private Spinner spinnerSTDsNumberOfDiagnosis;
    private Spinner spinnerSTDsTimeSinceFirstDiagnosis;
    private Spinner spinnerSTDsTimeSinceLastDiagnosis;
    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Nullable

        @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_risk_assessment, container, false);
//        FragmentManager fragmentManager = getSupportFragmentManager();
        // Retrofit client setup
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:8000/")  // Replace with your API URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        // Initialize the Firebase Realtime Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();
        // Find the Predict button by its ID

        Button predictButton = view.findViewById(R.id.buttonPredict);

        // Set up OnClickListener for the Predict button
        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform prediction when the Predict button is clicked
                performPrediction();
            }
        });



        // Initialize spinners
        spinnerAge = view.findViewById(R.id.spinnerAge);
        spinnerNumPartners = view.findViewById(R.id.spinnerNumPartners);
        spinnerFirstIntercourse = view.findViewById(R.id.spinnerFirstIntercourse);
        spinnerNumPregnancies = view.findViewById(R.id.spinnerNumPregnancies);
        spinnerSmokes = view.findViewById(R.id.spinnerSmokes);
        spinnerSmokesYears = view.findViewById(R.id.spinnerSmokesYears);
        spinnerSmokesPacksYear = view.findViewById(R.id.spinnerSmokesPacksYear);
        spinnerHormonalContraceptives = view.findViewById(R.id.spinnerHormonalContraceptives);
        spinnerHormonalContraceptivesYears = view.findViewById(R.id.spinnerHormonalContraceptivesYears);
        spinnerIUD = view.findViewById(R.id.spinnerIUD);
        spinnerIUDYears = view.findViewById(R.id.spinnerIUDYears);
        spinnerSTDs = view.findViewById(R.id.spinnerSTDs);
        spinnerSTDsNumber = view.findViewById(R.id.spinnerSTDsNumber);
        spinnerSTDsCondylomatosis = view.findViewById(R.id.spinnerSTDsCondylomatosis);
        spinnerSTDsCervicalCondylomatosis = view.findViewById(R.id.spinnerSTDsCervicalCondylomatosis);
        spinnerSTDsVaginalCondylomatosis = view.findViewById(R.id.spinnerSTDsVaginalCondylomatosis);
        spinnerSTDsVulvoPerinealCondylomatosis = view.findViewById(R.id.spinnerSTDsVulvoPerinealCondylomatosis);
        spinnerSTDsSyphilis = view.findViewById(R.id.spinnerSTDsSyphilis);
        spinnerSTDsPelvicInflammatoryDisease = view.findViewById(R.id.spinnerSTDPelvicInflammatoryDisease);
        spinnerSTDsGenitalHerpes = view.findViewById(R.id.spinnerSTDsGenitalHerpes);
        spinnerSTDsMolluscumContagiosum = view.findViewById(R.id.spinnerSTDsMolluscumContagiosum);
        spinnerSTDsAIDS = view.findViewById(R.id.spinnerSTDAIDS);
        spinnerSTDsHIV = view.findViewById(R.id.spinnerSTDsHIV);
        spinnerSTDsHepatitisB = view.findViewById(R.id.spinnerSTDsHepatitisB);
        spinnerSTDsHPV = view.findViewById(R.id.spinnerSTDsHPV);
        spinnerSTDsNumberOfDiagnosis = view.findViewById(R.id.spinnerSTDsNumDiagnosis);
        spinnerSTDsTimeSinceFirstDiagnosis = view.findViewById(R.id.spinnerSTDsTimeSinceFirstDiagnosis);
        spinnerSTDsTimeSinceLastDiagnosis = view.findViewById(R.id.spinnerSTDsTimeSinceLastDiagnosis);


        int[] ageArray = getResources().getIntArray(R.array.age_array);
        IntArrayAdapter ageAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, ageArray);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAge.setAdapter(ageAdapter);
// Create IntegerArrayAdapter for numPartnersSpinner
        // Example for numPartnersSpinner
        int[] numPartnersArray = getResources().getIntArray(R.array.num_partners_array);
        IntArrayAdapter numPartnersAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, numPartnersArray);
        numPartnersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNumPartners.setAdapter(numPartnersAdapter);

// Create IntArrayAdapter for firstIntercourseSpinner
        int[] firstIntercourseArray = getResources().getIntArray(R.array.first_intercourse_array);
        IntArrayAdapter firstIntercourseAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, firstIntercourseArray);
        firstIntercourseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFirstIntercourse.setAdapter(firstIntercourseAdapter);

// Create IntArrayAdapter for numPregnanciesSpinner
        int[] numPregnanciesArray = getResources().getIntArray(R.array.num_pregnancies_array);
        IntArrayAdapter numPregnanciesAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, numPregnanciesArray);
        numPregnanciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNumPregnancies.setAdapter(numPregnanciesAdapter);

// Create IntArrayAdapter for smokesYearsSpinner
        int[] smokesYearsArray = getResources().getIntArray(R.array.smokes_years_array);
        IntArrayAdapter smokesYearsAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, smokesYearsArray);
        smokesYearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSmokesYears.setAdapter(smokesYearsAdapter);

// Create IntArrayAdapter for smokesPacksYearSpinner
        int[] smokesPacksYearArray = getResources().getIntArray(R.array.smokes_packs_per_year_array);
        IntArrayAdapter smokesPacksYearAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, smokesPacksYearArray);
        smokesPacksYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSmokesPacksYear.setAdapter(smokesPacksYearAdapter);
        // Create IntArrayAdapter for spinnerSmokes
        int[] smokesArray = getResources().getIntArray(R.array.smokes_array);
        IntArrayAdapter smokesAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, smokesArray);
        smokesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSmokes.setAdapter(smokesAdapter);

// Create IntArrayAdapter for spinnerHormonalContraceptives
        int[] hormonalContraceptivesArray = getResources().getIntArray(R.array.hormonal_contraceptives_array);
        IntArrayAdapter hormonalContraceptivesAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, hormonalContraceptivesArray);
        hormonalContraceptivesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHormonalContraceptives.setAdapter(hormonalContraceptivesAdapter);

// Create IntArrayAdapter for spinnerIUD
        int[] iudArray = getResources().getIntArray(R.array.iud_array);
        IntArrayAdapter iudAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, iudArray);
        iudAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIUD.setAdapter(iudAdapter);

// Create IntArrayAdapter for spinnerSTDsCondylomatosis
        int[] stdsCondylomatosisArray = getResources().getIntArray(R.array.stds_condylomatosis_array);
        IntArrayAdapter stdsCondylomatosisAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsCondylomatosisArray);
        stdsCondylomatosisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsCondylomatosis.setAdapter(stdsCondylomatosisAdapter);

// Create IntArrayAdapter for spinnerSTDsCervicalCondylomatosis
        int[] stdsCervicalCondylomatosisArray = getResources().getIntArray(R.array.stds_cervical_condylomatosis_array);
        IntArrayAdapter stdsCervicalCondylomatosisAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsCervicalCondylomatosisArray);
        stdsCervicalCondylomatosisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsCervicalCondylomatosis.setAdapter(stdsCervicalCondylomatosisAdapter);

// Create IntArrayAdapter for spinnerSTDsVaginalCondylomatosis
        int[] stdsVaginalCondylomatosisArray = getResources().getIntArray(R.array.stds_vaginal_condylomatosis_array);
        IntArrayAdapter stdsVaginalCondylomatosisAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsVaginalCondylomatosisArray);
        stdsVaginalCondylomatosisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsVaginalCondylomatosis.setAdapter(stdsVaginalCondylomatosisAdapter);

// Create IntArrayAdapter for spinnerSTDsVulvoPerinealCondylomatosis
        int[] stdsVulvoPerinealCondylomatosisArray = getResources().getIntArray(R.array.stds_vulvo_perineal_condylomatosis_array);
        IntArrayAdapter stdsVulvoPerinealCondylomatosisAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsVulvoPerinealCondylomatosisArray);
        stdsVulvoPerinealCondylomatosisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsVulvoPerinealCondylomatosis.setAdapter(stdsVulvoPerinealCondylomatosisAdapter);

// Create IntArrayAdapter for spinnerSTDsSyphilis
        int[] stdsSyphilisArray = getResources().getIntArray(R.array.stds_syphilis_array);
        IntArrayAdapter stdsSyphilisAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsSyphilisArray);
        stdsSyphilisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsSyphilis.setAdapter(stdsSyphilisAdapter);

// Create IntArrayAdapter for spinnerSTDsPelvicInflammatoryDisease
        int[] stdsPelvicInflammatoryDiseaseArray = getResources().getIntArray(R.array.stds_pelvic_inflammatory_disease_array);
        IntArrayAdapter stdsPelvicInflammatoryDiseaseAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsPelvicInflammatoryDiseaseArray);
        stdsPelvicInflammatoryDiseaseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsPelvicInflammatoryDisease.setAdapter(stdsPelvicInflammatoryDiseaseAdapter);

// Create IntArrayAdapter for spinnerSTDsGenitalHerpes
        int[] stdsGenitalHerpesArray = getResources().getIntArray(R.array.stds_genital_herpes_array);
        IntArrayAdapter stdsGenitalHerpesAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsGenitalHerpesArray);
        stdsGenitalHerpesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsGenitalHerpes.setAdapter(stdsGenitalHerpesAdapter);

// Create IntArrayAdapter for spinnerSTDsMolluscumContagiosum
        int[] stdsMolluscumContagiosumArray = getResources().getIntArray(R.array.stds_molluscum_contagiosum_array);
        IntArrayAdapter stdsMolluscumContagiosumAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsMolluscumContagiosumArray);
        stdsMolluscumContagiosumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsMolluscumContagiosum.setAdapter(stdsMolluscumContagiosumAdapter);

// Create IntArrayAdapter for spinnerSTDsAIDS
        int[] stdsAIDSArray = getResources().getIntArray(R.array.stds_aids_array);
        IntArrayAdapter stdsAIDSAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsAIDSArray);
        stdsAIDSAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsAIDS.setAdapter(stdsAIDSAdapter);

// Create IntArrayAdapter for spinnerSTDsHIV
        int[] stdsHIVArray = getResources().getIntArray(R.array.stds_hiv_array);
        IntArrayAdapter stdsHIVAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsHIVArray);
        stdsHIVAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsHIV.setAdapter(stdsHIVAdapter);

// Create IntArrayAdapter for spinnerSTDsHepatitisB
        int[] stdsHepatitisBArray = getResources().getIntArray(R.array.stds_hepatitis_b_array);
        IntArrayAdapter stdsHepatitisBAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsHepatitisBArray);
        stdsHepatitisBAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsHepatitisB.setAdapter(stdsHepatitisBAdapter);

// Create IntArrayAdapter for spinnerSTDsHPV
        int[] stdsHPVArray = getResources().getIntArray(R.array.stds_hpv_array);
        IntArrayAdapter stdsHPVAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsHPVArray);
        stdsHPVAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsHPV.setAdapter(stdsHPVAdapter);

// Create IntArrayAdapter for spinnerSTDsNumberOfDiagnosis
        int[] stdsNumberOfDiagnosisArray = getResources().getIntArray(R.array.stds_number_of_diagnosis_array);
        IntArrayAdapter stdsNumberOfDiagnosisAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsNumberOfDiagnosisArray);
        stdsNumberOfDiagnosisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsNumberOfDiagnosis.setAdapter(stdsNumberOfDiagnosisAdapter);

// Create IntArrayAdapter for spinnerSTDsTimeSinceFirstDiagnosis
        int[] stdsTimeSinceFirstDiagnosisArray = getResources().getIntArray(R.array.stds_time_since_first_diagnosis_array);
        IntArrayAdapter stdsTimeSinceFirstDiagnosisAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsTimeSinceFirstDiagnosisArray);
        stdsTimeSinceFirstDiagnosisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsTimeSinceFirstDiagnosis.setAdapter(stdsTimeSinceFirstDiagnosisAdapter);

// Create IntArrayAdapter for spinnerSTDsTimeSinceLastDiagnosis
        int[] stdsTimeSinceLastDiagnosisArray = getResources().getIntArray(R.array.stds_time_since_last_diagnosis_array);
        IntArrayAdapter stdsTimeSinceLastDiagnosisAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsTimeSinceLastDiagnosisArray);
        stdsTimeSinceLastDiagnosisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsTimeSinceLastDiagnosis.setAdapter(stdsTimeSinceLastDiagnosisAdapter);


// Create IntArrayAdapter for hormonalContraceptivesYearsSpinner
        int[] hormonalContraceptivesYearsArray = getResources().getIntArray(R.array.hormonal_contraceptives_years_array);
        IntArrayAdapter hormonalContraceptivesYearsAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, hormonalContraceptivesYearsArray);
        hormonalContraceptivesYearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHormonalContraceptivesYears.setAdapter(hormonalContraceptivesYearsAdapter);

// Create IntArrayAdapter for iudYearsSpinner
        int[] iudYearsArray = getResources().getIntArray(R.array.iud_years_array);
        IntArrayAdapter iudYearsAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, iudYearsArray);
        iudYearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIUDYears.setAdapter(iudYearsAdapter);
        // Create IntArrayAdapter for stdsNumberSpinner
        int[] stdsArray = getResources().getIntArray(R.array.stds_array);
        IntArrayAdapter stdsAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsArray);
        stdsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDs.setAdapter(stdsAdapter);

// Create IntArrayAdapter for stdsNumberSpinner
        int[] stdsNumberArray = getResources().getIntArray(R.array.stds_number_array);
        IntArrayAdapter stdsNumberAdapter = new IntArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, stdsNumberArray);
        stdsNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSTDsNumber.setAdapter(stdsNumberAdapter);


        return view;
    }

    private FragmentManager getSupportFragmentManager() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return fragmentManager;
    }


    private void performPrediction() {
        // Fetch the user's email from Firebase Authentication
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            // User not logged in, handle this case as needed (e.g., show a login screen)
            return;
        }
        String email = currentUser.getEmail();


        int age = Integer.parseInt(spinnerAge.getSelectedItem().toString());
        int numPartners = Integer.parseInt(spinnerNumPartners.getSelectedItem().toString());
        int firstIntercourse = Integer.parseInt(spinnerFirstIntercourse.getSelectedItem().toString());
        int numPregnancies = Integer.parseInt(spinnerNumPregnancies.getSelectedItem().toString());
        int smokes = Integer.parseInt(spinnerSmokes.getSelectedItem().toString());
        int smokesYears = Integer.parseInt(spinnerSmokesYears.getSelectedItem().toString());
        int smokesPacksYear = Integer.parseInt(spinnerSmokesPacksYear.getSelectedItem().toString());
        int hormonalContraceptives = Integer.parseInt(spinnerHormonalContraceptives.getSelectedItem().toString());
        int hormonalContraceptivesYears = Integer.parseInt(spinnerHormonalContraceptivesYears.getSelectedItem().toString());
        int iud = Integer.parseInt(spinnerIUD.getSelectedItem().toString());
        int iudYears = Integer.parseInt(spinnerIUDYears.getSelectedItem().toString());
        int stds = Integer.parseInt(spinnerSTDs.getSelectedItem().toString());
        int stdsNumber = Integer.parseInt(spinnerSTDsNumber.getSelectedItem().toString());
        int stdsCondylomatosis = Integer.parseInt(spinnerSTDsCondylomatosis.getSelectedItem().toString());
        int stdsCervicalCondylomatosis = Integer.parseInt(spinnerSTDsCervicalCondylomatosis.getSelectedItem().toString());
        int stdsVaginalCondylomatosis = Integer.parseInt(spinnerSTDsVaginalCondylomatosis.getSelectedItem().toString());
        int stdsVulvoPerinealCondylomatosis = Integer.parseInt(spinnerSTDsVulvoPerinealCondylomatosis.getSelectedItem().toString());
        int stdsSyphilis = Integer.parseInt(spinnerSTDsSyphilis.getSelectedItem().toString());
        int stdsPelvicInflammatoryDisease = Integer.parseInt(spinnerSTDsPelvicInflammatoryDisease.getSelectedItem().toString());
        int stdsGenitalHerpes = Integer.parseInt(spinnerSTDsGenitalHerpes.getSelectedItem().toString());
        int stdsMolluscumContagiosum = Integer.parseInt(spinnerSTDsMolluscumContagiosum.getSelectedItem().toString());
        int stdsAIDS = Integer.parseInt(spinnerSTDsAIDS.getSelectedItem().toString());
        int stdsHIV = Integer.parseInt(spinnerSTDsHIV.getSelectedItem().toString());
        int stdsHepatitisB = Integer.parseInt(spinnerSTDsHepatitisB.getSelectedItem().toString());
        int stdsHPV = Integer.parseInt(spinnerSTDsHPV.getSelectedItem().toString());
        int stdsNumberOfDiagnosis = Integer.parseInt(spinnerSTDsNumberOfDiagnosis.getSelectedItem().toString());
        int stdsTimeSinceFirstDiagnosis = Integer.parseInt(spinnerSTDsTimeSinceFirstDiagnosis.getSelectedItem().toString());
        int stdsTimeSinceLastDiagnosis = Integer.parseInt(spinnerSTDsTimeSinceLastDiagnosis.getSelectedItem().toString());


        // Create an InputData object with the selected values
        InputData inputData = new InputData();
        inputData.setAge(age);
        inputData.setNumberOfSexualPartners(numPartners);
        inputData.setFirstSexualIntercourse(firstIntercourse);
        inputData.setNumOfPregnancies(numPregnancies);
        inputData.setSmokes(smokes);
        inputData.setSmokesYears(smokesYears);
        inputData.setSmokesPacksYear(smokesPacksYear);
        inputData.setHormonalContraceptives(hormonalContraceptives);
        inputData.setHormonalContraceptivesYears(hormonalContraceptivesYears);
        inputData.setIud(iud);
        inputData.setIudYears(iudYears);
        inputData.setStds(stds);
        inputData.setStdsNumber(stdsNumber);
        inputData.setStdsCondylomatosis(stdsCondylomatosis);
        inputData.setStdsCervicalCondylomatosis(stdsCervicalCondylomatosis);
        inputData.setStdsVaginalCondylomatosis(stdsVaginalCondylomatosis);
        inputData.setStdsVulvoPerinealCondylomatosis(stdsVulvoPerinealCondylomatosis);
        inputData.setStdsSyphilis(stdsSyphilis);
        inputData.setStdsPelvicInflammatoryDisease(stdsPelvicInflammatoryDisease);
        inputData.setStdsGenitalHerpes(stdsGenitalHerpes);
        inputData.setStdsMolluscumContagiosum(stdsMolluscumContagiosum);
        inputData.setStdsAIDS(stdsAIDS);
        inputData.setStdsHIV(stdsHIV);
        inputData.setStdsHepatitisB(stdsHepatitisB);
        inputData.setStdsHPV(stdsHPV);
        inputData.setStdsNumberOfDiagnosis(stdsNumberOfDiagnosis);
        inputData.setStdsTimeSinceFirstDiagnosis(stdsTimeSinceFirstDiagnosis);
        inputData.setStdsTimeSinceLastDiagnosis(stdsTimeSinceLastDiagnosis);

        // Make the API call using Retrofit

        ProgressDialog progressDialog = ProgressDialog.show(requireContext(), "Predicting", "Please wait...", true);

// Make the API call using Retrofit
        Call<PredictionResponse> call = apiService.predictCancer(inputData);
        call.enqueue(new Callback<PredictionResponse>() {
            @Override
            public void onResponse(Call<PredictionResponse> call, Response<PredictionResponse> response) {
                progressDialog.dismiss(); // Dismiss the progress dialog

                if (response != null && response.isSuccessful() && response.body() != null) {
                    PredictionResponse predictionResponse = response.body();
                    int prediction = predictionResponse.getPrediction();

                    // Handle the prediction result in your app's UI
                    Log.d("Prediction", "prediction: " + prediction);

                    // Get the reference to the EditText field
                    EditText editTextPrediction = requireView().findViewById(R.id.editTextPrediction);
                    // Set the EditText field based on the prediction result
                    if (prediction < 100) {
                        editTextPrediction.setText("Low Risk");
                        showPopup1();
                    } else if (prediction < 200) {
                        editTextPrediction.setText("Moderate Risk");
                        showPopup2();
                    } else {
                        editTextPrediction.setText("High Risk");

                        showPopup();
                    }

                    // Save user data and prediction to Firebase
                    saveUserDataAndPrediction(email, inputData, prediction);
                } else {
                    // Handle API request failure
                    Log.d("API Error", "API request failed");
                }
            }

                         @Override
                         public void onFailure(Call<PredictionResponse> call, Throwable t) {
                             // Handle API request failure
                    Log.e("API Error", "API request failed", t);
                         }

        }

        );
    }

    // Show the full-screen dialog when a button is clicked or when needed
    private void showFullScreenDialog() {
        FullScreenDialogFragment dialogFragment = FullScreenDialogFragment.newInstance();
        dialogFragment.show(getSupportFragmentManager(), "FullScreenDialogFragment");
    }


    private void showPopup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("you have a High Risk of getting cervical cancer");
        builder.setMessage(" Visit your nearest health center for regular screenings. This includes Pap tests and HPV tests. \n\n"+
                "These tests can help detect any abnormal cells on your cervix early, so that they can be treated before they turn into cancer.\n\n" +
                "Get vaccinated against HPV. The HPV vaccine can help protect you from the strains of HPV that cause most cases of cervical cancer.\n\n"+
                "The vaccine is recommended for girls  ages 9-12, but it can also be given to people up to age 45.\n\n" +
                "Reduce your risk of HPV infection. This includes avoiding sexual contact with people who have multiple partners, and using condoms consistently.\n" +
                "Don't smoke. Smoking increases your risk of cervical cancer. If you smoke, quitting is the best thing you can do for your health.\n\n" +
                "Eat a healthy diet. Eating a healthy diet can help boost your immune system, which can help protect you from HPV infection and cervical cancer.\n" +
                "Maintain a healthy weight. Being overweight or obese increases your risk of cervical cancer.\n\n" +
                "Test for other diseases such as STDs.These might also increase your risk of getting cervical cancer if not treated\n\n"+
                "Get regular exercise. Exercise can help boost your immune system and reduce your risk of cervical cancer.\n\n"+
                 "  with these few Recommendations if followed,you will reduce the risk of getting cervical cancer");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when the "OK" button is clicked (if needed)
                dialog.dismiss();
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showPopup2() {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("you have a Moderate Risk of getting cervical cancer");
        builder.setMessage(" Visit your nearest health center for regular screenings. This includes Pap tests and HPV tests. \n\n"+
                "These tests can help detect any abnormal cells on your cervix early, so that they can be treated before they turn into cancer.\n\n" +
                "Get vaccinated against HPV. The HPV vaccine can help protect you from the strains of HPV that cause most cases of cervical cancer.\n\n"+
                "The vaccine is recommended for girls  ages 9-12, but it can also be given to people up to age 45.\n\n" +
                "Reduce your risk of HPV infection. This includes avoiding sexual contact with people who have multiple partners, and using condoms consistently.\n" +
                "Don't smoke. Smoking increases your risk of cervical cancer. If you smoke, quitting is the best thing you can do for your health.\n\n" +
                "Eat a healthy diet. Eating a healthy diet can help boost your immune system, which can help protect you from HPV infection and cervical cancer.\n" +
                "Maintain a healthy weight. Being overweight or obese increases your risk of cervical cancer.\n\n" +
                "Test for other diseases such as STDs.These might also increase your risk of getting cervical cancer if not treated\n\n"+
                "Get regular exercise. Exercise can help boost your immune system and reduce your risk of cervical cancer.\n\n"+
                "  with these few Recommendations if followed,you will reduce the risk of getting cervical cancer");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when the "OK" button is clicked (if needed)
                dialog.dismiss();
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showPopup1() {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("you have a low risk of getting cervical cancer");
        builder.setMessage(" Visit your nearest health center for regular screenings. This includes Pap tests and HPV tests. \n\n"+
                "These tests can help detect any abnormal cells on your cervix early, so that they can be treated before they turn into cancer.\n\n" +
                "Get vaccinated against HPV. The HPV vaccine can help protect you from the strains of HPV that cause most cases of cervical cancer.\n\n"+
                "The vaccine is recommended for girls ages 9-12, but it can also be given to people up to age 45.\n\n" +
                "Reduce your risk of HPV infection. This includes avoiding sexual contact with people who have multiple partners, and using condoms consistently.\n" +
                "Don't smoke. Smoking increases your risk of cervical cancer. If you smoke, quitting is the best thing you can do for your health.\n\n" +
                "Eat a healthy diet. Eating a healthy diet can help boost your immune system, which can help protect you from HPV infection and cervical cancer.\n" +
                "Maintain a healthy weight. Being overweight or obese increases your risk of cervical cancer.\n\n" +
                "Test for other diseases such as STDs.These might also increase your risk of getting cervical cancer if not treated\n\n"+
                "Get regular exercise. Exercise can help boost your immune system and reduce your risk of cervical cancer.\n\n"+
                "  with these few Recommendations if followed,you will maintain the low risk of getting cervical cancer");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when the "OK" button is clicked (if needed)
                dialog.dismiss();
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    // Save user data and prediction to the Firebase Realtime Database
    private void saveUserDataAndPrediction(String userEmail, InputData inputData, int prediction) {
        // Create a new HashMap to store user data
        Map<String, Object> userDataMap = new HashMap<>();
        userDataMap.put("email", userEmail);
        userDataMap.put("age", inputData.getAge());
        userDataMap.put("numPartners", inputData.getNumberOfSexualPartners());
        userDataMap.put("firstIntercourse", inputData.getFirstSexualIntercourse());
        userDataMap.put("numPregnancies", inputData.getNumOfPregnancies());
        userDataMap.put("smokes", inputData.getSmokes());
        userDataMap.put("smokesYears", inputData.getSmokesYears());
        userDataMap.put("smokesPacksYear", inputData.getSmokesPacksYear());
        userDataMap.put("hormonalContraceptives", inputData.getHormonalContraceptives());
        userDataMap.put("hormonalContraceptivesYears", inputData.getHormonalContraceptivesYears());
        userDataMap.put("iud", inputData.getIud());
        userDataMap.put("iudYears", inputData.getIudYears());
        userDataMap.put("stds", inputData.getStds());
        userDataMap.put("stdsNumber", inputData.getStdsNumber());
        userDataMap.put("stdsCondylomatosis", inputData.getStdsCondylomatosis());
        userDataMap.put("stdsCervicalCondylomatosis", inputData.getStdsCervicalCondylomatosis());
        userDataMap.put("stdsVaginalCondylomatosis", inputData.getStdsVaginalCondylomatosis());
        userDataMap.put("stdsVulvoPerinealCondylomatosis", inputData.getStdsVulvoPerinealCondylomatosis());
        userDataMap.put("stdsSyphilis", inputData.getStdsSyphilis());
        userDataMap.put("stdsPelvicInflammatoryDisease", inputData.getStdsPelvicInflammatoryDisease());
        userDataMap.put("stdsGenitalHerpes", inputData.getStdsGenitalHerpes());
        userDataMap.put("stdsMolluscumContagiosum", inputData.getStdsMolluscumContagiosum());
        userDataMap.put("stdsAIDS", inputData.getStdsAIDS());
        userDataMap.put("stdsHIV", inputData.getStdsHIV());
        userDataMap.put("stdsHepatitisB", inputData.getStdsHepatitisB());
        userDataMap.put("stdsHPV", inputData.getStdsHPV());
        userDataMap.put("stdsNumberOfDiagnosis", inputData.getStdsNumberOfDiagnosis());
        userDataMap.put("stdsTimeSinceFirstDiagnosis", inputData.getStdsTimeSinceFirstDiagnosis());
        userDataMap.put("stdsTimeSinceLastDiagnosis", inputData.getStdsTimeSinceLastDiagnosis());
        userDataMap.put("prediction", prediction);

        // Generate a unique key for the user data entry
        String userId = databaseReference.push().getKey();


        // Save the user data to the "users" node in the database
        databaseReference.child("inputData").child(userId).setValue(userDataMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Data successfully saved to Firebase Realtime Database
                        Log.d("Firebase", "Data saved successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors that occurred while saving data to Firebase
                        Log.e("Firebase", "Data could not be saved: " + e.getMessage());
                    }

                });

    }












//// Save user data and prediction to the Firebase Realtime Database
//private void saveUserDataAndPrediction(String userEmail, InputData inputData, int prediction) {
//    // Create a new HashMap to store user data
//    Map<String, Object> userDataMap = new HashMap<>();
//    userDataMap.put("email", userEmail);
//    userDataMap.put("age", inputData.getAge());
//    userDataMap.put("numPartners", inputData.getNumberOfSexualPartners());
//    userDataMap.put("firstIntercourse", inputData.getFirstSexualIntercourse());
//    userDataMap.put("numPregnancies", inputData.getNumOfPregnancies());
//    userDataMap.put("smokes", inputData.getSmokes());
//    userDataMap.put("smokesYears", inputData.getSmokesYears());
//    userDataMap.put("smokesPacksYear", inputData.getSmokesPacksYear());
//    userDataMap.put("hormonalContraceptives", inputData.getHormonalContraceptives());
//    userDataMap.put("hormonalContraceptivesYears", inputData.getHormonalContraceptivesYears());
//    userDataMap.put("iud", inputData.getIud());
//    userDataMap.put("iudYears", inputData.getIudYears());
//    userDataMap.put("stds", inputData.getStds());
//    userDataMap.put("stdsNumber", inputData.getStdsNumber());
//    userDataMap.put("stdsCondylomatosis", inputData.getStdsCondylomatosis());
//    userDataMap.put("stdsCervicalCondylomatosis", inputData.getStdsCervicalCondylomatosis());
//    userDataMap.put("stdsVaginalCondylomatosis", inputData.getStdsVaginalCondylomatosis());
//    userDataMap.put("stdsVulvoPerinealCondylomatosis", inputData.getStdsVulvoPerinealCondylomatosis());
//    userDataMap.put("stdsSyphilis", inputData.getStdsSyphilis());
//    userDataMap.put("stdsPelvicInflammatoryDisease", inputData.getStdsPelvicInflammatoryDisease());
//    userDataMap.put("stdsGenitalHerpes", inputData.getStdsGenitalHerpes());
//    userDataMap.put("stdsMolluscumContagiosum", inputData.getStdsMolluscumContagiosum());
//    userDataMap.put("stdsAIDS", inputData.getStdsAIDS());
//    userDataMap.put("stdsHIV", inputData.getStdsHIV());
//    userDataMap.put("stdsHepatitisB", inputData.getStdsHepatitisB());
//    userDataMap.put("stdsHPV", inputData.getStdsHPV());
//    userDataMap.put("stdsNumberOfDiagnosis", inputData.getStdsNumberOfDiagnosis());
//    userDataMap.put("stdsTimeSinceFirstDiagnosis", inputData.getStdsTimeSinceFirstDiagnosis());
//    userDataMap.put("stdsTimeSinceLastDiagnosis", inputData.getStdsTimeSinceLastDiagnosis());
//    userDataMap.put("prediction", prediction);
//
//    // Generate a unique key for the user data entry
//    String userId = databaseReference.push().getKey();
//
//    // Save the user data to the "users" node in the database
//    databaseReference.child("user").child(userId).setValue(userDataMap)
//            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//                    // Data successfully saved to Firebase Realtime Database
//                    Log.d("Firebase", "Data saved successfully");
//                }
//            })
//            .addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    // Handle any errors that occurred while saving data to Firebase
//                    Log.e("Firebase", "Data could not be saved: " + e.getMessage());
//                }
//            });
//}
//


}




//
//
//
//package com.example.cervical_cancer;
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//
//import com.example.cervical_cancer.modals.InputData;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class RiskAssessmentFragment extends Fragment {
//
//    private ApiService apiService;
//    private ProgressDialog progressDialog;
//    private Spinner spinnerAge;
//    private Spinner spinnerNumPartners;
//    private Spinner spinnerFirstIntercourse;
//    private Spinner spinnerNumPregnancies;
//    private Spinner spinnerSmokes;
//    private Spinner spinnerSmokesYears;
//    private Spinner spinnerSmokesPacksYear;
//    private Spinner spinnerHormonalContraceptives;
//    private Spinner spinnerHormonalContraceptivesYears;
//    private Spinner spinnerIUD;
//    private Spinner spinnerIUDYears;
//    private Spinner spinnerSTDs;
//    private Spinner spinnerSTDsNumber;
//    private Spinner spinnerSTDsCondylomatosis;
//    private Spinner spinnerSTDsCervicalCondylomatosis;
//    private Spinner spinnerSTDsVaginalCondylomatosis;
//    private Spinner spinnerSTDsVulvoPerinealCondylomatosis;
//    private Spinner spinnerSTDsSyphilis;
//    private Spinner spinnerSTDsPelvicInflammatoryDisease;
//    private Spinner spinnerSTDsGenitalHerpes;
//    private Spinner spinnerSTDsMolluscumContagiosum;
//    private Spinner spinnerSTDsAIDS;
//    private Spinner spinnerSTDsHIV;
//    private Spinner spinnerSTDsHepatitisB;
//    private Spinner spinnerSTDsHPV;
//    private Spinner spinnerSTDsNumberOfDiagnosis;
//    private Spinner spinnerSTDsTimeSinceFirstDiagnosis;
//    private Spinner spinnerSTDsTimeSinceLastDiagnosis;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_risk_assessment, container, false);
//
//        // Retrofit client setup
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://172.20.10.2:8000/")  // Replace with your FastAPI server URL
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        apiService = retrofit.create(ApiService.class);
//
//        // Find the Predict button by its ID
//        Button predictButton = view.findViewById(R.id.buttonPredict);
//
//        // Set up OnClickListener for the Predict button
//        predictButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Perform prediction when the Predict button is clicked
//                performPrediction();
//            }
//        });
//
//        // Initialize the Spinners
//        spinnerAge = view.findViewById(R.id.spinnerAge);
//        spinnerNumPartners = view.findViewById(R.id.spinnerNumPartners);
//        spinnerFirstIntercourse = view.findViewById(R.id.spinnerFirstIntercourse);
//        spinnerNumPregnancies = view.findViewById(R.id.spinnerNumPregnancies);
//        spinnerSmokes = view.findViewById(R.id.spinnerSmokes);
//        spinnerSmokesYears = view.findViewById(R.id.spinnerSmokesYears);
//        spinnerSmokesPacksYear = view.findViewById(R.id.spinnerSmokesPacksYear);
//        spinnerHormonalContraceptives = view.findViewById(R.id.spinnerHormonalContraceptives);
//        spinnerHormonalContraceptivesYears = view.findViewById(R.id.spinnerHormonalContraceptivesYears);
//        spinnerIUD = view.findViewById(R.id.spinnerIUD);
//        spinnerIUDYears = view.findViewById(R.id.spinnerIUDYears);
//        spinnerSTDs = view.findViewById(R.id.spinnerSTDs);
//        spinnerSTDsNumber = view.findViewById(R.id.spinnerSTDsNumber);
//        spinnerSTDsCondylomatosis = view.findViewById(R.id.spinnerSTDsCondylomatosis);
//        spinnerSTDsCervicalCondylomatosis = view.findViewById(R.id.spinnerSTDsCervicalCondylomatosis);
//        spinnerSTDsVaginalCondylomatosis = view.findViewById(R.id.spinnerSTDsVaginalCondylomatosis);
//        spinnerSTDsVulvoPerinealCondylomatosis = view.findViewById(R.id.spinnerSTDsVulvoPerinealCondylomatosis);
//        spinnerSTDsSyphilis = view.findViewById(R.id.spinnerSTDsSyphilis);
//        spinnerSTDsPelvicInflammatoryDisease = view.findViewById(R.id.spinnerSTDPelvicInflammatoryDisease);
//        spinnerSTDsGenitalHerpes = view.findViewById(R.id.spinnerSTDsGenitalHerpes);
//        spinnerSTDsMolluscumContagiosum = view.findViewById(R.id.spinnerSTDsMolluscumContagiosum);
//        spinnerSTDsAIDS = view.findViewById(R.id.spinnerSTDAIDS);
//        spinnerSTDsHIV = view.findViewById(R.id.spinnerSTDsHIV);
//        spinnerSTDsHepatitisB = view.findViewById(R.id.spinnerSTDsHepatitisB);
//        spinnerSTDsHPV = view.findViewById(R.id.spinnerSTDsHPV);
//        spinnerSTDsNumberOfDiagnosis = view.findViewById(R.id.spinnerSTDsNumDiagnosis);
//        spinnerSTDsTimeSinceFirstDiagnosis = view.findViewById(R.id.spinnerSTDsTimeSinceFirstDiagnosis);
//        spinnerSTDsTimeSinceLastDiagnosis = view.findViewById(R.id.spinnerSTDsTimeSinceLastDiagnosis);
//
//        // Create ArrayAdapter for ageSpinner
//        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.age_array, android.R.layout.simple_spinner_item);
//        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerAge.setAdapter(ageAdapter);
//
//        // Create ArrayAdapter for numPartnersSpinner
//        ArrayAdapter<CharSequence> numPartnersAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.num_partners_array, android.R.layout.simple_spinner_item);
//        numPartnersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerNumPartners.setAdapter(numPartnersAdapter);
//
//        // Create ArrayAdapter for firstIntercourseSpinner
//        ArrayAdapter<CharSequence> firstIntercourseAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.first_intercourse_array, android.R.layout.simple_spinner_item);
//        firstIntercourseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerFirstIntercourse.setAdapter(firstIntercourseAdapter);
//
//        // Create ArrayAdapter for numPregnanciesSpinner
//        ArrayAdapter<CharSequence> numPregnanciesAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.num_pregnancies_array, android.R.layout.simple_spinner_item);
//        numPregnanciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerNumPregnancies.setAdapter(numPregnanciesAdapter);
//
//        // Create ArrayAdapter for smokesSpinner
//        ArrayAdapter<CharSequence> smokesAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.smokes_array, android.R.layout.simple_spinner_item);
//        smokesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSmokes.setAdapter(smokesAdapter);
//
//        // Create ArrayAdapter for smokesYearsSpinner
//        ArrayAdapter<CharSequence> smokesYearsAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.smokes_years_array, android.R.layout.simple_spinner_item);
//        smokesYearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSmokesYears.setAdapter(smokesYearsAdapter);
//
//        // Create ArrayAdapter for smokesPacksYearSpinner
//        ArrayAdapter<CharSequence> smokesPacksYearAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.smokes_packs_year_array, android.R.layout.simple_spinner_item);
//        smokesPacksYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSmokesPacksYear.setAdapter(smokesPacksYearAdapter);
//
//        // Create ArrayAdapter for hormonalContraceptivesSpinner
//        ArrayAdapter<CharSequence> hormonalContraceptivesAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.hormonal_contraceptives_array, android.R.layout.simple_spinner_item);
//        hormonalContraceptivesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerHormonalContraceptives.setAdapter(hormonalContraceptivesAdapter);
//
//        // Create ArrayAdapter for hormonalContraceptivesYearsSpinner
//        ArrayAdapter<CharSequence> hormonalContraceptivesYearsAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.hormonal_contraceptives_years_array, android.R.layout.simple_spinner_item);
//        hormonalContraceptivesYearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerHormonalContraceptivesYears.setAdapter(hormonalContraceptivesYearsAdapter);
//
//        // Create ArrayAdapter for iudSpinner
//        ArrayAdapter<CharSequence> iudAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.iud_array, android.R.layout.simple_spinner_item);
//        iudAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerIUD.setAdapter(iudAdapter);
//
//        // Create ArrayAdapter for iudYearsSpinner
//        ArrayAdapter<CharSequence> iudYearsAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.iud_years_array, android.R.layout.simple_spinner_item);
//        iudYearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerIUDYears.setAdapter(iudYearsAdapter);
//
//        // Create ArrayAdapter for stdsSpinner
//        ArrayAdapter<CharSequence> stdsAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_array, android.R.layout.simple_spinner_item);
//        stdsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDs.setAdapter(stdsAdapter);
//
//        // Create ArrayAdapter for stdsNumberSpinner
//        ArrayAdapter<CharSequence> stdsNumberAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_number_array, android.R.layout.simple_spinner_item);
//        stdsNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsNumber.setAdapter(stdsNumberAdapter);
//
//        // Create ArrayAdapter for stdsCondylomatosisSpinner
//        ArrayAdapter<CharSequence> stdsCondylomatosisAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_condylomatosis_array, android.R.layout.simple_spinner_item);
//        stdsCondylomatosisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsCondylomatosis.setAdapter(stdsCondylomatosisAdapter);
//
//        // Create ArrayAdapter for stdsCervicalCondylomatosisSpinner
//        ArrayAdapter<CharSequence> stdsCervicalCondylomatosisAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_cervical_condylomatosis_array, android.R.layout.simple_spinner_item);
//        stdsCervicalCondylomatosisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsCervicalCondylomatosis.setAdapter(stdsCervicalCondylomatosisAdapter);
//
//        // Create ArrayAdapter for stdsVaginalCondylomatosisSpinner
//        ArrayAdapter<CharSequence> stdsVaginalCondylomatosisAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_vaginal_condylomatosis_array, android.R.layout.simple_spinner_item);
//        stdsVaginalCondylomatosisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsVaginalCondylomatosis.setAdapter(stdsVaginalCondylomatosisAdapter);
//
//        // Create ArrayAdapter for stdsVulvoPerinealCondylomatosisSpinner
//        ArrayAdapter<CharSequence> stdsVulvoPerinealCondylomatosisAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_vulvo_perineal_condylomatosis_array, android.R.layout.simple_spinner_item);
//        stdsVulvoPerinealCondylomatosisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsVulvoPerinealCondylomatosis.setAdapter(stdsVulvoPerinealCondylomatosisAdapter);
//
//        // Create ArrayAdapter for stdsSyphilisSpinner
//        ArrayAdapter<CharSequence> stdsSyphilisAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_syphilis_array, android.R.layout.simple_spinner_item);
//        stdsSyphilisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsSyphilis.setAdapter(stdsSyphilisAdapter);
//
//        // Create ArrayAdapter for stdsPelvicInflammatoryDiseaseSpinner
//        ArrayAdapter<CharSequence> stdsPelvicInflammatoryDiseaseAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_pelvic_inflammatory_disease_array, android.R.layout.simple_spinner_item);
//        stdsPelvicInflammatoryDiseaseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsPelvicInflammatoryDisease.setAdapter(stdsPelvicInflammatoryDiseaseAdapter);
//
//        // Create ArrayAdapter for stdsGenitalHerpesSpinner
//        ArrayAdapter<CharSequence> stdsGenitalHerpesAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_genital_herpes_array, android.R.layout.simple_spinner_item);
//        stdsGenitalHerpesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsGenitalHerpes.setAdapter(stdsGenitalHerpesAdapter);
//
//        // Create ArrayAdapter for stdsMolluscumContagiosumSpinner
//        ArrayAdapter<CharSequence> stdsMolluscumContagiosumAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_molluscum_contagiosum_array, android.R.layout.simple_spinner_item);
//        stdsMolluscumContagiosumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsMolluscumContagiosum.setAdapter(stdsMolluscumContagiosumAdapter);
//
//        // Create ArrayAdapter for stdsAIDSSpinner
//        ArrayAdapter<CharSequence> stdsAIDSAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_aids_array, android.R.layout.simple_spinner_item);
//        stdsAIDSAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsAIDS.setAdapter(stdsAIDSAdapter);
//
//        // Create ArrayAdapter for stdsHIVSpinner
//        ArrayAdapter<CharSequence> stdsHIVAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_hiv_array, android.R.layout.simple_spinner_item);
//        stdsHIVAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsHIV.setAdapter(stdsHIVAdapter);
//
//        // Create ArrayAdapter for stdsHepatitisBSpinner
//        ArrayAdapter<CharSequence> stdsHepatitisBAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_hepatitis_b_array, android.R.layout.simple_spinner_item);
//        stdsHepatitisBAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsHepatitisB.setAdapter(stdsHepatitisBAdapter);
//
//        // Create ArrayAdapter for stdsHPVSpinner
//        ArrayAdapter<CharSequence> stdsHPVAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_hpv_array, android.R.layout.simple_spinner_item);
//        stdsHPVAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsHPV.setAdapter(stdsHPVAdapter);
//
//        // Create ArrayAdapter for stdsNumberOfDiagnosisSpinner
//        ArrayAdapter<CharSequence> stdsNumberOfDiagnosisAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_number_of_diagnosis_array, android.R.layout.simple_spinner_item);
//        stdsNumberOfDiagnosisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsNumberOfDiagnosis.setAdapter(stdsNumberOfDiagnosisAdapter);
//
//        // Create ArrayAdapter for stdsTimeSinceFirstDiagnosisSpinner
//        ArrayAdapter<CharSequence> stdsTimeSinceFirstDiagnosisAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_time_since_first_diagnosis_array, android.R.layout.simple_spinner_item);
//        stdsTimeSinceFirstDiagnosisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsTimeSinceFirstDiagnosis.setAdapter(stdsTimeSinceFirstDiagnosisAdapter);
//
//        // Create ArrayAdapter for stdsTimeSinceLastDiagnosisSpinner
//        ArrayAdapter<CharSequence> stdsTimeSinceLastDiagnosisAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.stds_time_since_last_diagnosis_array, android.R.layout.simple_spinner_item);
//        stdsTimeSinceLastDiagnosisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSTDsTimeSinceLastDiagnosis.setAdapter(stdsTimeSinceLastDiagnosisAdapter);
//
//        return view;
//    }
//
//    private void performPrediction() {
//        // Show progress dialog while making the prediction request
//        progressDialog = new ProgressDialog(requireContext());
//        progressDialog.setMessage("Predicting...");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
//
//        // Get the selected values from the Spinners
//        int age = Integer.parseInt(spinnerAge.getSelectedItem().toString());
//        int numPartners = Integer.parseInt(spinnerNumPartners.getSelectedItem().toString());
//        int firstIntercourse = Integer.parseInt(spinnerFirstIntercourse.getSelectedItem().toString());
//        int numPregnancies = Integer.parseInt(spinnerNumPregnancies.getSelectedItem().toString());
//        int smokes = Integer.parseInt(spinnerSmokes.getSelectedItem().toString());
//        int smokesYears = Integer.parseInt(spinnerSmokesYears.getSelectedItem().toString());
//        int smokesPacksYear = Integer.parseInt(spinnerSmokesPacksYear.getSelectedItem().toString());
//        int hormonalContraceptives = Integer.parseInt(spinnerHormonalContraceptives.getSelectedItem().toString());
//        int hormonalContraceptivesYears = Integer.parseInt(spinnerHormonalContraceptivesYears.getSelectedItem().toString());
//        int iud = Integer.parseInt(spinnerIUD.getSelectedItem().toString());
//        int iudYears = Integer.parseInt(spinnerIUDYears.getSelectedItem().toString());
//        int stds = Integer.parseInt(spinnerSTDs.getSelectedItem().toString());
//        int stdsNumber = Integer.parseInt(spinnerSTDsNumber.getSelectedItem().toString());
//        int stdsCondylomatosis = Integer.parseInt(spinnerSTDsCondylomatosis.getSelectedItem().toString());
//        int stdsCervicalCondylomatosis = Integer.parseInt(spinnerSTDsCervicalCondylomatosis.getSelectedItem().toString());
//        int stdsVaginalCondylomatosis = Integer.parseInt(spinnerSTDsVaginalCondylomatosis.getSelectedItem().toString());
//        int stdsVulvoPerinealCondylomatosis = Integer.parseInt(spinnerSTDsVulvoPerinealCondylomatosis.getSelectedItem().toString());
//        int stdsSyphilis = Integer.parseInt(spinnerSTDsSyphilis.getSelectedItem().toString());
//        int stdsPelvicInflammatoryDisease = Integer.parseInt(spinnerSTDsPelvicInflammatoryDisease.getSelectedItem().toString());
//        int stdsGenitalHerpes = Integer.parseInt(spinnerSTDsGenitalHerpes.getSelectedItem().toString());
//        int stdsMolluscumContagiosum = Integer.parseInt(spinnerSTDsMolluscumContagiosum.getSelectedItem().toString());
//        int stdsAIDS = Integer.parseInt(spinnerSTDsAIDS.getSelectedItem().toString());
//        int stdsHIV = Integer.parseInt(spinnerSTDsHIV.getSelectedItem().toString());
//        int stdsHepatitisB = Integer.parseInt(spinnerSTDsHepatitisB.getSelectedItem().toString());
//        int stdsHPV = Integer.parseInt(spinnerSTDsHPV.getSelectedItem().toString());
//        int stdsNumberOfDiagnosis = Integer.parseInt(spinnerSTDsNumberOfDiagnosis.getSelectedItem().toString());
//        int stdsTimeSinceFirstDiagnosis = Integer.parseInt(spinnerSTDsTimeSinceFirstDiagnosis.getSelectedItem().toString());
//        int stdsTimeSinceLastDiagnosis = Integer.parseInt(spinnerSTDsTimeSinceLastDiagnosis.getSelectedItem().toString());
//
//        // Create an instance of InputData
//        InputData inputData = new InputData();
//        inputData.setAge(age);
//        inputData.setNumberOfSexualPartners(numPartners);
//        inputData.setFirstSexualIntercourse(firstIntercourse);
//        inputData.setNumOfPregnancies(numPregnancies);
//        inputData.setSmokes(smokes);
//        inputData.setSmokesYears(smokesYears);
//        inputData.setSmokesPacksYear(smokesPacksYear);
//        inputData.setHormonalContraceptives(hormonalContraceptives);
//        inputData.setHormonalContraceptivesYears(hormonalContraceptivesYears);
//        inputData.setIud(iud);
//        inputData.setIudYears(iudYears);
//        inputData.setStds(stds);
//        inputData.setStdsNumber(stdsNumber);
//        inputData.setStdsCondylomatosis(stdsCondylomatosis);
//        inputData.setStdsCervicalCondylomatosis(stdsCervicalCondylomatosis);
//        inputData.setStdsVaginalCondylomatosis(stdsVaginalCondylomatosis);
//        inputData.setStdsVulvoPerinealCondylomatosis(stdsVulvoPerinealCondylomatosis);
//        inputData.setStdsSyphilis(stdsSyphilis);
//        inputData.setStdsPelvicInflammatoryDisease(stdsPelvicInflammatoryDisease);
//        inputData.setStdsGenitalHerpes(stdsGenitalHerpes);
//        inputData.setStdsMolluscumContagiosum(stdsMolluscumContagiosum);
//        inputData.setStdsAIDS(stdsAIDS);
//        inputData.setStdsHIV(stdsHIV);
//        inputData.setStdsHepatitisB(stdsHepatitisB);
//        inputData.setStdsHPV(stdsHPV);
//        inputData.setStdsNumberOfDiagnosis(stdsNumberOfDiagnosis);
//        inputData.setStdsTimeSinceFirstDiagnosis(stdsTimeSinceFirstDiagnosis);
//        inputData.setStdsTimeSinceLastDiagnosis(stdsTimeSinceLastDiagnosis);
//
//
////    }
//
//        // Make the API call using Retrofit
//        Call<PredictionResponse> call = apiService.predictCancer(inputData);
//
//        call.enqueue(new Callback<PredictionResponse>() {
//            @Override
//            public void onResponse(Call<PredictionResponse> call, Response<PredictionResponse> response) {
//                if (response != null && response.isSuccessful()) {
//                    PredictionResponse predictionResponse = response.body();
//                    int prediction = predictionResponse.getPrediction();
//                    // Handle the prediction in your app's UI
//                    Log.d("Prediction", "Prediction: " + prediction);
//
//                    // Get the reference to the EditText field
//                    EditText editTextPrediction = getView().findViewById(R.id.editTextPrediction);
//                    // Set the EditText field based on the prediction result
//                    if (prediction == 1) {
//                        editTextPrediction.setText("you have a high risk of getting cervical cancer");
//                    } else if (prediction == 0) {
//                        editTextPrediction.setText("you have a low risk of getting cervical cancer");
//                    } else {
//                        editTextPrediction.setText("Unknown");
//                    }
//                } else {
//                    // Handle API request failure
//                    Log.d("API Error", "API request failed");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PredictionResponse> call, Throwable t) {
//                // Handle API request failure
//                Log.e("API Error", "API request failed", t);
//            }
//        });
//    }
//}
//
//        InputData inputData = new InputData();
////                (age, numPartners, firstIntercourse, numPregnancies, smokes, smokesYears,
////                smokesPacksYear, hormonalContraceptives, hormonalContraceptivesYears, iud, iudYears, stds, stdsNumber,
////                stdsCondylomatosis, stdsCervicalCondylomatosis, stdsVaginalCondylomatosis,
////                stdsVulvoPerinealCondylomatosis, stdsSyphilis, stdsPelvicInflammatoryDisease, stdsGenitalHerpes,
////                stdsMolluscumContagiosum, stdsAIDS, stdsHIV, stdsHepatitisB, stdsHPV, stdsNumDiagnosis,
////                stdsTimeSinceFirstDiagnosis, stdsTimeSinceLastDiagnosis);
//
//        // Make a prediction request using the Retrofit API service
//
//        Call<PredictionResponse> call = apiService.predictCancer(inputData);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()) {
//                    String result = response.body();
//                    // Display the prediction result
//                    Toast.makeText(requireContext(), "Cervical Cancer Risk: " + result, Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.e("Prediction Error", "Prediction request failed: " + response.errorBody());
//                    Toast.makeText(requireContext(), "Prediction failed. Please try again.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.e("Prediction Error", "Prediction request failed: " + t.getMessage());
//                Toast.makeText(requireContext(), "Prediction failed. Please try again.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


//
////package com.example.cervical_cancer;
////
////import android.app.ProgressDialog;
////import android.os.Bundle;
////import android.util.Log;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.AdapterView;
////import android.widget.ArrayAdapter;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.Spinner;
////import android.widget.Toast;
////
////import androidx.annotation.NonNull;
////import androidx.annotation.Nullable;
////import androidx.fragment.app.Fragment;
////
////import com.example.cervical_cancer.modals.InputData;
////
////import retrofit2.Call;
////import retrofit2.Callback;
////import retrofit2.Response;
////import retrofit2.Retrofit;
////import retrofit2.converter.gson.GsonConverterFactory;
////
////public class RiskAssessmentFragment extends Fragment {
////
////    private ApiService apiService;
////    private ProgressDialog progressDialog;
////    private Spinner spinnerAge;
////    private Spinner spinnerNumPartners;
////    private Spinner spinnerFirstIntercourse;
////    private Spinner spinnerNumPregnancies;
////    // Add other Spinners here for the remaining fields
////
////    @Nullable
////    @Override
////    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
////        View view = inflater.inflate(R.layout.fragment_risk_assessment, container, false);
////
////        // Retrofit client setup
////        Retrofit retrofit = new Retrofit.Builder()
////                .baseUrl("http://172.20.10.2:8000/")  // Replace with your API URL
////                .addConverterFactory(GsonConverterFactory.create())
////                .build();
////
////        apiService = retrofit.create(ApiService.class);
////
////        // Find the Predict button by its ID
////        Button predictButton = view.findViewById(R.id.buttonPredict);
////
////        // Set up OnClickListener for the Predict button
////        predictButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                // Perform prediction when the Predict button is clicked
////                performPrediction();
////            }
////        });
////
////        // Initialize the Spinners
////        initializeSpinners(view);
////
////        return view;
////    }
////
////    private void initializeSpinners(View view) {
////        spinnerAge = view.findViewById(R.id.spinnerAge);
////        spinnerNumPartners = view.findViewById(R.id.spinnerNumPartners);
////        spinnerFirstIntercourse = view.findViewById(R.id.spinnerFirstIntercourse);
////        spinnerNumPregnancies = view.findViewById(R.id.spinnerNumPregnancies);
////        // Add other Spinners here for the remaining fields
////
////        // Create ArrayAdapter for ageSpinner
////        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(requireContext(),
////                R.array.age_array, android.R.layout.simple_spinner_item);
////        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        spinnerAge.setAdapter(ageAdapter);
////
////        // Create ArrayAdapter for numPartnersSpinner
////        ArrayAdapter<CharSequence> numPartnersAdapter = ArrayAdapter.createFromResource(requireContext(),
////                R.array.num_partners_array, android.R.layout.simple_spinner_item);
////        numPartnersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        spinnerNumPartners.setAdapter(numPartnersAdapter);
////
////        // Create ArrayAdapter for firstIntercourseSpinner
////        ArrayAdapter<CharSequence> firstIntercourseAdapter = ArrayAdapter.createFromResource(requireContext(),
////                R.array.first_intercourse_array, android.R.layout.simple_spinner_item);
////        firstIntercourseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        spinnerFirstIntercourse.setAdapter(firstIntercourseAdapter);
////
////        // Create ArrayAdapter for numPregnanciesSpinner
////        ArrayAdapter<CharSequence> numPregnanciesAdapter = ArrayAdapter.createFromResource(requireContext(),
////                R.array.num_pregnancies_array, android.R.layout.simple_spinner_item);
////        numPregnanciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        spinnerNumPregnancies.setAdapter(numPregnanciesAdapter);
////
////        // Set OnItemSelectedListener for each Spinner
////        spinnerAge.setOnItemSelectedListener(spinnerItemSelectedListener);
////        spinnerNumPartners.setOnItemSelectedListener(spinnerItemSelectedListener);
////        spinnerFirstIntercourse.setOnItemSelectedListener(spinnerItemSelectedListener);
////        spinnerNumPregnancies.setOnItemSelectedListener(spinnerItemSelectedListener);
////        // Add other Spinners here for the remaining fields
////    }
////
////    private AdapterView.OnItemSelectedListener spinnerItemSelectedListener = new AdapterView.OnItemSelectedListener() {
////        @Override
////        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////            // Perform any necessary actions when a Spinner item is selected
////        }
////
////        @Override
////        public void onNothingSelected(AdapterView<?> parent) {
////            // Handle the case when no Spinner item is selected
////        }
////    };
////
////    private void performPrediction() {
////        // Show a progress dialog while making the API call
////        progressDialog = ProgressDialog.show(requireContext(), "Predicting", "Please wait...", true, false);
////
////        // Retrieve selected values from Spinners
////        int age = Integer.parseInt(spinnerAge.getSelectedItem().toString());
////        int numPartners = Integer.parseInt(spinnerNumPartners.getSelectedItem().toString());
////        int firstIntercourse = Integer.parseInt(spinnerFirstIntercourse.getSelectedItem().toString());
////        int numPregnancies = Integer.parseInt(spinnerNumPregnancies.getSelectedItem().toString());
////        // Retrieve values from other Spinners here for the remaining fields
////
////        // Create an instance of your InputData model and set the values
////        InputData inputData = new InputData();
////        inputData.setAge(age);
////        inputData.setNumberOfSexualPartners(numPartners);
////        inputData.setFirstSexualIntercourse(firstIntercourse);
////        inputData.setNumOfPregnancies(numPregnancies);
////        // Set values in inputData for the remaining fields
////
////        // Make the API call using Retrofit
////        Call<PredictionResponse> call = apiService.predictCancer(inputData);
////
////        call.enqueue(new Callback<PredictionResponse>() {
////            @Override
////            public void onResponse(Call<PredictionResponse> call, Response<PredictionResponse> response) {
////                // Dismiss the progress dialog
////                progressDialog.dismiss();
////
////                if (response != null && response.isSuccessful()) {
////                    PredictionResponse predictionResponse = response.body();
////                    int prediction = predictionResponse.getPrediction();
////                    // Handle the prediction in your app's UI
////                    Log.d("Prediction", "Prediction: " + prediction);
////
////                    // Get the reference to the EditText field
////                    EditText editTextPrediction = requireView().findViewById(R.id.editTextPrediction);
////                    // Set the EditText field based on the prediction result
////                    if (prediction == 1) {
////                        editTextPrediction.setText("You have a high risk of getting cervical cancer");
////                    } else if (prediction == 0) {
////                        editTextPrediction.setText("You have a low risk of getting cervical cancer");
////                    } else {
////                        editTextPrediction.setText("Unknown");
////                    }
////                } else {
////                    // Handle API request failure
////                    Log.d("API Error", "API request failed");
////                    Toast.makeText(requireContext(), "Failed to predict risk. Please try again.", Toast.LENGTH_SHORT).show();
////                }
////            }
////
////            @Override
////            public void onFailure(Call<PredictionResponse> call, Throwable t) {
////                // Dismiss the progress dialog
////                progressDialog.dismiss();
////
////                // Handle API request failure
////                Log.e("API Error", "API request failed", t);
////                Toast.makeText(requireContext(), "Failed to predict risk. Please try again.", Toast.LENGTH_SHORT).show();
////            }
////        });
////    }
////}
