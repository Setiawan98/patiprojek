package projekpati.com.projekpati.Model.FasilitasUmum;

import android.os.Parcel;
import android.os.Parcelable;

public class ListFasilitasUmum implements Parcelable {
    String id, nama, telp, email, website, alamat, tipe, rating_jumlah, rating, file, file_small, latitude, longitude, deskripsi;
    Integer nomor;
    String ref_lain_lain_icon, ref_lain_lain_nama, ref_lain_lain_warna;

    public ListFasilitasUmum(String id, String nama, String telp, String email, String website, String alamat, String tipe, String rating_jumlah, String rating, String file, String file_small, String latitude, String longitude, String deskripsi, Integer nomor, String ref_lain_lain_icon, String ref_lain_lain_nama, String ref_lain_lain_warna) {
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
        this.deskripsi = deskripsi;
        this.nomor = nomor;
        this.ref_lain_lain_icon = ref_lain_lain_icon;
        this.ref_lain_lain_nama = ref_lain_lain_nama;
        this.ref_lain_lain_warna = ref_lain_lain_warna;
    }


    protected ListFasilitasUmum(Parcel in) {
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
        deskripsi = in.readString();
        if (in.readByte() == 0) {
            nomor = null;
        } else {
            nomor = in.readInt();
        }
        ref_lain_lain_icon = in.readString();
        ref_lain_lain_nama = in.readString();
        ref_lain_lain_warna = in.readString();
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
        dest.writeString(deskripsi);
        if (nomor == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(nomor);
        }
        dest.writeString(ref_lain_lain_icon);
        dest.writeString(ref_lain_lain_nama);
        dest.writeString(ref_lain_lain_warna);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListFasilitasUmum> CREATOR = new Creator<ListFasilitasUmum>() {
        @Override
        public ListFasilitasUmum createFromParcel(Parcel in) {
            return new ListFasilitasUmum(in);
        }

        @Override
        public ListFasilitasUmum[] newArray(int size) {
            return new ListFasilitasUmum[size];
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

    public String getDeskripsi() {
        return deskripsi;
    }

    public Integer getNomor() {
        return nomor;
    }

    public String getRef_lain_lain_icon() {
        return ref_lain_lain_icon;
    }

    public String getRef_lain_lain_nama() {
        return ref_lain_lain_nama;
    }

    public String getRef_lain_lain_warna() {
        return ref_lain_lain_warna;
    }
}