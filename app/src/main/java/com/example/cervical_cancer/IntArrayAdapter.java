package com.example.cervical_cancer;

import android.content.Context;
import android.widget.ArrayAdapter;

public class IntArrayAdapter extends ArrayAdapter<Integer> {

    public IntArrayAdapter(Context context, int resource, int[] objects) {
        super(context, resource);
        for (int value : objects) {
            add(value);
        }
    }
}

