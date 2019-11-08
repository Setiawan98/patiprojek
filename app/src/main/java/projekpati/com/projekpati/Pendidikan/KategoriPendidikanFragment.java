package projekpati.com.projekpati.Pendidikan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.fragment.app.Fragment;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.Pendidikan.JenisPendidikan;
import projekpati.com.projekpati.Model.Pendidikan.JenisPendidikanLengkap;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriPendidikanFragment extends Fragment {
    GridView listPendidikan;
    List<JenisPendidikan> list = new ArrayList<>();

    public KategoriPendidikanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kategori_pendidikan, container, false);
        listPendidikan = (GridView) view.findViewById(R.id.listPendidikan);
        getAllJenis();
        return view;
    }

    public void getAllJenis(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<JenisPendidikanLengkap> call = api.tampilSemuaTingkat();

        call.enqueue(new Callback<JenisPendidikanLengkap>() {
            @Override
            public void onResponse(Call<JenisPendidikanLengkap> call, final Response<JenisPendidikanLengkap> response) {
                Map<String, JenisPendidikan> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }

                listPendidikan.setAdapter(new JenisPendidikanAdapter(getContext(), R.layout.jenis_pendidikan_adapter, list));
                Toast.makeText(getContext().getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<JenisPendidikanLengkap> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listPendidikan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),TampilPendidikanByJenis.class);
                intent.putExtra("id_pendidikan",list.get(position).getId());
                intent.putExtra("kategori",list.get(position).getNama());
                startActivity(intent);
            }
        });

    }
}
