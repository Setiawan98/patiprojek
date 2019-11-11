package projekpati.com.projekpati.Model.Spbu;

public class DetilSpbuModel {
    String status, judul, key, waktu, icon;
    DetilSpbuBaru data;

    public DetilSpbuModel(String status, String judul, String key, String waktu, String icon, DetilSpbuBaru data) {
        this.status = status;
        this.judul = judul;
        this.key = key;
        this.waktu = waktu;
        this.icon = icon;
        this.data = data;
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

    public DetilSpbuBaru getData() {
        return data;
    }

    public void setData(DetilSpbuBaru data) {
        this.data = data;
    }
}
