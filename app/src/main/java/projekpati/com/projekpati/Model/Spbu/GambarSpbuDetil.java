package projekpati.com.projekpati.Model.Spbu;

public class GambarSpbuDetil {
    String file_spbu_id, file_spbu_img;

    public GambarSpbuDetil(String file_spbu_id, String file_spbu_img) {
        this.file_spbu_id = file_spbu_id;
        this.file_spbu_img = file_spbu_img;
    }

    public String getFile_spbu_id() {
        return file_spbu_id;
    }

    public void setFile_spbu_id(String file_spbu_id) {
        this.file_spbu_id = file_spbu_id;
    }

    public String getFile_spbu_img() {
        return file_spbu_img;
    }

    public void setFile_spbu_img(String file_spbu_img) {
        this.file_spbu_img = file_spbu_img;
    }
}
