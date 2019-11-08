package projekpati.com.projekpati.Model.Pendidikan;

public class GambarPendidikanDetil {
    String file_pendidikan_id, file_pendidikan_img;

    public GambarPendidikanDetil(String file_pendidikan_id, String file_pendidikan_img) {
        this.file_pendidikan_id = file_pendidikan_id;
        this.file_pendidikan_img = file_pendidikan_img;
    }

    public String getFile_pendidikan_id() {
        return file_pendidikan_id;
    }

    public void setFile_pendidikan_id(String file_pendidikan_id) {
        this.file_pendidikan_id = file_pendidikan_id;
    }

    public String getFile_pendidikan_img() {
        return file_pendidikan_img;
    }

    public void setFile_pendidikan_img(String file_pendidikan_img) {
        this.file_pendidikan_img = file_pendidikan_img;
    }
}
