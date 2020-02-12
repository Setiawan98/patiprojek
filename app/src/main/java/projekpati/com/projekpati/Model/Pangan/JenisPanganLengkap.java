package projekpati.com.projekpati.Model.Pangan;

import java.util.Map;

import projekpati.com.projekpati.Model.Pariwisata.JenisPariwisata;

public class JenisPanganLengkap {
    String status, judul, key, waktu, icon;
    Integer jumlah_data;
    Map<String, JenisPangan> data;

    public JenisPanganLengkap(String status, String judul, String key, String waktu, String icon, Integer jumlah_data, Map<String, JenisPangan> data) {
        this.status = status;
        this.judul = judul;
        this.key = key;
        this.waktu = waktu;
        this.icon = icon;
        this.jumlah_data = jumlah_data;
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setJumlah_data(Integer jumlah_data) {
        this.jumlah_data = jumlah_data;
    }

    public void setData(Map<String, JenisPangan> data) {
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

    public Map<String, JenisPangan> getData() {
        return data;
    }
}
