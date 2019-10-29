package projekpati.com.projekpati.Model;

public class postKomentar {
    String status, judul, key, waktu, icon, dataid;
    KomentarParent komentar;

    public postKomentar(String status, String judul, String key, String waktu, String icon, String dataid, KomentarParent komentar) {
        this.status = status;
        this.judul = judul;
        this.key = key;
        this.waktu = waktu;
        this.icon = icon;
        this.dataid = dataid;
        this.komentar = komentar;
    }

    public String getStatus() {
        return status;
    }

    public String getJudul() {
        return judul;
    }

    public String getKey() {
        return key;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getIcon() {
        return icon;
    }

    public String getDataid() {
        return dataid;
    }

    public KomentarParent getKomentar() {
        return komentar;
    }
}
