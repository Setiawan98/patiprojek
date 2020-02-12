package projekpati.com.projekpati.Model.TempatIbadah;

public class GambarIbadahDetil {
    String file_ibadah_id, file_ibadah_img;

    public GambarIbadahDetil(String file_ibadah_id, String file_ibadah_img) {
        this.file_ibadah_id = file_ibadah_id;
        this.file_ibadah_img = file_ibadah_img;
    }

    public String getFile_ibadah_id() {
        return file_ibadah_id;
    }

    public void setFile_ibadah_id(String file_ibadah_id) {
        this.file_ibadah_id = file_ibadah_id;
    }

    public String getFile_ibadah_img() {
        return file_ibadah_img;
    }

    public void setFile_ibadah_img(String file_ibadah_img) {
        this.file_ibadah_img = file_ibadah_img;
    }
}
