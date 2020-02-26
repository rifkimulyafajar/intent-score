package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.opengl.ETC1;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import id.putraprima.skorbola.model.Data;

public class MainActivity extends AppCompatActivity {

    private EditText inputHome;
    private EditText inputAway;
    private ImageView logoHome;
    private ImageView logoAway;

    private Uri uriImageHome;
    private Uri uriImageAway;
    private Bitmap bitmapHome;
    private Bitmap bitmapAway;

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE_HOME = 1;
    private static final int GALLERY_REQUEST_CODE_AWAY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputHome = findViewById(R.id.home_team);
        inputAway = findViewById(R.id.away_team);

        logoHome = findViewById(R.id.home_logo);
        logoAway = findViewById(R.id.away_logo);

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALLERY_REQUEST_CODE_HOME) {
            if (data != null) {
                try {
                    uriImageHome = data.getData();
                    bitmapHome = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriImageHome);
                    logoHome.setImageBitmap(bitmapHome);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        else if (requestCode == GALLERY_REQUEST_CODE_AWAY) {
            if (data != null) {
                try {
                    uriImageAway = data.getData();
                    bitmapAway = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriImageAway);
                    logoAway.setImageBitmap(bitmapAway);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void btnLogoHome(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE_HOME);
    }

    public void btnLogoAway(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE_AWAY);
    }

    public void btnNext(View view) {
        String home = inputHome.getText().toString();
        String away = inputAway.getText().toString();

        Intent intent = new Intent(this, MatchActivity.class);
        Data data = new Data(home, away, uriImageHome, uriImageAway);


        if (home.isEmpty()) {
            inputHome.setError("mohon diisi");
        }
        else if (away.isEmpty()) {
            inputAway.setError("mohon diisi");
        }
        else if (bitmapHome == null) {
            Toast.makeText(this, "kedua logo image mohon ditambah", Toast.LENGTH_SHORT).show();
        }
        else if (bitmapAway == null) {
            Toast.makeText(this, "kedua logo image mohon ditambah", Toast.LENGTH_SHORT).show();
        }
        else {
            intent.putExtra("key", data);
            startActivity(intent);
        }
    }
}
