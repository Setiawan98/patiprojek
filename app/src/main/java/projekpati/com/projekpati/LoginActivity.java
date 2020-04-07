package projekpati.com.projekpati;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.API.RetrofitClientInstanceDemo;
import projekpati.com.projekpati.Kuliner.MenuKuliner;
import projekpati.com.projekpati.Model.Pendidikan.DetilPendidikanBaru;
import projekpati.com.projekpati.Model.userData;
import projekpati.com.projekpati.Model.userDataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    Button signin;
    int RC_SIGN_IN=0;
    GoogleSignInClient mGoogleSignInClient;
    Toolbar toolbar;
    TextView title;
    EditText mUsername, mSandi;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin = findViewById(R.id.sign_in_button);
        toolbar = (Toolbar) findViewById(R.id.kulinerToolbar);
        setSupportActionBar(toolbar);
        title = toolbar.findViewById(R.id.title);
        title.setTextColor(0xFFFFFFFF);
        title.setText("Login");

        mUsername = findViewById(R.id.mUsername);
        mSandi = findViewById(R.id.mSandi);
        sharedPreferences = getSharedPreferences("userData",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();



        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }


    public void signIn()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstanceDemo.getRetrofitInstance().create(API.class);
        Call<userDataModel> call = api.login(mUsername.getText().toString(),mSandi.getText().toString());

        call.enqueue(new Callback<userDataModel>() {
            @Override
            public void onResponse(Call<userDataModel> call, final Response<userDataModel> response) {

                Log.w("ResponseLogin", new Gson().toJson(response.body()));
                if(response.body().getStatus().equals("OK"))
                {
                    myEdit.putString("user_id",response.body().getData().getUser_id());
                    myEdit.putString("user_nama",response.body().getData().getUser_nama());
                    myEdit.putString("user_namalogin",response.body().getData().getUser_namalogin());
                    myEdit.putString("user_password",response.body().getData().getUser_password());
                    myEdit.putString("user_email",response.body().getData().getUser_email());
                    myEdit.putString("user_telp",response.body().getData().getUser_telp());
                    myEdit.putString("user_website",response.body().getData().getUser_website());
                    myEdit.putString("user_kode",response.body().getData().getUser_kode());
                    myEdit.putString("user_last_login",response.body().getData().getUser_last_login());
                    myEdit.putString("user_waktu_register",response.body().getData().getUser_waktu_register());
                    myEdit.putString("user_aktif",response.body().getData().getUser_aktif());
                    myEdit.putString("lapor_user_id",response.body().getData().getLapor_user_id());
                    myEdit.commit();


                    Toast.makeText(LoginActivity.this,response.body().getPesan(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(LoginActivity.this,response.body().getPesan(),Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<userDataModel> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("onResponse", t.toString());
                Toast.makeText(LoginActivity.this, "Login gagal.. ",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
