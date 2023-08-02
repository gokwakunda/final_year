package com.example.cervical_cancer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cervical_cancer.adapters.UserDataAdapter;
import com.example.cervical_cancer.modals.UserData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDataFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserDataAdapter userDataAdapter;
    private List<UserData> userDataList;
    private DatabaseReference databaseReference; // Add this line to declare the databaseReference variable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_data, container, false);

        // Initialize the RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewUserData);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        userDataList = new ArrayList<>();
        userDataAdapter = new UserDataAdapter(userDataList);
        recyclerView.setAdapter(userDataAdapter);

        // Initialize the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Query the Firebase Realtime Database to fetch the data
        DatabaseReference userDataRef = databaseReference.child("inputData");
        userDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userDataList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserData userData = snapshot.getValue(UserData.class);
                    if (userData != null) {
                        userDataList.add(userData);
                    }
                }
                userDataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Data retrieval cancelled: " + databaseError.getMessage());
            }
        });

        return view;
    }
           private void retrieveUserData() {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<UserData> userDataList = new ArrayList<>();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Map<String, Object> userDataMap = (Map<String, Object>) snapshot.getValue();

                        // Retrieve the email as a String
                        String email = (String) userDataMap.get("email");

                        // Retrieve all other features as integers
                        int age = getIntValue(userDataMap.get("age"));
                        int numberOfSexualPartners = getIntValue(userDataMap.get("numPartners"));
                        int firstSexualIntercourse = getIntValue(userDataMap.get("firstIntercourse"));
                        int numOfPregnancies = getIntValue(userDataMap.get("numPregnancies"));
                        int smokes = getIntValue(userDataMap.get("smokes"));
                        int smokesYears = getIntValue(userDataMap.get("smokesYears"));
                        int smokesPacksYear = getIntValue(userDataMap.get("smokesPacksYear"));
                        int hormonalContraceptives = getIntValue(userDataMap.get("hormonalContraceptives"));
                        int hormonalContraceptivesYears = getIntValue(userDataMap.get("hormonalContraceptivesYears"));
                        int iud = getIntValue(userDataMap.get("iud"));
                        int iudYears = getIntValue(userDataMap.get("iudYears"));
                        int stds = getIntValue(userDataMap.get("stds"));
                        int stdsNumber = getIntValue(userDataMap.get("stdsNumber"));
                        int stdsCondylomatosis= getIntValue(userDataMap.get("stdsCondylomatosis"));
                        int stdsCervicalCondylomatosis=getIntValue(userDataMap.get("stdsCervicalCondylomatosis"));
                        int stdsVaginalCondylomatosis=getIntValue(userDataMap.get("stdsVaginalCondylomatosis"));
                        int stdsVulvoPerinealCondylomatosis=getIntValue(userDataMap.get("stdsVulvoPerinealCondylomatosis"));
                        int stdsSyphilis=getIntValue(userDataMap.get("stdsSyphilis"));
                        int stdsPelvicInflammatoryDisease=getIntValue(userDataMap.get("stdsPelvicInflammatoryDisease"));
                         int stdsGenitalHerpes=getIntValue(userDataMap.get("stdsGenitalHerpes"));
                         int stdsMolluscumContagiosum=getIntValue(userDataMap.get("stdsMolluscumContagiosum"));
                         int stdsAIDS=getIntValue(userDataMap.get("stdsAIDS"));
                         int stdsHIV=getIntValue(userDataMap.get("stdsHIV"));
                         int stdsHepatitisB=getIntValue(userDataMap.get("stdsHIV"));
                         int stdsHPV=getIntValue(userDataMap.get("stdsHPV"));
                        int stdsNumberOfDiagnosis = getIntValue(userDataMap.get("stdsNumberOfDiagnosis"));
                        int stdsTimeSinceFirstDiagnosis = getIntValue(userDataMap.get("stdsTimeSinceFirstDiagnosis"));
                        int stdsTimeSinceLastDiagnosis = getIntValue(userDataMap.get("stdsTimeSinceLastDiagnosis"));
                        int prediction = getIntValue(userDataMap.get("prediction"));

                        // Create a new UserData object with the retrieved data
                        UserData userData = new UserData();
                        userData.setEmail(email);
                        userData.setAge(age);
                        userData.setNumberOfSexualPartners(numberOfSexualPartners);
                        userData.setFirstSexualIntercourse(firstSexualIntercourse);
                        userData.setNumOfPregnancies(numOfPregnancies);
                        userData.setSmokes(smokes);
                        userData.setSmokesYears(smokesYears);
                        userData.setSmokesPacksYear(smokesPacksYear);
                        userData.setHormonalContraceptives(hormonalContraceptives);
                        userData.setHormonalContraceptivesYears(hormonalContraceptivesYears);
                        userData.setIud(iud);
                        userData.setIudYears(iudYears);
                        userData.setStds(stds);
                        userData.setStdsNumber(stdsNumber);
                        userData.setStdsCondylomatosis(stdsCondylomatosis);
                        userData.setStdsCervicalCondylomatosis(stdsCervicalCondylomatosis);
                        userData.setStdsVaginalCondylomatosis(stdsVaginalCondylomatosis);
                        userData.setStdsNumberOfDiagnosis(stdsNumberOfDiagnosis);
                        userData.setStdsVulvoPerinealCondylomatosis(stdsVulvoPerinealCondylomatosis);
                        userData.setStdsSyphilis(stdsSyphilis);
                        userData.setStdsPelvicInflammatoryDisease(stdsPelvicInflammatoryDisease);
                        userData.setStdsGenitalHerpes(stdsGenitalHerpes);
                        userData.setStdsMolluscumContagiosum(stdsMolluscumContagiosum);
                        userData.setStdsAIDS(stdsAIDS);
                        userData.setStdsAIDS(stdsHIV);
                        userData.setStdsHepatitisB(stdsHepatitisB);
                        userData.setStdsHPV(stdsHPV);
                        userData.setStdsTimeSinceFirstDiagnosis(stdsTimeSinceFirstDiagnosis);
                        userData.setStdsTimeSinceLastDiagnosis(stdsTimeSinceLastDiagnosis);
                        userData.setPrediction(prediction);

                        // Add the UserData object to the list
                        userDataList.add(userData);
                    }

                    // Now you have the list of UserData objects with retrieved data
                    // Do whatever you want with the userDataList here
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error if any
                }
            });
        }

        private int getIntValue(Object value) {
            if (value instanceof Long) {
                return ((Long) value).intValue();
            } else if (value instanceof Integer) {
                return (Integer) value;
            }
            // Handle other cases as needed (e.g., throw an exception or return a default value)
            return 0; // Return 0 as a default value
        }
    }


