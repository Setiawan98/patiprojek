package projekpati.com.projekpati.Model.Agenda;

public class JenisAgenda {

    String id, nama, icon, warna;
    Integer nomor;

    public JenisAgenda(String id, String nama, String icon, String warna, Integer nomor) {
        this.id = id;
        this.nama = nama;
        this.icon = icon;
        this.warna = warna;
        this.nomor = nomor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public void setNomor(Integer nomor) {
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
