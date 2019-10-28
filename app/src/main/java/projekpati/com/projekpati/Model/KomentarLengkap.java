package projekpati.com.projekpati.Model;

import java.util.Map;

public class KomentarLengkap {
    String status, judul, key, waktu, icon;
    Map<String,KomentarParent> komentar_parent;

    public KomentarLengkap(String status, String judul, String key, String waktu, String icon, Map<String, KomentarParent> komentar_parent) {
        this.status = status;
        this.judul = judul;
        this.key = key;
        this.waktu = waktu;
        this.icon = icon;
        this.komentar_parent = komentar_parent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Map<String, KomentarParent> getKomentar_parent() {
        return komentar_parent;
    }

    public void setKomentar_parent(Map<String, KomentarParent> komentar_parent) {
        this.komentar_parent = komentar_parent;
    }
}
