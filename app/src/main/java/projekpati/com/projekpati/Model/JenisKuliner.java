package projekpati.com.projekpati.Model;

public class JenisKuliner {
    String id, nama;
    Integer nomor;

    public JenisKuliner(String id, String nama, Integer nomor) {
        this.id = id;
        this.nama = nama;
        this.nomor = nomor;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public Integer getNomor() {
        return nomor;
    }
}
