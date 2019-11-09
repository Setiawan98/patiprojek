package projekpati.com.projekpati.Model.Kesehatan;

public class GambarKesehatanDetil {
    String file_kesehatan_id, file_kesehatan_img;

    public GambarKesehatanDetil(String file_kesehatan_id, String file_kesehatan_img) {
        this.file_kesehatan_id = file_kesehatan_id;
        this.file_kesehatan_img = file_kesehatan_img;
    }

    public String getFile_kesehatan_id() {
        return file_kesehatan_id;
    }

    public void setFile_kesehatan_id(String file_kesehatan_id) {
        this.file_kesehatan_id = file_kesehatan_id;
    }

    public String getFile_kesehatan_img() {
        return file_kesehatan_img;
    }

    public void setFile_kesehatan_img(String file_kesehatan_img) {
        this.file_kesehatan_img = file_kesehatan_img;
    }
}
