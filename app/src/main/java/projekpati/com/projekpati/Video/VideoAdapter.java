package projekpati.com.projekpati.Video;

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

import projekpati.com.projekpati.Model.Video.ListVideo;
import projekpati.com.projekpati.R;

public class VideoAdapter  extends ArrayAdapter<ListVideo> {

    private Context context;
    private List<ListVideo> Video;

    public VideoAdapter(Context context, int resource, List<ListVideo> objects) {
        super(context,resource,objects);
        this.context = context;
        this.Video = objects;
    }



    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.video_adapter, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.mImage);
        TextView textNama = (TextView) rowView.findViewById(R.id.mNama);
        TextView textAlamat = (TextView) rowView.findViewById(R.id.mDeskripsi);
        TextView textJam = (TextView) rowView.findViewById(R.id.mTime);
        ImageView iconJam= (ImageView) rowView.findViewById(R.id.iconJam);
        TextView textRef = rowView.findViewById(R.id.mRefNama);



        URL url = null;
        String linkGambar ="https://img.youtube.com/vi/"+Video.get(pos).getKode()+"/mqdefault.jpg";
        try {
            url = new URL(linkGambar);
            Picasso.get()
                    .load(String.valueOf(url))
                    .resize(150,100).noFade().into(imageView);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        textNama.setText(Video.get(pos).getNama());
        textAlamat.setText(Video.get(pos).getDeskripsi());
        textJam.setText(Video.get(pos).getTime());
        textRef.setText(Video.get(pos).getRef_video_nama());
        if(textJam.getText().equals(""))
        {
            iconJam.setVisibility(View.INVISIBLE);
        }


        return rowView;
    }


}
