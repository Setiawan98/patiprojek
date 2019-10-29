package projekpati.com.projekpati.Model;

public class GambarDetil {
    String file_kuliner_id, file_kuliner_img;

    public GambarDetil(String file_kuliner_id, String file_kuliner_img) {
        this.file_kuliner_id = file_kuliner_id;
        this.file_kuliner_img = file_kuliner_img;
    }

    public String getFile_kuliner_id() {
        return file_kuliner_id;
    }

    public String getFile_kuliner_img() {
        return file_kuliner_img;
    }
}
