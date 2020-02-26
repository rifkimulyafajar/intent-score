package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import id.putraprima.skorbola.model.Data;

public class MatchActivity extends AppCompatActivity {

    private static final String HASILKEY = "hasil";

    private TextView tvHome;
    private TextView tvAway;
    private TextView tvSkorHome;
    private TextView tvSkorAway;
    private ImageView imgHomeLogo;
    private ImageView imgAwayLogo;

    private int scoreHome=0;
    private int scoreAway=0;

    private Bitmap bitmapHome;
    private Bitmap bitmapAway;

    private Uri uriHome;
    private Uri uriAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"

        tvHome = findViewById(R.id.txt_home);
        tvAway = findViewById(R.id.txt_away);

        tvSkorHome = findViewById(R.id.score_home);
        tvSkorAway = findViewById(R.id.score_away);

        imgHomeLogo = findViewById(R.id.home_logo);
        imgAwayLogo = findViewById(R.id.away_logo);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Data data = extras.getParcelable("key");

            tvHome.setText(data.getTeamHome());
            tvAway.setText(data.getTeamAway());
            uriHome = data.getLogoHomeUri();
            uriAway = data.getLogoAwayUri();

            try {
                bitmapHome = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriHome);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bitmapAway = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriAway);
            } catch (IOException e) {
                e.printStackTrace();
            }

            imgHomeLogo.setImageBitmap(bitmapHome);
            imgAwayLogo.setImageBitmap(bitmapAway);
        }
    }

    public void btn_ScoreHome(View view) {
        scoreHome++;
        tvSkorHome.setText(String.valueOf(scoreHome));
    }

    public void btn_ScoreAway(View view) {
        scoreAway++;
        tvSkorAway.setText(String.valueOf(scoreAway));
    }

    public void btn_cekHasil(View view) {
        String hasil;

        Intent intent = new Intent(this, ResultActivity.class);

        if (scoreHome > scoreAway) {
            hasil = tvHome.getText().toString() + " is winner";
        } else if (scoreHome < scoreAway) {
            hasil = tvAway.getText().toString() + " is winner";
        }
        else {
            hasil = "DRAW";
        }

        intent.putExtra(HASILKEY, hasil);
        startActivity(intent);
    }
}
