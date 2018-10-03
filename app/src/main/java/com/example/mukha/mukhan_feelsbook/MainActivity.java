package com.example.mukha.mukhan_feelsbook;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "emotionsFile.sav";
    private EditText commentTextField;
    private ListView oldEmotionsList;

    ArrayList<Emotions> emotionList = new ArrayList<Emotions>();
    ArrayAdapter emotionsArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.emotions_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(spinnerAdapter);




        commentTextField = (EditText) findViewById(R.id.commentText);
        Button insertButton = (Button) findViewById(R.id.insert);

        oldEmotionsList = (ListView) findViewById(R.id.emotionsList);
        oldEmotionsList.setAdapter(emotionsArrayAdapter);
        registerForContextMenu(oldEmotionsList);
        public void onCreateContextMenu(final ContextMenu menu,
        final View v, final ContextMenu.ContextMenuInfo menuInfo) {
 ...
        }
        insertButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                String comment = commentTextField.getText().toString();
                String emotionType = spinner.getSelectedItem().toString();
                    try {
                        Emotions emotion = null;
                        switch (emotionType) {
                            case "Anger":
                                emotion = (Anger) new Anger(comment);
                                break;
                            case "Fear":
                                emotion = (Fear) new Fear(comment);
                                break;
                            case "Joy":
                                emotion = (Joy) new Joy(comment);
                                break;
                            case "Love":
                                emotion = (Love) new Love(comment);
                                break;
                            case "Sadness":
                                emotion = (Sadness) new Sadness(comment);
                                break;
                            case "Surprise":
                                emotion = (Surprise) new Surprise(comment);
                                break;
                        }
                        emotionList.add(emotion);
                        emotionsArrayAdapter.notifyDataSetChanged();
                        saveInFile();
                    } catch (commentTooLongException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
            }
        });

    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        emotionsArrayAdapter = new ArrayAdapter<Emotions>(this, android.R.layout.simple_list_item_1, emotionList);
        oldEmotionsList.setAdapter(emotionsArrayAdapter);
    }


    private void loadFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Emotions>>(){}.getType();
            emotionList = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            emotionList = new ArrayList<Emotions>();

        } catch (IOException e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void saveInFile() {
        try {

            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(emotionList, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    }

