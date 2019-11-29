package projekpati.com.projekpati.Otomotif;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projekpati.com.projekpati.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KategoriOtomotifFragment extends Fragment {


    public KategoriOtomotifFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kategori_otomotif, container, false);
    }

}
