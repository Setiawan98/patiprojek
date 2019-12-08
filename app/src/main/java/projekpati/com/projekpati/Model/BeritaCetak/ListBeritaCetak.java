package projekpati.com.projekpati.Model.BeritaCetak;

public class ListBeritaCetak {
    String id, judul, time, rating_jumlah, rating, file, file_small, deskripsi;
    Integer nomor;
    String ref_berita_cetak_icon, ref_berita_cetak_nama, ref_berita_cetak_warna;

    public ListBeritaCetak(String id, String judul, String time, String rating_jumlah, String rating, String file, String file_small, String deskripsi, Integer nomor, String ref_berita_cetak_icon, String ref_berita_cetak_nama, String ref_berita_cetak_warna) {
        this.id = id;
        this.judul = judul;
        this.time = time;
        this.rating_jumlah = rating_jumlah;
        this.rating = rating;
        this.file = file;
        this.file_small = file_small;
        this.deskripsi = deskripsi;
        this.nomor = nomor;
        this.ref_berita_cetak_icon = ref_berita_cetak_icon;
        this.ref_berita_cetak_nama = ref_berita_cetak_nama;
        this.ref_berita_cetak_warna = ref_berita_cetak_warna;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRating_jumlah() {
        return rating_jumlah;
    }

    public void setRating_jumlah(String rating_jumlah) {
        this.rating_jumlah = rating_jumlah;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getNomor() {
        return nomor;
    }

    public void setNomor(Integer nomor) {
        this.nomor = nomor;
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
}
