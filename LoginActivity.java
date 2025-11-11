package com.example.firstrecapafterds;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_USER_NAME = "EXTRA_USER_NAME";

    private EditText etName;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginRoot), (v, insets) -> {
        //    Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //    v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
        //    return insets;
        //});

        etName = findViewById(R.id.etName);
        btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(v -> {
            // Hide keyboard
            View current = getCurrentFocus();
            if (current != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(current.getWindowToken(), 0);
            }

            String name = etName.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(LoginActivity.this, "Please enter your name 💫", Toast.LENGTH_SHORT).show();
                return;
            }

            // Put into a Bundle and start MainActivity
            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_USER_NAME, name);

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });
    }
}
