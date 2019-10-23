package projekpati.com.projekpati.Kuliner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import projekpati.com.projekpati.Model.JenisMakanan;
import projekpati.com.projekpati.R;

public class JenisMakananAdapter extends ArrayAdapter<JenisMakanan> {
    private Context context;
    private List<JenisMakanan> kuliner;

    public JenisMakananAdapter(Context context, int resource, List<JenisMakanan> objects) {
        super(context,resource,objects);
        this.context = context;
        this.kuliner = objects;
    }

    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.jenis_makanan_adapter, parent, false);

        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);

        textNama.setText(kuliner.get(pos).getNama());

        return rowView;
    }
}
