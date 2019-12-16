package projekpati.com.projekpati.Model.TelpPenting;

import java.util.List;

import projekpati.com.projekpati.Model.TelpPenting.GambarTelpPentingDetil;

public class DetilTelpPentingBaru {
    String id, ref_telp_penting_icon, ref_telp_penting_nama, ref_telp_penting_warna, rating, rating_jumlah, nama,notelp, deskripsi, time, file, file_small, publish;
    List<GambarTelpPentingDetil> gambar;

    public DetilTelpPentingBaru(String id, String ref_telp_penting_icon, String ref_telp_penting_nama, String ref_telp_penting_warna, String rating, String rating_jumlah, String nama, String notelp, String deskripsi, String time, String file, String file_small, String publish, List<GambarTelpPentingDetil> gambar) {
        this.id = id;
        this.ref_telp_penting_icon = ref_telp_penting_icon;
        this.ref_telp_penting_nama = ref_telp_penting_nama;
        this.ref_telp_penting_warna = ref_telp_penting_warna;
        this.rating = rating;
        this.rating_jumlah = rating_jumlah;
        this.nama = nama;
        this.notelp = notelp;
        this.deskripsi = deskripsi;
        this.time = time;
        this.file = file;
        this.file_small = file_small;
        this.publish = publish;
        this.gambar = gambar;
    }

    public String getId() {
        return id;
    }

    public String getRef_telp_penting_icon() {
        return ref_telp_penting_icon;
    }

    public String getRef_telp_penting_nama() {
        return ref_telp_penting_nama;
    }

    public String getRef_telp_penting_warna() {
        return ref_telp_penting_warna;
    }

    public String getRating() {
        return rating;
    }

    public String getRating_jumlah() {
        return rating_jumlah;
    }

    public String getNama() {
        return nama;
    }

    public String getNotelp() {
        return notelp;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getTime() {
        return time;
    }

    public String getFile() {
        return file;
    }

    public String getFile_small() {
        return file_small;
    }

    public String getPublish() {
        return publish;
    }

    public List<GambarTelpPentingDetil> getGambar() {
        return gambar;
    }
}
