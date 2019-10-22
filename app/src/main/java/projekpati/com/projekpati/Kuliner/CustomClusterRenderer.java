package projekpati.com.projekpati.Kuliner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import projekpati.com.projekpati.Model.MyItem;
import projekpati.com.projekpati.R;

public class CustomClusterRenderer extends DefaultClusterRenderer<MyItem> {
    private final IconGenerator iconGenerator;
    private final Context context;

    public CustomClusterRenderer(Context context, GoogleMap map, ClusterManager<MyItem> clusterManager) {
        super(context, map, clusterManager);
        iconGenerator = new IconGenerator(context.getApplicationContext());
        this.context = context;
    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions) {
        final BitmapDescriptor markerDescriptor = bitmapDescriptor(context, R.drawable.ic_location_on_black_24dp);
        markerOptions.icon(markerDescriptor).title(item.getTitle()).snippet(item.getSnippet());

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
        return cluster.getSize() >=3;
    }


}
