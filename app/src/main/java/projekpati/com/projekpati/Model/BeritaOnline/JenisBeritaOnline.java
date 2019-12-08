package projekpati.com.projekpati.Model.BeritaOnline;

public class JenisBeritaOnline {
    String id, nama, icon, warna;
    Integer nomor;

    public JenisBeritaOnline(String id, String nama, String icon, String warna, Integer nomor) {
        this.id = id;
        this.nama = nama;
        this.icon = icon;
        this.warna = warna;
        this.nomor = nomor;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getIcon() {
        return icon;
    }

    public String getWarna() {
        return warna;
    }

    public Integer getNomor() {
        return nomor;
    }
}
