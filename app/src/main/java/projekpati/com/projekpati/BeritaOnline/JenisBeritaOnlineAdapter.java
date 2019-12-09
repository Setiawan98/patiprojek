package projekpati.com.projekpati.BeritaOnline;

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

import projekpati.com.projekpati.Model.BeritaOnline.JenisBeritaOnline;
import projekpati.com.projekpati.R;

public class JenisBeritaOnlineAdapter extends ArrayAdapter<JenisBeritaOnline> {
    private Context context;
    private List<JenisBeritaOnline> beritaOnline;

    public JenisBeritaOnlineAdapter(Context context, int resource, List<JenisBeritaOnline> objects) {
        super(context,resource,objects);
        this.context = context;
        this.beritaOnline = objects;
    }

    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.jenis_berita_online_adapter, parent, false);

        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);
        ImageView mImage = rowView.findViewById(R.id.mImage);

        URL url = null;
        if(beritaOnline.get(pos).getIcon().equals(""))
        {
            //tidak terjadi perubahan apapun
        }
        else
        {
            try {
                url = new URL(beritaOnline.get(pos).getIcon());
                Picasso.get()
                        .load(String.valueOf(url))
                        .resize(150,100).noFade().into(mImage);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        textNama.setText(beritaOnline.get(pos).getNama());

        return rowView;
    }
}