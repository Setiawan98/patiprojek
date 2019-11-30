package projekpati.com.projekpati.Model.Otomotif;

public class JenisOtomotif {
    String id, nama, icon, warna;
    Integer nomor;

    public JenisOtomotif(String id, String nama, String icon, String warna, Integer nomor) {
        this.id = id;
        this.nama = nama;
        this.icon = icon;
        this.warna = warna;
        this.nomor = nomor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public Integer getNomor() {
        return nomor;
    }

    public void setNomor(Integer nomor) {
        this.nomor = nomor;
    }
}
