package projekpati.com.projekpati.Model.PelelanganIkan;

public class GambarPelelanganIkanDetil {
    String file_pelelangan_ikan_id, file_pelelangan_ikan_img;

    public GambarPelelanganIkanDetil(String file_pelelangan_ikan_id, String file_pelelangan_ikan_img) {
        this.file_pelelangan_ikan_id = file_pelelangan_ikan_id;
        this.file_pelelangan_ikan_img = file_pelelangan_ikan_img;
    }

    public String getFile_pelelangan_ikan_id() {
        return file_pelelangan_ikan_id;
    }

    public void setFile_pelelangan_ikan_id(String file_pelelangan_ikan_id) {
        this.file_pelelangan_ikan_id = file_pelelangan_ikan_id;
    }

    public String getFile_pelelangan_ikan_img() {
        return file_pelelangan_ikan_img;
    }

    public void setFile_pelelangan_ikan_img(String file_pelelangan_ikan_img) {
        this.file_pelelangan_ikan_img = file_pelelangan_ikan_img;
    }
}
