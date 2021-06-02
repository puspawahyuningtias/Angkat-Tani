package com.lgstech.angkattani;

import org.json.JSONObject;

public class Model {

    Float harga;
    int tahun,bulan;

    public Model(JSONObject history) {
        try {
            int tahun = history.optInt("tahun");
            float harga = Float.parseFloat(history.optString("harga"));
            this.tahun = tahun;
            this.harga = harga;
            setHarga(harga);
            setTahun(tahun);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Model() {

    }

    public float getHarga() {
        return harga;
    }

    public float setHarga(float harga) {
        return harga;
    }

    public int getTahun() {
        return tahun;
    }

    public int setTahun(int tahun) {
        return tahun;
    }

    public int getBulan() {
        return bulan;
    }

    public int setBulan(int bulan) {
        this.bulan = bulan;
        return bulan;
    }
}
