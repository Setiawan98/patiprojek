package projekpati.com.projekpati.API;

import projekpati.com.projekpati.Model.APIKey;
import projekpati.com.projekpati.Model.DetilKulinerBaru;
import projekpati.com.projekpati.Model.DetilKulinerModel;
import projekpati.com.projekpati.Model.JenisKulinerLengkap;
import projekpati.com.projekpati.Model.JenisMakananLengkap;
import projekpati.com.projekpati.Model.KomentarLengkap;
import projekpati.com.projekpati.Model.KomentarParent;
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

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=kuliner")
    Call<KomentarLengkap> getKomentar(@Query("dataID") String data_id);

    @GET("gkey")
    Call<APIKey> getAPIkey();

    @FormUrlEncoded
    @POST("kuliner/update?key=TechnoPhoriaIndonesia")
    Call<DetilKulinerBaru> addDataKuliner(
            @Field("nama") String nama,
             @Field("pemilik") String pemilik,
             @Field("telp") String telp,
             @Field("email") String email,
             @Field("website") String website,
             @Field("deskripsi") String deskripsi,
             @Field("latitude") String latitude,
             @Field("longitude") String longitude,
             @Field("hari_0") String hari_0,
             @Field("hari_1") String hari_1,
             @Field("hari_2") String hari_2,
             @Field("hari_3") String hari_3,
             @Field("hari_4") String hari_4,
             @Field("hari_5") String hari_5,
             @Field("hari_6") String hari_6
    );

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=kuliner")
    Call<KomentarParent> addKomentar(@Query("dataID") String data_id,
                                     @Field("nama") String nama,
                                     @Field("email") String email,
                                     @Field("telp") String telp,
                                     @Field("website") String website,
                                     @Field("isi") String isi,
                                     @Field("rating") String rating,
                                     @Field("userID") String userID);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=kuliner")
    Call<KomentarParent> addKomentarBalas(@Query("dataID") String data_id,
                                     @Field("nama") String nama,
                                     @Field("email") String email,
                                     @Field("telp") String telp,
                                     @Field("website") String website,
                                     @Field("parentID") String parentID,
                                     @Field("isi") String isi,
                                     @Field("userID") String userID);


}
