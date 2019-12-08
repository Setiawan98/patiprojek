package projekpati.com.projekpati.Model.BeritaCetak;

public class DetilBeritaCetakModel {
    String status, judul, key, waktu, icon;
    DetilBeritaCetakBaru data;

    public DetilBeritaCetakModel(String status, String judul, String key, String waktu, String icon, DetilBeritaCetakBaru data) {
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

    public DetilBeritaCetakBaru getData() {
        return data;
    }

    public void setData(DetilBeritaCetakBaru data) {
        this.data = data;
    }
}
