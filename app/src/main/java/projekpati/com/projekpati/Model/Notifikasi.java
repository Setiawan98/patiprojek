package projekpati.com.projekpati.Model;

public class Notifikasi {
    String status, key, waktu, jenis, judul, pesan;

    public Notifikasi(String status, String key, String waktu, String jenis, String judul, String pesan) {
        this.status = status;
        this.key = key;
        this.waktu = waktu;
        this.jenis = jenis;
        this.judul = judul;
        this.pesan = pesan;
    }

    public String getStatus() {
        return status;
    }

    public String getKey() {
        return key;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getJenis() {
        return jenis;
    }

    public String getJudul() {
        return judul;
    }

    public String getPesan() {
        return pesan;
    }
}
