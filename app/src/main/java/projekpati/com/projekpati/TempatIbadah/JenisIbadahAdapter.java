package projekpati.com.projekpati.TempatIbadah;

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

import projekpati.com.projekpati.Model.TempatIbadah.JenisIbadah;
import projekpati.com.projekpati.R;

public class JenisIbadahAdapter extends ArrayAdapter<JenisIbadah> {
    private Context context;
    private List<JenisIbadah> ibadah;

    public JenisIbadahAdapter(Context context, int resource, List<JenisIbadah> objects) {
        super(context,resource,objects);
        this.context = context;
        this.ibadah = objects;
    }

    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.jenis_ibadah_adapter, parent, false);

        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);
        ImageView mImage = rowView.findViewById(R.id.mImage);

        URL url = null;
        if(ibadah.get(pos).getIcon().equals(""))
        {
            //tidak terjadi perubahan apapun
        }
        else
        {
            try {
                url = new URL(ibadah.get(pos).getIcon());
                Picasso.get()
                        .load(String.valueOf(url))
                        .resize(150,100).noFade().into(mImage);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        textNama.setText(ibadah.get(pos).getNama());

        return rowView;
    }
}
