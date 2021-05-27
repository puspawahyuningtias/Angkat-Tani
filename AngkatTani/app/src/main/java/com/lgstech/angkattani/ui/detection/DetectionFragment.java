package com.lgstech.angkattani.ui.detection;

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

public class DetectionFragment extends Fragment {

    private DetectionViewModel detectionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        detectionViewModel =
                new ViewModelProvider(this).get(DetectionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_detection, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        detectionViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}