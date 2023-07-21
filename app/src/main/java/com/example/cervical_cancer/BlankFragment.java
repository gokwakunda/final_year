package com.example.cervical_cancer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cervical_cancer.adapters.YouTubePlayerAdapter;

public class BlankFragment extends Fragment {

    private RecyclerView recyclerView;
    private YouTubePlayerAdapter adapter;
    private String[] videoIds = {
            "DuVYzxa0HVI", // Replace with the first video ID
            "CXvWBzmQ__E", // Replace with the second video ID
            "N208o3klgpo", // Replace with the third video ID
            "6g4X8hAoFqM"  // Replace with the fourth video ID
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new YouTubePlayerAdapter(videoIds);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
