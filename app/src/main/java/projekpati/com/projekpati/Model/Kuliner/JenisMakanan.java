package projekpati.com.projekpati.Model.Kuliner;

public class JenisMakanan {
    String id, nama, icon;
    Integer nomor;

    public JenisMakanan(String id, String nama, String icon, Integer nomor) {
        this.id = id;
        this.nama = nama;
        this.icon = icon;
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

    public String getIcon() {
        return icon;
    }
}
