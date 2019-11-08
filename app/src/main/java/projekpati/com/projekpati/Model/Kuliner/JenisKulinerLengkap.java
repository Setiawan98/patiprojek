package projekpati.com.projekpati.Model.Kuliner;

import java.util.Map;

public class JenisKulinerLengkap {
    String status, judul, key, waktu, icon;
    Integer jumlah_data;
    Map<String,JenisKuliner> data;

    public JenisKulinerLengkap(String status, String judul, String key, String waktu, String icon, Integer jumlah_data, Map<String, JenisKuliner> data) {
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

    public Map<String, JenisKuliner> getData() {
        return data;
    }
}
