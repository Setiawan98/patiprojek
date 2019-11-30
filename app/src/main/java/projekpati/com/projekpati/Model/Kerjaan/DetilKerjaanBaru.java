package projekpati.com.projekpati.Model.Kerjaan;

import java.util.List;

import projekpati.com.projekpati.Model.Kerjaan.GambarKerjaanDetil;

public class DetilKerjaanBaru {


    String id, ref_kerjaan_icon, ref_kerjaan_nama, ref_kerjaan_warna, rating, rating_jumlah, nama, telp, email, website, deskripsi, latitude, longitude, alamat, file, file_small, publish, kantor, gaji, pengalaman;
    List<GambarKerjaanDetil> gambar;

    public DetilKerjaanBaru(String id, String ref_kerjaan_icon, String ref_kerjaan_nama, String ref_kerjaan_warna, String rating, String rating_jumlah, String nama, String telp, String email, String website, String deskripsi, String latitude, String longitude, String alamat, String file, String file_small, String publish, String kantor, String gaji, String pengalaman, List<GambarKerjaanDetil> gambar) {
        this.id = id;
        this.ref_kerjaan_icon = ref_kerjaan_icon;
        this.ref_kerjaan_nama = ref_kerjaan_nama;
        this.ref_kerjaan_warna = ref_kerjaan_warna;
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
        this.kantor = kantor;
        this.gaji = gaji;
        this.pengalaman = pengalaman;
        this.gambar = gambar;
    }

    public String getId() {
        return id;
    }

    public String getRef_kerjaan_icon() {
        return ref_kerjaan_icon;
    }

    public String getRef_kerjaan_nama() {
        return ref_kerjaan_nama;
    }

    public String getRef_kerjaan_warna() {
        return ref_kerjaan_warna;
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

    public String getTelp() {
        return telp;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getAlamat() {
        return alamat;
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

    public String getKantor() {
        return kantor;
    }

    public String getGaji() {
        return gaji;
    }

    public String getPengalaman() {
        return pengalaman;
    }

    public List<GambarKerjaanDetil> getGambar() {
        return gambar;
    }
}
