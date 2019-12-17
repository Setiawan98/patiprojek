package projekpati.com.projekpati.Model.PerangkatDaerah;

public class ListPerda {
    String id, ref_perangkat_daerah_icon, ref_perangkat_daerah_nama, ref_perangkat_daerah_warna, nama, alamat, email, telpon, website, deskripsi, rating_jumlah, rating, file, file_small;
    Integer nomor;

    public ListPerda(String id, String ref_perangkat_daerah_icon, String ref_perangkat_daerah_nama, String ref_perangkat_daerah_warna, String nama, String alamat, String email, String telpon, String website, String deskripsi, String rating_jumlah, String rating, String file, String file_small, Integer nomor) {
        this.id = id;
        this.ref_perangkat_daerah_icon = ref_perangkat_daerah_icon;
        this.ref_perangkat_daerah_nama = ref_perangkat_daerah_nama;
        this.ref_perangkat_daerah_warna = ref_perangkat_daerah_warna;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.telpon = telpon;
        this.website = website;
        this.deskripsi = deskripsi;
        this.rating_jumlah = rating_jumlah;
        this.rating = rating;
        this.file = file;
        this.file_small = file_small;
        this.nomor = nomor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef_perangkat_daerah_icon() {
        return ref_perangkat_daerah_icon;
    }

    public void setRef_perangkat_daerah_icon(String ref_perangkat_daerah_icon) {
        this.ref_perangkat_daerah_icon = ref_perangkat_daerah_icon;
    }

    public String getRef_perangkat_daerah_nama() {
        return ref_perangkat_daerah_nama;
    }

    public void setRef_perangkat_daerah_nama(String ref_perangkat_daerah_nama) {
        this.ref_perangkat_daerah_nama = ref_perangkat_daerah_nama;
    }

    public String getRef_perangkat_daerah_warna() {
        return ref_perangkat_daerah_warna;
    }

    public void setRef_perangkat_daerah_warna(String ref_perangkat_daerah_warna) {
        this.ref_perangkat_daerah_warna = ref_perangkat_daerah_warna;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
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

    public Integer getNomor() {
        return nomor;
    }

    public void setNomor(Integer nomor) {
        this.nomor = nomor;
    }
}
