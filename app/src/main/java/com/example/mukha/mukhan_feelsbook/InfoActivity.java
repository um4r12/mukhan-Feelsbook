package com.example.mukha.mukhan_feelsbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * The InfoActivity is used to display overall view of the counts of each emotion
 *
 */
public class InfoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent = getIntent();


        final TextView angerCount = findViewById(R.id.angerCount);
        angerCount.setText(intent.getStringExtra("angerTotal"));

        final TextView fearCount = findViewById(R.id.fearCount);
        fearCount.setText(intent.getStringExtra("fearTotal"));

        final TextView joyCount = findViewById(R.id.joyCount);
        joyCount.setText(intent.getStringExtra("joyTotal"));

        final TextView loveCount = findViewById(R.id.loveCount);
        loveCount.setText(intent.getStringExtra("loveTotal"));

        final TextView sadnessCount = findViewById(R.id.sadnessCount);
        sadnessCount.setText(intent.getStringExtra("sadnessTotal"));

        final TextView surpriseCount = findViewById(R.id.surpriseCount);
        surpriseCount.setText(intent.getStringExtra("surpriseTotal"));

    }
}