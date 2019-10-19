package projekpati.com.projekpati;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import projekpati.com.projekpati.Model.ListKuliner;

public class DetailMap extends AppCompatActivity implements OnMapReadyCallback {

    ArrayList<ListKuliner> listLatn;
    LinearLayout loadlLayout;
    View beforeClick;
    SupportMapFragment mapFragment;
    GoogleMap mMap;
    HorizontalScrollView horizontalScrollView;
    LinearLayout content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_map);

        Toolbar toolbar;

        toolbar = (Toolbar) findViewById(R.id.kulinerToolbar);
        loadlLayout = (LinearLayout) findViewById(R.id.loadLayout);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.Horizontalscroll) ;

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Bundle bundle = getIntent().getExtras();
        listLatn = this.getIntent().getParcelableArrayListExtra("list");
        Log.w("List", new Gson().toJson((listLatn)));


        if(listLatn!=null)
        {
            loadMap();
        }

    }

    public void loadMap()
    {
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.clear();

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(-6.7487,111.0379))
                .zoom(15)
                .bearing(0)
                .build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex),100,null);

        for(ListKuliner lk : listLatn)
        {
            Log.d("masuk","a");
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Float.parseFloat(lk.getLatitude()),Float.parseFloat(lk.getLongitude())))
                    .title(lk.getNama())
                    .snippet(lk.getAlamat())
                    .icon(bitmapDescriptor(this,R.drawable.ic_location_on_black_24dp)));
        }

        listKuliner();

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==android.R.id.home)
        {
            Intent i = new Intent(DetailMap.this,MenuKuliner.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setVisible(View v)
    {
        if(beforeClick!=null)
        {
            beforeClick.setVisibility(View.GONE);
        }
        v.setVisibility(View.VISIBLE);
        beforeClick=v;
    }


    public void listKuliner()
    {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        int size = listLatn.size();
        Log.d("size",String.valueOf(size));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                for(int i = 0;i<listLatn.size();i++)
                {

                    LinearLayout ly = (LinearLayout) horizontalScrollView.getChildAt(0);
                    TextView name = ly.getChildAt(i).findViewById(R.id.textThumbnail);
                    content = (LinearLayout) ly.getChildAt(i);
                    if(name.getText().toString().equals(marker.getTitle()))
                    {
                        setOnClickMarker(content);
                    }

                }
                return true;
            }
        });
        for(int i =0;i<size;i++)
        {
            final ListKuliner lk = listLatn.get(i);
            Log.d("nama",lk.getNama());

            if(lk!=null)
            {
                Log.d("z","11");
                LinearLayout clickAbleColumn = (LinearLayout) inflater.inflate(R.layout.clickable_column,null);
                //clickAbleColumn.setR
                ImageView thumbnailImage = (ImageView) clickAbleColumn.findViewById(R.id.thubnail_image);
                TextView titleText = (TextView) clickAbleColumn.findViewById(R.id.textThumbnail);
                TextView titleJenis = (TextView) clickAbleColumn.findViewById(R.id.textJenis);
                URL url = null;
                if(lk.getFile_small().equals(""))
                {
                    //tidak terjadi perubahan apapun
                }
                else
                {
                    try {
                        url = new URL(lk.getFile_small());
                        Picasso.with(DetailMap.this)
                                .load(String.valueOf(url))
                                .resize(156,108).noFade().into(thumbnailImage);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                titleText.setText(lk.getNama());
                titleJenis.setText((lk.getTipe()));
                if(lk.getTipe().equals("Cafe"))
                {
                    titleJenis.setTextColor(getResources().getColor(R.color.blue));
                }
                else  if(lk.getTipe().equals("PKL"))
                {
                    titleJenis.setTextColor(getResources().getColor(R.color.green));
                }
                else  if(lk.getTipe().equals("Restoran"))
                {
                    titleJenis.setTextColor(getResources().getColor(R.color.yellow));
                }
                else  if(lk.getTipe().equals("Warung"))
                {
                    titleJenis.setTextColor(getResources().getColor(R.color.red));
                }

                loadlLayout.addView(clickAbleColumn);
                if(i==size-1)
                {
                    ImageView layout = (ImageView) clickAbleColumn.findViewById(R.id.padding);
                    layout.setVisibility(View.VISIBLE);
                    beforeClick = layout;

                }


                clickAbleColumn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        int x = (int) v.getLeft()-((horizontalScrollView.getWidth()-v.getWidth())/2);
                        int y = v.getTop();
                        horizontalScrollView.smoothScrollTo(x,y);
                        Log.d("width hv",String.valueOf((horizontalScrollView.getWidth()-v.getWidth())/2));
                        ImageView layout = (ImageView) v.findViewById(R.id.padding);

                        setVisible(layout);
                        CameraPosition googlePlex = CameraPosition.builder()
                                .target(new LatLng(Float.parseFloat(lk.getLatitude()),Float.parseFloat(lk.getLongitude())))
                                .zoom(20)
                                .bearing(0)
                                .build();

                        Marker marker = mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(Float.parseFloat(lk.getLatitude()),Float.parseFloat(lk.getLongitude())))
                                .title(lk.getNama())
                                .snippet(lk.getAlamat())
                                .icon(bitmapDescriptor(DetailMap.this,R.drawable.ic_location_on_black_24dp)));

                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex),1000,null);
                        marker.showInfoWindow();


                    }
                });

            }
        }

    }

    public void setOnClickMarker(LinearLayout clickAbleColumn)
    {
        clickAbleColumn.callOnClick();
    }



}