package projekpati.com.projekpati.Model.TelpPenting;

public class ListTelpPenting {

    String id, nama,notelp, rating_jumlah, rating, file, file_small, deskripsi;
    Integer nomor;
    String ref_telp_penting_icon, ref_telp_penting_nama, ref_telp_penting_warna;

    public ListTelpPenting(String id, String nama, String notelp, String rating_jumlah, String rating, String file, String file_small, String deskripsi, Integer nomor, String ref_telp_penting_icon, String ref_telp_penting_nama, String ref_telp_penting_warna) {
        this.id = id;
        this.nama = nama;
        this.notelp = notelp;
        this.rating_jumlah = rating_jumlah;
        this.rating = rating;
        this.file = file;
        this.file_small = file_small;
        this.deskripsi = deskripsi;
        this.nomor = nomor;
        this.ref_telp_penting_icon = ref_telp_penting_icon;
        this.ref_telp_penting_nama = ref_telp_penting_nama;
        this.ref_telp_penting_warna = ref_telp_penting_warna;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNotelp() {
        return notelp;
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

    public String getDeskripsi() {
        return deskripsi;
    }

    public Integer getNomor() {
        return nomor;
    }

    public String getRef_telp_penting_icon() {
        return ref_telp_penting_icon;
    }

    public String getRef_telp_penting_nama() {
        return ref_telp_penting_nama;
    }

    public String getRef_telp_penting_warna() {
        return ref_telp_penting_warna;
    }
}

    

