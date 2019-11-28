package projekpati.com.projekpati.Model.Agenda;

import android.os.Parcel;
import android.os.Parcelable;

public class ListAgenda implements Parcelable {
    String id, nama, telp, email, website, alamat, tipe, rating_jumlah, rating, file, file_small, latitude, longitude, deskripsi,tgl_mulai, tgl_selesai, harga_tiket;
    Integer nomor;
    String ref_agenda_icon, ref_agenda_nama, ref_agenda_warna;

    public ListAgenda(String id, String nama, String telp, String email, String website, String alamat, String tipe, String rating_jumlah, String rating, String file, String file_small, String latitude, String longitude, String deskripsi, String tgl_mulai, String tgl_selesai, String harga_tiket, Integer nomor, String ref_agenda_icon, String ref_agenda_nama, String ref_agenda_warna) {
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
        this.tgl_mulai = tgl_mulai;
        this.tgl_selesai = tgl_selesai;
        this.harga_tiket = harga_tiket;
        this.nomor = nomor;
        this.ref_agenda_icon = ref_agenda_icon;
        this.ref_agenda_nama = ref_agenda_nama;
        this.ref_agenda_warna = ref_agenda_warna;
    }

    protected ListAgenda(Parcel in) {
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
        tgl_mulai = in.readString();
        tgl_selesai = in.readString();
        harga_tiket = in.readString();
        if (in.readByte() == 0) {
            nomor = null;
        } else {
            nomor = in.readInt();
        }
        ref_agenda_icon = in.readString();
        ref_agenda_nama = in.readString();
        ref_agenda_warna = in.readString();
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
        dest.writeString(tgl_mulai);
        dest.writeString(tgl_selesai);
        dest.writeString(harga_tiket);
        if (nomor == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(nomor);
        }
        dest.writeString(ref_agenda_icon);
        dest.writeString(ref_agenda_nama);
        dest.writeString(ref_agenda_warna);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListAgenda> CREATOR = new Creator<ListAgenda>() {
        @Override
        public ListAgenda createFromParcel(Parcel in) {
            return new ListAgenda(in);
        }

        @Override
        public ListAgenda[] newArray(int size) {
            return new ListAgenda[size];
        }
    };

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public void setRating_jumlah(String rating_jumlah) {
        this.rating_jumlah = rating_jumlah;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setFile_small(String file_small) {
        this.file_small = file_small;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setTgl_mulai(String tgl_mulai) {
        this.tgl_mulai = tgl_mulai;
    }

    public void setTgl_selesai(String tgl_selesai) {
        this.tgl_selesai = tgl_selesai;
    }

    public void setHarga_tiket(String harga_tiket) {
        this.harga_tiket = harga_tiket;
    }

    public void setNomor(Integer nomor) {
        this.nomor = nomor;
    }

    public void setRef_agenda_icon(String ref_agenda_icon) {
        this.ref_agenda_icon = ref_agenda_icon;
    }

    public void setRef_agenda_nama(String ref_agenda_nama) {
        this.ref_agenda_nama = ref_agenda_nama;
    }

    public void setRef_agenda_warna(String ref_agenda_warna) {
        this.ref_agenda_warna = ref_agenda_warna;
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

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public String getHarga_tiket() {
        return harga_tiket;
    }

    public Integer getNomor() {
        return nomor;
    }

    public String getRef_agenda_icon() {
        return ref_agenda_icon;
    }

    public String getRef_agenda_nama() {
        return ref_agenda_nama;
    }

    public String getRef_agenda_warna() {
        return ref_agenda_warna;
    }
}
