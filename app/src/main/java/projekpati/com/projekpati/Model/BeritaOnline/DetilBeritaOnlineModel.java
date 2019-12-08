package projekpati.com.projekpati.Model.BeritaOnline;

import projekpati.com.projekpati.Model.BeritaOnline.DetilBeritaOnlineBaru;

public class DetilBeritaOnlineModel {
    String status, judul, key, waktu, icon;
    DetilBeritaOnlineBaru data;

    public DetilBeritaOnlineModel(String status, String judul, String key, String waktu, String icon, DetilBeritaOnlineBaru data) {
        this.status = status;
        this.judul = judul;
        this.key = key;
        this.waktu = waktu;
        this.icon = icon;
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

    public DetilBeritaOnlineBaru getData() {
        return data;
    }
}
