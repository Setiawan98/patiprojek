package projekpati.com.projekpati.Model.Kerjaan;

public class GambarKerjaanDetil {

    String file_kerjaan_id, file_kerjaan_img;

    public GambarKerjaanDetil(String file_kerjaan_id, String file_kerjaan_img) {
        this.file_kerjaan_id = file_kerjaan_id;
        this.file_kerjaan_img = file_kerjaan_img;
    }

    public String getFile_kerjaan_id() {
        return file_kerjaan_id;
    }

    public String getFile_kerjaan_img() {
        return file_kerjaan_img;
    }

    public void setFile_kerjaan_id(String file_kerjaan_id) {
        this.file_kerjaan_id = file_kerjaan_id;
    }

    public void setFile_kerjaan_img(String file_kerjaan_img) {
        this.file_kerjaan_img = file_kerjaan_img;
    }
}
