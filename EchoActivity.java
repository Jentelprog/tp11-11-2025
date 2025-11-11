package com.example.firstrecapafterds;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class EchoActivity extends AppCompatActivity {

    public static final String EXTRA_INPUT_TEXT  = "EXTRA_INPUT_TEXT";
    public static final String EXTRA_OUTPUT_TEXT = "EXTRA_OUTPUT_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_echo);

        TextView tvPreview = findViewById(R.id.tvPreview);
        Button btnReturn   = findViewById(R.id.btnReturn);
        Button btnCancel   = findViewById(R.id.btnCancel);
        Bundle dat = getIntent().getExtras();
        String msg = dat.getString(EXTRA_INPUT_TEXT);
        if (msg == null) msg = "";

        tvPreview.setText(msg);

        String finalMsg = msg;
        btnReturn.setOnClickListener(v -> {
            Intent data = new Intent();
            data.putExtra(EXTRA_OUTPUT_TEXT, finalMsg); // return as-is
            setResult(RESULT_OK, data);
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}
