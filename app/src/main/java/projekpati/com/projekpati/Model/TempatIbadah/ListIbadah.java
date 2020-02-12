package projekpati.com.projekpati.Model.TempatIbadah;

import android.os.Parcel;
import android.os.Parcelable;

public class ListIbadah implements Parcelable {
    String id, nama, telp, email, website, alamat, rating_jumlah, rating, file, file_small, latitude, longitude, deskripsi;
    Integer nomor;
    String ref_ibadah_icon, ref_ibadah_nama, ref_ibadah_warna;

    public ListIbadah(String id, String nama, String telp, String email, String website, String alamat, String rating_jumlah, String rating, String file, String file_small, String latitude, String longitude, String deskripsi, Integer nomor, String ref_ibadah_icon, String ref_ibadah_nama, String ref_ibadah_warna) {
        this.id = id;
        this.nama = nama;
        this.telp = telp;
        this.email = email;
        this.website = website;
        this.alamat = alamat;
        this.rating_jumlah = rating_jumlah;
        this.rating = rating;
        this.file = file;
        this.file_small = file_small;
        this.latitude = latitude;
        this.longitude = longitude;
        this.deskripsi = deskripsi;
        this.nomor = nomor;
        this.ref_ibadah_icon = ref_ibadah_icon;
        this.ref_ibadah_nama = ref_ibadah_nama;
        this.ref_ibadah_warna = ref_ibadah_warna;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getRating_jumlah() {
        return rating_jumlah;
    }

    public void setRating_jumlah(String rating_jumlah) {
        this.rating_jumlah = rating_jumlah;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFile_small() {
        return file_small;
    }

    public void setFile_small(String file_small) {
        this.file_small = file_small;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getNomor() {
        return nomor;
    }

    public void setNomor(Integer nomor) {
        this.nomor = nomor;
    }

    public String getRef_ibadah_icon() {
        return ref_ibadah_icon;
    }

    public void setRef_ibadah_icon(String ref_ibadah_icon) {
        this.ref_ibadah_icon = ref_ibadah_icon;
    }

    public String getRef_ibadah_nama() {
        return ref_ibadah_nama;
    }

    public void setRef_ibadah_nama(String ref_ibadah_nama) {
        this.ref_ibadah_nama = ref_ibadah_nama;
    }

    public String getRef_ibadah_warna() {
        return ref_ibadah_warna;
    }

    public void setRef_ibadah_warna(String ref_ibadah_warna) {
        this.ref_ibadah_warna = ref_ibadah_warna;
    }

    protected ListIbadah(Parcel in) {
        id = in.readString();
        nama = in.readString();
        telp = in.readString();
        email = in.readString();
        website = in.readString();
        alamat = in.readString();
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
        ref_ibadah_icon = in.readString();
        ref_ibadah_nama = in.readString();
        ref_ibadah_warna = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
        dest.writeString(telp);
        dest.writeString(email);
        dest.writeString(website);
        dest.writeString(alamat);
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
        dest.writeString(ref_ibadah_icon);
        dest.writeString(ref_ibadah_nama);
        dest.writeString(ref_ibadah_warna);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListIbadah> CREATOR = new Creator<ListIbadah>() {
        @Override
        public ListIbadah createFromParcel(Parcel in) {
            return new ListIbadah(in);
        }

        @Override
        public ListIbadah[] newArray(int size) {
            return new ListIbadah[size];
        }
    };
}
