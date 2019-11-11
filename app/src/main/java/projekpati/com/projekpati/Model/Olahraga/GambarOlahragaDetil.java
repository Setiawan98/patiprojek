package projekpati.com.projekpati.Model.Olahraga;

public class GambarOlahragaDetil {
    String file_olahraga_id, file_olahraga_img;

    public GambarOlahragaDetil(String file_olahraga_id, String file_olahraga_img) {
        this.file_olahraga_id = file_olahraga_id;
        this.file_olahraga_img = file_olahraga_img;
    }

    public String getFile_olahraga_id() {
        return file_olahraga_id;
    }

    public void setFile_olahraga_id(String file_olahraga_id) {
        this.file_olahraga_id = file_olahraga_id;
    }

    public String getFile_olahraga_img() {
        return file_olahraga_img;
    }

    public void setFile_olahraga_img(String file_olahraga_img) {
        this.file_olahraga_img = file_olahraga_img;
    }
}
