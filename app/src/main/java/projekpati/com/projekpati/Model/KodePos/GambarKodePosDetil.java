package projekpati.com.projekpati.Model.KodePos;

public class GambarKodePosDetil {
    String file_kode_pos_id, file_kode_pos_img;

    public GambarKodePosDetil(String file_kode_pos_id, String file_kode_pos_img) {
        this.file_kode_pos_id = file_kode_pos_id;
        this.file_kode_pos_img = file_kode_pos_img;
    }

    public String getFile_kode_pos_id() {
        return file_kode_pos_id;
    }

    public void setFile_kode_pos_id(String file_kode_pos_id) {
        this.file_kode_pos_id = file_kode_pos_id;
    }

    public String getFile_kode_pos_img() {
        return file_kode_pos_img;
    }

    public void setFile_kode_pos_img(String file_kode_pos_img) {
        this.file_kode_pos_img = file_kode_pos_img;
    }
}
