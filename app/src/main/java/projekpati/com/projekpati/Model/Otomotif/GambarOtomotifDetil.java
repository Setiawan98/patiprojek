package projekpati.com.projekpati.Model.Otomotif;

public class GambarOtomotifDetil {
    String file_otomotif_id, file_otomotif_img;

    public GambarOtomotifDetil(String file_otomotif_id, String file_otomotif_img) {
        this.file_otomotif_id = file_otomotif_id;
        this.file_otomotif_img = file_otomotif_img;
    }

    public String getFile_otomotif_id() {
        return file_otomotif_id;
    }

    public void setFile_otomotif_id(String file_otomotif_id) {
        this.file_otomotif_id = file_otomotif_id;
    }

    public String getFile_otomotif_img() {
        return file_otomotif_img;
    }

    public void setFile_otomotif_img(String file_otomotif_img) {
        this.file_otomotif_img = file_otomotif_img;
    }
}
