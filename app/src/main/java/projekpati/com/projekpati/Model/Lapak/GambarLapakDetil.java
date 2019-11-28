package projekpati.com.projekpati.Model.Lapak;

public class GambarLapakDetil {
    String file_lapak_id, file_lapak_img;

    public GambarLapakDetil(String file_lapak_id, String file_lapak_img) {
        this.file_lapak_id = file_lapak_id;
        this.file_lapak_img = file_lapak_img;
    }

    public String getFile_lapak_id() {
        return file_lapak_id;
    }

    public void setFile_lapak_id(String file_lapak_id) {
        this.file_lapak_id = file_lapak_id;
    }

    public String getFile_lapak_img() {
        return file_lapak_img;
    }

    public void setFile_lapak_img(String file_lapak_img) {
        this.file_lapak_img = file_lapak_img;
    }
}
