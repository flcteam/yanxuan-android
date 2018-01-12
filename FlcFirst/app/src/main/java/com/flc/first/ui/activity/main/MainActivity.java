package com.flc.first.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.flc.first.R;
import com.flc.first.ui.activity.develop.DevelopActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        Intent intent = new Intent(this, DevelopActivity.class);
        startActivity(intent);
    }

}
