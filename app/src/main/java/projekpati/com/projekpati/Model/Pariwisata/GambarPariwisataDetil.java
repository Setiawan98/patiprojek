package projekpati.com.projekpati.Model.Pariwisata;

public class GambarPariwisataDetil {
    String file_pariwisata_id, file_pariwisata_img;

    public GambarPariwisataDetil(String file_pariwisata_id, String file_pariwisata_img) {
        this.file_pariwisata_id = file_pariwisata_id;
        this.file_pariwisata_img = file_pariwisata_img;
    }

    public String getFile_pariwisata_id() {
        return file_pariwisata_id;
    }

    public void setFile_pariwisata_id(String file_pariwisata_id) {
        this.file_pariwisata_id = file_pariwisata_id;
    }

    public String getFile_pariwisata_img() {
        return file_pariwisata_img;
    }

    public void setFile_pariwisata_img(String file_pariwisata_img) {
        this.file_pariwisata_img = file_pariwisata_img;
    }
}
