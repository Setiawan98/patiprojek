package projekpati.com.projekpati.Model.BeritaCetak;

public class GambarBeritaCetakDetil {
    String file_berita_cetak_id, file_berita_cetak_img;

    public GambarBeritaCetakDetil(String file_berita_cetak_id, String file_berita_cetak_img) {
        this.file_berita_cetak_id = file_berita_cetak_id;
        this.file_berita_cetak_img = file_berita_cetak_img;
    }

    public String getFile_berita_cetak_id() {
        return file_berita_cetak_id;
    }

    public void setFile_berita_cetak_id(String file_berita_cetak_id) {
        this.file_berita_cetak_id = file_berita_cetak_id;
    }

    public String getFile_berita_cetak_img() {
        return file_berita_cetak_img;
    }

    public void setFile_berita_cetak_img(String file_berita_cetak_img) {
        this.file_berita_cetak_img = file_berita_cetak_img;
    }
}
