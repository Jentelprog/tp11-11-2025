package com.example.firstrecapafterds;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int RC_ECHO = 1001;

    private TextView tvTitle;
    private EditText etMessage;
    private Button btnSend;
    private ListView listMessages;

    private ArrayList<String> messages;
    private ArrayAdapter<String> adapter;

    private static final String STATE_MESSAGES = "STATE_MESSAGES";
    private String helloName = "there";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        listMessages = findViewById(R.id.listMessages);

        // Get name from Login
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String maybeName = extras.getString(LoginActivity.EXTRA_USER_NAME);
            if (maybeName != null && !maybeName.trim().isEmpty()) helloName = maybeName.trim();
        }
        tvTitle.setText("Mascot Chat");

        // Restore or init messages
        if (savedInstanceState != null) {
            messages = savedInstanceState.getStringArrayList(STATE_MESSAGES);
        }
        if (messages == null) {
            messages = new ArrayList<>();
            messages.add("Hello, " + helloName + " 👋");
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);
        listMessages.setAdapter(adapter);

        btnSend.setOnClickListener(v -> {
            String text = etMessage.getText().toString().trim();
            if (text.isEmpty()) return;

            // Launch EchoActivity and wait for result
            Intent intent = new Intent(MainActivity.this, EchoActivity.class);
            intent.putExtra(EchoActivity.EXTRA_INPUT_TEXT, text);
            startActivityForResult(intent, RC_ECHO);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_ECHO && resultCode == RESULT_OK && data != null) {
            String returned = data.getStringExtra(EchoActivity.EXTRA_OUTPUT_TEXT);
            if (returned != null && !returned.isEmpty()) {
                messages.add("You: " + returned);  // show it as-is (with a label)
                adapter.notifyDataSetChanged();
                listMessages.post(() -> listMessages.setSelection(adapter.getCount() - 1));
                etMessage.setText(""); // clear input
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(STATE_MESSAGES, messages);
    }
}
