package projekpati.com.projekpati.Model.Kerjaan;

import android.os.Parcel;
import android.os.Parcelable;

public class ListKerjaan implements Parcelable {
    String id, nama, telp, email, website, alamat, tipe, rating_jumlah, rating, file, file_small, latitude, longitude, deskripsi, kantor, gaji, pengalaman;
    Integer nomor;
    String ref_kerjaan_icon, ref_kerjaan_nama, ref_kerjaan_warna;

    public ListKerjaan(String id, String nama, String telp, String email, String website, String alamat, String tipe, String rating_jumlah, String rating, String file, String file_small, String latitude, String longitude, String deskripsi, String kantor, String gaji, String pengalaman, Integer nomor, String ref_kerjaan_icon, String ref_kerjaan_nama, String ref_kerjaan_warna) {
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
        this.kantor = kantor;
        this.gaji = gaji;
        this.pengalaman = pengalaman;
        this.nomor = nomor;
        this.ref_kerjaan_icon = ref_kerjaan_icon;
        this.ref_kerjaan_nama = ref_kerjaan_nama;
        this.ref_kerjaan_warna = ref_kerjaan_warna;
    }


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

    public String getKantor() {
        return kantor;
    }

    public String getGaji() {
        return gaji;
    }

    public String getPengalaman() {
        return pengalaman;
    }

    public Integer getNomor() {
        return nomor;
    }

    public String getRef_kerjaan_icon() {
        return ref_kerjaan_icon;
    }

    public String getRef_kerjaan_nama() {
        return ref_kerjaan_nama;
    }

    public String getRef_kerjaan_warna() {
        return ref_kerjaan_warna;
    }

    public static Creator<ListKerjaan> getCREATOR() {
        return CREATOR;
    }

    protected ListKerjaan(Parcel in) {
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
        kantor = in.readString();
        gaji = in.readString();
        pengalaman = in.readString();
        if (in.readByte() == 0) {
            nomor = null;
        } else {
            nomor = in.readInt();
        }
        ref_kerjaan_icon = in.readString();
        ref_kerjaan_nama = in.readString();
        ref_kerjaan_warna = in.readString();
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
        dest.writeString(kantor);
        dest.writeString(gaji);
        dest.writeString(pengalaman);
        if (nomor == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(nomor);
        }
        dest.writeString(ref_kerjaan_icon);
        dest.writeString(ref_kerjaan_nama);
        dest.writeString(ref_kerjaan_warna);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListKerjaan> CREATOR = new Creator<ListKerjaan>() {
        @Override
        public ListKerjaan createFromParcel(Parcel in) {
            return new ListKerjaan(in);
        }

        @Override
        public ListKerjaan[] newArray(int size) {
            return new ListKerjaan[size];
        }
    };
}
