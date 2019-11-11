package projekpati.com.projekpati.Model.Salon;

public class GambarSalonDetil {
    String file_salon_id, file_salon_img;

    public GambarSalonDetil(String file_salon_id, String file_salon_img) {
        this.file_salon_id = file_salon_id;
        this.file_salon_img = file_salon_img;
    }

    public String getFile_salon_id() {
        return file_salon_id;
    }

    public void setFile_salon_id(String file_salon_id) {
        this.file_salon_id = file_salon_id;
    }

    public String getFile_salon_img() {
        return file_salon_img;
    }

    public void setFile_salon_img(String file_salon_img) {
        this.file_salon_img = file_salon_img;
    }
}
