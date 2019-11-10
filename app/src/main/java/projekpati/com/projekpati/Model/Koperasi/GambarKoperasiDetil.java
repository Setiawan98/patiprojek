package projekpati.com.projekpati.Model.Koperasi;

public class GambarKoperasiDetil {
    String file_koperasi_id, file_koperasi_img;

    public GambarKoperasiDetil(String file_koperasi_id, String file_koperasi_img) {
        this.file_koperasi_id = file_koperasi_id;
        this.file_koperasi_img = file_koperasi_img;
    }

    public String getFile_koperasi_id() {
        return file_koperasi_id;
    }

    public void setFile_koperasi_id(String file_koperasi_id) {
        this.file_koperasi_id = file_koperasi_id;
    }

    public String getFile_koperasi_img() {
        return file_koperasi_img;
    }

    public void setFile_koperasi_img(String file_koperasi_img) {
        this.file_koperasi_img = file_koperasi_img;
    }
}
