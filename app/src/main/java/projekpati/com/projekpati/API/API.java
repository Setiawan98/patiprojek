package projekpati.com.projekpati.API;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import projekpati.com.projekpati.Bank.BankAdapter;
import projekpati.com.projekpati.Model.APIKey;
import projekpati.com.projekpati.Model.Agenda.AgendaModel;
import projekpati.com.projekpati.Model.Agenda.DetilAgendaBaru;
import projekpati.com.projekpati.Model.Agenda.DetilAgendaModel;
import projekpati.com.projekpati.Model.Agenda.JenisAgendaLengkap;
import projekpati.com.projekpati.Model.Aspirasi.AspirasiModel;
import projekpati.com.projekpati.Model.Aspirasi.DetilAspirasiBaru;
import projekpati.com.projekpati.Model.Aspirasi.DetilAspirasiModel;
import projekpati.com.projekpati.Model.Aspirasi.JenisAspirasiLengkap;
import projekpati.com.projekpati.Model.Bank.BankModel;
import projekpati.com.projekpati.Model.Bank.DetilBankBaru;
import projekpati.com.projekpati.Model.Bank.DetilBankModel;
import projekpati.com.projekpati.Model.Bank.JenisBankLengkap;
import projekpati.com.projekpati.Model.BeritaCetak.BeritaCetakModel;
import projekpati.com.projekpati.Model.BeritaCetak.DetilBeritaCetakModel;
import projekpati.com.projekpati.Model.BeritaCetak.JenisBeritaCetakLengkap;
import projekpati.com.projekpati.Model.BeritaOnline.BeritaOnlineModel;
import projekpati.com.projekpati.Model.BeritaOnline.DetilBeritaOnlineBaru;
import projekpati.com.projekpati.Model.BeritaOnline.DetilBeritaOnlineModel;
import projekpati.com.projekpati.Model.BeritaOnline.JenisBeritaOnlineLengkap;
import projekpati.com.projekpati.Model.Bioskop.BioskopModel;
import projekpati.com.projekpati.Model.Bioskop.DetilBioskopBaru;
import projekpati.com.projekpati.Model.Bioskop.DetilBioskopModel;
import projekpati.com.projekpati.Model.Bioskop.JenisBioskopLengkap;
import projekpati.com.projekpati.Model.FasilitasUmum.FasilitasUmumModel;
import projekpati.com.projekpati.Model.Hotel.DetilHotelBaru;
import projekpati.com.projekpati.Model.Hotel.DetilHotelModel;
import projekpati.com.projekpati.Model.Hotel.HotelModel;
import projekpati.com.projekpati.Model.Hotel.JenisHotelLengkap;
import projekpati.com.projekpati.Model.Kerjaan.DetilKerjaanBaru;
import projekpati.com.projekpati.Model.Kerjaan.DetilKerjaanModel;
import projekpati.com.projekpati.Model.Kerjaan.JenisKerjaanLengkap;
import projekpati.com.projekpati.Model.Kerjaan.KerjaanModel;
import projekpati.com.projekpati.Model.Kesehatan.DetilKesehatanBaru;
import projekpati.com.projekpati.Model.Kesehatan.DetilKesehatanModel;
import projekpati.com.projekpati.Model.Kesehatan.JenisKesehatanLengkap;
import projekpati.com.projekpati.Model.Kesehatan.KesehatanModel;
import projekpati.com.projekpati.Model.KodePos.DetilKodePosModel;
import projekpati.com.projekpati.Model.KodePos.JenisKodePosLengkap;
import projekpati.com.projekpati.Model.KodePos.KodePosModel;
import projekpati.com.projekpati.Model.Koperasi.DetilKoperasiBaru;
import projekpati.com.projekpati.Model.Koperasi.DetilKoperasiModel;
import projekpati.com.projekpati.Model.Koperasi.JenisKoperasiLengkap;
import projekpati.com.projekpati.Model.Koperasi.KoperasiModel;
import projekpati.com.projekpati.Model.Kuliner.DetilKulinerBaru;
import projekpati.com.projekpati.Model.Kuliner.DetilKulinerModel;
import projekpati.com.projekpati.Model.Kuliner.JenisKulinerLengkap;
import projekpati.com.projekpati.Model.Kuliner.JenisMakananLengkap;
import projekpati.com.projekpati.Model.KomentarLengkap;
import projekpati.com.projekpati.Model.Kuliner.KulinerModel;
import projekpati.com.projekpati.Model.Lapak.DetilLapakBaru;
import projekpati.com.projekpati.Model.Lapak.DetilLapakModel;
import projekpati.com.projekpati.Model.Lapak.JenisLapakLengkap;
import projekpati.com.projekpati.Model.Lapak.LapakModel;
import projekpati.com.projekpati.Model.Olahraga.DetilOlahragaBaru;
import projekpati.com.projekpati.Model.Olahraga.DetilOlahragaModel;
import projekpati.com.projekpati.Model.Olahraga.JenisOlahragaLengkap;
import projekpati.com.projekpati.Model.Olahraga.OlahragaModel;
import projekpati.com.projekpati.Model.Otomotif.DetilOtomotifBaru;
import projekpati.com.projekpati.Model.Otomotif.DetilOtomotifModel;
import projekpati.com.projekpati.Model.Otomotif.JenisOtomotif;
import projekpati.com.projekpati.Model.Otomotif.JenisOtomotifLengkap;
import projekpati.com.projekpati.Model.Otomotif.OtomotifModel;
import projekpati.com.projekpati.Model.Pangan.JenisPanganLengkap;
import projekpati.com.projekpati.Model.Pangan.PanganModel;
import projekpati.com.projekpati.Model.Pariwisata.DetilPariwisataBaru;
import projekpati.com.projekpati.Model.Pariwisata.DetilPariwisataModel;
import projekpati.com.projekpati.Model.Pariwisata.JenisPariwisataLengkap;
import projekpati.com.projekpati.Model.Pariwisata.PariwisataModel;
import projekpati.com.projekpati.Model.Pendidikan.DetilPendidikanBaru;
import projekpati.com.projekpati.Model.Pendidikan.DetilPendidikanModel;
import projekpati.com.projekpati.Model.Pendidikan.JenisPendidikanLengkap;
import projekpati.com.projekpati.Model.Pendidikan.PendidikanModel;
import projekpati.com.projekpati.Model.PerangkatDaerah.DetilPerdaModel;
import projekpati.com.projekpati.Model.PerangkatDaerah.JenisPerdaLengkap;
import projekpati.com.projekpati.Model.PerangkatDaerah.PerdaModel;
import projekpati.com.projekpati.Model.Polisi.DetilPolisiBaru;
import projekpati.com.projekpati.Model.Polisi.DetilPolisiModel;
import projekpati.com.projekpati.Model.Polisi.JenisPolisiLengkap;
import projekpati.com.projekpati.Model.Polisi.PolisiModel;
import projekpati.com.projekpati.Model.Salon.DetilSalonBaru;
import projekpati.com.projekpati.Model.Salon.DetilSalonModel;
import projekpati.com.projekpati.Model.Salon.JenisSalonLengkap;
import projekpati.com.projekpati.Model.Salon.SalonModel;
import projekpati.com.projekpati.Model.Spbu.DetilSpbuBaru;
import projekpati.com.projekpati.Model.Spbu.DetilSpbuModel;
import projekpati.com.projekpati.Model.Spbu.JenisSpbuLengkap;
import projekpati.com.projekpati.Model.Spbu.SpbuModel;
import projekpati.com.projekpati.Model.TelpPenting.DetilTelpPentingModel;
import projekpati.com.projekpati.Model.TelpPenting.JenisTelpPentingLengkap;
import projekpati.com.projekpati.Model.TelpPenting.TelpPentingModel;
import projekpati.com.projekpati.Model.TempatIbadah.DetilIbadahBaru;
import projekpati.com.projekpati.Model.TempatIbadah.DetilIbadahModel;
import projekpati.com.projekpati.Model.TempatIbadah.IbadahModel;
import projekpati.com.projekpati.Model.TempatIbadah.JenisIbadahLengkap;
import projekpati.com.projekpati.Model.Tukang.DetilTukangBaru;
import projekpati.com.projekpati.Model.Tukang.DetilTukangModel;
import projekpati.com.projekpati.Model.Tukang.JenisTukangLengkap;
import projekpati.com.projekpati.Model.Tukang.TukangModel;
import projekpati.com.projekpati.Model.Video.VideoModel;
import projekpati.com.projekpati.Model.Video.jenisVideoLengkap;
import projekpati.com.projekpati.Model.galeri_android.galeriModel;
import projekpati.com.projekpati.Model.postKomentar;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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


    @Multipart
    @POST("kuliner/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilKulinerBaru> updateDataKulinerWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("pemilik") RequestBody pemilik,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("hari_0") RequestBody hari_0,
            @Part("hari_1") RequestBody hari_1,
            @Part("hari_2") RequestBody hari_2,
            @Part("hari_3") RequestBody hari_3,
            @Part("hari_4") RequestBody hari_4,
            @Part("hari_5") RequestBody hari_5,
            @Part("hari_6") RequestBody hari_6,
            @Part("ref_kuliner_id") RequestBody ref_kuliner_id
    );

    @Multipart
    @POST("kuliner/update?key=TechnoPhoriaIndonesia")
    Call<DetilKulinerBaru> addDataKulinerWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("pemilik") RequestBody pemilik,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("hari_0") RequestBody hari_0,
            @Part("hari_1") RequestBody hari_1,
            @Part("hari_2") RequestBody hari_2,
            @Part("hari_3") RequestBody hari_3,
            @Part("hari_4") RequestBody hari_4,
            @Part("hari_5") RequestBody hari_5,
            @Part("hari_6") RequestBody hari_6,
            @Part("ref_kuliner_id") RequestBody ref_kuliner_id
    );

    @GET("kuliner/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilKulinerModel> hapusGambarKuliner(@Path("id") String id);



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

    @Multipart
    @POST("pendidikan/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilPendidikanBaru> updateDataPendidikanWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_pendidikan_id") RequestBody ref_pendidikan_id
    );

    @Multipart
    @POST("pendidikan/update?key=TechnoPhoriaIndonesia")
    Call<DetilPendidikanBaru> addDataPendidikanWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_pendidikan_id") RequestBody ref_pendidikan_id
    );

    @GET("pendidikan/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilPendidikanModel> hapusGambar(@Path("id") String id);



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

    @Multipart
    @POST("tukang/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilTukangBaru> updateDataTukangWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("hari_0") RequestBody hari_0,
            @Part("hari_1") RequestBody hari_1,
            @Part("hari_2") RequestBody hari_2,
            @Part("hari_3") RequestBody hari_3,
            @Part("hari_4") RequestBody hari_4,
            @Part("hari_5") RequestBody hari_5,
            @Part("hari_6") RequestBody hari_6,
            @Part("user_id") RequestBody user_id,
            @Part("ref_tukang_id") RequestBody ref_tukang_id
    );

    @Multipart
    @POST("tukang/update?key=TechnoPhoriaIndonesia")
    Call<DetilTukangBaru> addDataTukangWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("hari_0") RequestBody hari_0,
            @Part("hari_1") RequestBody hari_1,
            @Part("hari_2") RequestBody hari_2,
            @Part("hari_3") RequestBody hari_3,
            @Part("hari_4") RequestBody hari_4,
            @Part("hari_5") RequestBody hari_5,
            @Part("hari_6") RequestBody hari_6,
            @Part("user_id") RequestBody user_id,
            @Part("ref_tukang_id") RequestBody ref_tukang_id
    );

    @GET("tukang/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilTukangModel> hapusGambarTukang(@Path("id") String id);

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

    @Multipart
    @POST("pariwisata/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilPariwisataBaru> updateDataPariwisataWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("hari_0") RequestBody hari_0,
            @Part("hari_1") RequestBody hari_1,
            @Part("hari_2") RequestBody hari_2,
            @Part("hari_3") RequestBody hari_3,
            @Part("hari_4") RequestBody hari_4,
            @Part("hari_5") RequestBody hari_5,
            @Part("hari_6") RequestBody hari_6,
            @Part("user_id") RequestBody user_id,
            @Part("ref_pariwisata_id") RequestBody ref_pariwisata_id
    );

    @Multipart
    @POST("pariwisata/update?key=TechnoPhoriaIndonesia")
    Call<DetilPariwisataBaru> addDataPariwisataWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("hari_0") RequestBody hari_0,
            @Part("hari_1") RequestBody hari_1,
            @Part("hari_2") RequestBody hari_2,
            @Part("hari_3") RequestBody hari_3,
            @Part("hari_4") RequestBody hari_4,
            @Part("hari_5") RequestBody hari_5,
            @Part("hari_6") RequestBody hari_6,
            @Part("user_id") RequestBody user_id,
            @Part("ref_pariwisata_id") RequestBody ref_pariwisata_id
    );

    @GET("pariwisata/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilPariwisataModel> hapusGambarPariwisata(@Path("id") String id);

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

    @Multipart
    @POST("kesehatan/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilKesehatanBaru> updateDataKesehatanWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("hari_0") RequestBody hari_0,
            @Part("hari_1") RequestBody hari_1,
            @Part("hari_2") RequestBody hari_2,
            @Part("hari_3") RequestBody hari_3,
            @Part("hari_4") RequestBody hari_4,
            @Part("hari_5") RequestBody hari_5,
            @Part("hari_6") RequestBody hari_6,
            @Part("user_id") RequestBody user_id,
            @Part("ref_kesehatan_id") RequestBody ref_kesehatan_id
    );

    @Multipart
    @POST("kesehatan/update?key=TechnoPhoriaIndonesia")
    Call<DetilKesehatanBaru> addDataKesehatanWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("hari_0") RequestBody hari_0,
            @Part("hari_1") RequestBody hari_1,
            @Part("hari_2") RequestBody hari_2,
            @Part("hari_3") RequestBody hari_3,
            @Part("hari_4") RequestBody hari_4,
            @Part("hari_5") RequestBody hari_5,
            @Part("hari_6") RequestBody hari_6,
            @Part("user_id") RequestBody user_id,
            @Part("ref_kesehatan_id") RequestBody ref_kesehatan_id
    );

    @GET("kesehatan/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilKesehatanModel> hapusGambarKesehatan(@Path("id") String id);

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

    //Bank
    @GET("bank/data/?key=TechnoPhoriaIndonesia")
    Call<BankModel> tampilSemuaBank();

    @GET("bank/data/{id}?key=TechnoPhoriaIndonesia")
    Call<BankModel> loadMoreBank(@Path("id") String id);

    @GET("bank/data?key=TechnoPhoriaIndonesia")
    Call<BankModel> cariBankByJenis(@Query("IDJenis") String keyword);

    @GET("bank/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisBankLengkap> tampilJenisBank();

    @GET("bank/data/?key=TechnoPhoriaIndonesia")
    Call<BankModel> cariBankbyAPI(@Query("cari") String keyword);

    @GET("bank/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilBankModel> detailBank(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=bank")
    Call<postKomentar> addKomentarBalasBank(@Query("dataID") String data_id,
                                                 @Field("nama") String nama,
                                                 @Field("email") String email,
                                                 @Field("telp") String telp,
                                                 @Field("website") String website,
                                                 @Field("parentID") String parentID,
                                                 @Field("isi") String isi,
                                                 @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=bank")
    Call<KomentarLengkap> getKomentarBank(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("bank/update?key=TechnoPhoriaIndonesia")
    Call<DetilBankBaru> addDataBank(
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("website") String website,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_bank_id") String ref_bank_id
    );

    @Multipart
    @POST("bank/update?key=TechnoPhoriaIndonesia")
    Call<DetilBankBaru> addDataBankWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_bank_id") RequestBody ref_bank_id
    );

    @Multipart
    @POST("bank/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilBankBaru> updateDataBankWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_bank_id") RequestBody ref_bank_id
    );
    @GET("bank/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilBankModel> hapusGambarBank(@Path("id") String id);

    //Bioskop
    @GET("bioskop/data/?key=TechnoPhoriaIndonesia")
    Call<BioskopModel> tampilSemuaBioskop();

    @GET("bioskop/data/{id}?key=TechnoPhoriaIndonesia")
    Call<BioskopModel> loadMoreBioskop(@Path("id") String id);

    @GET("bioskop/data/?key=TechnoPhoriaIndonesia")
    Call<BioskopModel> cariBioskopbyAPI(@Query("cari") String keyword);

    @GET("bioskop/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilBioskopModel> detailBioskop(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=bioskop")
    Call<postKomentar> addKomentarBalasBioskop(@Query("dataID") String data_id,
                                            @Field("nama") String nama,
                                            @Field("email") String email,
                                            @Field("telp") String telp,
                                            @Field("website") String website,
                                            @Field("parentID") String parentID,
                                            @Field("isi") String isi,
                                            @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=bioskop")
    Call<KomentarLengkap> getKomentarBioskop(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("bioskop/update?key=TechnoPhoriaIndonesia")
    Call<DetilBioskopBaru> addDataBioskop(
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("website") String website,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_bioskop_id") String ref_bioskop_id
    );

    @Multipart
    @POST("bioskop/update?key=TechnoPhoriaIndonesia")
    Call<DetilBioskopBaru> addDataBioskopWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_bioskop_id") RequestBody ref_bioskop_id
    );

    @Multipart
    @POST("bioskop/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilBioskopBaru> updateDataBioskopWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_bioskop_id") RequestBody ref_bioskop_id
    );

    @GET("bioskop/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilBioskopModel> hapusGambarBioskop(@Path("id") String id);


    @GET("bioskop/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisBioskopLengkap> tampilJenisBioskop();

    @GET("bioskop/data?key=TechnoPhoriaIndonesia")
    Call<BioskopModel> cariBioskopByJenis(@Query("IDJenis") String keyword);

    //Hotel
    @GET("hotel/data/?key=TechnoPhoriaIndonesia")
    Call<HotelModel> tampilSemuaHotel();

    @GET("hotel/data/{id}?key=TechnoPhoriaIndonesia")
    Call<HotelModel> loadMoreHotel(@Path("id") String id);

    @GET("hotel/data/?key=TechnoPhoriaIndonesia")
    Call<HotelModel> cariHotelbyAPI(@Query("cari") String keyword);

    @GET("hotel/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilHotelModel> detailHotel(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=hotel")
    Call<postKomentar> addKomentarBalasHotel(@Query("dataID") String data_id,
                                            @Field("nama") String nama,
                                            @Field("email") String email,
                                            @Field("telp") String telp,
                                            @Field("website") String website,
                                            @Field("parentID") String parentID,
                                            @Field("isi") String isi,
                                            @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=hotel")
    Call<KomentarLengkap> getKomentarHotel(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("hotel/update?key=TechnoPhoriaIndonesia")
    Call<DetilHotelBaru> addDataHotel(
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("website") String website,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_hotel_id") String ref_hotel_id
    );

    @Multipart
    @POST("hotel/update?key=TechnoPhoriaIndonesia")
    Call<DetilHotelBaru> addDataHotelWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_hotel_id") RequestBody ref_hotel_id
    );

    @Multipart
    @POST("hotel/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilHotelBaru> updateDataHotelWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_hotel_id") RequestBody ref_hotel_id
    );

    @GET("hotel/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilHotelModel> hapusGambarHotel(@Path("id") String id);

    @GET("hotel/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisHotelLengkap> tampilJenisHotel();

    @GET("hotel/data?key=TechnoPhoriaIndonesia")
    Call<HotelModel> cariHotelByJenis(@Query("IDJenis") String keyword);

    //Koperasi
    @GET("koperasi/data/?key=TechnoPhoriaIndonesia")
    Call<KoperasiModel> tampilSemuaKoperasi();

    @GET("koperasi/data/{id}?key=TechnoPhoriaIndonesia")
    Call<KoperasiModel> loadMoreKoperasi(@Path("id") String id);

    @GET("koperasi/data/?key=TechnoPhoriaIndonesia")
    Call<KoperasiModel> cariKoperasibyAPI(@Query("cari") String keyword);

    @GET("koperasi/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilKoperasiModel> detailKoperasi(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=koperasi")
    Call<postKomentar> addKomentarBalasKoperasi(@Query("dataID") String data_id,
                                            @Field("nama") String nama,
                                            @Field("email") String email,
                                            @Field("telp") String telp,
                                            @Field("website") String website,
                                            @Field("parentID") String parentID,
                                            @Field("isi") String isi,
                                            @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=koperasi")
    Call<KomentarLengkap> getKomentarKoperasi(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("koperasi/update?key=TechnoPhoriaIndonesia")
    Call<DetilKoperasiBaru> addDataKoperasi(
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("website") String website,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_koperasi_id") String ref_koperasi_id
    );

    @Multipart
    @POST("koperasi/update?key=TechnoPhoriaIndonesia")
    Call<DetilKoperasiBaru> addDataKoperasiWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_koperasi_id") RequestBody ref_koperasi_id
    );


    @Multipart
    @POST("koperasi/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilKoperasiBaru> updateDataKoperasiWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_koperasi_id") RequestBody ref_koperasi_id
    );
    @GET("koperasi/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilKoperasiModel> hapusGambarKoperasi(@Path("id") String id);

    @GET("koperasi/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisKoperasiLengkap> tampilJenisKoperasi();

    @GET("koperasi/data?key=TechnoPhoriaIndonesia")
    Call<KoperasiModel> cariKoperasiByJenis(@Query("IDJenis") String keyword);

    //Olahraga
    @GET("olahraga/data/?key=TechnoPhoriaIndonesia")
    Call<OlahragaModel> tampilSemuaOlahraga();

    @GET("olahraga/data/{id}?key=TechnoPhoriaIndonesia")
    Call<OlahragaModel> loadMoreOlahraga(@Path("id") String id);

    @GET("olahraga/data/?key=TechnoPhoriaIndonesia")
    Call<OlahragaModel> cariOlahragabyAPI(@Query("cari") String keyword);

    @GET("olahraga/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilOlahragaModel> detailOlahraga(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=olahraga")
    Call<postKomentar> addKomentarBalasOlahraga(@Query("dataID") String data_id,
                                                @Field("nama") String nama,
                                                @Field("email") String email,
                                                @Field("telp") String telp,
                                                @Field("website") String website,
                                                @Field("parentID") String parentID,
                                                @Field("isi") String isi,
                                                @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=olahraga")
    Call<KomentarLengkap> getKomentarOlahraga(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("olahraga/update?key=TechnoPhoriaIndonesia")
    Call<DetilOlahragaBaru> addDataOlahraga(
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("website") String website,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_olahraga_id") String ref_olahraga_id
    );

    @GET("olahraga/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisOlahragaLengkap> tampilJenisOlahraga();

    @GET("olahraga/data?key=TechnoPhoriaIndonesia")
    Call<OlahragaModel> cariOlahragaByJenis(@Query("IDJenis") String keyword);

    @Multipart
    @POST("olahraga/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilOlahragaBaru> updateDataOlahragaWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_olahraga_id") RequestBody ref_olahraga_id
    );

    @Multipart
    @POST("olahraga/update?key=TechnoPhoriaIndonesia")
    Call<DetilOlahragaBaru> addDataOlahragaWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_olahraga_id") RequestBody ref_olahraga_id
    );

    @GET("olahraga/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilOlahragaModel> hapusGambarOlahraga(@Path("id") String id);

    //Polisi
    @GET("polisi/data/?key=TechnoPhoriaIndonesia")
    Call<PolisiModel> tampilSemuaPolisi();

    @GET("polisi/data/{id}?key=TechnoPhoriaIndonesia")
    Call<PolisiModel> loadMorePolisi(@Path("id") String id);

    @GET("polisi/data/?key=TechnoPhoriaIndonesia")
    Call<PolisiModel> cariPolisibyAPI(@Query("cari") String keyword);

    @GET("polisi/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilPolisiModel> detailPolisi(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=polisi")
    Call<postKomentar> addKomentarBalasPolisi(@Query("dataID") String data_id,
                                                @Field("nama") String nama,
                                                @Field("email") String email,
                                                @Field("telp") String telp,
                                                @Field("website") String website,
                                                @Field("parentID") String parentID,
                                                @Field("isi") String isi,
                                                @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=polisi")
    Call<KomentarLengkap> getKomentarPolisi(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("polisi/update?key=TechnoPhoriaIndonesia")
    Call<DetilPolisiBaru> addDataPolisi(
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("website") String website,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_polisi_id") String ref_polisi_id
    );

    @GET("polisi/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisPolisiLengkap> tampilJenisPolisi();

    @GET("polisi/data?key=TechnoPhoriaIndonesia")
    Call<PolisiModel> cariPolisiByJenis(@Query("IDJenis") String keyword);

    @Multipart
    @POST("polisi/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilPolisiBaru> updateDataPolisiWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_polisi_id") RequestBody ref_polisi_id
    );

    @Multipart
    @POST("polisi/update?key=TechnoPhoriaIndonesia")
    Call<DetilPolisiBaru> addDataPolisiWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_polisi_id") RequestBody ref_polisi_id
    );

    @GET("polisi/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilPolisiModel> hapusGambarPolisi(@Path("id") String id);
    //Salon

    @GET("salon/data/?key=TechnoPhoriaIndonesia")
    Call<SalonModel> tampilSemuaSalon();

    @GET("salon/data/{id}?key=TechnoPhoriaIndonesia")
    Call<SalonModel> loadMoreSalon(@Path("id") String id);

    @GET("salon/data/?key=TechnoPhoriaIndonesia")
    Call<SalonModel> cariSalonbyAPI(@Query("cari") String keyword);

    @GET("salon/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilSalonModel> detailSalon(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=salon")
    Call<postKomentar> addKomentarBalasSalon(@Query("dataID") String data_id,
                                              @Field("nama") String nama,
                                              @Field("email") String email,
                                              @Field("telp") String telp,
                                              @Field("website") String website,
                                              @Field("parentID") String parentID,
                                              @Field("isi") String isi,
                                              @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=salon")
    Call<KomentarLengkap> getKomentarSalon(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("salon/update?key=TechnoPhoriaIndonesia")
    Call<DetilSalonBaru> addDataSalon(
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("website") String website,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_salon_id") String ref_salon_id
    );

    @GET("salon/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisSalonLengkap> tampilJenisSalon();

    @GET("salon/data?key=TechnoPhoriaIndonesia")
    Call<SalonModel> cariSalonByJenis(@Query("IDJenis") String keyword);

    @Multipart
    @POST("salon/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilSalonBaru> updateDataSalonWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_salon_id") RequestBody ref_salon_id
    );

    @Multipart
    @POST("salon/update?key=TechnoPhoriaIndonesia")
    Call<DetilSalonBaru> addDataSalonWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_salon_id") RequestBody ref_salon_id
    );

    @GET("salon/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilSalonModel> hapusGambarSalon(@Path("id") String id);

    //SPBU

    @GET("spbu/data/?key=TechnoPhoriaIndonesia")
    Call<SpbuModel> tampilSemuaSpbu();

    @GET("spbu/data/{id}?key=TechnoPhoriaIndonesia")
    Call<SpbuModel> loadMoreSpbu(@Path("id") String id);

    @GET("spbu/data/?key=TechnoPhoriaIndonesia")
    Call<SpbuModel> cariSpbubyAPI(@Query("cari") String keyword);

    @GET("spbu/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilSpbuModel> detailSpbu(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=spbu")
    Call<postKomentar> addKomentarBalasSpbu(@Query("dataID") String data_id,
                                             @Field("nama") String nama,
                                             @Field("email") String email,
                                             @Field("telp") String telp,
                                             @Field("website") String website,
                                             @Field("parentID") String parentID,
                                             @Field("isi") String isi,
                                             @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=spbu")
    Call<KomentarLengkap> getKomentarSpbu(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("spbu/update?key=TechnoPhoriaIndonesia")
    Call<DetilSpbuBaru> addDataSpbu(
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("website") String website,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_spbu_id") String ref_spbu_id
    );

    @Multipart
    @POST("spbu/update?key=TechnoPhoriaIndonesia")
    Call<DetilSpbuBaru> addDataSpbuWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_spbu_id") RequestBody ref_spbu_id
    );

    @Multipart
    @POST("spbu/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilSpbuBaru> updateDataSpbuWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_spbu_id") RequestBody ref_spbu_id
    );

    @GET("spbu/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilSpbuModel> hapusGambarSpbu(@Path("id") String id);



    @GET("spbu/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisSpbuLengkap> tampilJenisSpbu();

    @GET("spbu/data?key=TechnoPhoriaIndonesia")
    Call<SpbuModel> cariSpbuByJenis(@Query("IDJenis") String keyword);

    //Aspirasi
    @GET("aspirasi/data/?key=TechnoPhoriaIndonesia")
    Call<AspirasiModel> tampilSemuaAspirasi();

    @GET("aspirasi/data/{id}?key=TechnoPhoriaIndonesia")
    Call<AspirasiModel> loadMoreAspirasi(@Path("id") String id);

    @GET("aspirasi/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisAspirasiLengkap> tampilJenisAspirasi();

    @GET("aspirasi/data/?key=TechnoPhoriaIndonesia")
    Call<AspirasiModel> cariAspirasibyAPI(@Query("cari") String keyword);

    @GET("aspirasi/data?key=TechnoPhoriaIndonesia")
    Call<AspirasiModel> cariAspirasiByJenis(@Query("IDJenis") String keyword);

    @GET("aspirasi/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilAspirasiModel> detailAspirasi(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=aspirasi")
    Call<postKomentar> addKomentarBalasAspirasi(@Query("dataID") String data_id,
                                            @Field("nama") String nama,
                                            @Field("email") String email,
                                            @Field("telp") String telp,
                                            @Field("website") String website,
                                            @Field("parentID") String parentID,
                                            @Field("isi") String isi,
                                            @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=aspirasi")
    Call<KomentarLengkap> getKomentarAspirasi(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("aspirasi/update?key=TechnoPhoriaIndonesia")
    Call<DetilAspirasiBaru> addDataAspirasi(
            @Field("nama") String nama,
            @Field("deskripsi") String deskripsi,
            @Field("tgl") String tgl,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_aspirasi_id") String ref_spbu_id
    );

    @Multipart
    @POST("aspirasi/update?key=TechnoPhoriaIndonesia")
    Call<DetilAspirasiBaru> addDataAspirasiWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("tgl") RequestBody tgl,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_aspirasi_id") RequestBody ref_aspirasi_id
    );

    @Multipart
    @POST("aspirasi/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilAspirasiBaru> updateDataAspirasiWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("tgl") RequestBody tgl,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_aspirasi_id") RequestBody ref_aspirasi_id
    );
    @GET("aspirasi/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilAspirasiModel> hapusGambarAspirasi(@Path("id") String id);

    //Agenda
    @GET("agenda/data/?key=TechnoPhoriaIndonesia")
    Call<AgendaModel> tampilSemuaAgenda();

    @GET("agenda/data/{id}?key=TechnoPhoriaIndonesia")
    Call<AgendaModel> loadMoreAgenda(@Path("id") String id);

    @GET("agenda/data?key=TechnoPhoriaIndonesia")
    Call<AgendaModel> cariAgendaByJenis(@Query("IDJenis") String keyword);

    @GET("agenda/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisAgendaLengkap> tampilJenisAgenda();

    @GET("agenda/data/?key=TechnoPhoriaIndonesia")
    Call<AgendaModel> cariAgendabyAPI(@Query("cari") String keyword);

    @GET("agenda/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilAgendaModel> detailAgenda(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=agenda")
    Call<postKomentar> addKomentarBalasAgenda(@Query("dataID") String data_id,
                                              @Field("nama") String nama,
                                              @Field("email") String email,
                                              @Field("telp") String telp,
                                              @Field("website") String website,
                                              @Field("parentID") String parentID,
                                              @Field("isi") String isi,
                                              @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=agenda")
    Call<KomentarLengkap> getKomentarAgenda(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("agenda/update?key=TechnoPhoriaIndonesia")
    Call<DetilAgendaBaru> addDataAgenda(
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("tgl_mulai") String tgl_mulai,
            @Field("tgl_selesai") String tgl_selesai,
            @Field("harga_tiket") String harga_tiket,
            @Field("website") String website,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_agenda_id") String ref_agenda_id

    );

    //Lapak

    @GET("lapak/data/?key=TechnoPhoriaIndonesia")
    Call<LapakModel> tampilSemuaLapak();

    @GET("lapak/data/{id}?key=TechnoPhoriaIndonesia")
    Call<LapakModel> loadMoreLapak(@Path("id") String id);

    @GET("lapak/data/?key=TechnoPhoriaIndonesia")
    Call<LapakModel> cariLapakbyAPI(@Query("cari") String keyword);

    @GET("lapak/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilLapakModel> detailLapak(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=lapak")
    Call<postKomentar> addKomentarBalasLapak(@Query("dataID") String data_id,
                                            @Field("nama") String nama,
                                            @Field("email") String email,
                                            @Field("telp") String telp,
                                            @Field("website") String website,
                                            @Field("parentID") String parentID,
                                            @Field("isi") String isi,
                                            @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=lapak")
    Call<KomentarLengkap> getKomentarLapak(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("lapak/update?key=TechnoPhoriaIndonesia")
    Call<DetilLapakBaru> addDataLapak(
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("website") String website,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("barang") String barang,
            @Field("harga") String harga,
            @Field("user_id") String user_id,
            @Field("ref_lapak_id") String ref_lapak_id
    );

    @GET("lapak/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisLapakLengkap> tampilJenisLapak();

    @GET("lapak/data?key=TechnoPhoriaIndonesia")
    Call<LapakModel> cariLapakByJenis(@Query("IDJenis") String keyword);

    @Multipart
    @POST("lapak/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilLapakBaru> updateDataLapakWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("barang") RequestBody barang,
            @Part("harga") RequestBody harga,
            @Part("user_id") RequestBody user_id,
            @Part("ref_lapak_id") RequestBody ref_lapak_id
    );

    @Multipart
    @POST("lapak/update?key=TechnoPhoriaIndonesia")
    Call<DetilLapakBaru> addDataLapakWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("barang") RequestBody barang,
            @Part("harga") RequestBody harga,
            @Part("user_id") RequestBody user_id,
            @Part("ref_lapak_id") RequestBody ref_lapak_id
    );

    @GET("lapak/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilLapakModel> hapusGambarLapak(@Path("id") String id);

    //Otomotif

    @GET("otomotif/data/?key=TechnoPhoriaIndonesia")
    Call<OtomotifModel> tampilSemuaOtomotif();

    @GET("otomotif/data/{id}?key=TechnoPhoriaIndonesia")
    Call<OtomotifModel> loadMoreOtomotif(@Path("id") String id);

    @GET("otomotif/data/?key=TechnoPhoriaIndonesia")
    Call<OtomotifModel> cariOtomotifbyAPI(@Query("cari") String keyword);

    @GET("otomotif/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilOtomotifModel> detailOtomotif(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=otomotif")
    Call<postKomentar> addKomentarBalasOtomotif(@Query("dataID") String data_id,
                                             @Field("nama") String nama,
                                             @Field("email") String email,
                                             @Field("telp") String telp,
                                             @Field("website") String website,
                                             @Field("parentID") String parentID,
                                             @Field("isi") String isi,
                                             @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=otomotif")
    Call<KomentarLengkap> getKomentarOtomotif(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("otomotif/update?key=TechnoPhoriaIndonesia")
    Call<DetilOtomotifBaru> addDataOtomotif(
            @Field("nama") String nama,
            @Field("penjual") String penjual,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("website") String website,
            @Field("alamat") String alamat,
            @Field("harga") String harga,
            @Field("kondisi") String kondisi,
            @Field("model") String model,
            @Field("warna") String warna,
            @Field("kilometer") String kilometer,
            @Field("thn_pembuatan") String thn_pembuatan,
            @Field("transmisi") String transmisi,
            @Field("habis_terjual") String habis_terjual,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_otomotif_id") String ref_otomotif_id
    );

    @GET("otomotif/jenis/motor?key=TechnoPhoriaIndonesia")
    Call<JenisOtomotifLengkap> tampilJenisMotorOtomotif();

    @GET("otomotif/jenis/mobil?key=TechnoPhoriaIndonesia")
    Call<JenisOtomotifLengkap> tampilJenisMobilOtomotif();

    @GET("otomotif/data?key=TechnoPhoriaIndonesia")
    Call<OtomotifModel> cariOtomotifByJenis(@Query("IDJenis") String keyword);

    @Multipart
    @POST("otomotif/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilOtomotifBaru> updateDataOtomotifWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("penjual") RequestBody penjual,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("alamat") RequestBody alamat,
            @Part("harga") RequestBody harga,
            @Part("kondisi") RequestBody kondisi,
            @Part("model") RequestBody model,
            @Part("warna") RequestBody warna,
            @Part("kilometer") RequestBody kilometer,
            @Part("thn_pembuatan") RequestBody thn_pembuatan,
            @Part("transmisi") RequestBody transmisi,
            @Part("habis_terjual") RequestBody habis_terjual,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_otomotif_id") RequestBody ref_otomotif_id
    );

    @Multipart
    @POST("otomotif/update?key=TechnoPhoriaIndonesia")
    Call<DetilOtomotifBaru> addDataOtomotifWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("penjual") RequestBody penjual,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("alamat") RequestBody alamat,
            @Part("harga") RequestBody harga,
            @Part("kondisi") RequestBody kondisi,
            @Part("model") RequestBody model,
            @Part("warna") RequestBody warna,
            @Part("kilometer") RequestBody kilometer,
            @Part("thn_pembuatan") RequestBody thn_pembuatan,
            @Part("transmisi") RequestBody transmisi,
            @Part("habis_terjual") RequestBody habis_terjual,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_otomotif_id") RequestBody ref_otomotif_id
    );

    @GET("otomotif/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilOtomotifModel> hapusGambarOtomotif(@Path("id") String id);


    //Kerjaan
    @GET("kerjaan/data/?key=TechnoPhoriaIndonesia")
    Call<KerjaanModel> tampilSemuaKerjaan();

    @GET("kerjaan/data/{id}?key=TechnoPhoriaIndonesia")
    Call<KerjaanModel> loadMoreKerjaan(@Path("id") String id);

    @GET("kerjaan/data?key=TechnoPhoriaIndonesia")
    Call<KerjaanModel> cariKerjaanByJenis(@Query("IDJenis") String keyword);

    @GET("kerjaan/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisKerjaanLengkap> tampilJenisKerjaan();

    @GET("kerjaan/data/?key=TechnoPhoriaIndonesia")
    Call<KerjaanModel> cariKerjaanbyAPI(@Query("cari") String keyword);

    @GET("kerjaan/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilKerjaanModel> detailKerjaan(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=kerjaan")
    Call<postKomentar> addKomentarBalasKerjaan(@Query("dataID") String data_id,
                                               @Field("nama") String nama,
                                               @Field("email") String email,
                                               @Field("telp") String telp,
                                               @Field("website") String website,
                                               @Field("parentID") String parentID,
                                               @Field("isi") String isi,
                                               @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=kerjaan")
    Call<KomentarLengkap> getKomentarKerjaan(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("kerjaan/update?key=TechnoPhoriaIndonesia")
    Call<DetilKerjaanBaru> addDataKerjaan(
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("kantor") String kantor,
            @Field("gaji") String gaji,
            @Field("pengalaman") String pengalaman,
            @Field("website") String website,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_kerjaan_id") String ref_kerjaan_id

    );

    @Multipart
    @POST("kerjaan/update?key=TechnoPhoriaIndonesia")
    Call<DetilKerjaanBaru> addDataKerjaanWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("kantor") RequestBody kantor,
            @Part("gaji") RequestBody gaji,
            @Part("pengalaman") RequestBody pengalaman,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_kerjaan_id") RequestBody ref_kerjaan_id
    );

    @Multipart
    @POST("kerjaan/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilKerjaanBaru> updateDataKerjaanWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("kantor") RequestBody kantor,
            @Part("gaji") RequestBody gaji,
            @Part("pengalaman") RequestBody pengalaman,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_kerjaan_id") RequestBody ref_kerjaan_id
    );

    @GET("kerjaan/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilKerjaanModel> hapusGambarKerjaan(@Path("id") String id);


    //gambar slider
    @GET("galeri_android/data?key=TechnoPhoriaIndonesia")
    Call<galeriModel> getGaleri();


    //BeritaOnline
    @GET("berita_online/data/?key=TechnoPhoriaIndonesia")
    Call<BeritaOnlineModel> tampilSemuaBeritaOnline();

    @GET("berita_online/data/{id}?key=TechnoPhoriaIndonesia")
    Call<BeritaOnlineModel> loadMoreBeritaOnline(@Path("id") String id);

    @GET("berita_online/data?key=TechnoPhoriaIndonesia")
    Call<BeritaOnlineModel> cariBeritaOnlineByJenis(@Query("IDJenis") String keyword);

    @GET("berita_online/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisBeritaOnlineLengkap> tampilJenisBeritaOnline();

    @GET("berita_online/data/?key=TechnoPhoriaIndonesia")
    Call<BeritaOnlineModel> cariBeritaOnlinebyAPI(@Query("cari") String keyword);

    @GET("berita_online/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilBeritaOnlineModel> detailBeritaOnline(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=berita_online")
    Call<postKomentar> addKomentarBalasBeritaOnline(@Query("dataID") String data_id,
                                                    @Field("nama") String nama,
                                                    @Field("email") String email,
                                                    @Field("telp") String telp,
                                                    @Field("website") String website,
                                                    @Field("parentID") String parentID,
                                                    @Field("isi") String isi,
                                                    @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=berita_online")
    Call<KomentarLengkap> getKomentarBeritaOnline(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("berita_online/update?key=TechnoPhoriaIndonesia")
    Call<DetilBeritaOnlineBaru> addDataBeritaOnline(
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("website") String website,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_berita_online_id") String ref_berita_online_id
    );

    //Berita Cetak
    @GET("berita_cetak/data/?key=TechnoPhoriaIndonesia")
    Call<BeritaCetakModel> tampilSemuaBeritaCetak();

    @GET("berita_cetak/data/{id}?key=TechnoPhoriaIndonesia")
    Call<BeritaCetakModel> loadMoreBeritaCetak(@Path("id") String id);

    @GET("berita_cetak/data?key=TechnoPhoriaIndonesia")
    Call<BeritaCetakModel> cariBeritaCetakByJenis(@Query("IDJenis") String keyword);

    @GET("berita_cetak/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisBeritaCetakLengkap> tampilJenisBeritaCetak();

    @GET("berita_cetak/data/?key=TechnoPhoriaIndonesia")
    Call<BeritaCetakModel> cariBeritaCetakbyAPI(@Query("cari") String keyword);

    @GET("berita_cetak/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilBeritaCetakModel> detailBeritaCetak(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=berita_cetak")
    Call<postKomentar> addKomentarBalasBeritaCetak(@Query("dataID") String data_id,
                                                   @Field("nama") String nama,
                                                   @Field("email") String email,
                                                   @Field("telp") String telp,
                                                   @Field("website") String website,
                                                   @Field("parentID") String parentID,
                                                   @Field("isi") String isi,
                                                   @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=berita_cetak")
    Call<KomentarLengkap> getKomentarBeritaCetak
            (@Query("dataID") String data_id);

    //TelpPenting
    @GET("info/data/telp_penting?key=TechnoPhoriaIndonesia")
    Call<TelpPentingModel> tampilSemuaTelpPenting();

    @GET("info/data/telp_penting/{id}?key=TechnoPhoriaIndonesia")
    Call<TelpPentingModel> loadMoreTelpPenting(@Path("id") String id);

    @GET("info/data/telp_penting?key=TechnoPhoriaIndonesia")
    Call<TelpPentingModel> cariTelpPentingByJenis(@Query("IDJenis") String keyword);

    @GET("info/jenis/telp_penting?key=TechnoPhoriaIndonesia")
    Call<JenisTelpPentingLengkap> tampilJenisTelpPenting();

    @GET("info/data/telp_penting?key=TechnoPhoriaIndonesia")
    Call<TelpPentingModel> cariTelpPentingbyAPI(@Query("cari") String keyword);

    @GET("info/detail/telp_penting/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilTelpPentingModel> detailTelpPenting(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=telp_penting")
    Call<postKomentar> addKomentarBalasTelpPenting(@Query("dataID") String data_id,
                                                   @Field("nama") String nama,
                                                   @Field("email") String email,
                                                   @Field("telp") String telp,
                                                   @Field("website") String website,
                                                   @Field("parentID") String parentID,
                                                   @Field("isi") String isi,
                                                   @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=telp_penting")
    Call<KomentarLengkap> getKomentarTelpPenting
            (@Query("dataID") String data_id);


    //KodePos
    @GET("info/data/kode_pos?key=TechnoPhoriaIndonesia")
    Call<KodePosModel> tampilSemuaKodePos();

    @GET("info/data/kode_pos/{id}?key=TechnoPhoriaIndonesia")
    Call<KodePosModel> loadMoreKodePos(@Path("id") String id);

    @GET("info/data/kode_pos?key=TechnoPhoriaIndonesia")
    Call<KodePosModel> cariKodePosByJenis(@Query("IDJenis") String keyword);

    @GET("info/jenis/kode_pos?key=TechnoPhoriaIndonesia")
    Call<JenisKodePosLengkap> tampilJenisKodePos();

    @GET("info/data/kode_pos?key=TechnoPhoriaIndonesia")
    Call<KodePosModel> cariKodePosbyAPI(@Query("cari") String keyword);

    @GET("info/detail/kode_pos/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilKodePosModel> detailKodePos(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=kode_pos")
    Call<postKomentar> addKomentarBalasKodePos(@Query("dataID") String data_id,
                                               @Field("nama") String nama,
                                               @Field("email") String email,
                                               @Field("telp") String telp,
                                               @Field("website") String website,
                                               @Field("parentID") String parentID,
                                               @Field("isi") String isi,
                                               @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=kode_pos")
    Call<KomentarLengkap> getKomentarKodePos
            (@Query("dataID") String data_id);

    //PerangkatDaerah
    @GET("info/data/perangkat_daerah?key=TechnoPhoriaIndonesia")
    Call<PerdaModel> tampilSemuaPerda();

    @GET("info/data/perangkat_daerah/{id}?key=TechnoPhoriaIndonesia")
    Call<PerdaModel> loadMorePerda(@Path("id") String id);

    @GET("info/data/perangkat_daerah?key=TechnoPhoriaIndonesia")
    Call<PerdaModel> cariPerdaByJenis(@Query("IDJenis") String keyword);

    @GET("info/jenis/perangkat_daerah?key=TechnoPhoriaIndonesia")
    Call<JenisPerdaLengkap> tampilJenisPerda();

    @GET("info/data/perangkat_daerah?key=TechnoPhoriaIndonesia")
    Call<PerdaModel> cariPerdabyAPI(@Query("cari") String keyword);

    @GET("info/detail/perangkat_daerah/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilPerdaModel> detailPerda(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=perangkat_daerah")
    Call<postKomentar> addKomentarBalasPerda(@Query("dataID") String data_id,
                                               @Field("nama") String nama,
                                               @Field("email") String email,
                                               @Field("telp") String telp,
                                               @Field("website") String website,
                                               @Field("parentID") String parentID,
                                               @Field("isi") String isi,
                                               @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=perangkat_daerah")
    Call<KomentarLengkap> getKomentarPerda
            (@Query("dataID") String data_id);

    // Beli Mobil dan Motor
    @GET("otomotif/data?key=TechnoPhoriaIndonesia&IDJenisParent=motor")
    Call<OtomotifModel> tampilSemuaMotor();

    @GET("otomotif/data/{id}?key=TechnoPhoriaIndonesia&IDJenisParent=motor")
    Call<OtomotifModel> loadMoreMotor(@Path("id") String id);

    @GET("otomotif/data?key=TechnoPhoriaIndonesia&IDJenisParent=mobil")
    Call<OtomotifModel> tampilSemuaMobil();

    @GET("otomotif/data/{id}?key=TechnoPhoriaIndonesia&IDJenisParent=mobil")
    Call<OtomotifModel> loadMoreMobil(@Path("id") String id);

    //Video
    @GET("data/video?key=TechnoPhoriaIndonesia")
    Call<VideoModel> tampilSemuaVideo();

    @GET("data/video/{id}?key=TechnoPhoriaIndonesia")
    Call<VideoModel> loadMoreVideo(@Path("id") String id);

    @GET("data/video?key=TechnoPhoriaIndonesia")
    Call<VideoModel> cariVideobyAPI(@Query("cari") String keyword);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=video")
    Call<postKomentar> addKomentarBalasVideo(@Query("dataID") String data_id,
                                             @Field("nama") String nama,
                                             @Field("email") String email,
                                             @Field("telp") String telp,
                                             @Field("website") String website,
                                             @Field("parentID") String parentID,
                                             @Field("isi") String isi,
                                             @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=video")
    Call<KomentarLengkap> getKomentarVideo(@Query("dataID") String data_id);


    @GET("jenis/video?key=TechnoPhoriaIndonesia")
    Call<jenisVideoLengkap> tampilJenisVideo();

    @GET("data/video?key=TechnoPhoriaIndonesia")
    Call<VideoModel> cariVideoByJenis(@Query("IDJenis") String keyword);

    //Pangan
    @GET("data/harga_pangan?key=TechnoPhoriaIndonesia")
    Call<PanganModel> tampilSemuaPangan();

    @GET("data/harga_pangan/{id}?key=TechnoPhoriaIndonesia")
    Call<PanganModel> loadMorePangan(@Path("id") String id);

    @GET("data/harga_pangan?key=TechnoPhoriaIndonesia")
    Call<PanganModel> cariPanganbyAPI(@Query("cari") String keyword);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=Pangan")
    Call<postKomentar> addKomentarBalasPangan(@Query("dataID") String data_id,
                                              @Field("nama") String nama,
                                              @Field("email") String email,
                                              @Field("telp") String telp,
                                              @Field("website") String website,
                                              @Field("parentID") String parentID,
                                              @Field("isi") String isi,
                                              @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=Pangan")
    Call<KomentarLengkap> getKomentarPangan(@Query("dataID") String data_id);


    @GET("jenis/harga_pangan?key=TechnoPhoriaIndonesia")
    Call<JenisPanganLengkap> tampilJenisPangan();

    @GET("data/harga_pangan?key=TechnoPhoriaIndonesia")
    Call<PanganModel> cariPanganByJenis(@Query("IDJenis") String keyword);

    //Tempat Ibadah
    @GET("ibadah/data/?key=TechnoPhoriaIndonesia")
    Call<IbadahModel> tampilSemuaIbadah();

    @GET("ibadah/data/{id}?key=TechnoPhoriaIndonesia")
    Call<IbadahModel> loadMoreIbadah(@Path("id") String id);

    @GET("ibadah/data/?key=TechnoPhoriaIndonesia")
    Call<IbadahModel> cariIbadahbyAPI(@Query("cari") String keyword);

    @GET("ibadah/detail/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilIbadahModel> detailIbadah(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar/?key=TechnoPhoriaIndonesia&dataJenis=ibadah")
    Call<postKomentar> addKomentarBalasIbadah(@Query("dataID") String data_id,
                                              @Field("nama") String nama,
                                              @Field("email") String email,
                                              @Field("telp") String telp,
                                              @Field("website") String website,
                                              @Field("parentID") String parentID,
                                              @Field("isi") String isi,
                                              @Field("userID") String userID);

    @GET("komentar/get/?key=TechnoPhoriaIndonesia&dataJenis=ibadah")
    Call<KomentarLengkap> getKomentarIbadah(@Query("dataID") String data_id);

    @FormUrlEncoded
    @POST("ibadah/update?key=TechnoPhoriaIndonesia")
    Call<DetilIbadahBaru> addDataIbadah(
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("website") String website,
            @Field("deskripsi") String deskripsi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("user_id") String user_id,
            @Field("ref_ibadah_id") String ref_ibadah_id
    );

    @GET("ibadah/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisIbadahLengkap> tampilJenisIbadah();

    @GET("ibadah/data?key=TechnoPhoriaIndonesia")
    Call<IbadahModel> cariIbadahByJenis(@Query("IDJenis") String keyword);

    @Multipart
    @POST("ibadah/update/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilIbadahBaru> updateDataIbadahWithGambar(
            @Path("id") String id,
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_ibadah_id") RequestBody ref_ibadah_id
    );

    @Multipart
    @POST("ibadah/update?key=TechnoPhoriaIndonesia")
    Call<DetilIbadahBaru> addDataIbadahWithGambar(
            @Part MultipartBody.Part gambar,
            @Part MultipartBody.Part gambar2,
            @Part MultipartBody.Part gambar3,
            @Part MultipartBody.Part gambarutama,
            @Part("nama") RequestBody nama,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("website") RequestBody website,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("user_id") RequestBody user_id,
            @Part("ref_ibadah_id") RequestBody ref_ibadah_id
    );

    @GET("ibadah/hapusgambar/{id}?key=TechnoPhoriaIndonesia")
    Call<DetilIbadahModel> hapusGambarIbadah(@Path("id") String id);
}
