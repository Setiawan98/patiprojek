package projekpati.com.projekpati.Notif;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import projekpati.com.projekpati.Model.Notif.ListNotif;
import projekpati.com.projekpati.R;

public class NotifAdapter extends ArrayAdapter<ListNotif> {

    private Context context;
    private List<ListNotif> notif;

    public NotifAdapter(Context context, int resource, List<ListNotif> objects) {
        super(context,resource,objects);
        this.context = context;
        this.notif = objects;
    }



    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.notif_adapter, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.mImage);
        TextView textJudul = (TextView) rowView.findViewById(R.id.mJudul);
        TextView textIsi = (TextView) rowView.findViewById(R.id.mIsi);
        TextView textTime = rowView.findViewById(R.id.mTime);

        textTime.setText(notif.get(pos).getTime2());
        textJudul.setText(notif.get(pos).getJudul());
        textIsi.setText(notif.get(pos).getIsi());
        return rowView;
    }
}
