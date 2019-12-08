package projekpati.com.projekpati.Model.galeri_android;

public class listGaleri {
    String id, nama, gambar, gambar_small;

    public listGaleri(String id, String nama, String gambar, String gambar_small) {
        this.id = id;
        this.nama = nama;
        this.gambar = gambar;
        this.gambar_small = gambar_small;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public void setGambar_small(String gambar_small) {
        this.gambar_small = gambar_small;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getGambar() {
        return gambar;
    }

    public String getGambar_small() {
        return gambar_small;
    }
}
