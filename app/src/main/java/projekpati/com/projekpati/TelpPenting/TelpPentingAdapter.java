package projekpati.com.projekpati.TelpPenting;

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

import projekpati.com.projekpati.Model.TelpPenting.ListTelpPenting;
import projekpati.com.projekpati.R;

public class TelpPentingAdapter extends ArrayAdapter<ListTelpPenting> {
    private Context context;
    private List<ListTelpPenting> beritaCetak;

    public TelpPentingAdapter(Context context, int resource, List<ListTelpPenting> objects) {
        super(context,resource,objects);
        this.context = context;
        this.beritaCetak = objects;
    }



    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.telp_penting_adapter, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.mImage);
        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);
        TextView textAlamat = (TextView) rowView.findViewById(R.id.mAlamat);


        URL url = null;
        if(beritaCetak.get(pos).getFile_small().equals(""))
        {
            //tidak terjadi perubahan apapun
        }
        else
        {
            try {
                url = new URL(beritaCetak.get(pos).getFile_small());
                Picasso.get()
                        .load(String.valueOf(url))
                        .resize(150,100).noFade().into(imageView);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        textNama.setText(beritaCetak.get(pos).getNama());
        textAlamat.setText(beritaCetak.get(pos).getNotelp());


        return rowView;
    }
}
