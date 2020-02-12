package projekpati.com.projekpati.Model.Pangan;

public class ListPangan {
    String id, nama, harga, tgl, rating_jumlah, rating, file, file_small;
    Integer nomor;
    String ref_harga_pangan_icon, ref_harga_pangan_nama, ref_harga_pangan_warna, hari_ini, jam_buka;

    public ListPangan(String id, String nama, String harga, String tgl, String rating_jumlah, String rating, String file, String file_small, Integer nomor, String ref_harga_pangan_icon, String ref_harga_pangan_nama, String ref_harga_pangan_warna, String hari_ini, String jam_buka) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.tgl = tgl;
        this.rating_jumlah = rating_jumlah;
        this.rating = rating;
        this.file = file;
        this.file_small = file_small;
        this.nomor = nomor;
        this.ref_harga_pangan_icon = ref_harga_pangan_icon;
        this.ref_harga_pangan_nama = ref_harga_pangan_nama;
        this.ref_harga_pangan_warna = ref_harga_pangan_warna;
        this.hari_ini = hari_ini;
        this.jam_buka = jam_buka;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public void setRating_jumlah(String rating_jumlah) {
        this.rating_jumlah = rating_jumlah;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setFile_small(String file_small) {
        this.file_small = file_small;
    }

    public void setNomor(Integer nomor) {
        this.nomor = nomor;
    }

    public void setRef_harga_pangan_icon(String ref_harga_pangan_icon) {
        this.ref_harga_pangan_icon = ref_harga_pangan_icon;
    }

    public void setRef_harga_pangan_nama(String ref_harga_pangan_nama) {
        this.ref_harga_pangan_nama = ref_harga_pangan_nama;
    }

    public void setRef_harga_pangan_warna(String ref_harga_pangan_warna) {
        this.ref_harga_pangan_warna = ref_harga_pangan_warna;
    }

    public void setHari_ini(String hari_ini) {
        this.hari_ini = hari_ini;
    }

    public void setJam_buka(String jam_buka) {
        this.jam_buka = jam_buka;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getHarga() {
        return harga;
    }

    public String getTgl() {
        return tgl;
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

    public Integer getNomor() {
        return nomor;
    }

    public String getRef_harga_pangan_icon() {
        return ref_harga_pangan_icon;
    }

    public String getRef_harga_pangan_nama() {
        return ref_harga_pangan_nama;
    }

    public String getRef_harga_pangan_warna() {
        return ref_harga_pangan_warna;
    }

    public String getHari_ini() {
        return hari_ini;
    }

    public String getJam_buka() {
        return jam_buka;
    }
}
