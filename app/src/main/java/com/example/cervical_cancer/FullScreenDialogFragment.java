package com.example.cervical_cancer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;
public class FullScreenDialogFragment extends DialogFragment {

    public static FullScreenDialogFragment newInstance() {
        return new FullScreenDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create and return a custom dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_full_screen_dialog, null); // Replace with your custom layout
        builder.setView(view);
        return builder.create();
    }
}
