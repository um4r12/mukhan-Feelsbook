package com.example.mukha.mukhan_feelsbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Locale;

/**
 * Helps user modify an emotion by making changes to the emotion type,
 * date, and comment
 */
public class EditActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Intent intentIn = getIntent();

        // Finding the spinner that will be used to make changes to the dropdown of emotions
        // Linked to a string resource with static values
        final Spinner emotionDropDown = (Spinner) findViewById(R.id.emotionType);
        ArrayAdapter<CharSequence> emotionDropDownAdapter = ArrayAdapter
                .createFromResource(this, R.array.emotions_array,
                        android.R.layout.simple_spinner_item);
        emotionDropDownAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        emotionDropDown.setAdapter(emotionDropDownAdapter);
        // Sets the default value of the spinner to the feeling of the emotion object
        emotionDropDown.setSelection(getIndex(emotionDropDown, intentIn.getStringExtra("feeling")));
        final TextView dateText = findViewById(R.id.dateText);
        dateText.setText(intentIn.getStringExtra("date"));

        final TextView commentText = findViewById(R.id.commentText);
        commentText.setText(intentIn.getStringExtra("comment"));

        final Button updateButton = findViewById(R.id.updateButton);
        final String itemIndex = intentIn.getStringExtra("position");
        // Once the user has provided us the relevant updates, the onClick method is invoked
        // To create a new intent, that will be used to pass information back to the MainActivity
        // for the object to be modified
        updateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intentOut = new Intent();
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
                    dateFormat.parse(dateText.getText().toString());
                    intentOut.putExtra("date", dateText.getText().toString());
                    intentOut.putExtra("feeling", emotionDropDown.getSelectedItem().toString());
                    intentOut.putExtra("comment", commentText.getText().toString());
                    intentOut.putExtra("position", itemIndex);
                    setResult(RESULT_OK, intentOut);
                    finish();
                } catch (ParseException e) {
                        Toast.makeText(getApplicationContext(), "Unable to parse date. Enter valid entry.", Toast.LENGTH_SHORT).show();
                        finish();

                }



            }
        });


    }
    // Attempts to find the index of the spinner/dropdown where a particular feeling
    // is located
    private int getIndex(Spinner spinner, String feeling){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(feeling)){
                return i;
            }
        }

        return 0;
    }
}
