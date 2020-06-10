package projekpati.com.projekpati.Model.Notif;

public class ListNotif {
    String id, judul, isi, time, time2;
    Integer nomor;

    public ListNotif(String id, String judul, String isi, String time, String time2, Integer nomor) {
        this.id = id;
        this.judul = judul;
        this.isi = isi;
        this.time = time;
        this.time2 = time2;
        this.nomor = nomor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public Integer getNomor() {
        return nomor;
    }

    public void setNomor(Integer nomor) {
        this.nomor = nomor;
    }
}
