package projekpati.com.projekpati.Kuliner;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.JenisKuliner;
import projekpati.com.projekpati.Model.JenisKulinerLengkap;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class KategoriFragment extends Fragment {

    GridView listView;
    List<JenisKuliner> list = new ArrayList<>();

    public KategoriFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kategori, container, false);
        listView = (GridView) view.findViewById(R.id.listKuliner);
        getAllJenis();
        return view;
    }

    public void getAllJenis(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<JenisKulinerLengkap> call = api.tampilSemuaJenis();

        call.enqueue(new Callback<JenisKulinerLengkap>() {
            @Override
            public void onResponse(Call<JenisKulinerLengkap> call, final Response<JenisKulinerLengkap> response) {
                Map<String, JenisKuliner> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= 20; i++)
                {
                    if (data.get(String.valueOf(i)) == null) {
                        break;
                    } else {
                        list.add(data.get(String.valueOf(i)));
                    }
                }

                listView.setAdapter(new JenisAdapter(getContext(), R.layout.jenis_adapter, list));
                Toast.makeText(getContext().getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<JenisKulinerLengkap> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),TampilKulinerByJenis.class);
                intent.putExtra("kategori",list.get(position).getNama());
                startActivity(intent);
            }
        });
    }

}
