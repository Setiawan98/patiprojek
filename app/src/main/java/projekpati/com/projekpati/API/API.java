package projekpati.com.projekpati.API;

import projekpati.com.projekpati.Model.APIKey;
import projekpati.com.projekpati.Model.FasilitasUmum.FasilitasUmumModel;
import projekpati.com.projekpati.Model.Kesehatan.DetilKesehatanBaru;
import projekpati.com.projekpati.Model.Kesehatan.DetilKesehatanModel;
import projekpati.com.projekpati.Model.Kesehatan.JenisKesehatanLengkap;
import projekpati.com.projekpati.Model.Kesehatan.KesehatanModel;
import projekpati.com.projekpati.Model.Kuliner.DetilKulinerBaru;
import projekpati.com.projekpati.Model.Kuliner.DetilKulinerModel;
import projekpati.com.projekpati.Model.Kuliner.JenisKulinerLengkap;
import projekpati.com.projekpati.Model.Kuliner.JenisMakananLengkap;
import projekpati.com.projekpati.Model.KomentarLengkap;
import projekpati.com.projekpati.Model.Kuliner.KulinerModel;
import projekpati.com.projekpati.Model.Pariwisata.DetilPariwisataBaru;
import projekpati.com.projekpati.Model.Pariwisata.DetilPariwisataModel;
import projekpati.com.projekpati.Model.Pariwisata.JenisPariwisataLengkap;
import projekpati.com.projekpati.Model.Pariwisata.PariwisataModel;
import projekpati.com.projekpati.Model.Pendidikan.DetilPendidikanBaru;
import projekpati.com.projekpati.Model.Pendidikan.DetilPendidikanModel;
import projekpati.com.projekpati.Model.Pendidikan.JenisPendidikanLengkap;
import projekpati.com.projekpati.Model.Pendidikan.PendidikanModel;
import projekpati.com.projekpati.Model.Tukang.DetilTukangBaru;
import projekpati.com.projekpati.Model.Tukang.DetilTukangModel;
import projekpati.com.projekpati.Model.Tukang.JenisTukangLengkap;
import projekpati.com.projekpati.Model.Tukang.TukangModel;
import projekpati.com.projekpati.Model.postKomentar;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    //Kuliner
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
             @Field("hari_6") String hari_6,
            @Field("ref_kuliner_id") String ref_kuliner_id
    );

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia")
    Call<postKomentar> addKomentar(@Query("dataID") String data_id,
                                   @Query("dataJenis") String dataJenis,
                                   @Field("nama") String nama,
                                   @Field("email") String email,
                                   @Field("telp") String telp,
                                   @Field("website") String website,
                                   @Field("isi") String isi,
                                   @Field("rating") String rating,
                                   @Field("userID") String userID);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=kuliner")
    Call<postKomentar> addKomentarBalas(@Query("dataID") String data_id,
                                     @Field("nama") String nama,
                                     @Field("email") String email,
                                     @Field("telp") String telp,
                                     @Field("website") String website,
                                     @Field("parentID") String parentID,
                                     @Field("isi") String isi,
                                     @Field("userID") String userID);




    ///Pendidikan
    @GET("pendidikan/data/?key=TechnoPhoriaIndonesia")
    Call<PendidikanModel> tampilSemuaPendidikan();


    @GET("pendidikan/data/{id}?key=TechnoPhoriaIndonesia")
    Call<PendidikanModel> loadMorePendidikan(@Path("id") String id);

    @GET("pendidikan/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisPendidikanLengkap> tampilSemuaTingkat();

    @GET("pendidikan/data?key=TechnoPhoriaIndonesia")
    Call<PendidikanModel> cariPendidikanByJenis(@Query("IDJenis") String keyword);

    @GET("pendidikan/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilPendidikanModel> detailPendidikan(@Path("id") String id);

    @GET("pendidikan/data?key=TechnoPhoriaIndonesia")
    Call<PendidikanModel> cariPendidikanByApi(@Query("cari") String keyword);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=pendidikan")
    Call<postKomentar> addKomentarBalasPendidikan(@Query("dataID") String data_id,
                                        @Field("nama") String nama,
                                        @Field("email") String email,
                                        @Field("telp") String telp,
                                        @Field("website") String website,
                                        @Field("parentID") String parentID,
                                        @Field("isi") String isi,
                                        @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=pendidikan")
    Call<KomentarLengkap> getKomentarPendidikan(@Query("dataID") String data_id);

    @GET("pendidikan/data/?key=TechnoPhoriaIndonesia")
    Call<PendidikanModel> cariPendidikanbyAPI(@Query("cari") String keyword);

    @FormUrlEncoded
    @POST("pendidikan/update?key=TechnoPhoriaIndonesia")
    Call<DetilPendidikanBaru> addDataPendidikan(
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("website") String website,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_pendidikan_id") String ref_pendidikan_id
    );


    //Tukang
    @GET("tukang/data/?key=TechnoPhoriaIndonesia")
    Call<TukangModel> tampilSemuaTukang();

    @GET("tukang/data/{id}?key=TechnoPhoriaIndonesia")
    Call<TukangModel> loadMoreTukang(@Path("id") String id);

    @GET("tukang/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisTukangLengkap> tampilJenisTukang();

    @GET("tukang/data?key=TechnoPhoriaIndonesia")
    Call<TukangModel> cariTukangByJenis(@Query("IDJenis") String keyword);

    @GET("tukang/data/?key=TechnoPhoriaIndonesia")
    Call<TukangModel> cariTukangbyAPI(@Query("cari") String keyword);

    @GET("tukang/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilTukangModel> detailTukang(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=tukang")
    Call<postKomentar> addKomentarBalasTukang(@Query("dataID") String data_id,
                                                  @Field("nama") String nama,
                                                  @Field("email") String email,
                                                  @Field("telp") String telp,
                                                  @Field("website") String website,
                                                  @Field("parentID") String parentID,
                                                  @Field("isi") String isi,
                                                  @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=tukang")
    Call<KomentarLengkap> getKomentarTukang(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("tukang/update?key=TechnoPhoriaIndonesia")
    Call<DetilTukangBaru> addDataTukang(
            @Field("nama") String nama,
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
            @Field("hari_6") String hari_6,
            @Field("user_id") String user_id,
            @Field("ref_tukang_id") String ref_tukang_id
    );

    //Pariwisata
    @GET("pariwisata/data/?key=TechnoPhoriaIndonesia")
    Call<PariwisataModel> tampilSemuaPariwisata();

    @GET("pariwisata/data/{id}?key=TechnoPhoriaIndonesia")
    Call<PariwisataModel> loadMorePariwisata(@Path("id") String id);

    @GET("pariwisata/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisPariwisataLengkap> tampilJenisPariwisata();

    @GET("pariwisata/data?key=TechnoPhoriaIndonesia")
    Call<PariwisataModel> cariPariwisataByJenis(@Query("IDJenis") String keyword);

    @GET("pariwisata/data/?key=TechnoPhoriaIndonesia")
    Call<PariwisataModel> cariPariwisatabyAPI(@Query("cari") String keyword);

    @GET("pariwisata/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilPariwisataModel> detailPariwisata(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=pariwisata")
    Call<postKomentar> addKomentarBalasPariwisata(@Query("dataID") String data_id,
                                              @Field("nama") String nama,
                                              @Field("email") String email,
                                              @Field("telp") String telp,
                                              @Field("website") String website,
                                              @Field("parentID") String parentID,
                                              @Field("isi") String isi,
                                              @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=pariwisata")
    Call<KomentarLengkap> getKomentarPariwisata(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("pariwisata/update?key=TechnoPhoriaIndonesia")
    Call<DetilPariwisataBaru> addDataPariwisata(
            @Field("nama") String nama,
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
            @Field("hari_6") String hari_6,
            @Field("user_id") String user_id,
            @Field("ref_pariwisata_id") String ref_pariwisata_id
    );

    //Kesehatan
    @GET("kesehatan/data/?key=TechnoPhoriaIndonesia")
    Call<KesehatanModel> tampilSemuaKesehatan();

    @GET("kesehatan/data/{id}?key=TechnoPhoriaIndonesia")
    Call<KesehatanModel> loadMoreKesehatan(@Path("id") String id);

    @GET("kesehatan/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisKesehatanLengkap> tampilJenisKesehatan();

    @GET("kesehatan/data?key=TechnoPhoriaIndonesia")
    Call<KesehatanModel> cariKesehatanByJenis(@Query("IDJenis") String keyword);

    @GET("kesehatan/data/?key=TechnoPhoriaIndonesia")
    Call<KesehatanModel> cariKesehatanbyAPI(@Query("cari") String keyword);

    @GET("kesehatan/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilKesehatanModel> detailKesehatan(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=kesehatan")
    Call<postKomentar> addKomentarBalasKesehatan(@Query("dataID") String data_id,
                                                  @Field("nama") String nama,
                                                  @Field("email") String email,
                                                  @Field("telp") String telp,
                                                  @Field("website") String website,
                                                  @Field("parentID") String parentID,
                                                  @Field("isi") String isi,
                                                  @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=kesehatan")
    Call<KomentarLengkap> getKomentarKesehatan(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("kesehatan/update?key=TechnoPhoriaIndonesia")
    Call<DetilKesehatanBaru> addDataKesehatan(
            @Field("nama") String nama,
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
            @Field("hari_6") String hari_6,
            @Field("user_id") String user_id,
            @Field("ref_kesehatan_id") String ref_kesehatan_id
    );

    //Fasilitas Umum
    @GET("lain_lain/data/?key=TechnoPhoriaIndonesia")
    Call<FasilitasUmumModel> tampilSemuaFasilitasUmum();

    @GET("lain_lain/data/{id}?key=TechnoPhoriaIndonesia")
    Call<FasilitasUmumModel> loadMoreFasilitasUmum(@Path("id") String id);

    @GET("lain_lain/jenis?key=TechnoPhoriaIndonesia")
    Call<FasilitasUmumModel> tampilJenisFasilitasUmum();

    @GET("lain_lain/data?key=TechnoPhoriaIndonesia")
    Call<FasilitasUmumModel> cariFasilitasUmumByJenis(@Query("IDJenis") String keyword);

    @GET("lain_lain/data/?key=TechnoPhoriaIndonesia")
    Call<FasilitasUmumModel> cariFasilitasUmumbyAPI(@Query("cari") String keyword);


}
