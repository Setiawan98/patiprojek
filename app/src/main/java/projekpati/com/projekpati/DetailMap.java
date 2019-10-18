package projekpati.com.projekpati;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import projekpati.com.projekpati.Model.ListKuliner;

public class DetailMap extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_map);

        loadMap();
    }

    public void loadMap()
    {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.clear();

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(-6.7487,111.0379))
                .zoom(15)
                .bearing(0)
                .build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex),100,null);

//        for(ListKuliner lk : listLatn)
//        {
//            Log.d("masuk","a");
//            googleMap.addMarker(new MarkerOptions()
//                    .position(new LatLng(Float.parseFloat(lk.getLatitude()),Float.parseFloat(lk.getLongitude())))
//                    .title(lk.getNama())
//                    .snippet(lk.getAlamat())
//                    .icon(bitmapDescriptor(getContext(),R.drawable.ic_location_on_black_24dp)));
//        }

//        googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(48.8584,2.2945))
//                .title("c")
//                .snippet("d")
//                .icon(bitmapDescriptor(getContext(),R.drawable.ic_location_on_black_24dp)));

    }

    private BitmapDescriptor bitmapDescriptor(Context context, int vectorID)
    {
        Drawable vectorDrawable = ContextCompat.getDrawable(context,vectorID);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
