package projekpati.com.projekpati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnKuliner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //meki jalang bernama Andrew


        btnKuliner = (Button) findViewById(R.id.btnKuliner);
        btnKuliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuKuliner.class);
                startActivity(i);
            }
        });

    }
}
