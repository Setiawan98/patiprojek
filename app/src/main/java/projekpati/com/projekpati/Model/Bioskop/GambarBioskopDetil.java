package projekpati.com.projekpati.Model.Bioskop;

public class GambarBioskopDetil {
    String file_bioskop_id, file_bioskop_img;

    public GambarBioskopDetil(String file_bioskop_id, String file_bioskop_img) {
        this.file_bioskop_id = file_bioskop_id;
        this.file_bioskop_img = file_bioskop_img;
    }

    public String getFile_bioskop_id() {
        return file_bioskop_id;
    }

    public void setFile_bioskop_id(String file_bioskop_id) {
        this.file_bioskop_id = file_bioskop_id;
    }

    public String getFile_bioskop_img() {
        return file_bioskop_img;
    }

    public void setFile_bioskop_img(String file_bioskop_img) {
        this.file_bioskop_img = file_bioskop_img;
    }
}
