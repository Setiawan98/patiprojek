package projekpati.com.projekpati.Model.Lapak;

import java.util.List;

public class DetilLapakBaru {
    String id, ref_lapak_icon, ref_lapak_nama, ref_lapak_warna, rating, rating_jumlah, nama, barang, harga, telp, email, website, deskripsi, latitude, longitude, alamat, file, file_small, publish;
    List<GambarLapakDetil> gambar;

    public DetilLapakBaru(String id, String ref_lapak_icon, String ref_lapak_nama, String ref_lapak_warna, String rating, String rating_jumlah, String nama, String barang, String harga, String telp, String email, String website, String deskripsi, String latitude, String longitude, String alamat, String file, String file_small, String publish, List<GambarLapakDetil> gambar) {
        this.id = id;
        this.ref_lapak_icon = ref_lapak_icon;
        this.ref_lapak_nama = ref_lapak_nama;
        this.ref_lapak_warna = ref_lapak_warna;
        this.rating = rating;
        this.rating_jumlah = rating_jumlah;
        this.nama = nama;
        this.barang = barang;
        this.harga = harga;
        this.telp = telp;
        this.email = email;
        this.website = website;
        this.deskripsi = deskripsi;
        this.latitude = latitude;
        this.longitude = longitude;
        this.alamat = alamat;
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

    public String getRef_lapak_icon() {
        return ref_lapak_icon;
    }

    public void setRef_lapak_icon(String ref_lapak_icon) {
        this.ref_lapak_icon = ref_lapak_icon;
    }

    public String getRef_lapak_nama() {
        return ref_lapak_nama;
    }

    public void setRef_lapak_nama(String ref_lapak_nama) {
        this.ref_lapak_nama = ref_lapak_nama;
    }

    public String getRef_lapak_warna() {
        return ref_lapak_warna;
    }

    public void setRef_lapak_warna(String ref_lapak_warna) {
        this.ref_lapak_warna = ref_lapak_warna;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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

    public List<GambarLapakDetil> getGambar() {
        return gambar;
    }

    public void setGambar(List<GambarLapakDetil> gambar) {
        this.gambar = gambar;
    }
}
