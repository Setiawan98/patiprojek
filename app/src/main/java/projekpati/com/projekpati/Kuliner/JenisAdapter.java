package projekpati.com.projekpati.Kuliner;

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

import projekpati.com.projekpati.Model.JenisKuliner;
import projekpati.com.projekpati.R;

public class JenisAdapter  extends ArrayAdapter<JenisKuliner> {
    private Context context;
    private List<JenisKuliner> kuliner;

    public JenisAdapter(Context context, int resource, List<JenisKuliner> objects) {
        super(context,resource,objects);
        this.context = context;
        this.kuliner = objects;
    }

    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.jenis_adapter, parent, false);

        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);

        textNama.setText(kuliner.get(pos).getNama());

        return rowView;
    }

}
