package projekpati.com.projekpati.Otomotif;

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

import projekpati.com.projekpati.Model.Otomotif.ListOtomotif;
import projekpati.com.projekpati.R;

public class OtomotifAdapter extends ArrayAdapter<ListOtomotif> {
    private Context context;
    private List<ListOtomotif> otomotif;

    public OtomotifAdapter(Context context, int resource, List<ListOtomotif> objects) {
        super(context,resource,objects);
        this.context = context;
        this.otomotif = objects;
    }



    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.otomotif_adapter, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.mImage);
        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);
        TextView textAlamat = (TextView) rowView.findViewById(R.id.mAlamat);
        TextView textRef = rowView.findViewById(R.id.mRefNama);



        URL url = null;
        if(otomotif.get(pos).getFile_small().equals(""))
        {
            //tidak terjadi perubahan apapun
        }
        else
        {
            try {
                url = new URL(otomotif.get(pos).getFile_small());
                Picasso.get()
                        .load(String.valueOf(url))
                        .resize(150,100).noFade().into(imageView);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        textNama.setText(otomotif.get(pos).getNama());
        textAlamat.setText(otomotif.get(pos).getAlamat());
        textRef.setText(otomotif.get(pos).getRef_otomotif_nama());

        return rowView;
    }
}
