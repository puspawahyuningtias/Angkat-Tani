package com.lgstech.angkattani.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Angkat Tani is a project part of capstonce project under Bangkit Program 2021. Angkat Tani is a projet that help farmers to optimalize their crop, so they can get the highest income, and finally reach economic welfare.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}