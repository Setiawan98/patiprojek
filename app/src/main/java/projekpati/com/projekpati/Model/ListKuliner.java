package projekpati.com.projekpati.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;

public class ListKuliner implements Parcelable {
    String id, nama, pemilik, telp, email, website, deskripsi, alamat, tipe, file, file_small, hari_ini, jam_buka, situs_sumber,tipe_sumber,latitude,longitude;
    Integer nomor;
    String ref_kuliner_nama,ref_kuliner_icon;


    public ListKuliner(String id, String nama, String pemilik, String telp, String email, String website, String deskripsi, String alamat, String tipe, String file, String file_small, String hari_ini, String jam_buka, String situs_sumber, String tipe_sumber, String latitude, String longitude, Integer nomor, String ref_kuliner_nama, String ref_kuliner_icon) {
        this.id = id;
        this.nama = nama;
        this.pemilik = pemilik;
        this.telp = telp;
        this.email = email;
        this.website = website;
        this.deskripsi = deskripsi;
        this.alamat = alamat;
        this.tipe = tipe;
        this.file = file;
        this.file_small = file_small;
        this.hari_ini = hari_ini;
        this.jam_buka = jam_buka;
        this.situs_sumber = situs_sumber;
        this.tipe_sumber = tipe_sumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nomor = nomor;
        this.ref_kuliner_nama = ref_kuliner_nama;
        this.ref_kuliner_icon = ref_kuliner_icon;
    }

    protected ListKuliner(Parcel in) {
        id = in.readString();
        nama = in.readString();
        pemilik = in.readString();
        telp = in.readString();
        email = in.readString();
        website = in.readString();
        deskripsi = in.readString();
        alamat = in.readString();
        tipe = in.readString();
        file = in.readString();
        file_small = in.readString();
        hari_ini = in.readString();
        jam_buka = in.readString();
        situs_sumber = in.readString();
        tipe_sumber = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        ref_kuliner_nama = in.readString();
        ref_kuliner_icon = in.readString();
        if (in.readByte() == 0) {
            nomor = null;
        } else {
            nomor = in.readInt();
        }
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
        dest.writeString(pemilik);
        dest.writeString(telp);
        dest.writeString(email);
        dest.writeString(website);
        dest.writeString(deskripsi);
        dest.writeString(alamat);
        dest.writeString(tipe);
        dest.writeString(file);
        dest.writeString(file_small);
        dest.writeString(hari_ini);
        dest.writeString(jam_buka);
        dest.writeString(situs_sumber);
        dest.writeString(tipe_sumber);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(ref_kuliner_nama);
        dest.writeString(ref_kuliner_icon);
        if (nomor == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(nomor);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListKuliner> CREATOR = new Creator<ListKuliner>() {
        @Override
        public ListKuliner createFromParcel(Parcel in) {
            return new ListKuliner(in);
        }

        @Override
        public ListKuliner[] newArray(int size) {
            return new ListKuliner[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getPemilik() {
        return pemilik;
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

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTipe() {
        return tipe;
    }

    public String getFile() {
        return file;
    }

    public String getFile_small() {
        return file_small;
    }

    public String getHari_ini() {
        return hari_ini;
    }

    public String getJam_buka() {
        return jam_buka;
    }

    public Integer getNomor() {
        return nomor;
    }

    public String getSitus_sumber() {
        return situs_sumber;
    }

    public String getTipe_sumber() {
        return tipe_sumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getRef_kuliner_nama() {
        return ref_kuliner_nama;
    }

    public String getRef_kuliner_icon() {
        return ref_kuliner_icon;
    }
}
