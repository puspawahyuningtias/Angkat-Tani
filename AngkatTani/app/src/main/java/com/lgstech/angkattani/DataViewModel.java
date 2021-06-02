package com.lgstech.angkattani;

import android.graphics.Color;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.lgstech.angkattani.ui.prediction.PredictionFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyStore;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static android.content.ContentValues.TAG;

public class DataViewModel extends ViewModel {
    private static final String URL = "https://angkat-tani-40404.et.r.appspot.com/";
//    private static final String URL = "https://192.168.100.27/api_capstone/";
    //    private static final String URL = "https://192.168.5.104/api_capstone/";
    private static final String HISTORY = URL + "history_baru.php";
    private static final String CEKPRICE = URL + "cekprice.php";
    private static final String PREDICT = URL + "prediksi.php";
    public static float hasil;
    final MutableLiveData<String> parsresult = new MutableLiveData<>();

    public void setHistory(String comodity) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<BarEntry> bar = new ArrayList<>();
        Log.d(TAG, "1. setURL : " + HISTORY);
        String url = HISTORY + "?comodity=" + comodity;
        bar.clear();
        try {
            KeyStore trustStore = MySSLSocketFactory.getKeystore();
            MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            client.setSSLSocketFactory(sf);
            client.setTimeout(600000);
            client.setConnectTimeout(10000);
        } catch (Exception e) {
            Log.e(TAG, "error while using truststore for apk instllation..");
        }
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d(TAG, "2. onSuccess : " + responseBody);
                try {

                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    Log.d("onSuccess", "3. onSuccess : result : " + result);

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject his = list.getJSONObject(i);
                        Model models = new Model();
                        float harga = models.setHarga(Float.parseFloat(his.optString("harga")));
                        int tahun = models.setTahun(his.optInt("tahun"));
                        int bulan = models.setBulan(his.optInt("bulan"));
                        bar.add(new BarEntry(bulan, harga, tahun));

                        Log.d(TAG, "4. onSuccess : Data : " + his);
                    }
                    BarDataSet barDataset = new BarDataSet(bar, "Last years data / Kg");
                    BarData barData = new BarData(barDataset);
                    barData.setBarWidth(0.4f);
                    PredictionFragment.barPredict.animateY(2000);
                    PredictionFragment.barPredict.setData(barData);
                    PredictionFragment.barPredict.setFitBars(true);
                    Description description = new Description();
                    description.setText(" ");
                    PredictionFragment.barPredict.setDescription(description);
                    PredictionFragment.barPredict.invalidate();
                    barDataset.setColor(Color.argb(200, 82, 155, 77));

                } catch (JSONException e) {
                    Log.d(TAG, e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, error.getMessage());
            }
        });
    }

    public void setPrice(String value, String date, boolean status) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url;
//        final float hasil = new float[1];
        if (status == true) {
            Log.d(TAG, "1. setURL : " + CEKPRICE);
            url = CEKPRICE + "?comodity=" + value + "&date=" + date + "&status=" + status;
        } else {
            Log.d(TAG, "1. setURL : " + PREDICT);
            url = PREDICT + "?comodity=" + value + "&date=" + date + "&status=" + status;
        }
        try {
            KeyStore trustStore = MySSLSocketFactory.getKeystore();
            MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            client.setSSLSocketFactory(sf);
            client.setTimeout(600000);
            client.setConnectTimeout(10000);
        } catch (Exception e) {
            Log.e(TAG, "error while using truststore for apk instllation..");
        }
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d(TAG, "2. onSuccess : " + responseBody);
                try {

                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    Log.d("onSuccess", "3. onSuccess : result : " + result);
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject his = list.getJSONObject(i);
                        Model models = new Model();
                        hasil = models.setHarga(Float.parseFloat(his.optString("harga")));
                        Log.d(TAG, "4. onSuccess : Data : " + his);
                    }
                    if (hasil == 0) parsresult.postValue(null);
                    else parsresult.postValue(String.valueOf(hasil));
                } catch (JSONException e) {
                    Log.d(TAG, e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, error.getMessage());
            }
        });
    }


    public LiveData<String> getParsresult() {
        return parsresult;
    }

}
