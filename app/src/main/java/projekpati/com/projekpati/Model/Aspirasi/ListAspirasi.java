package projekpati.com.projekpati.Model.Aspirasi;

import android.os.Parcel;
import android.os.Parcelable;

public class ListAspirasi implements Parcelable {
    String id, nama, deskripsi, rating_jumlah, rating, file, file_small, latitude, longitude;
    Integer nomor;
    String ref_aspirasi_icon,  ref_aspirasi_nama, ref_aspirasi_warna;

    public ListAspirasi(String id, String nama, String deskripsi, String rating_jumlah, String rating, String file, String file_small, String latitude, String longitude, Integer nomor, String ref_aspirasi_icon, String ref_aspirasi_nama, String ref_aspirasi_warna) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.rating_jumlah = rating_jumlah;
        this.rating = rating;
        this.file = file;
        this.file_small = file_small;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nomor = nomor;
        this.ref_aspirasi_icon = ref_aspirasi_icon;
        this.ref_aspirasi_nama = ref_aspirasi_nama;
        this.ref_aspirasi_warna = ref_aspirasi_warna;
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

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
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

    public Integer getNomor() {
        return nomor;
    }

    public void setNomor(Integer nomor) {
        this.nomor = nomor;
    }

    public String getRef_aspirasi_icon() {
        return ref_aspirasi_icon;
    }

    public void setRef_aspirasi_icon(String ref_aspirasi_icon) {
        this.ref_aspirasi_icon = ref_aspirasi_icon;
    }

    public String getRef_aspirasi_nama() {
        return ref_aspirasi_nama;
    }

    public void setRef_aspirasi_nama(String ref_aspirasi_nama) {
        this.ref_aspirasi_nama = ref_aspirasi_nama;
    }

    public String getRef_aspirasi_warna() {
        return ref_aspirasi_warna;
    }

    public void setRef_aspirasi_warna(String ref_aspirasi_warna) {
        this.ref_aspirasi_warna = ref_aspirasi_warna;
    }

    protected ListAspirasi(Parcel in) {
        id = in.readString();
        nama = in.readString();
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
        ref_aspirasi_icon = in.readString();
        ref_aspirasi_nama = in.readString();
        ref_aspirasi_warna = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
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
        dest.writeString(ref_aspirasi_icon);
        dest.writeString(ref_aspirasi_nama);
        dest.writeString(ref_aspirasi_warna);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListAspirasi> CREATOR = new Creator<ListAspirasi>() {
        @Override
        public ListAspirasi createFromParcel(Parcel in) {
            return new ListAspirasi(in);
        }

        @Override
        public ListAspirasi[] newArray(int size) {
            return new ListAspirasi[size];
        }
    };
}
