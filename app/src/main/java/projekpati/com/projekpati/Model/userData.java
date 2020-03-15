package projekpati.com.projekpati.Model;

public class userData {

    String user_id, user_nama, user_namalogin, user_password,
            user_email, user_telp, user_website, user_kode,
            user_last_login, user_waktu_register, user_aktif,
            lapor_user_id;


    public userData(String user_id, String user_nama, String user_namalogin, String user_password, String user_email, String user_telp, String user_website, String user_kode, String user_last_login, String user_waktu_register, String user_aktif, String lapor_user_id) {
        this.user_id = user_id;
        this.user_nama = user_nama;
        this.user_namalogin = user_namalogin;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_telp = user_telp;
        this.user_website = user_website;
        this.user_kode = user_kode;
        this.user_last_login = user_last_login;
        this.user_waktu_register = user_waktu_register;
        this.user_aktif = user_aktif;
        this.lapor_user_id = lapor_user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_nama(String user_nama) {
        this.user_nama = user_nama;
    }

    public void setUser_namalogin(String user_namalogin) {
        this.user_namalogin = user_namalogin;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setUser_telp(String user_telp) {
        this.user_telp = user_telp;
    }

    public void setUser_website(String user_website) {
        this.user_website = user_website;
    }

    public void setUser_kode(String user_kode) {
        this.user_kode = user_kode;
    }

    public void setUser_last_login(String user_last_login) {
        this.user_last_login = user_last_login;
    }

    public void setUser_waktu_register(String user_waktu_register) {
        this.user_waktu_register = user_waktu_register;
    }

    public void setUser_aktif(String user_aktif) {
        this.user_aktif = user_aktif;
    }

    public void setLapor_user_id(String lapor_user_id) {
        this.lapor_user_id = lapor_user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_nama() {
        return user_nama;
    }

    public String getUser_namalogin() {
        return user_namalogin;
    }

    public String getUser_password() {
        return user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_telp() {
        return user_telp;
    }

    public String getUser_website() {
        return user_website;
    }

    public String getUser_kode() {
        return user_kode;
    }

    public String getUser_last_login() {
        return user_last_login;
    }

    public String getUser_waktu_register() {
        return user_waktu_register;
    }

    public String getUser_aktif() {
        return user_aktif;
    }

    public String getLapor_user_id() {
        return lapor_user_id;
    }
}
