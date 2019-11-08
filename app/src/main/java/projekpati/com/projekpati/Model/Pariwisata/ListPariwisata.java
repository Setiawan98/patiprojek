package projekpati.com.projekpati.Model.Pariwisata;

import android.os.Parcel;
import android.os.Parcelable;

public class ListPariwisata implements Parcelable {
    String id, nama, telp, email, website, alamat, tipe, rating_jumlah, rating, file, file_small, latitude, longitude;
    Integer nomor;
    String ref_pariwisata_icon, ref_pariwisata_nama, ref_pariwisata_warna, hari_ini, jam_buka;

    public ListPariwisata(String id, String nama, String telp, String email, String website, String alamat, String tipe, String rating_jumlah, String rating, String file, String file_small, String latitude, String longitude, Integer nomor, String ref_pariwisata_icon, String ref_pariwisata_nama, String ref_pariwisata_warna, String hari_ini, String jam_buka) {
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
        this.ref_pariwisata_icon = ref_pariwisata_icon;
        this.ref_pariwisata_nama = ref_pariwisata_nama;
        this.ref_pariwisata_warna = ref_pariwisata_warna;
        this.hari_ini = hari_ini;
        this.jam_buka = jam_buka;
    }

    protected ListPariwisata(Parcel in) {
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
        ref_pariwisata_icon = in.readString();
        ref_pariwisata_nama = in.readString();
        ref_pariwisata_warna = in.readString();
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
        dest.writeString(ref_pariwisata_icon);
        dest.writeString(ref_pariwisata_nama);
        dest.writeString(ref_pariwisata_warna);
        dest.writeString(hari_ini);
        dest.writeString(jam_buka);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListPariwisata> CREATOR = new Creator<ListPariwisata>() {
        @Override
        public ListPariwisata createFromParcel(Parcel in) {
            return new ListPariwisata(in);
        }

        @Override
        public ListPariwisata[] newArray(int size) {
            return new ListPariwisata[size];
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

    public String getRef_pariwisata_icon() {
        return ref_pariwisata_icon;
    }

    public String getRef_pariwisata_nama() {
        return ref_pariwisata_nama;
    }

    public String getRef_pariwisata_warna() {
        return ref_pariwisata_warna;
    }

    public String getHari_ini() {
        return hari_ini;
    }

    public String getJam_buka() {
        return jam_buka;
    }
}