package projekpati.com.projekpati.Model.Hotel;

public class GambarHotelDetil {
    String file_hotel_id, file_hotel_img;

    public GambarHotelDetil(String file_hotel_id, String file_hotel_img) {
        this.file_hotel_id = file_hotel_id;
        this.file_hotel_img = file_hotel_img;
    }

    public String getFile_hotel_id() {
        return file_hotel_id;
    }

    public void setFile_hotel_id(String file_hotel_id) {
        this.file_hotel_id = file_hotel_id;
    }

    public String getFile_hotel_img() {
        return file_hotel_img;
    }

    public void setFile_hotel_img(String file_hotel_img) {
        this.file_hotel_img = file_hotel_img;
    }
}
