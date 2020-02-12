package projekpati.com.projekpati.Model.TempatIbadah;

import java.util.List;

public class DetilIbadahBaru {
    String id, ref_ibadah_icon, ref_ibadah_nama, ref_ibadah_warna, rating, rating_jumlah, nama, telp, email, website, deskripsi, latitude, longitude, alamat, file, file_small, publish, hari_0, hari_1, hari_2, hari_3, hari_4, hari_5, hari_6;
    List<GambarIbadahDetil> gambar;

    public DetilIbadahBaru(String id, String ref_ibadah_icon, String ref_ibadah_nama, String ref_ibadah_warna, String rating, String rating_jumlah, String nama, String telp, String email, String website, String deskripsi, String latitude, String longitude, String alamat, String file, String file_small, String publish, String hari_0, String hari_1, String hari_2, String hari_3, String hari_4, String hari_5, String hari_6, List<GambarIbadahDetil> gambar) {
        this.id = id;
        this.ref_ibadah_icon = ref_ibadah_icon;
        this.ref_ibadah_nama = ref_ibadah_nama;
        this.ref_ibadah_warna = ref_ibadah_warna;
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
        this.hari_0 = hari_0;
        this.hari_1 = hari_1;
        this.hari_2 = hari_2;
        this.hari_3 = hari_3;
        this.hari_4 = hari_4;
        this.hari_5 = hari_5;
        this.hari_6 = hari_6;
        this.gambar = gambar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef_ibadah_icon() {
        return ref_ibadah_icon;
    }

    public void setRef_ibadah_icon(String ref_ibadah_icon) {
        this.ref_ibadah_icon = ref_ibadah_icon;
    }

    public String getRef_ibadah_nama() {
        return ref_ibadah_nama;
    }

    public void setRef_ibadah_nama(String ref_ibadah_nama) {
        this.ref_ibadah_nama = ref_ibadah_nama;
    }

    public String getRef_ibadah_warna() {
        return ref_ibadah_warna;
    }

    public void setRef_ibadah_warna(String ref_ibadah_warna) {
        this.ref_ibadah_warna = ref_ibadah_warna;
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

    public String getHari_0() {
        return hari_0;
    }

    public void setHari_0(String hari_0) {
        this.hari_0 = hari_0;
    }

    public String getHari_1() {
        return hari_1;
    }

    public void setHari_1(String hari_1) {
        this.hari_1 = hari_1;
    }

    public String getHari_2() {
        return hari_2;
    }

    public void setHari_2(String hari_2) {
        this.hari_2 = hari_2;
    }

    public String getHari_3() {
        return hari_3;
    }

    public void setHari_3(String hari_3) {
        this.hari_3 = hari_3;
    }

    public String getHari_4() {
        return hari_4;
    }

    public void setHari_4(String hari_4) {
        this.hari_4 = hari_4;
    }

    public String getHari_5() {
        return hari_5;
    }

    public void setHari_5(String hari_5) {
        this.hari_5 = hari_5;
    }

    public String getHari_6() {
        return hari_6;
    }

    public void setHari_6(String hari_6) {
        this.hari_6 = hari_6;
    }

    public List<GambarIbadahDetil> getGambar() {
        return gambar;
    }

    public void setGambar(List<GambarIbadahDetil> gambar) {
        this.gambar = gambar;
    }
}
