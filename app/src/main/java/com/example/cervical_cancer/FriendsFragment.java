package com.example.cervical_cancer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.cervical_cancer.adapters.UsersAdapter;
import com.example.cervical_cancer.modals.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FriendsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<User> users;
    private ProgressBar progressBar;
    private UsersAdapter usersAdapter;
    private UsersAdapter.OnUserClickListener onUserClickListener;
    private SwipeRefreshLayout refreshLayout;

    private String myImgUrl;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FriendsFragment() {
        // Required empty public constructor
    }

    public static FriendsFragment newInstance(String param1, String param2) {
        FriendsFragment fragment = new FriendsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        recyclerView = view.findViewById(R.id.user_rv);
        progressBar = view.findViewById(R.id.frnds_progressbar);
        users = new ArrayList<>();
        refreshLayout = view.findViewById(R.id.fndSwipeLayout);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
                refreshLayout.setRefreshing(false);
            }
        });

        onUserClickListener = new UsersAdapter.OnUserClickListener() {
            @Override
            public void onUserClicked(int position) {
                User selectedUser = users.get(position);
                selectedUser.setHasUnreadMessages(false);
                usersAdapter.notifyItemChanged(position);
                startActivity(new Intent(getContext(), MessageActivity.class)
                        .putExtra("username_of_roommate", selectedUser.getUsername())
                        .putExtra("email_of_roommate", selectedUser.getEmail())
                        .putExtra("image_of_roommate", selectedUser.getProfilePicture())
                        .putExtra("my_img", myImgUrl));
                Toast.makeText(getContext(), "Clicked on user: " + selectedUser.getUsername(), Toast.LENGTH_SHORT).show();
            }
        };

        getUsers();
        return view;
    }

    private void getUsers() {
        users.clear();
        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user != null) {
                        users.add(user);
                    }
                }

                usersAdapter = new UsersAdapter(users, getContext(), onUserClickListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(usersAdapter);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }
}


//package com.example.cervical_cancer;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//
//import com.example.cervical_cancer.adapters.UsersAdaptor;
//import com.example.cervical_cancer.modals.User;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class FriendsFragment extends Fragment {
//    private RecyclerView recyclerView;
//    private ArrayList<User> users;
//    private ProgressBar progressBar;
//    private UsersAdaptor usersAdapter;
//    private UsersAdaptor.OnUserClickListener onUserClickListener;
//    private SwipeRefreshLayout refreshLayout;
//
//    private String myImgUrl;
//
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    private String mParam1;
//    private String mParam2;
//
//    public FriendsFragment() {
//        // Required empty public constructor
//    }
//
//    public static FriendsFragment newInstance(String param1, String param2) {
//        FriendsFragment fragment = new FriendsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_friends, container, false);
//
//        recyclerView = view.findViewById(R.id.user_rv);
//        progressBar = view.findViewById(R.id.frnds_progressbar);
//        users = new ArrayList<>();
//        refreshLayout = view.findViewById(R.id.fndSwipeLayout);
//
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                getUsers();
//                refreshLayout.setRefreshing(false);
//            }
//        });
//
//        onUserClickListener = new UsersAdaptor.OnUserClickListener() {
//            @Override
//            public void onUserClicked(int position) {
//                startActivity(new Intent(getContext(), MessageActivity.class)
//                        .putExtra("username_of_roommate", users.get(position).getUsername())
//                        .putExtra("email_of_roommate", users.get(position).getEmail())
//                        .putExtra("image_of_roommate", users.get(position).getProfilePicture())
//                        .putExtra("my_img", myImgUrl));
//                Toast.makeText(getContext(), "Clicked on user: " + users.get(position).getUsername(), Toast.LENGTH_SHORT).show();
//            }
//        };
//
//        getUsers();
//        return view;
//    }
//
//    private void getUsers() {
//        users.clear();
//        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    User user = dataSnapshot.getValue(User.class);
//                    if (user != null) {
//                        users.add(user);
//                    }
//                }
//
//                usersAdapter = new UsersAdaptor(users, getContext(), onUserClickListener);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                recyclerView.setAdapter(usersAdapter);
//                progressBar.setVisibility(View.GONE);
//                recyclerView.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Handle error
//            }
//        });
//    }
//}
//

//package com.example.cervical_cancer;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//
//import com.example.cervical_cancer.adapters.UsersAdaptor;
//import com.example.cervical_cancer.modals.User;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link FriendsFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class FriendsFragment extends Fragment {
//    private RecyclerView recyclerView;
//    private ArrayList<User> users;
//    private ProgressBar progressBar;
//    private UsersAdaptor usersAdaptor;
//    UsersAdaptor.OnUserClickListener onUserClickListener;
//    private SwipeRefreshLayout refreshLayout;
//
//    private String myImgUrl;
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public FriendsFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment FriendsFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static FriendsFragment newInstance(String param1, String param2) {
//        FriendsFragment fragment = new FriendsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_friends, container, false);
//
//        recyclerView = view.findViewById(R.id.user_rv);
//        progressBar = view.findViewById(R.id.frnds_progressbar);
//        users = new ArrayList<>();
//        refreshLayout = view.findViewById(R.id.fndSwipeLayout);
//
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                getUsers();
//                refreshLayout.setRefreshing(false);
//            }
//        });
//
//        onUserClickListener = new UsersAdaptor.OnUserClickListener() {
//            @Override
//            public void onUserClicked(int position) {
//                startActivity(new Intent(getContext(), MessageActivity.class)
//                        .putExtra("username_of_roommate", users.get(position).getUsername())
//                        .putExtra("email_of_roommate", users.get(position).getEmail())
//                        .putExtra("image_of_roommate", users.get(position).getProfilePicture())
//                        .putExtra("my_img", myImgUrl));
//                Toast.makeText(getContext(), "clicked on user" + users.get(position).getUsername(), Toast.LENGTH_SHORT).show();
//            }
//        };
//
//        getUsers();
//        return view;
//    }
//    private void getUsers() {
//        users.clear();
//        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
//
////            }
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//        User user = dataSnapshot.getValue(User.class);
//        if (user != null) {
//            users.add(user);
//        }
//    }
//
//    // Update the adapter and UI
//    usersAdaptor = new UsersAdaptor(users, getContext(), onUserClickListener);
//    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//    recyclerView.setAdapter(usersAdaptor);
//    progressBar.setVisibility(View.GONE);
//    recyclerView.setVisibility(View.VISIBLE);
//}
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Handle error
//            }
//        });
//    }
//
//}