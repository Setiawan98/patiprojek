package projekpati.com.projekpati.Model.Agenda;

import projekpati.com.projekpati.Model.Agenda.DetilAgendaBaru;

public class DetilAgendaModel {
    String status, judul, key, waktu, icon;
    DetilAgendaBaru data;

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

    public DetilAgendaBaru getData() {
        return data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setData(DetilAgendaBaru data) {
        this.data = data;
    }
}
