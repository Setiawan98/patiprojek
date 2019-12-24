package projekpati.com.projekpati.KodePos;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import projekpati.com.projekpati.Model.KodePos.ListKodePos;
import projekpati.com.projekpati.R;

public class KodePosAdapter extends ArrayAdapter<ListKodePos> {
    private Context context;
    private List<ListKodePos> kodePos;

    public KodePosAdapter(Context context, int resource, List<ListKodePos> objects) {
        super(context,resource,objects);
        this.context = context;
        this.kodePos = objects;
    }



    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.kode_pos_adapter, parent, false);


        TextView textInitial = (TextView) rowView.findViewById(R.id.mInitial);
        TextView textKelurahan = (TextView) rowView.findViewById(R.id.mKelurahan);
        TextView textKecamatan = (TextView) rowView.findViewById(R.id.mKecamatan);
        TextView textKodePos = rowView.findViewById(R.id.mKodePos);
        CardView background = rowView.findViewById(R.id.backgroundInitial);

        Random rnd = new Random();
        int color = Color.argb(255,rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
        background.setBackgroundColor(color);


        if(kodePos.get(pos).getKelurahan()!=null)
        {
            textInitial.setText(kodePos.get(pos).getKelurahan().substring(0,1));
        }

        textKelurahan.setText(kodePos.get(pos).getKelurahan());
        textKecamatan.setText("Kec. "+kodePos.get(pos).getKecamatan());
        textKodePos.setText(kodePos.get(pos).getKode());
        return rowView;
    }
}
