package projekpati.com.projekpati.Model.KodePos;

public class DetilKodePosModel {
    String status, judul, key, waktu, icon;
    DetilKodePosBaru data;

    public DetilKodePosModel(String status, String judul, String key, String waktu, String icon, DetilKodePosBaru data) {
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

    public DetilKodePosBaru getData() {
        return data;
    }

    public void setData(DetilKodePosBaru data) {
        this.data = data;
    }
}
