package projekpati.com.projekpati.Model;

public class tempImageModel {
    String index;
    byte[] imagebyte;

    public tempImageModel(String index, byte[] imagebyte) {
        this.index = index;
        this.imagebyte = imagebyte;
    }

    public String getIndex() {
        return index;
    }

    public byte[] getImagebyte() {
        return imagebyte;
    }
}
