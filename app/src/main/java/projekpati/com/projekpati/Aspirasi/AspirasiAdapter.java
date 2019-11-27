package projekpati.com.projekpati.Aspirasi;

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

import projekpati.com.projekpati.Model.Aspirasi.ListAspirasi;
import projekpati.com.projekpati.R;

public class AspirasiAdapter extends ArrayAdapter<ListAspirasi> {
    private Context context;
    private List<ListAspirasi> aspirasi;

    public AspirasiAdapter(Context context, int resource, List<ListAspirasi> objects) {
        super(context,resource,objects);
        this.context = context;
        this.aspirasi = objects;
    }



    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.aspirasi_adapter, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.mImage);
        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);
        TextView textDeskripsi = (TextView) rowView.findViewById(R.id.mDeskripsi);


        URL url = null;
        if(aspirasi.get(pos).getFile_small().equals(""))
        {
            //tidak terjadi perubahan apapun
        }
        else
        {
            try {
                url = new URL(aspirasi.get(pos).getFile_small());
                Picasso.get()
                        .load(String.valueOf(url))
                        .resize(150,100).noFade().into(imageView);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        textNama.setText(aspirasi.get(pos).getNama());
        textDeskripsi.setText(aspirasi.get(pos).getDeskripsi());
        return rowView;
    }
}
