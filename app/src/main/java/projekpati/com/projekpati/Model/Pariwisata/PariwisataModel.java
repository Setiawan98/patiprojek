package projekpati.com.projekpati.Model.Pariwisata;

import java.util.Map;

import projekpati.com.projekpati.Model.Tukang.ListTukang;

public class PariwisataModel {
    String status, judul, key, waktu, icon;
    Integer jumlah_data, halaman_selanjutnya;
    Map<String, ListPariwisata> data;

    public PariwisataModel(String status, String judul, String key, String waktu, String icon, Integer jumlah_data, Integer halaman_selanjutnya, Map<String, ListPariwisata> data) {
        this.status = status;
        this.judul = judul;
        this.key = key;
        this.waktu = waktu;
        this.icon = icon;
        this.jumlah_data = jumlah_data;
        this.halaman_selanjutnya = halaman_selanjutnya;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getJudul() {
        return judul;
    }

    public String getKey() {
        return key;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getIcon() {
        return icon;
    }

    public Integer getJumlah_data() {
        return jumlah_data;
    }

    public Integer getHalaman_selanjutnya() {
        return halaman_selanjutnya;
    }

    public Map<String, ListPariwisata> getData() {
        return data;
    }
}
