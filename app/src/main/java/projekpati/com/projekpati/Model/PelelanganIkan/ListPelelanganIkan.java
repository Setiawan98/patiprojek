package projekpati.com.projekpati.Model.PelelanganIkan;

import android.os.Parcel;
import android.os.Parcelable;

public class ListPelelanganIkan implements Parcelable {
    String id, nama, pemilik, telp, email, website, alamat, tipe, rating_jumlah, rating, file, file_small, latitude, longitude, deskripsi;
    Integer nomor;
    String ref_pelelangan_ikan_icon, ref_pelelangan_ikan_nama, ref_pelelangan_ikan_warna;

    public ListPelelanganIkan(String id, String nama, String pemilik, String telp, String email, String website, String alamat, String tipe, String rating_jumlah, String rating, String file, String file_small, String latitude, String longitude, String deskripsi, Integer nomor, String ref_pelelangan_ikan_icon, String ref_pelelangan_ikan_nama, String ref_pelelangan_ikan_warna) {
        this.id = id;
        this.nama = nama;
        this.pemilik = pemilik;
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
        this.ref_pelelangan_ikan_icon = ref_pelelangan_ikan_icon;
        this.ref_pelelangan_ikan_nama = ref_pelelangan_ikan_nama;
        this.ref_pelelangan_ikan_warna = ref_pelelangan_ikan_warna;
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

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
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

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
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

    public String getRef_pelelangan_ikan_icon() {
        return ref_pelelangan_ikan_icon;
    }

    public void setRef_pelelangan_ikan_icon(String ref_pelelangan_ikan_icon) {
        this.ref_pelelangan_ikan_icon = ref_pelelangan_ikan_icon;
    }

    public String getRef_pelelangan_ikan_nama() {
        return ref_pelelangan_ikan_nama;
    }

    public void setRef_pelelangan_ikan_nama(String ref_pelelangan_ikan_nama) {
        this.ref_pelelangan_ikan_nama = ref_pelelangan_ikan_nama;
    }

    public String getRef_pelelangan_ikan_warna() {
        return ref_pelelangan_ikan_warna;
    }

    public void setRef_pelelangan_ikan_warna(String ref_pelelangan_ikan_warna) {
        this.ref_pelelangan_ikan_warna = ref_pelelangan_ikan_warna;
    }

    protected ListPelelanganIkan(Parcel in) {
        id = in.readString();
        pemilik = in.readString();
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
        ref_pelelangan_ikan_icon = in.readString();
        ref_pelelangan_ikan_nama = in.readString();
        ref_pelelangan_ikan_warna = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
        dest.writeString(pemilik);
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
        dest.writeString(ref_pelelangan_ikan_icon);
        dest.writeString(ref_pelelangan_ikan_nama);
        dest.writeString(ref_pelelangan_ikan_warna);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListPelelanganIkan> CREATOR = new Creator<ListPelelanganIkan>() {
        @Override
        public ListPelelanganIkan createFromParcel(Parcel in) {
            return new ListPelelanganIkan(in);
        }

        @Override
        public ListPelelanganIkan[] newArray(int size) {
            return new ListPelelanganIkan[size];
        }
    };
}
