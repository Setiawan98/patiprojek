package projekpati.com.projekpati.Model.KodePos;

import java.util.List;

public class DetilKodePosBaru {
    String id, ref_kode_pos_icon, ref_kode_pos_nama, ref_kode_pos_warna, rating, rating_jumlah, kode,kelurahan, kecamatan,kabupaten, deskripsi, time, file, file_small, publish;
    List<GambarKodePosDetil> gambar;

    public DetilKodePosBaru(String id, String ref_kode_pos_icon, String ref_kode_pos_nama, String ref_kode_pos_warna, String rating, String rating_jumlah, String kode, String kelurahan, String kecamatan, String kabupaten, String deskripsi, String time, String file, String file_small, String publish, List<GambarKodePosDetil> gambar) {
        this.id = id;
        this.ref_kode_pos_icon = ref_kode_pos_icon;
        this.ref_kode_pos_nama = ref_kode_pos_nama;
        this.ref_kode_pos_warna = ref_kode_pos_warna;
        this.rating = rating;
        this.rating_jumlah = rating_jumlah;
        this.kode = kode;
        this.kelurahan = kelurahan;
        this.kecamatan = kecamatan;
        this.kabupaten = kabupaten;
        this.deskripsi = deskripsi;
        this.time = time;
        this.file = file;
        this.file_small = file_small;
        this.publish = publish;
        this.gambar = gambar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRef_kode_pos_icon(String ref_kode_pos_icon) {
        this.ref_kode_pos_icon = ref_kode_pos_icon;
    }

    public void setRef_kode_pos_nama(String ref_kode_pos_nama) {
        this.ref_kode_pos_nama = ref_kode_pos_nama;
    }

    public void setRef_kode_pos_warna(String ref_kode_pos_warna) {
        this.ref_kode_pos_warna = ref_kode_pos_warna;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setRating_jumlah(String rating_jumlah) {
        this.rating_jumlah = rating_jumlah;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setFile_small(String file_small) {
        this.file_small = file_small;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public void setGambar(List<GambarKodePosDetil> gambar) {
        this.gambar = gambar;
    }

    public String getId() {
        return id;
    }

    public String getRef_kode_pos_icon() {
        return ref_kode_pos_icon;
    }

    public String getRef_kode_pos_nama() {
        return ref_kode_pos_nama;
    }

    public String getRef_kode_pos_warna() {
        return ref_kode_pos_warna;
    }

    public String getRating() {
        return rating;
    }

    public String getRating_jumlah() {
        return rating_jumlah;
    }

    public String getKode() {
        return kode;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getTime() {
        return time;
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

    public List<GambarKodePosDetil> getGambar() {
        return gambar;
    }
}
