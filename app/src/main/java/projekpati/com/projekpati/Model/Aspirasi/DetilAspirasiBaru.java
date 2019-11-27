package projekpati.com.projekpati.Model.Aspirasi;

import java.util.List;

public class DetilAspirasiBaru {
    String id, ref_aspirasi_nama, ref_aspirasi_icon, ref_aspirasi_warna, rating, rating_jumlah, nama, deskripsi, latitude, longitude, file, file_small, publish;
    List<GambarAspirasiDetil> gambar;

    public DetilAspirasiBaru(String id, String ref_aspirasi_nama, String ref_aspirasi_icon, String ref_aspirasi_warna, String rating, String rating_jumlah, String nama, String deskripsi, String latitude, String longitude, String file, String file_small, String publish, List<GambarAspirasiDetil> gambar) {
        this.id = id;
        this.ref_aspirasi_nama = ref_aspirasi_nama;
        this.ref_aspirasi_icon = ref_aspirasi_icon;
        this.ref_aspirasi_warna = ref_aspirasi_warna;
        this.rating = rating;
        this.rating_jumlah = rating_jumlah;
        this.nama = nama;
        this.deskripsi = deskripsi;
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

    public String getRef_aspirasi_nama() {
        return ref_aspirasi_nama;
    }

    public void setRef_aspirasi_nama(String ref_aspirasi_nama) {
        this.ref_aspirasi_nama = ref_aspirasi_nama;
    }

    public String getRef_aspirasi_icon() {
        return ref_aspirasi_icon;
    }

    public void setRef_aspirasi_icon(String ref_aspirasi_icon) {
        this.ref_aspirasi_icon = ref_aspirasi_icon;
    }

    public String getRef_aspirasi_warna() {
        return ref_aspirasi_warna;
    }

    public void setRef_aspirasi_warna(String ref_aspirasi_warna) {
        this.ref_aspirasi_warna = ref_aspirasi_warna;
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

    public List<GambarAspirasiDetil> getGambar() {
        return gambar;
    }

    public void setGambar(List<GambarAspirasiDetil> gambar) {
        this.gambar = gambar;
    }
}
