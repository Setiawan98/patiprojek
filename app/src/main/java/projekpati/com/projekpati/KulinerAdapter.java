package projekpati.com.projekpati;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.xml.transform.Result;

import projekpati.com.projekpati.Model.KulinerModel;
import projekpati.com.projekpati.Model.ListKuliner;

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

//        ListKuliner[] data = kuliner.get(pos).getData();
        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);
        TextView textAlamat = (TextView) rowView.findViewById(R.id.mAlamat);
        TextView textJam = (TextView) rowView.findViewById(R.id.mJamBuka);

        textNama.setText(kuliner.get(pos).getNama());
        textAlamat.setText(kuliner.get(pos).getAlamat());
       textJam.setText(kuliner.get(pos).getJam_buka());


        return rowView;
    }


}
