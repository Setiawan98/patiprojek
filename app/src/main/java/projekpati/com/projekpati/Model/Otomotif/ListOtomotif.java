package projekpati.com.projekpati.Model.Otomotif;

import android.os.Parcel;
import android.os.Parcelable;

public class ListOtomotif implements Parcelable {
    String id, nama, penjual, telp, email, website, alamat, harga, kondisi, model, warna, kilometer, thn_pembuatan, transmisi, habis_terjual, rating_jumlah, rating, file, file_small, latitude, longitude, deskripsi;
    Integer nomor;
    String ref_otomotif_icon, ref_otomotif_nama, ref_otomotif_warna;

    public ListOtomotif(String id, String nama, String penjual, String telp, String email, String website, String alamat, String harga, String kondisi, String model, String warna, String kilometer, String thn_pembuatan, String transmisi, String habis_terjual, String rating_jumlah, String rating, String file, String file_small, String latitude, String longitude, String deskripsi, Integer nomor, String ref_otomotif_icon, String ref_otomotif_nama, String ref_otomotif_warna) {
        this.id = id;
        this.nama = nama;
        this.penjual = penjual;
        this.telp = telp;
        this.email = email;
        this.website = website;
        this.alamat = alamat;
        this.harga = harga;
        this.kondisi = kondisi;
        this.model = model;
        this.warna = warna;
        this.kilometer = kilometer;
        this.thn_pembuatan = thn_pembuatan;
        this.transmisi = transmisi;
        this.habis_terjual = habis_terjual;
        this.rating_jumlah = rating_jumlah;
        this.rating = rating;
        this.file = file;
        this.file_small = file_small;
        this.latitude = latitude;
        this.longitude = longitude;
        this.deskripsi = deskripsi;
        this.nomor = nomor;
        this.ref_otomotif_icon = ref_otomotif_icon;
        this.ref_otomotif_nama = ref_otomotif_nama;
        this.ref_otomotif_warna = ref_otomotif_warna;
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

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
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

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getKilometer() {
        return kilometer;
    }

    public void setKilometer(String kilometer) {
        this.kilometer = kilometer;
    }

    public String getThn_pembuatan() {
        return thn_pembuatan;
    }

    public void setThn_pembuatan(String thn_pembuatan) {
        this.thn_pembuatan = thn_pembuatan;
    }

    public String getTransmisi() {
        return transmisi;
    }

    public void setTransmisi(String transmisi) {
        this.transmisi = transmisi;
    }

    public String getHabis_terjual() {
        return habis_terjual;
    }

    public void setHabis_terjual(String habis_terjual) {
        this.habis_terjual = habis_terjual;
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

    public String getRef_otomotif_icon() {
        return ref_otomotif_icon;
    }

    public void setRef_otomotif_icon(String ref_otomotif_icon) {
        this.ref_otomotif_icon = ref_otomotif_icon;
    }

    public String getRef_otomotif_nama() {
        return ref_otomotif_nama;
    }

    public void setRef_otomotif_nama(String ref_otomotif_nama) {
        this.ref_otomotif_nama = ref_otomotif_nama;
    }

    public String getRef_otomotif_warna() {
        return ref_otomotif_warna;
    }

    public void setRef_otomotif_warna(String ref_otomotif_warna) {
        this.ref_otomotif_warna = ref_otomotif_warna;
    }

    protected ListOtomotif(Parcel in) {
        id = in.readString();
        nama = in.readString();
        penjual = in.readString();
        telp = in.readString();
        email = in.readString();
        website = in.readString();
        alamat = in.readString();
        harga = in.readString();
        kondisi = in.readString();
        model = in.readString();
        warna = in.readString();
        kilometer = in.readString();
        thn_pembuatan = in.readString();
        transmisi = in.readString();
        habis_terjual = in.readString();
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
        ref_otomotif_icon = in.readString();
        ref_otomotif_nama = in.readString();
        ref_otomotif_warna = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
        dest.writeString(penjual);
        dest.writeString(telp);
        dest.writeString(email);
        dest.writeString(harga);
        dest.writeString(kondisi);
        dest.writeString(model);
        dest.writeString(warna);
        dest.writeString(kilometer);
        dest.writeString(thn_pembuatan);
        dest.writeString(transmisi);
        dest.writeString(habis_terjual);
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
        dest.writeString(ref_otomotif_icon);
        dest.writeString(ref_otomotif_nama);
        dest.writeString(ref_otomotif_warna);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListOtomotif> CREATOR = new Creator<ListOtomotif>() {
        @Override
        public ListOtomotif createFromParcel(Parcel in) {
            return new ListOtomotif(in);
        }

        @Override
        public ListOtomotif[] newArray(int size) {
            return new ListOtomotif[size];
        }
    };
}
