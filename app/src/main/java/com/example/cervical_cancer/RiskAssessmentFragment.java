
package com.example.cervical_cancer;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cervical_cancer.modals.InputData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RiskAssessmentFragment extends Fragment {

    private ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_risk_assessment, container, false);

        // Retrofit client setup
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.2:8000/")  // Replace with your API URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

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

        return view;
    }

    private void performPrediction() {
        // Retrieve input values from EditText fields
        EditText editTextAge = getView().findViewById(R.id.editTextAge);
        EditText editTextNumPartners = getView().findViewById(R.id.editTextNumPartners);
        EditText editTextFirstIntercourse = getView().findViewById(R.id.editTextFirstIntercourse);
        EditText editTextNumPregnancies = getView().findViewById(R.id.editTextNumPregnancies);
        EditText editTextSmokes = getView().findViewById(R.id.editTextSmokes);
        EditText editTextSmokesYears = getView().findViewById(R.id.editTextSmokesYears);
        EditText editTextSmokesPacksYear = getView().findViewById(R.id.editTextSmokesPacksYear);
        EditText editTextHormonalContraceptives = getView().findViewById(R.id.editTextHormonalContraceptives);
        EditText editTextHormonalContraceptivesYears = getView().findViewById(R.id.editTextHormonalContraceptivesYears);
        EditText editTextIUD = getView().findViewById(R.id.editTextIUD);
        EditText editTextIUDYears = getView().findViewById(R.id.editTextIUDYears);
        EditText editTextSTDs = getView().findViewById(R.id.editTextSTDs);
        EditText editTextSTDsNumber = getView().findViewById(R.id.editTextSTDsNumber);
        EditText editTextSTDsCondylomatosis = getView().findViewById(R.id.editTextSTDsCondylomatosis);
        EditText editTextSTDsCervicalCondylomatosis = getView().findViewById(R.id.editTextSTDsCervicalCondylomatosis);
        EditText editTextSTDsVaginalCondylomatosis = getView().findViewById(R.id.editTextSTDsVaginalCondylomatosis);
        EditText editTextSTDsVulvoPerinealCondylomatosis = getView().findViewById(R.id.editTextSTDsVulvoPerinealCondylomatosis);
        EditText editTextSTDsSyphilis = getView().findViewById(R.id.editTextSTDsSyphilis);
        EditText editTextSTDPelvicInflammatoryDisease = getView().findViewById(R.id.editTextSTDPelvicInflammatoryDisease);
        EditText editTextSTDsGenitalHerpes = getView().findViewById(R.id.editTextSTDsGenitalHerpes);
        EditText editTextSTDsMolluscumContagiosum = getView().findViewById(R.id.editTextSTDsMolluscumContagiosum);
        EditText editTextSTDAIDS = getView().findViewById(R.id.editTextSTDAIDS);
        EditText editTextSTDsHIV = getView().findViewById(R.id.editTextSTDsHIV);
        EditText editTextSTDsHepatitisB = getView().findViewById(R.id.editTextSTDsHepatitisB);
        EditText editTextSTDsHPV = getView().findViewById(R.id.editTextSTDsHPV);
        EditText editTextSTDsNumDiagnosis = getView().findViewById(R.id.editTextSTDsNumDiagnosis);
        EditText editTextSTDsTimeSinceFirstDiagnosis = getView().findViewById(R.id.editTextSTDsTimeSinceFirstDiagnosis);
        EditText editTextSTDsTimeSinceLastDiagnosis = getView().findViewById(R.id.editTextSTDsTimeSinceLastDiagnosis);

        // Validate input values
        if (TextUtils.isEmpty(editTextAge.getText().toString())
                || TextUtils.isEmpty(editTextNumPartners.getText().toString())
                || TextUtils.isEmpty(editTextFirstIntercourse.getText().toString())
                || TextUtils.isEmpty(editTextNumPregnancies.getText().toString())
                || TextUtils.isEmpty(editTextSmokes.getText().toString())
                || TextUtils.isEmpty(editTextSmokesYears.getText().toString())
                || TextUtils.isEmpty(editTextSmokesPacksYear.getText().toString())
                || TextUtils.isEmpty(editTextHormonalContraceptives.getText().toString())
                || TextUtils.isEmpty(editTextHormonalContraceptivesYears.getText().toString())
                || TextUtils.isEmpty(editTextIUD.getText().toString())
                || TextUtils.isEmpty(editTextIUDYears.getText().toString())
                || TextUtils.isEmpty(editTextSTDs.getText().toString())
                || TextUtils.isEmpty(editTextSTDsNumber.getText().toString())
                || TextUtils.isEmpty(editTextSTDsCondylomatosis.getText().toString())
                || TextUtils.isEmpty(editTextSTDsCervicalCondylomatosis.getText().toString())
                || TextUtils.isEmpty(editTextSTDsVaginalCondylomatosis.getText().toString())
                || TextUtils.isEmpty(editTextSTDsVulvoPerinealCondylomatosis.getText().toString())
                || TextUtils.isEmpty(editTextSTDsSyphilis.getText().toString())
                || TextUtils.isEmpty(editTextSTDPelvicInflammatoryDisease.getText().toString())
                || TextUtils.isEmpty(editTextSTDsGenitalHerpes.getText().toString())
                || TextUtils.isEmpty(editTextSTDsMolluscumContagiosum.getText().toString())
                || TextUtils.isEmpty(editTextSTDAIDS.getText().toString())
                || TextUtils.isEmpty(editTextSTDsHIV.getText().toString())
                || TextUtils.isEmpty(editTextSTDsHepatitisB.getText().toString())
                || TextUtils.isEmpty(editTextSTDsHPV.getText().toString())
                || TextUtils.isEmpty(editTextSTDsNumDiagnosis.getText().toString())
                || TextUtils.isEmpty(editTextSTDsTimeSinceFirstDiagnosis.getText().toString())
                || TextUtils.isEmpty(editTextSTDsTimeSinceLastDiagnosis.getText().toString())
        ) {
            Toast.makeText(getActivity(), "Please enter all values", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert input values to the desired data types
        int age = Integer.parseInt(editTextAge.getText().toString());
        int numPartners = Integer.parseInt(editTextNumPartners.getText().toString());
        int firstIntercourse = Integer.parseInt(editTextFirstIntercourse.getText().toString());
        int numPregnancies = Integer.parseInt(editTextNumPregnancies.getText().toString());
        int smokes = Integer.parseInt(editTextSmokes.getText().toString());
        int smokesYears = Integer.parseInt(editTextSmokesYears.getText().toString());
        int smokesPacksYear = Integer.parseInt(editTextSmokesPacksYear.getText().toString());
        int hormonalContraceptives = Integer.parseInt(editTextHormonalContraceptives.getText().toString());
        int hormonalContraceptivesYears = Integer.parseInt(editTextHormonalContraceptivesYears.getText().toString());
        int iud = Integer.parseInt(editTextIUD.getText().toString());
        int iudYears = Integer.parseInt(editTextIUDYears.getText().toString());
        int stds = Integer.parseInt(editTextSTDs.getText().toString());
        int stdsNumber = Integer.parseInt(editTextSTDsNumber.getText().toString());
        int stdsCondylomatosis = Integer.parseInt(editTextSTDsCondylomatosis.getText().toString());
        int stdsCervicalCondylomatosis = Integer.parseInt(editTextSTDsCervicalCondylomatosis.getText().toString());
        int stdsVaginalCondylomatosis = Integer.parseInt(editTextSTDsVaginalCondylomatosis.getText().toString());
        int stdsVulvoPerinealCondylomatosis = Integer.parseInt(editTextSTDsVulvoPerinealCondylomatosis.getText().toString());
        int stdsSyphilis = Integer.parseInt(editTextSTDsSyphilis.getText().toString());
        int stdsPelvicInflammatoryDisease = Integer.parseInt(editTextSTDPelvicInflammatoryDisease.getText().toString());
        int stdsGenitalHerpes = Integer.parseInt(editTextSTDsGenitalHerpes.getText().toString());
        int stdsMolluscumContagiosum = Integer.parseInt(editTextSTDsMolluscumContagiosum.getText().toString());
        int stdsAIDS = Integer.parseInt(editTextSTDAIDS.getText().toString());
        int stdsHIV = Integer.parseInt(editTextSTDsHIV.getText().toString());
        int stdsHepatitisB = Integer.parseInt(editTextSTDsHepatitisB.getText().toString());
        int stdsHPV = Integer.parseInt(editTextSTDsHPV.getText().toString());
        int stdsNumDiagnosis = Integer.parseInt(editTextSTDsNumDiagnosis.getText().toString());
        int stdsTimeSinceFirstDiagnosis = Integer.parseInt(editTextSTDsTimeSinceFirstDiagnosis.getText().toString());
        int stdsTimeSinceLastDiagnosis = Integer.parseInt(editTextSTDsTimeSinceLastDiagnosis.getText().toString());

        // Create an instance of your InputData model and set the values
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
        inputData.setStdsNumberOfDiagnosis(stdsNumDiagnosis);
        inputData.setStdsTimeSinceFirstDiagnosis(stdsTimeSinceFirstDiagnosis);
        inputData.setStdsTimeSinceLastDiagnosis(stdsTimeSinceLastDiagnosis);

        // Make the API call using Retrofit
        Call<PredictionResponse> call = apiService.predictCancer(inputData);

        call.enqueue(new Callback<PredictionResponse>() {
            @Override
            public void onResponse(Call<PredictionResponse> call, Response<PredictionResponse> response) {
                if (response != null && response.isSuccessful()) {
                    PredictionResponse predictionResponse = response.body();
                    int prediction = predictionResponse.getPrediction();
                    // Handle the prediction in your app's UI
                    Log.d("Prediction", "Prediction: " + prediction);

                    // Get the reference to the EditText field
                    EditText editTextPrediction = getView().findViewById(R.id.editTextPrediction);
                    // Set the EditText field based on the prediction result
                    if (prediction == 1) {
                        editTextPrediction.setText("you have a high risk of getting cervical cancer");
                    } else if (prediction == 0) {
                        editTextPrediction.setText("you have a low risk of getting cervical cancer");
                    } else {
                        editTextPrediction.setText("Unknown");
                    }
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
        });
    }
}


//package com.example.cervical_cancer;
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
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
//import androidx.annotation.Nullable;
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
//    private ProgressDialog progressDialog;
//    private ApiService apiService;
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
//
//        @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_risk_assessment, container, false);
//
//        // Retrofit client setup
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://172.20.10.2:8000/")  // Replace with your API URL
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
//
//        // Initialize spinners
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
//                R.array.smokes_packs_per_year_array, android.R.layout.simple_spinner_item);
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
//        // Set spinner item selection listeners if needed
//
//        return view;
//    }
//
//
//
//    private void performPrediction() {
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
//        // Create an InputData object with the selected values
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
//        // Show progress dialog while waiting for the prediction result
//        progressDialog = ProgressDialog.show(requireContext(), "Predicting", "Please wait...", true);
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
////        // Make the prediction request
////        Call<PredictionResponse> call = apiService.predictCancer(inputData);
////        call.enqueue(new Callback<PredictionResponse>() {
////            @Override
////            public void onResponse(Call<PredictionResponse> call, Response<PredictionResponse> response) {
////                if (response.isSuccessful()) {
////                    PredictionResponse predictionResponse = response.body();
////                    if (predictionResponse != null) {
////                        // Handle the prediction response
////                        handlePredictionResponse(predictionResponse);
////                    } else {
////                        // Show error message if the response body is null
////                        showErrorMessage("Prediction response body is null");
////                    }
////                } else {
////                    // Show error message if the response is not successful
////                    showErrorMessage("Prediction request failed");
////                }
////                // Dismiss the progress dialog
////                progressDialog.dismiss();
////            }
////
////            @Override
////            public void onFailure(Call<PredictionResponse> call, Throwable t) {
////                // Show error message if the prediction request fails
////                showErrorMessage("Prediction request failed: " + t.getMessage());
////                // Dismiss the progress dialog
////                progressDialog.dismiss();
////            }
////        });
////    }
////
////    private void handlePredictionResponse(PredictionResponse predictionResponse) {
////        // Get the predicted risk value from the response
////        double risk = predictionResponse.getRisk();
////
////        // Display the predicted risk to the user
////        Toast.makeText(requireContext(), "Predicted Risk: " + risk, Toast.LENGTH_SHORT).show();
////    }
////
////    private void showErrorMessage(String message) {
////        Toast.makeText(requireContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
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

//        InputData inputData = new InputData();
////                (age, numPartners, firstIntercourse, numPregnancies, smokes, smokesYears,
////                smokesPacksYear, hormonalContraceptives, hormonalContraceptivesYears, iud, iudYears, stds, stdsNumber,
////                stdsCondylomatosis, stdsCervicalCondylomatosis, stdsVaginalCondylomatosis,
////                stdsVulvoPerinealCondylomatosis, stdsSyphilis, stdsPelvicInflammatoryDisease, stdsGenitalHerpes,
////                stdsMolluscumContagiosum, stdsAIDS, stdsHIV, stdsHepatitisB, stdsHPV, stdsNumDiagnosis,
////                stdsTimeSinceFirstDiagnosis, stdsTimeSinceLastDiagnosis);
//
//        // Make a prediction request using the Retrofit API service
////        Call<String> call = apiService.predictCervicalCancerRisk(inputData);
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
