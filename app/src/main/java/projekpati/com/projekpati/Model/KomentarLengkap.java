package projekpati.com.projekpati.Model;

import java.util.List;
import java.util.Map;

public class KomentarLengkap {
    String status, judul, key, waktu, icon;
    List<KomentarParent> komentar_parent;
    Map<String,List<KomentarParent>> komentar_child;

    public KomentarLengkap(String status, String judul, String key, String waktu, String icon, List<KomentarParent> komentar_parent, Map<String, List<KomentarParent>> komentar_child) {
        this.status = status;
        this.judul = judul;
        this.key = key;
        this.waktu = waktu;
        this.icon = icon;
        this.komentar_parent = komentar_parent;
        this.komentar_child = komentar_child;
    }

    public Map<String, List<KomentarParent>> getKomentar_child() {
        return komentar_child;
    }

    public void setKomentar_child(Map<String, List<KomentarParent>> komentar_child) {
        this.komentar_child = komentar_child;
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

    public List<KomentarParent> getKomentar_parent() {
        return komentar_parent;
    }

    public void setKomentar_parent(List<KomentarParent> komentar_parent) {
        this.komentar_parent = komentar_parent;
    }
}
