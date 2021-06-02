package com.lgstech.angkattani.ui.prediction;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.BarChart;
import com.lgstech.angkattani.DataViewModel;
import com.lgstech.angkattani.R;
import com.lgstech.angkattani.databinding.FragmentPredictionBinding;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class PredictionFragment extends Fragment {

    public static BarChart barPredict;
    private static FragmentPredictionBinding binding;
    private static DataViewModel dataViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_prediction, container, false);
        binding = FragmentPredictionBinding.inflate(inflater, container, false);
        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);
//        Spinner spinner = root.findViewById(R.id.spinner_comodity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerComodity.setAdapter(adapter);
        barPredict = binding.barchart;

        dataViewModel.getParsresult().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    LayoutInflater inflater = getLayoutInflater();
                    View vw = inflater.inflate(R.layout.item, null);
                    TextView tvnama = vw.findViewById(R.id.tvnama);
                    TextView tvharga = vw.findViewById(R.id.tvharga);
                    TextView tvdate = vw.findViewById(R.id.tvdate);
                    tvnama.setText(binding.spinnerComodity.getSelectedItem().toString());
                    tvharga.setText(kurs("Rp. ", Double.parseDouble(s)));
                    tvdate.setText(binding.etdate.getText().toString());
                    builder.setView(vw);
                    builder.show();
                } else {
                    Toast.makeText(getActivity(), "Data not available", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dataViewModel.setHistory("0");
        binding.spinnerComodity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) dataViewModel.setHistory("0");
                else if (i == 1) dataViewModel.setHistory("1");
                else if (i == 2) dataViewModel.setHistory("2");
                else if (i == 3) dataViewModel.setHistory("3");
                else if (i == 4) dataViewModel.setHistory("4");
                else if (i == 5) dataViewModel.setHistory("5");
                else if (i == 6) dataViewModel.setHistory("6");
                else if (i == 7) dataViewModel.setHistory("7");
                else if (i == 8) dataViewModel.setHistory("8");
                else if (i == 9) dataViewModel.setHistory("9");
                else if (i == 10) dataViewModel.setHistory("10");
                else if (i == 11) dataViewModel.setHistory("11");
                else if (i == 12) dataViewModel.setHistory("12");
                else if (i == 13) dataViewModel.setHistory("13");
                else if (i == 14) dataViewModel.setHistory("14");
                else if (i == 15) dataViewModel.setHistory("15");
                else if (i == 16) dataViewModel.setHistory("16");
                Toast.makeText(getActivity(), String.valueOf(binding.spinnerComodity.getSelectedItemPosition()), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        binding.etdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTruitonDatePickerDialog(view);
                inputManager.hideSoftInputFromWindow(binding.etdate.getWindowToken(), 0);
            }
        });


        return binding.getRoot();
    }

    public void showTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    public String kurs(String simbol, double nominal) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setGroupingSeparator('.');
        formatRp.setCurrencySymbol(simbol);
        formatRp.setMonetaryDecimalSeparator(',');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(nominal);

    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog;
            datePickerDialog = new DatePickerDialog(getActivity(), this, year,
                    month, day);
            return datePickerDialog;
        }


        public void onDateSet(DatePicker view, int year, int month, int day) {
            DecimalFormat mFormat = new DecimalFormat("00");
//            mFormat.format(Double.valueOf(year));
            binding.etdate.setText(year + "-" + mFormat.format(Double.valueOf(month + 1)) + "-" + mFormat.format(Double.valueOf(day)));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date strDate = null;
            try {
                strDate = sdf.parse(binding.etdate.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (System.currentTimeMillis() > strDate.getTime()) {
                binding.btncekprice.setBackgroundColor(Color.argb(250, 82, 155, 77));
                binding.btnpredict.setBackgroundColor(Color.argb(100, 145, 199, 136));
                binding.btncekprice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dataViewModel.setPrice(String.valueOf(binding.spinnerComodity.getSelectedItemPosition()), binding.etdate.getText().toString(), true);
                    }
                });
            } else if (System.currentTimeMillis() < strDate.getTime()) {
                binding.btnpredict.setBackgroundColor(Color.argb(250, 82, 155, 77));
                binding.btncekprice.setBackgroundColor(Color.argb(100, 145, 199, 136));
                binding.btnpredict.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("TAG", "PREDICT");
                        dataViewModel.setPrice(String.valueOf(binding.spinnerComodity.getSelectedItemPosition()), binding.etdate.getText().toString(), false);
                    }
                });
            }
        }


    }
}