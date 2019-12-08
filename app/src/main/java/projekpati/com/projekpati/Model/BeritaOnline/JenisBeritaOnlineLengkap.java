package projekpati.com.projekpati.Model.BeritaOnline;

import java.util.Map;

import projekpati.com.projekpati.Model.BeritaOnline.JenisBeritaOnline;

public class JenisBeritaOnlineLengkap {

    String status, judul, key, waktu, icon;
    Integer jumlah_data;
    Map<String, JenisBeritaOnline> data;

    public JenisBeritaOnlineLengkap(String status, String judul, String key, String waktu, String icon, Integer jumlah_data, Map<String, JenisBeritaOnline> data) {
        this.status = status;
        this.judul = judul;
        this.key = key;
        this.waktu = waktu;
        this.icon = icon;
        this.jumlah_data = jumlah_data;
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

    public Map<String, JenisBeritaOnline> getData() {
        return data;
    }
}
