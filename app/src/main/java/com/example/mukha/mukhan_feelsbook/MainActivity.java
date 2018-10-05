package com.example.mukha.mukhan_feelsbook;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Muhammad Khan
 *
 * This file is the main entry point for the Feelsbook application
 * It enables users to insert an emotion, with an optional comment
 * Users are able to edit the emotion and as well as delete them
 * from the history
 *
 */
public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "emotionsFile.sav";
    // The different possible emotional states
    private static final String ANGER = "Anger";
    private static final String FEAR = "Fear";
    private static final String JOY = "Joy";
    private static final String LOVE = "Love";
    private static final String SADNESS = "Sadness";
    private static final String SURPRISE = "Surprise";

    private EditText commentTextField;


    private ListView oldEmotionsList;

    ArrayList<Emotion> emotionList;
    EmotionListAdapter emotionsArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Changing the input mode so that keyboard popup does not interfere with input field
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);



        // Identifying the list view and making the connection with the UI
        oldEmotionsList = (ListView) findViewById(R.id.emotionsList);
        oldEmotionsList.setAdapter(emotionsArrayAdapter);

        commentTextField = (EditText) findViewById(R.id.commentText);

        // Identifying the different buttons that will be used to create
        // different emotional states
        final Button angerButton = (Button) findViewById(R.id.anger);
        final Button fearButton = (Button) findViewById(R.id.fear);
        final Button joyButton = (Button) findViewById(R.id.joy);
        final Button loveButton = (Button) findViewById(R.id.love);
        final Button sadnessButton = (Button) findViewById(R.id.sadness);
        final Button surpriseButton = (Button) findViewById(R.id.surprise);

        // Each button has an onClickListener that will execute once a user clicks on it
        // It creates the respective emotion
        angerButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);
                createEmotion(ANGER);
            }
        });
        fearButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);
                createEmotion(FEAR);

            }
        });
        joyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);
                createEmotion(JOY);

            }
        });
        loveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);
                createEmotion(LOVE);

            }
        });
        sadnessButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);
                createEmotion(SADNESS);

            }
        });
        surpriseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);
                createEmotion(SURPRISE);

            }
        });

        // Creating the info button that will be used to display the total counts of all emotions
        final FloatingActionButton infoStats = (FloatingActionButton)findViewById(R.id.infoStats);
        infoStats.setOnClickListener(new View.OnClickListener() {

            /**
             * This button will redirect the user to a new editActivity.
             * It will calculate the current total of each emotion and passes it
             * Into the editActivity for display.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                intent.putExtra("angerTotal", countEmotion(ANGER));
                intent.putExtra("fearTotal", countEmotion(FEAR));
                intent.putExtra("joyTotal", countEmotion(JOY));
                intent.putExtra("loveTotal", countEmotion(LOVE));
                intent.putExtra("sadnessTotal", countEmotion(SADNESS));
                intent.putExtra("surpriseTotal", countEmotion(SURPRISE));
                startActivity(intent);
            }
        });
        /**
         * Enabling a context menu to popup once an item on the list view is pressed
         */
        registerForContextMenu(oldEmotionsList);

    }
    /**
     * This links the context menu with the emotions list and provides the possible edit and delete
     * options which are defined in the menu_list.xml
     */

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.emotionsList) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }
    }

    /**
     * Responsible for identifying and executing the action selected by the user
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        // The index of the item that was clicked will be used to either delete or modify itself
        int itemIndex = info.position;
        Emotion emotion = emotionList.get(itemIndex);
        switch(item.getItemId()) {

            // Defines the case in which the user wants to edit the information of an emotion
            case R.id.edit:
                // Creates an intent to be used to pass the torch to the EditActivity
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                // The EditActivity requires information and this is being initialized
                intent.putExtra("date", emotion.getDate());
                intent.putExtra("feeling", emotion.getFeeling());
                intent.putExtra("comment", emotion.getComment());
                intent.putExtra("position", Integer.toString(itemIndex));
                // Waits for information to be returned by EditActivity
                startActivityForResult(intent, 1);
                return true;
            case R.id.delete:
                // Deletes the item from the list and notifies the adapter to update itself
                emotionList.remove(itemIndex);
                emotionsArrayAdapter.notifyDataSetChanged();
                saveInFile();
                toastMessage("Emotion deleted!");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    protected void onStart() {
        // Whenever an application is started, it loads the relevant information to be updated
        // and displayed
        super.onStart();
        loadFromFile();
        emotionsArrayAdapter = new EmotionListAdapter(this, R.layout.adapter_view_layout, emotionList);
        oldEmotionsList.setAdapter(emotionsArrayAdapter);
    }

    /**
     * Responsible for creating an emotion based off an emotionalSate
     * It adds the emotion to the list, sorts the list, and saves the changes to file
     * @param emotionType
     */
    private void createEmotion(String emotionType) {

        try {
            checkValidEmotion(emotionType);
            String comment = commentTextField.getText().toString();
            Emotion emotion = new Emotion(emotionType, comment);
            emotionList.add(emotion);
            sort();
            emotionsArrayAdapter.notifyDataSetChanged();
            saveInFile();
            toastMessage(emotionType + " emotion added!");
            commentTextField.setText("");
        } catch (Exception e) {
            toastMessage(e.getMessage());
        }

    }
    private void loadFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Emotion>>(){}.getType();
            emotionList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            emotionList = new ArrayList<Emotion>();
        }
    }

    /**
     * Only these emotions will be recognized, any other entries will result into an exception being thrown
     * @param emotionType
     * @throws emotionNotRecognized
     */
    private void checkValidEmotion(String emotionType) throws emotionNotRecognized {
        switch (emotionType) {
            case ANGER:
                break;
            case FEAR:
                break;
            case JOY:
                break;
            case LOVE:
                break;
            case SADNESS:
                break;
            case SURPRISE:
                break;
            default:
                throw new emotionNotRecognized();
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
    private void sort(){
        Collections.sort(emotionList, new Comparator<Emotion>(){
            public int compare(Emotion emotionOne, Emotion emotionTwo) {
                return emotionOne.getDate().compareToIgnoreCase(emotionTwo.getDate()); // To compare string values
            }
        });
    }

    /**
     * Takes an input of the emotion that you want to count, and
     * calculates the total number of that emotion in the arrayList
     * @param emotionToMatch
     * @return count
     */
    public String countEmotion(String emotionToMatch) {

        int count = 0;
        for (Emotion emotion: emotionList) {
            if (emotion.getFeeling().equals(emotionToMatch)) { // you will have to make this
                count++;

            }

        }


        return Integer.toString(count);

    }

    /**
     * After the EditActivity returns back the updated information, this method
     * is executed to make those changes be reflected in the emotion object.
     * It sets the feeeling, comment, and date to reflect what the user wanted modified
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            try {
                String feeling = intent.getStringExtra("feeling");
                checkValidEmotion(feeling);
                String comment = intent.getStringExtra("comment");
                String dateString = intent.getStringExtra("date");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
                Date date = dateFormat.parse(dateString);
                String position = intent.getStringExtra("position");
                int itemIndex = Integer.parseInt(position);

                Emotion emotion = emotionList.get(itemIndex);

                emotion.setFeeling(feeling);
                emotion.setComment(comment);
                emotion.setDate(date);
                sort();
                emotionsArrayAdapter.notifyDataSetChanged();
                saveInFile();
            } catch (commentTooLongException e){
                toastMessage(e.getMessage());
            } catch (ParseException e) {
                toastMessage(e.getMessage());
            } catch (emotionNotRecognized e) {
                toastMessage(e.getMessage());
            }



        }
    }

    /**
     * Used to display feedback to the user on screen
     * @param message
     */
    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}

