package projekpati.com.projekpati.Model.Angkutan;

public class GambarAngkutanDetil {
    String file_angkutan_id, file_angkutan_img;

    public GambarAngkutanDetil(String file_angkutan_id, String file_angkutan_img) {
        this.file_angkutan_id = file_angkutan_id;
        this.file_angkutan_img = file_angkutan_img;
    }

    public String getFile_angkutan_id() {
        return file_angkutan_id;
    }

    public void setFile_angkutan_id(String file_angkutan_id) {
        this.file_angkutan_id = file_angkutan_id;
    }

    public String getFile_angkutan_img() {
        return file_angkutan_img;
    }

    public void setFile_angkutan_img(String file_angkutan_img) {
        this.file_angkutan_img = file_angkutan_img;
    }
}
