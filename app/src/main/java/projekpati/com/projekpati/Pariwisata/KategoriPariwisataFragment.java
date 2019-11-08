package projekpati.com.projekpati.Pariwisata;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projekpati.com.projekpati.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KategoriPariwisataFragment extends Fragment {


    public KategoriPariwisataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kategori_pariwisata, container, false);
    }

}
