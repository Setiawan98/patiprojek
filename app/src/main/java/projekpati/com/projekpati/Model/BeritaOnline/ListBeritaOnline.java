package projekpati.com.projekpati.Model.BeritaOnline;

import android.os.Parcel;
import android.os.Parcelable;

public class ListBeritaOnline implements Parcelable {
    String id, judul, time,  rating_jumlah, rating, file, file_small, publish, deskripsi;
    Integer nomor;
    String ref_beritaOnline_icon, ref_beritaOnline_nama, ref_beritaOnline_warna;


    public ListBeritaOnline(String id, String judul, String time, String rating_jumlah, String rating, String file, String file_small, String publish, String deskripsi, Integer nomor, String ref_beritaOnline_icon, String ref_beritaOnline_nama, String ref_beritaOnline_warna) {
        this.id = id;
        this.judul = judul;
        this.time = time;
        this.rating_jumlah = rating_jumlah;
        this.rating = rating;
        this.file = file;
        this.file_small = file_small;
        this.publish = publish;
        this.deskripsi = deskripsi;
        this.nomor = nomor;
        this.ref_beritaOnline_icon = ref_beritaOnline_icon;
        this.ref_beritaOnline_nama = ref_beritaOnline_nama;
        this.ref_beritaOnline_warna = ref_beritaOnline_warna;
    }

    public String getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getTime() {
        return time;
    }

    public String getRating_jumlah() {
        return rating_jumlah;
    }

    public String getRating() {
        return rating;
    }

    public String getFile() {
        return file;
    }

    public String getFile_small() {
        return file_small;
    }

    public String getPublish() {
        return publish;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public Integer getNomor() {
        return nomor;
    }

    public String getRef_beritaOnline_icon() {
        return ref_beritaOnline_icon;
    }

    public String getRef_beritaOnline_nama() {
        return ref_beritaOnline_nama;
    }

    public String getRef_beritaOnline_warna() {
        return ref_beritaOnline_warna;
    }

    public static Creator<ListBeritaOnline> getCREATOR() {
        return CREATOR;
    }

    protected ListBeritaOnline(Parcel in) {
        id = in.readString();
        judul = in.readString();
        time = in.readString();
        rating_jumlah = in.readString();
        rating = in.readString();
        file = in.readString();
        file_small = in.readString();
        publish = in.readString();
        deskripsi = in.readString();
        if (in.readByte() == 0) {
            nomor = null;
        } else {
            nomor = in.readInt();
        }
        ref_beritaOnline_icon = in.readString();
        ref_beritaOnline_nama = in.readString();
        ref_beritaOnline_warna = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(judul);
        dest.writeString(time);
        dest.writeString(rating_jumlah);
        dest.writeString(rating);
        dest.writeString(file);
        dest.writeString(file_small);
        dest.writeString(publish);
        dest.writeString(deskripsi);
        if (nomor == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(nomor);
        }
        dest.writeString(ref_beritaOnline_icon);
        dest.writeString(ref_beritaOnline_nama);
        dest.writeString(ref_beritaOnline_warna);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListBeritaOnline> CREATOR = new Creator<ListBeritaOnline>() {
        @Override
        public ListBeritaOnline createFromParcel(Parcel in) {
            return new ListBeritaOnline(in);
        }

        @Override
        public ListBeritaOnline[] newArray(int size) {
            return new ListBeritaOnline[size];
        }
    };


}
