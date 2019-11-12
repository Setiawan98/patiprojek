package projekpati.com.projekpati.API;

import projekpati.com.projekpati.Bank.BankAdapter;
import projekpati.com.projekpati.Model.APIKey;
import projekpati.com.projekpati.Model.Bank.BankModel;
import projekpati.com.projekpati.Model.Bank.DetilBankBaru;
import projekpati.com.projekpati.Model.Bank.DetilBankModel;
import projekpati.com.projekpati.Model.Bioskop.BioskopModel;
import projekpati.com.projekpati.Model.Bioskop.DetilBioskopBaru;
import projekpati.com.projekpati.Model.Bioskop.DetilBioskopModel;
import projekpati.com.projekpati.Model.FasilitasUmum.FasilitasUmumModel;
import projekpati.com.projekpati.Model.Hotel.DetilHotelBaru;
import projekpati.com.projekpati.Model.Hotel.DetilHotelModel;
import projekpati.com.projekpati.Model.Hotel.HotelModel;
import projekpati.com.projekpati.Model.Kesehatan.DetilKesehatanBaru;
import projekpati.com.projekpati.Model.Kesehatan.DetilKesehatanModel;
import projekpati.com.projekpati.Model.Kesehatan.JenisKesehatanLengkap;
import projekpati.com.projekpati.Model.Kesehatan.KesehatanModel;
import projekpati.com.projekpati.Model.Koperasi.DetilKoperasiBaru;
import projekpati.com.projekpati.Model.Koperasi.DetilKoperasiModel;
import projekpati.com.projekpati.Model.Koperasi.KoperasiModel;
import projekpati.com.projekpati.Model.Kuliner.DetilKulinerBaru;
import projekpati.com.projekpati.Model.Kuliner.DetilKulinerModel;
import projekpati.com.projekpati.Model.Kuliner.JenisKulinerLengkap;
import projekpati.com.projekpati.Model.Kuliner.JenisMakananLengkap;
import projekpati.com.projekpati.Model.KomentarLengkap;
import projekpati.com.projekpati.Model.Kuliner.KulinerModel;
import projekpati.com.projekpati.Model.Olahraga.DetilOlahragaBaru;
import projekpati.com.projekpati.Model.Olahraga.DetilOlahragaModel;
import projekpati.com.projekpati.Model.Olahraga.JenisOlahragaLengkap;
import projekpati.com.projekpati.Model.Olahraga.OlahragaModel;
import projekpati.com.projekpati.Model.Pariwisata.DetilPariwisataBaru;
import projekpati.com.projekpati.Model.Pariwisata.DetilPariwisataModel;
import projekpati.com.projekpati.Model.Pariwisata.JenisPariwisataLengkap;
import projekpati.com.projekpati.Model.Pariwisata.PariwisataModel;
import projekpati.com.projekpati.Model.Pendidikan.DetilPendidikanBaru;
import projekpati.com.projekpati.Model.Pendidikan.DetilPendidikanModel;
import projekpati.com.projekpati.Model.Pendidikan.JenisPendidikanLengkap;
import projekpati.com.projekpati.Model.Pendidikan.PendidikanModel;
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

    //Bank
    @GET("bank/data/?key=TechnoPhoriaIndonesia")
    Call<BankModel> tampilSemuaBank();

    @GET("bank/data/{id}?key=TechnoPhoriaIndonesia")
    Call<BankModel> loadMoreBank(@Path("id") String id);

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

    @GET("spbu/jenis?key=TechnoPhoriaIndonesia")
    Call<JenisSpbuLengkap> tampilJenisSpbu();

    @GET("spbu/data?key=TechnoPhoriaIndonesia")
    Call<SpbuModel> cariSpbuByJenis(@Query("IDJenis") String keyword);
}
