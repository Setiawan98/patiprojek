package projekpati.com.projekpati;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowGoogleMaps implements GoogleMap.InfoWindowAdapter {

    private final LayoutInflater mInflater;

    public CustomInfoWindowGoogleMaps(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    @Override public View getInfoWindow(Marker marker) {
        final View popup = mInflater.inflate(R.layout.custom_infowindow_map, null);

        ((TextView) popup.findViewById(R.id.infoName)).setText(marker.getTitle());

        return popup;
        //return null;
    }

    @Override public View getInfoContents(Marker marker) {
        final View popup = mInflater.inflate(R.layout.custom_infowindow_map, null);

        ((TextView) popup.findViewById(R.id.infoName)).setText(marker.getTitle());

        return popup;
    }



}
