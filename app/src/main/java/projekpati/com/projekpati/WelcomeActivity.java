package projekpati.com.projekpati;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.Polisi.ListPolisi;
import projekpati.com.projekpati.Model.Polisi.PolisiModel;
import projekpati.com.projekpati.Model.galeri_android.galeriModel;
import projekpati.com.projekpati.Model.galeri_android.listGaleri;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private PrefManager prefManager;
    Context context;
    String[] linkUrl = new String[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // membuat transparan notifikasi
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        setContentView(R.layout.activity_welcome);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);


        getGaleri();





    }
    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(true);
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // mengubah button lanjut 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View Layout = layoutInflater.inflate(layouts[position],container, false);
            Bitmap image1 = getBitmapFromURL(linkUrl[position]);
            BitmapDrawable drawable1 = new BitmapDrawable((image1));
            Layout.setBackgroundDrawable(drawable1);

           // View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(Layout);

            return Layout;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    public void getGaleri(){
        //defining a progress dialog to show while signing up
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<galeriModel> call = api.getGaleri();

        call.enqueue(new Callback<galeriModel>() {
            @Override
            public void onResponse(Call<galeriModel> call, final Response<galeriModel> response) {
                Map<String, listGaleri> data = response.body().getData();


                for(int i=1;i<=response.body().getJumlah_data();i++)
                {
                    linkUrl[i-1]= data.get(String.valueOf(i)).getGambar();
                }

                // layout xml slide 1 sampai 4
                // add few more layouts if you want


                layouts = new int[response.body().getJumlah_data()];
                for(int i=0;i<response.body().getJumlah_data();i++)
                {
                    layouts[i] = R.layout.slide1;
                }


                /*Layout_2 = getLayoutInflater().inflate(R.layout.slide2,null);
                Layout_3 = getLayoutInflater().inflate(R.layout.slide3,null);
                Layout_4 = getLayoutInflater().inflate(R.layout.slide4,null);*/

              /*  layouts = new int[]{
                        R.layout.slide1,
                        R.layout.slide2,
                        R.layout.slide3,
                        R.layout.slide4};*/


                // tombol dots (lingkaran kecil perpindahan slide)
                addBottomDots(0);

                // membuat transparan notifikasi
                changeStatusBarColor();

                myViewPagerAdapter = new MyViewPagerAdapter();
                viewPager.setAdapter(myViewPagerAdapter);
                viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

                btnSkip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        launchHomeScreen();
                    }
                });

                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // mengecek page terakhir (slide 4)
                        // jika activity home sudah tampil
                        int current = getItem(+1);
                        if (current < layouts.length) {
                            // move to next screen
                            viewPager.setCurrentItem(current);
                        } else {
                            launchHomeScreen();
                        }
                    }
                });

            }

            @Override
            public void onFailure(Call<galeriModel> call, Throwable t) {

                Log.d("onResponse", t.toString());
            }
        });

    }


    public Bitmap getBitmapFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
