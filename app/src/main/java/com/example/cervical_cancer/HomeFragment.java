package com.example.cervical_cancer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cervical_cancer.adapters.IssueAdaptor;
import com.example.cervical_cancer.modals.IssueModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements IssueAdaptor.OnIssueClickListener {

    private RecyclerView recyclerView;
    private DatabaseReference database;
    private IssueAdaptor issueAdapter;
    private ArrayList<IssueModal> originalList;
    private ArrayList<IssueModal> filteredList;
    private SearchView searchField;
    private ArrayList<IssueModal> list;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.post_listRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchField = view.findViewById(R.id.searchInput);

        database = FirebaseDatabase.getInstance().getReference("issues");
        list = new ArrayList<>();
        originalList = new ArrayList<>();
        filteredList = new ArrayList<>();
        issueAdapter = new IssueAdaptor(getContext(), filteredList, this); // Update the adapter with filteredList
        recyclerView.setAdapter(issueAdapter);

        // Read data from the database
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                originalList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    IssueModal issueModal = snapshot.getValue(IssueModal.class);
                    list.add(issueModal);
                    originalList.add(issueModal);
                }
                filterList(""); // Initially, show all items in the RecyclerView
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });

        // Add search functionality
        searchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        return view;
    }

    private void filterList(String query) {
        filteredList.clear(); // Clear the filteredList ArrayList before filtering

        if (query == null || query.trim().isEmpty()) {
            // If the query is null or empty, show all items from the originalList
            filteredList.addAll(originalList);
        } else {
            // Filter the items based on the query
            for (IssueModal issueModal : originalList) {
                if (issueModal.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(issueModal);
                }
            }
        }

        issueAdapter.notifyDataSetChanged(); // Notify the adapter about the data change
    }

    @Override
    public void onIssueClicked(int position) {
        IssueModal issueModal = filteredList.get(position);

        startActivity(new Intent(getContext(), CommentActivity.class)
                .putExtra("id", list.get(position).getId()));
//        startActivity(new Intent(getContext(), CommentActivity.class)
//                .putExtra("issue_id", issueModal.getId()));

    }
}