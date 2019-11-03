package projekpati.com.projekpati.Model;

public class MenuDaftar {
    String nama, harga, deskripsi;

    public MenuDaftar(String nama, String harga, String deskripsi) {
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
