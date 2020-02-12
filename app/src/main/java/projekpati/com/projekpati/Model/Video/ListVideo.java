package projekpati.com.projekpati.Model.Video;

import android.os.Parcel;
import android.os.Parcelable;

import projekpati.com.projekpati.Model.Video.ListVideo;

public class ListVideo implements Parcelable {
    String id, nama, deskripsi, kode,time;
    Integer nomor;
    String ref_video_icon, ref_video_nama, ref_video_warna;

    public ListVideo(String id, String nama, String deskripsi, String kode, String time, Integer nomor, String ref_video_icon, String ref_video_nama, String ref_video_warna) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.kode = kode;
        this.time = time;
        this.nomor = nomor;
        this.ref_video_icon = ref_video_icon;
        this.ref_video_nama = ref_video_nama;
        this.ref_video_warna = ref_video_warna;
    }

    protected ListVideo(Parcel in) {
        id = in.readString();
        nama = in.readString();
        deskripsi = in.readString();
        kode = in.readString();
        time = in.readString();
        if (in.readByte() == 0) {
            nomor = null;
        } else {
            nomor = in.readInt();
        }
        ref_video_icon = in.readString();
        ref_video_nama = in.readString();
        ref_video_warna = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
        dest.writeString(deskripsi);
        dest.writeString(kode);
        dest.writeString(time);
        if (nomor == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(nomor);
        }
        dest.writeString(ref_video_icon);
        dest.writeString(ref_video_nama);
        dest.writeString(ref_video_warna);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListVideo> CREATOR = new Creator<ListVideo>() {
        @Override
        public ListVideo createFromParcel(Parcel in) {
            return new ListVideo(in);
        }

        @Override
        public ListVideo[] newArray(int size) {
            return new ListVideo[size];
        }
    };

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setNomor(Integer nomor) {
        this.nomor = nomor;
    }

    public void setRef_video_icon(String ref_video_icon) {
        this.ref_video_icon = ref_video_icon;
    }

    public void setRef_video_nama(String ref_video_nama) {
        this.ref_video_nama = ref_video_nama;
    }

    public void setRef_video_warna(String ref_video_warna) {
        this.ref_video_warna = ref_video_warna;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getKode() {
        return kode;
    }

    public String getTime() {
        return time;
    }

    public Integer getNomor() {
        return nomor;
    }

    public String getRef_video_icon() {
        return ref_video_icon;
    }

    public String getRef_video_nama() {
        return ref_video_nama;
    }

    public String getRef_video_warna() {
        return ref_video_warna;
    }
}

