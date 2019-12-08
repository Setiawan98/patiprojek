package projekpati.com.projekpati.Model.BeritaOnline;

import java.util.List;

import projekpati.com.projekpati.Model.BeritaOnline.GambarBeritaOnlineDetil;

public class DetilBeritaOnlineBaru {
    String id, ref_beritaOnline_icon, ref_beritaOnline_nama, ref_beritaOnline_warna, judul, time,  rating_jumlah, rating, file, file_small, publish, deskripsi;
    List<GambarBeritaOnlineDetil> gambar;

    public DetilBeritaOnlineBaru(String id, String ref_beritaOnline_icon, String ref_beritaOnline_nama, String ref_beritaOnline_warna, String judul, String time, String rating_jumlah, String rating, String file, String file_small, String publish, String deskripsi, List<GambarBeritaOnlineDetil> gambar) {
        this.id = id;
        this.ref_beritaOnline_icon = ref_beritaOnline_icon;
        this.ref_beritaOnline_nama = ref_beritaOnline_nama;
        this.ref_beritaOnline_warna = ref_beritaOnline_warna;
        this.judul = judul;
        this.time = time;
        this.rating_jumlah = rating_jumlah;
        this.rating = rating;
        this.file = file;
        this.file_small = file_small;
        this.publish = publish;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
    }

    public String getId() {
        return id;
    }

    public String getRef_beritaOnline_icon() {
        return ref_beritaOnline_icon;
    }

    public String getRef_beritaOnline_nama() {
        return ref_beritaOnline_nama;
    }

    public String getRef_beritaOnline_warna() {
        return ref_beritaOnline_warna;
    }

    public String getJudul() {
        return judul;
    }

    public String getTime() {
        return time;
    }

    public String getRating_jumlah() {
        return rating_jumlah;
    }

    public String getRating() {
        return rating;
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

    public String getDeskripsi() {
        return deskripsi;
    }

    public List<GambarBeritaOnlineDetil> getGambar() {
        return gambar;
    }
}
