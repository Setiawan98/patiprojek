package projekpati.com.projekpati.Model.Bioskop;

import java.util.Map;

public class JenisBioskopLengkap {
    String status, judul, key, waktu, icon;
    Integer jumlah_data;
    Map<String, JenisBioskop> data;

    public JenisBioskopLengkap(String status, String judul, String key, String waktu, String icon, Integer jumlah_data, Map<String, JenisBioskop> data) {
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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getJumlah_data() {
        return jumlah_data;
    }

    public void setJumlah_data(Integer jumlah_data) {
        this.jumlah_data = jumlah_data;
    }

    public Map<String, JenisBioskop> getData() {
        return data;
    }

    public void setData(Map<String, JenisBioskop> data) {
        this.data = data;
    }
}
