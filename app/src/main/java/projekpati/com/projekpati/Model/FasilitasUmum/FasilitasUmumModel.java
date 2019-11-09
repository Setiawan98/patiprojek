package projekpati.com.projekpati.Model.FasilitasUmum;

import java.util.Map;

import projekpati.com.projekpati.Model.Kesehatan.ListKesehatan;

public class FasilitasUmumModel {
    String status, judul, key, waktu, icon;
    Integer jumlah_data, halaman_selanjutnya;
    Map<String, ListFasilitasUmum> data;

    public FasilitasUmumModel(String status, String judul, String key, String waktu, String icon, Integer jumlah_data, Integer halaman_selanjutnya, Map<String, ListFasilitasUmum> data) {
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

    public Map<String, ListFasilitasUmum> getData() {
        return data;
    }
}
