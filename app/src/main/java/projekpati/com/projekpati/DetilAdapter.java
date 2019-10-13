package projekpati.com.projekpati;

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

import projekpati.com.projekpati.Model.ListKuliner;

public class DetilAdapter extends ArrayAdapter<ListKuliner> {
    private Context context;
    private List<ListKuliner> kuliner;

    public DetilAdapter(Context context, int resource, List<ListKuliner> objects) {
        super(context,resource,objects);
        this.context = context;
        this.kuliner = objects;
    }

    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.detil_adapter, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.mImage);
        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);
        TextView textJam = (TextView) rowView.findViewById(R.id.mJamBuka);
        TextView textAlamat = (TextView) rowView.findViewById(R.id.mAlamat);
        TextView textTelepon = (TextView) rowView.findViewById(R.id.mTelpon);

        URL url = null;
        if(kuliner.get(pos).getFile().equals(""))
        {
            //tidak terjadi perubahan apapun
        }
        else
        {
            try {
                url = new URL(kuliner.get(pos).getFile());
                Picasso.with(getContext())
                        .load(String.valueOf(url))
                        .resize(300,200).noFade().into(imageView);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        textNama.setText(kuliner.get(pos).getNama());
        textTelepon.setText(kuliner.get(pos).getTelp());
        textAlamat.setText(kuliner.get(pos).getAlamat());
        textJam.setText(kuliner.get(pos).getJam_buka());


        return rowView;
    }

}
