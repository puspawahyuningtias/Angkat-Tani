package com.lgstech.angkattani.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lgstech.angkattani.R;

public class AboutFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        final TextView textView = root.findViewById(R.id.tv_description);
        textView.setText("Angkat Tani is a project part of capstonce project under Bangkit Program 2021. Angkat Tani is a projet that help farmers to optimalize their crop, so they can get the highest income, and finally reach economic welfare.");
        return root;
    }
}