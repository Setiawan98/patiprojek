package projekpati.com.projekpati.API;

import java.util.List;

import projekpati.com.projekpati.DetilKuliner;
import projekpati.com.projekpati.Model.DetilKulinerModel;
import projekpati.com.projekpati.Model.KulinerModel;
import projekpati.com.projekpati.Model.ListKuliner;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
    @GET("kuliner/data/?key=TechnoPhoriaIndonesia")
    Call<KulinerModel> tampilSemuaKuliner();

    @GET("kuliner/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilKulinerModel> detailKuliner(@Path("id") String id);



}
