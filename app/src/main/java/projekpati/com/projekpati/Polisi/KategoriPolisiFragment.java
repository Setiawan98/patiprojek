package projekpati.com.projekpati.Polisi;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.Polisi.JenisPolisi;
import projekpati.com.projekpati.Model.Polisi.JenisPolisiLengkap;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class KategoriPolisiFragment extends Fragment {
    GridView listPolisi;
    List<JenisPolisi> list = new ArrayList<>();

    public KategoriPolisiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kategori_polisi, container, false);
        listPolisi = (GridView) view.findViewById(R.id.listPolisi);
        getAllJenis();
        return view;
    }
    public void getAllJenis(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<JenisPolisiLengkap> call = api.tampilJenisPolisi();

        call.enqueue(new Callback<JenisPolisiLengkap>() {
            @Override
            public void onResponse(Call<JenisPolisiLengkap> call, final Response<JenisPolisiLengkap> response) {
                Map<String, JenisPolisi> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }

                listPolisi.setAdapter(new JenisPolisiAdapter(getContext(), R.layout.jenis_polisi_adapter, list));
                Toast.makeText(getContext().getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<JenisPolisiLengkap> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

//        listPolisi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getContext(),TampilTukangByJenis.class);
//                intent.putExtra("id_polisi",list.get(position).getId());
//                intent.putExtra("kategori",list.get(position).getNama());
//                startActivity(intent);
//            }
//        });

    }

}
