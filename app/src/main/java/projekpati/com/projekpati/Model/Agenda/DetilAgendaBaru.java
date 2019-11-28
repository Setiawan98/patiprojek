package projekpati.com.projekpati.Model.Agenda;

import java.util.List;


public class DetilAgendaBaru {

    String id, ref_agenda_icon, ref_agenda_nama, ref_agenda_warna, rating, rating_jumlah, nama, telp, email, website, deskripsi, latitude, longitude, alamat, file, file_small, publish, tgl_mulai, tgl_selesai, harga_tiket;
    List<GambarAgendaDetil> gambar;

    public DetilAgendaBaru(String id, String ref_agenda_icon, String ref_agenda_nama, String ref_agenda_warna, String rating, String rating_jumlah, String nama, String telp, String email, String website, String deskripsi, String latitude, String longitude, String alamat, String file, String file_small, String publish, String tgl_mulai, String tgl_selesai, String harga_tiket, List<GambarAgendaDetil> gambar) {
        this.id = id;
        this.ref_agenda_icon = ref_agenda_icon;
        this.ref_agenda_nama = ref_agenda_nama;
        this.ref_agenda_warna = ref_agenda_warna;
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
        this.tgl_mulai = tgl_mulai;
        this.tgl_selesai = tgl_selesai;
        this.harga_tiket = harga_tiket;
        this.gambar = gambar;
    }

    public String getId() {
        return id;
    }

    public String getRef_agenda_icon() {
        return ref_agenda_icon;
    }

    public String getRef_agenda_nama() {
        return ref_agenda_nama;
    }

    public String getRef_agenda_warna() {
        return ref_agenda_warna;
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

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public String getHarga_tiket() {
        return harga_tiket;
    }

    public List<GambarAgendaDetil> getGambar() {
        return gambar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRef_agenda_icon(String ref_agenda_icon) {
        this.ref_agenda_icon = ref_agenda_icon;
    }

    public void setRef_agenda_nama(String ref_agenda_nama) {
        this.ref_agenda_nama = ref_agenda_nama;
    }

    public void setRef_agenda_warna(String ref_agenda_warna) {
        this.ref_agenda_warna = ref_agenda_warna;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setRating_jumlah(String rating_jumlah) {
        this.rating_jumlah = rating_jumlah;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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

    public void setTgl_mulai(String tgl_mulai) {
        this.tgl_mulai = tgl_mulai;
    }

    public void setTgl_selesai(String tgl_selesai) {
        this.tgl_selesai = tgl_selesai;
    }

    public void setHarga_tiket(String harga_tiket) {
        this.harga_tiket = harga_tiket;
    }

    public void setGambar(List<GambarAgendaDetil> gambar) {
        this.gambar = gambar;
    }
}
