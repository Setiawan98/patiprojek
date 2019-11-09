package projekpati.com.projekpati.Kesehatan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import projekpati.com.projekpati.Model.MyItem;
import projekpati.com.projekpati.R;

public class CustomClusterRendererKesehatan extends DefaultClusterRenderer<MyItem> {
    private final IconGenerator iconGenerator;
    private final Context context;

    public CustomClusterRendererKesehatan(Context context, GoogleMap map, ClusterManager<MyItem> clusterManager) {
        super(context, map, clusterManager);
        iconGenerator = new IconGenerator(context.getApplicationContext());
        this.context = context;
    }



    @Override
    protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions) {
        IconGenerator ig = new IconGenerator(context);
        View marker = View.inflate(context, R.layout.custom_infowindow_map,null);
        TextView name = marker.findViewById(R.id.infoName);
        ImageView mark = marker.findViewById(R.id.marker);
        name.setText(item.getTitle());
//        if(item.getMjenis()!=null) {
//
//            if(item.getMjenis().equals("Universitas"))
//            {
//                name.setBackgroundResource(R.color.blue);
//                mark.setColorFilter(ContextCompat.getColor(context,R.color.blue), PorterDuff.Mode.SRC_IN);
//            }
//            else  if(item.getMjenis().equals("SMA/SMK"))
//            {
//                name.setBackgroundResource(R.color.green);
//                mark.setColorFilter(ContextCompat.getColor(context,R.color.green), PorterDuff.Mode.SRC_IN);
//            }
//            else  if(item.getMjenis().equals("SMP"))
//            {
//                name.setBackgroundResource(R.color.yellow);
//                mark.setColorFilter(ContextCompat.getColor(context,R.color.yellow), PorterDuff.Mode.SRC_IN);
//            }
//            else  if(item.getMjenis().equals("SD"))
//            {
//                name.setBackgroundResource(R.color.red);
//                mark.setColorFilter(ContextCompat.getColor(context,R.color.red), PorterDuff.Mode.SRC_IN);
//            }
//            else  if(item.getMjenis().equals("SLB"))
//            {
//                name.setBackgroundResource(R.color.purple);
//                mark.setColorFilter(ContextCompat.getColor(context,R.color.purple), PorterDuff.Mode.SRC_IN);
//            }
//        }

        ig.setContentView(marker);
        ig.setBackground(null);
        Bitmap iconMarker = ig.makeIcon();
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(iconMarker));




//        final BitmapDescriptor markerDescriptor = bitmapDescriptor(context, R.drawable.ic_location_on_black_24dp);
//        markerOptions.icon(markerDescriptor).title(item.getTitle());
//
//       Log.d("location",String.valueOf(item.getPosition().latitude));
////       if(item.getPosition()!=null)
////       {
////           Log.d("masuk1",item.getTitle());
////           Marker marker =
////           marker.showInfoWindow();
////       }
//
//            //getMarker(item).showInfoWindow();


    }

    @Override
    protected void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions) {

        iconGenerator.setBackground(
                ContextCompat.getDrawable(context, R.drawable.circle));
        iconGenerator.setTextAppearance(R.style.AppTheme_WhiteTextAppearance);
        final Bitmap icon = iconGenerator.makeIcon(String.valueOf(cluster.getSize()));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));

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
    protected boolean shouldRenderAsCluster(Cluster<MyItem> cluster) {
        return cluster.getSize() >=4;
    }


}
