package projekpati.com.projekpati.Model.Bank;

public class GambarBankDetil {
    String file_bank_id, file_bank_img;

    public GambarBankDetil(String file_bank_id, String file_bank_img) {
        this.file_bank_id = file_bank_id;
        this.file_bank_img = file_bank_img;
    }

    public String getFile_bank_id() {
        return file_bank_id;
    }

    public void setFile_bank_id(String file_bank_id) {
        this.file_bank_id = file_bank_id;
    }

    public String getFile_bank_img() {
        return file_bank_img;
    }

    public void setFile_bank_img(String file_bank_img) {
        this.file_bank_img = file_bank_img;
    }
}
