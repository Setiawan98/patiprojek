package projekpati.com.projekpati.Kuliner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.core.graphics.PathParser;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.transform.Result;

import projekpati.com.projekpati.Model.KulinerModel;
import projekpati.com.projekpati.Model.ListKuliner;
import projekpati.com.projekpati.R;

public class KulinerAdapter extends ArrayAdapter<ListKuliner> {

    private Context context;
    private List<ListKuliner> kuliner;

    public KulinerAdapter(Context context, int resource, List<ListKuliner> objects) {
        super(context,resource,objects);
        this.context = context;
        this.kuliner = objects;
    }



    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.kuliner_adapter, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.mImage);
        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);
        TextView textAlamat = (TextView) rowView.findViewById(R.id.mAlamat);
        TextView textJam = (TextView) rowView.findViewById(R.id.mJamBuka);
        ImageView iconJam= (ImageView) rowView.findViewById(R.id.iconJam);
        TextView textRef = rowView.findViewById(R.id.mRefNama);



        URL url = null;
        if(kuliner.get(pos).getFile_small().equals(""))
        {
            //tidak terjadi perubahan apapun
        }
        else
        {
            try {
                url = new URL(kuliner.get(pos).getFile_small());
                Picasso.get()
                        .load(String.valueOf(url))
                        .resize(150,100).noFade().into(imageView);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        textNama.setText(kuliner.get(pos).getNama());
        textAlamat.setText(kuliner.get(pos).getAlamat());
        textJam.setText(kuliner.get(pos).getJam_buka());
        textRef.setText(kuliner.get(pos).getRef_kuliner_nama());
        if(textJam.getText().equals(""))
        {
            iconJam.setVisibility(View.INVISIBLE);
        }


        return rowView;
    }


}
