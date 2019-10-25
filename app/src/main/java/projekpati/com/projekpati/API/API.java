package projekpati.com.projekpati.API;

import projekpati.com.projekpati.Model.APIKey;
import projekpati.com.projekpati.Model.DetilKulinerBaru;
import projekpati.com.projekpati.Model.DetilKulinerModel;
import projekpati.com.projekpati.Model.JenisKulinerLengkap;
import projekpati.com.projekpati.Model.JenisMakananLengkap;
import projekpati.com.projekpati.Model.KulinerModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
    @GET("kuliner/data/?key=TechnoPhoriaIndonesia")
    Call<KulinerModel> tampilSemuaKuliner();

    @GET(" kuliner/jenis/?key=TechnoPhoriaIndonesia")
    Call<JenisKulinerLengkap> tampilSemuaJenis();

    @GET("kuliner/data/?key=TechnoPhoriaIndonesia")
    Call<KulinerModel> cariKulinerByJenis(@Query("IDJenis") String keyword);

    @GET("kuliner/data/?key=TechnoPhoriaIndonesia")
    Call<KulinerModel> cariKulinerJenisMakanan(@Query("IDJenisMakanan") String keyword);

    @GET(" kuliner/jenis_makanan?key=TechnoPhoriaIndonesia")
    Call<JenisMakananLengkap> tampilJenisMakanan();

    @GET("kuliner/data/{id}?key=TechnoPhoriaIndonesia")
    Call<KulinerModel> loadMoreKuliner(@Path("id") String id);

    @GET("kuliner/data/?key=TechnoPhoriaIndonesia")
    Call<KulinerModel> cariKulinerbyAPI(@Query("cari") String keyword);

    @GET("kuliner/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilKulinerModel> detailKuliner(@Path("id") String id);

    @GET("gkey")
    Call<APIKey> getAPIkey();

    @FormUrlEncoded
    @POST("kuliner/update?key=TechnoPhoriaIndonesia")
    Call<DetilKulinerBaru> addDataKuliner(
            @Field("nama") String nama);


}
