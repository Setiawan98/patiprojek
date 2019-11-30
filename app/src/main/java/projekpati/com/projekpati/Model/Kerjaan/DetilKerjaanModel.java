package projekpati.com.projekpati.Model.Kerjaan;

import projekpati.com.projekpati.Model.Kerjaan.DetilKerjaanBaru;

public class DetilKerjaanModel {
    String status, judul, key, waktu, icon;
    DetilKerjaanBaru data;


    public DetilKerjaanModel(String status, String judul, String key, String waktu, String icon, DetilKerjaanBaru data) {
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

    public DetilKerjaanBaru getData() {
        return data;
    }
}
