package projekpati.com.projekpati.Kuliner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import projekpati.com.projekpati.Model.KomentarParent;
import projekpati.com.projekpati.R;

public class KomentarAdapter extends ArrayAdapter<KomentarParent> {
    private Context context;
    private List<KomentarParent> kuliner;

    public KomentarAdapter(Context context, int resource, List<KomentarParent> objects) {
        super(context,resource,objects);
        this.context = context;
        this.kuliner = objects;
    }

    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.komentar_adapter, parent, false);

        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);
        TextView textRating = rowView.findViewById(R.id.mRating);
        TextView textKomentar = rowView.findViewById(R.id.mKomentar);

        textRating.setText(kuliner.get(pos).getKomentar_rating());
        textKomentar.setText(kuliner.get(pos).getKomentar_isi());
        return rowView;
    }
}
