package projekpati.com.projekpati.Model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MyItem implements ClusterItem {

    private final LatLng mPosition;
    private String mTitle;
    private String mSnippet;
    private  String mjenis;

    public MyItem(double lat, double lng, String title, String snippet, String jenis) {
        this.mPosition = new LatLng(lat,lng);
        mTitle = title;
        mSnippet = snippet;
        mjenis =jenis;
    }

    public MyItem(double lat, double lng) {
        this.mPosition = new LatLng(lat,lng);
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSnippet() {
        return mSnippet;
    }

    public String getMjenis() {
        return mjenis;
    }
}
