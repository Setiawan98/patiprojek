package projekpati.com.projekpati.Model.Otomotif;

import java.util.List;

public class DetilOtomotifBaru {
    String id, ref_otomotif_icon, ref_otomotif_nama, ref_otomotif_warna, rating, rating_jumlah, nama, penjual, telp, email, website, deskripsi, alamat, harga, kondisi, model, warna, kilometer, thn_pembuatan, transmisi, habis_terjual, latitude, longitude, file, file_small, publish;
    List<GambarOtomotifDetil> gambar;

    public DetilOtomotifBaru(String id, String ref_otomotif_icon, String ref_otomotif_nama, String ref_otomotif_warna, String rating, String rating_jumlah, String nama, String penjual, String telp, String email, String website, String deskripsi, String alamat, String harga, String kondisi, String model, String warna, String kilometer, String thn_pembuatan, String transmisi, String habis_terjual, String latitude, String longitude, String file, String file_small, String publish, List<GambarOtomotifDetil> gambar) {
        this.id = id;
        this.ref_otomotif_icon = ref_otomotif_icon;
        this.ref_otomotif_nama = ref_otomotif_nama;
        this.ref_otomotif_warna = ref_otomotif_warna;
        this.rating = rating;
        this.rating_jumlah = rating_jumlah;
        this.nama = nama;
        this.penjual = penjual;
        this.telp = telp;
        this.email = email;
        this.website = website;
        this.deskripsi = deskripsi;
        this.alamat = alamat;
        this.harga = harga;
        this.kondisi = kondisi;
        this.model = model;
        this.warna = warna;
        this.kilometer = kilometer;
        this.thn_pembuatan = thn_pembuatan;
        this.transmisi = transmisi;
        this.habis_terjual = habis_terjual;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getRef_otomotif_icon() {
        return ref_otomotif_icon;
    }

    public void setRef_otomotif_icon(String ref_otomotif_icon) {
        this.ref_otomotif_icon = ref_otomotif_icon;
    }

    public String getRef_otomotif_nama() {
        return ref_otomotif_nama;
    }

    public void setRef_otomotif_nama(String ref_otomotif_nama) {
        this.ref_otomotif_nama = ref_otomotif_nama;
    }

    public String getRef_otomotif_warna() {
        return ref_otomotif_warna;
    }

    public void setRef_otomotif_warna(String ref_otomotif_warna) {
        this.ref_otomotif_warna = ref_otomotif_warna;
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

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getKilometer() {
        return kilometer;
    }

    public void setKilometer(String kilometer) {
        this.kilometer = kilometer;
    }

    public String getThn_pembuatan() {
        return thn_pembuatan;
    }

    public void setThn_pembuatan(String thn_pembuatan) {
        this.thn_pembuatan = thn_pembuatan;
    }

    public String getTransmisi() {
        return transmisi;
    }

    public void setTransmisi(String transmisi) {
        this.transmisi = transmisi;
    }

    public String getHabis_terjual() {
        return habis_terjual;
    }

    public void setHabis_terjual(String habis_terjual) {
        this.habis_terjual = habis_terjual;
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

    public List<GambarOtomotifDetil> getGambar() {
        return gambar;
    }

    public void setGambar(List<GambarOtomotifDetil> gambar) {
        this.gambar = gambar;
    }
}
