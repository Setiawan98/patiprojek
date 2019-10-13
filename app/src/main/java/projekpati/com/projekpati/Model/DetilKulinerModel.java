package projekpati.com.projekpati.Model;

import java.util.Map;

public class DetilKulinerModel {
    String status, judul, key, waktu, icon;
    ListKuliner data;


    public DetilKulinerModel(String status, String judul, String key, String waktu, String icon, ListKuliner data) {
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


//    public ListKuliner getData() {
//        return data;
//    }


    public ListKuliner getData() {
        return data;
    }
}
