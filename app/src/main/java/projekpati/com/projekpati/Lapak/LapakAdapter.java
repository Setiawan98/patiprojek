package projekpati.com.projekpati.Lapak;

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

import projekpati.com.projekpati.Model.Lapak.ListLapak;
import projekpati.com.projekpati.R;

public class LapakAdapter extends ArrayAdapter<ListLapak> {
    private Context context;
    private List<ListLapak> lapak;

    public LapakAdapter(Context context, int resource, List<ListLapak> objects) {
        super(context,resource,objects);
        this.context = context;
        this.lapak = objects;
    }



    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.lapak_adapter, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.mImage);
        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);
        TextView textAlamat = (TextView) rowView.findViewById(R.id.mAlamat);
        TextView textRef = rowView.findViewById(R.id.mRefNama);


        URL url = null;
        if(lapak.get(pos).getFile_small().equals(""))
        {
            //tidak terjadi perubahan apapun
        }
        else
        {
            try {
                url = new URL(lapak.get(pos).getFile_small());
                Picasso.get()
                        .load(String.valueOf(url))
                        .resize(150,100).noFade().into(imageView);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        textRef.setText(lapak.get(pos).getRef_lapak_nama());
        textNama.setText(lapak.get(pos).getNama());
        textAlamat.setText(lapak.get(pos).getAlamat());
        return rowView;
    }
}
