package projekpati.com.projekpati.Model.Pendidikan;

import java.util.List;

public class DetilPendidikanBaru {
    String id, ref_pendidikan_nama, ref_pendidikan_icon, ref_pendidikan_warna, rating, rating_jumlah, nama, telp, email, website, deskripsi, latitude, longitude, alamat, file, file_small, publish;
    List<GambarPendidikanDetil> gambar;

    public DetilPendidikanBaru(String id, String ref_pendidikan_nama, String ref_pendidikan_icon, String ref_pendidikan_warna, String rating, String rating_jumlah, String nama, String telp, String email, String website, String deskripsi, String latitude, String longitude, String alamat, String file, String file_small, String publish, List<GambarPendidikanDetil> gambar) {
        this.id = id;
        this.ref_pendidikan_nama = ref_pendidikan_nama;
        this.ref_pendidikan_icon = ref_pendidikan_icon;
        this.ref_pendidikan_warna = ref_pendidikan_warna;
        this.rating = rating;
        this.rating_jumlah = rating_jumlah;
        this.nama = nama;
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

    public String getRef_pendidikan_nama() {
        return ref_pendidikan_nama;
    }

    public void setRef_pendidikan_nama(String ref_pendidikan_nama) {
        this.ref_pendidikan_nama = ref_pendidikan_nama;
    }

    public String getRef_pendidikan_icon() {
        return ref_pendidikan_icon;
    }

    public void setRef_pendidikan_icon(String ref_pendidikan_icon) {
        this.ref_pendidikan_icon = ref_pendidikan_icon;
    }

    public String getRef_pendidikan_warna() {
        return ref_pendidikan_warna;
    }

    public void setRef_pendidikan_warna(String ref_pendidikan_warna) {
        this.ref_pendidikan_warna = ref_pendidikan_warna;
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

    public List<GambarPendidikanDetil> getGambar() {
        return gambar;
    }

    public void setGambar(List<GambarPendidikanDetil> gambar) {
        this.gambar = gambar;
    }
}
