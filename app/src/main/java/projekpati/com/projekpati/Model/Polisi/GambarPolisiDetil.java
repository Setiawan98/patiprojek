package projekpati.com.projekpati.Model.Polisi;

public class GambarPolisiDetil {
    String file_polisi_id, file_polisi_img;

    public GambarPolisiDetil(String file_polisi_id, String file_polisi_img) {
        this.file_polisi_id = file_polisi_id;
        this.file_polisi_img = file_polisi_img;
    }

    public String getFile_polisi_id() {
        return file_polisi_id;
    }

    public void setFile_polisi_id(String file_polisi_id) {
        this.file_polisi_id = file_polisi_id;
    }

    public String getFile_polisi_img() {
        return file_polisi_img;
    }

    public void setFile_polisi_img(String file_polisi_img) {
        this.file_polisi_img = file_polisi_img;
    }
}
