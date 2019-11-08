package projekpati.com.projekpati.Model.Tukang;

public class GambarTukangDetil {
    String file_tukang_id, file_tukang_img;

    public GambarTukangDetil(String file_tukang_id, String file_tukang_img) {
        this.file_tukang_id = file_tukang_id;
        this.file_tukang_img = file_tukang_img;
    }

    public String getFile_tukang_id() {
        return file_tukang_id;
    }

    public void setFile_tukang_id(String file_tukang_id) {
        this.file_tukang_id = file_tukang_id;
    }

    public String getFile_tukang_img() {
        return file_tukang_img;
    }

    public void setFile_tukang_img(String file_tukang_img) {
        this.file_tukang_img = file_tukang_img;
    }
}
