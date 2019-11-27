package projekpati.com.projekpati.Model.Aspirasi;

public class GambarAspirasiDetil {
    String file_aspirasi_id, file_aspirasi_img;

    public GambarAspirasiDetil(String file_aspirasi_id, String file_aspirasi_img) {
        this.file_aspirasi_id = file_aspirasi_id;
        this.file_aspirasi_img = file_aspirasi_img;
    }

    public String getFile_aspirasi_id() {
        return file_aspirasi_id;
    }

    public void setFile_aspirasi_id(String file_aspirasi_id) {
        this.file_aspirasi_id = file_aspirasi_id;
    }

    public String getFile_aspirasi_img() {
        return file_aspirasi_img;
    }

    public void setFile_aspirasi_img(String file_aspirasi_img) {
        this.file_aspirasi_img = file_aspirasi_img;
    }
}
