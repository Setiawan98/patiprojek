package projekpati.com.projekpati.Kuliner;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.DetilKulinerBaru;
import projekpati.com.projekpati.Model.KulinerModel;
import projekpati.com.projekpati.Model.ListKuliner;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TambahFragment extends Fragment {


    public TambahFragment() {
        // Required empty public constructor
    }

    EditText Tnama,Talamat;
    Button btnTambah;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah, container, false);

        Tnama = view.findViewById(R.id.eNama);
       // Talamat = view.findViewById(R.id.mAlamat);
        btnTambah = view.findViewById(R.id.btnTambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addKuliner(Tnama.getText().toString());
            }
        });


        // Inflate the layout for this fragment
        return view;


    }


    public void addKuliner(String nama){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<DetilKulinerBaru> call = api.addDataKuliner(nama);

        call.enqueue(new Callback<DetilKulinerBaru>() {
            @Override
            public void onResponse(Call<DetilKulinerBaru> call, final Response<DetilKulinerBaru> response) {
                Toast.makeText(getContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<DetilKulinerBaru> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

    }

}
