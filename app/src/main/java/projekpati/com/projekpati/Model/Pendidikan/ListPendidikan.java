package projekpati.com.projekpati.Model.Pendidikan;

import android.os.Parcel;
import android.os.Parcelable;

public class ListPendidikan implements Parcelable {
    String id, nama, telp, email, website, alamat, tipe, rating_jumlah, rating, file, file_small, latitude,longitude;
    Integer nomor;
    String ref_pendidikan_icon;

    public ListPendidikan(String id, String nama, String telp, String email, String website, String alamat, String tipe, String rating_jumlah, String rating, String file, String file_small, String latitude, String longitude, Integer nomor, String ref_pendidikan_icon) {
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
        this.ref_pendidikan_icon = ref_pendidikan_icon;
    }

    protected ListPendidikan(Parcel in) {
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
        ref_pendidikan_icon = in.readString();
    }

    public static final Creator<ListPendidikan> CREATOR = new Creator<ListPendidikan>() {
        @Override
        public ListPendidikan createFromParcel(Parcel in) {
            return new ListPendidikan(in);
        }

        @Override
        public ListPendidikan[] newArray(int size) {
            return new ListPendidikan[size];
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

    public String getRef_pendidikan_icon() {
        return ref_pendidikan_icon;
    }

    @Override
    public int describeContents() {
        return 0;
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
        dest.writeString(ref_pendidikan_icon);
    }
}
