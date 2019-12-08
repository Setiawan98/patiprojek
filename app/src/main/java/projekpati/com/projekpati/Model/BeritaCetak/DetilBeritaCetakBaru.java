package projekpati.com.projekpati.Model.BeritaCetak;

import java.util.List;

public class DetilBeritaCetakBaru {
    String id, ref_berita_cetak_icon, ref_berita_cetak_nama, ref_berita_cetak_warna, rating, rating_jumlah, judul, deskripsi, time, file, file_small, publish;
    List<GambarBeritaCetakDetil> gambar;

    public DetilBeritaCetakBaru(String id, String ref_berita_cetak_icon, String ref_berita_cetak_nama, String ref_berita_cetak_warna, String rating, String rating_jumlah, String judul, String deskripsi, String time, String file, String file_small, String publish, List<GambarBeritaCetakDetil> gambar) {
        this.id = id;
        this.ref_berita_cetak_icon = ref_berita_cetak_icon;
        this.ref_berita_cetak_nama = ref_berita_cetak_nama;
        this.ref_berita_cetak_warna = ref_berita_cetak_warna;
        this.rating = rating;
        this.rating_jumlah = rating_jumlah;
        this.judul = judul;
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

    public void setId(String id) {
        this.id = id;
    }

    public String getRef_berita_cetak_icon() {
        return ref_berita_cetak_icon;
    }

    public void setRef_berita_cetak_icon(String ref_berita_cetak_icon) {
        this.ref_berita_cetak_icon = ref_berita_cetak_icon;
    }

    public String getRef_berita_cetak_nama() {
        return ref_berita_cetak_nama;
    }

    public void setRef_berita_cetak_nama(String ref_berita_cetak_nama) {
        this.ref_berita_cetak_nama = ref_berita_cetak_nama;
    }

    public String getRef_berita_cetak_warna() {
        return ref_berita_cetak_warna;
    }

    public void setRef_berita_cetak_warna(String ref_berita_cetak_warna) {
        this.ref_berita_cetak_warna = ref_berita_cetak_warna;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating_jumlah() {
        return rating_jumlah;
    }

    public void setRating_jumlah(String rating_jumlah) {
        this.rating_jumlah = rating_jumlah;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFile_small() {
        return file_small;
    }

    public void setFile_small(String file_small) {
        this.file_small = file_small;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public List<GambarBeritaCetakDetil> getGambar() {
        return gambar;
    }

    public void setGambar(List<GambarBeritaCetakDetil> gambar) {
        this.gambar = gambar;
    }
}
