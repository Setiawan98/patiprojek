package projekpati.com.projekpati.Spbu;

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

import projekpati.com.projekpati.Model.Spbu.ListSpbu;
import projekpati.com.projekpati.R;

public class SpbuAdapter extends ArrayAdapter<ListSpbu> {
    private Context context;
    private List<ListSpbu> spbu;

    public SpbuAdapter(Context context, int resource, List<ListSpbu> objects) {
        super(context,resource,objects);
        this.context = context;
        this.spbu = objects;
    }



    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.spbu_adapter, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.mImage);
        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);
        TextView textAlamat = (TextView) rowView.findViewById(R.id.mAlamat);
        TextView textJam = (TextView) rowView.findViewById(R.id.mJamBuka);
        ImageView iconJam= (ImageView) rowView.findViewById(R.id.iconJam);
        TextView textRef = rowView.findViewById(R.id.mRefNama);



        URL url = null;
        if(spbu.get(pos).getFile_small().equals(""))
        {
            //tidak terjadi perubahan apapun
        }
        else
        {
            try {
                url = new URL(spbu.get(pos).getFile_small());
                Picasso.get()
                        .load(String.valueOf(url))
                        .resize(150,100).noFade().into(imageView);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        textNama.setText(spbu.get(pos).getNama());
        textAlamat.setText(spbu.get(pos).getAlamat());
        // textJam.setText(fasilitasUmum.get(pos).getJam_buka());
        textRef.setText(spbu.get(pos).getRef_spbu_nama());
        if(textJam.getText().equals(""))
        {
            iconJam.setVisibility(View.INVISIBLE);
        }


        return rowView;
    }
}
