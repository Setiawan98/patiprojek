package projekpati.com.projekpati.Model;

import java.util.Map;

import projekpati.com.projekpati.Model.Video.jenisVideo;

public class userDataModel {
    String status, pesan,key, judul, waktu;
    userData data;

    public userDataModel(String status, String pesan, String key, String judul, String waktu, userData data) {
        this.status = status;
        this.pesan = pesan;
        this.key = key;
        this.judul = judul;
        this.waktu = waktu;
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public void setData(userData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getPesan() {
        return pesan;
    }

    public String getKey() {
        return key;
    }

    public String getJudul() {
        return judul;
    }

    public String getWaktu() {
        return waktu;
    }

    public userData getData() {
        return data;
    }
}
