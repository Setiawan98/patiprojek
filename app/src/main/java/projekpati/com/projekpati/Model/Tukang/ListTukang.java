package projekpati.com.projekpati.Model.Tukang;

import android.os.Parcel;
import android.os.Parcelable;

public class ListTukang implements Parcelable {
    String id, nama, telp, email, website, alamat, tipe, rating_jumlah, rating, file, file_small, latitude, longitude;
    Integer nomor;
    String ref_tukang_icon, ref_tukang_nama, ref_tukang_warna, hari_ini, jam_buka;


    public ListTukang(String id, String nama, String telp, String email, String website, String alamat, String tipe, String rating_jumlah, String rating, String file, String file_small, String latitude, String longitude, Integer nomor, String ref_tukang_icon, String ref_tukang_nama, String ref_tukang_warna, String hari_ini, String jam_buka) {
        this.id = id;
        this.nama = nama;
        this.telp = telp;
        this.email = email;
        this.website = website;
        this.alamat = alamat;
        this.tipe = tipe;
        this.rating_jumlah = rating_jumlah;
        this.rating = rating;
        this.file = file;
        this.file_small = file_small;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nomor = nomor;
        this.ref_tukang_icon = ref_tukang_icon;
        this.ref_tukang_nama = ref_tukang_nama;
        this.ref_tukang_warna = ref_tukang_warna;
        this.hari_ini = hari_ini;
        this.jam_buka = jam_buka;
    }

    protected ListTukang(Parcel in) {
        id = in.readString();
        nama = in.readString();
        telp = in.readString();
        email = in.readString();
        website = in.readString();
        alamat = in.readString();
        tipe = in.readString();
        rating_jumlah = in.readString();
        rating = in.readString();
        file = in.readString();
        file_small = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        if (in.readByte() == 0) {
            nomor = null;
        } else {
            nomor = in.readInt();
        }
        ref_tukang_icon = in.readString();
        ref_tukang_nama = in.readString();
        ref_tukang_warna = in.readString();
        hari_ini = in.readString();
        jam_buka = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
        dest.writeString(telp);
        dest.writeString(email);
        dest.writeString(website);
        dest.writeString(alamat);
        dest.writeString(tipe);
        dest.writeString(rating_jumlah);
        dest.writeString(rating);
        dest.writeString(file);
        dest.writeString(file_small);
        dest.writeString(latitude);
        dest.writeString(longitude);
        if (nomor == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(nomor);
        }
        dest.writeString(ref_tukang_icon);
        dest.writeString(ref_tukang_nama);
        dest.writeString(ref_tukang_warna);
        dest.writeString(hari_ini);
        dest.writeString(jam_buka);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListTukang> CREATOR = new Creator<ListTukang>() {
        @Override
        public ListTukang createFromParcel(Parcel in) {
            return new ListTukang(in);
        }

        @Override
        public ListTukang[] newArray(int size) {
            return new ListTukang[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getTelp() {
        return telp;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTipe() {
        return tipe;
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

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Integer getNomor() {
        return nomor;
    }

    public String getRef_tukang_icon() {
        return ref_tukang_icon;
    }

    public String getRef_tukang_nama() {
        return ref_tukang_nama;
    }

    public String getRef_tukang_warna() {
        return ref_tukang_warna;
    }

    public String getHari_ini() {
        return hari_ini;
    }

    public String getJam_buka() {
        return jam_buka;
    }
}