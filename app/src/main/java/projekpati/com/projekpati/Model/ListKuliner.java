package projekpati.com.projekpati.Model;

public class ListKuliner {
    String id, nama, pemilik, telp, email, website, deskripsi, alamat, tipe, file, file_small, hari_ini, jam_buka, situs_sumber,tipe_sumber,latitude,longitude;
    Integer nomor;

    public ListKuliner(String id, String nama, String pemilik, String telp, String email, String website, String deskripsi, String alamat, String tipe, String file, String file_small, String hari_ini, String jam_buka, String situs_sumber, String tipe_sumber, String latitude, String longitude, Integer nomor) {
        this.id = id;
        this.nama = nama;
        this.pemilik = pemilik;
        this.telp = telp;
        this.email = email;
        this.website = website;
        this.deskripsi = deskripsi;
        this.alamat = alamat;
        this.tipe = tipe;
        this.file = file;
        this.file_small = file_small;
        this.hari_ini = hari_ini;
        this.jam_buka = jam_buka;
        this.situs_sumber = situs_sumber;
        this.tipe_sumber = tipe_sumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nomor = nomor;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getPemilik() {
        return pemilik;
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

    public String getAlamat() {
        return alamat;
    }

    public String getTipe() {
        return tipe;
    }

    public String getFile() {
        return file;
    }

    public String getFile_small() {
        return file_small;
    }

    public String getHari_ini() {
        return hari_ini;
    }

    public String getJam_buka() {
        return jam_buka;
    }

    public Integer getNomor() {
        return nomor;
    }

    public String getSitus_sumber() {
        return situs_sumber;
    }

    public String getTipe_sumber() {
        return tipe_sumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
