package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvHasil = findViewById(R.id.textView3);

        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString("hasil");

        if (bundle != null) {
            tvHasil.setText(value);
        }
    }
}
