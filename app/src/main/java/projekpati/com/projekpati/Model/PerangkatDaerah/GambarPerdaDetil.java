package projekpati.com.projekpati.Model.PerangkatDaerah;

public class GambarPerdaDetil {
    String file_perangkat_daerah_id, file_perangkat_daerah_img;

    public GambarPerdaDetil(String file_perangkat_daerah_id, String file_perangkat_daerah_img) {
        this.file_perangkat_daerah_id = file_perangkat_daerah_id;
        this.file_perangkat_daerah_img = file_perangkat_daerah_img;
    }

    public String getFile_perangkat_daerah_id() {
        return file_perangkat_daerah_id;
    }

    public void setFile_perangkat_daerah_id(String file_perangkat_daerah_id) {
        this.file_perangkat_daerah_id = file_perangkat_daerah_id;
    }

    public String getFile_perangkat_daerah_img() {
        return file_perangkat_daerah_img;
    }

    public void setFile_perangkat_daerah_img(String file_perangkat_daerah_img) {
        this.file_perangkat_daerah_img = file_perangkat_daerah_img;
    }
}
