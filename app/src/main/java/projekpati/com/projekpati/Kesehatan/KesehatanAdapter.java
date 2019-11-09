package projekpati.com.projekpati.Kesehatan;

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

import projekpati.com.projekpati.Model.Kesehatan.ListKesehatan;
import projekpati.com.projekpati.Model.Pendidikan.ListPendidikan;
import projekpati.com.projekpati.R;

public class KesehatanAdapter extends ArrayAdapter<ListKesehatan> {

    private Context context;
    private List<ListKesehatan> kesehatan;

    public KesehatanAdapter(Context context, int resource, List<ListKesehatan> objects) {
        super(context,resource,objects);
        this.context = context;
        this.kesehatan = objects;
    }



    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.kesehatan_adapter, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.mImage);
        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);
        TextView textAlamat = (TextView) rowView.findViewById(R.id.mAlamat);
        TextView textJam = (TextView) rowView.findViewById(R.id.mJamBuka);
        ImageView iconJam= (ImageView) rowView.findViewById(R.id.iconJam);
        TextView textRef = rowView.findViewById(R.id.mRefNama);



        URL url = null;
        if(kesehatan.get(pos).getFile_small().equals(""))
        {
            //tidak terjadi perubahan apapun
        }
        else
        {
            try {
                url = new URL(kesehatan.get(pos).getFile_small());
                Picasso.get()
                        .load(String.valueOf(url))
                        .resize(150,100).noFade().into(imageView);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        textNama.setText(kesehatan.get(pos).getNama());
        textAlamat.setText(kesehatan.get(pos).getAlamat());
        textJam.setText(kesehatan.get(pos).getJam_buka());
        textRef.setText(kesehatan.get(pos).getRef_kesehatan_nama());
        if(textJam.getText().equals(""))
        {
            iconJam.setVisibility(View.INVISIBLE);
        }


        return rowView;
    }


}
