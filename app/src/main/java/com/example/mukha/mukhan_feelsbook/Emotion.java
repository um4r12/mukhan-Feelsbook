package com.example.mukha.mukhan_feelsbook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Muhammad Khan on 10/05/2018
 * A class which returns an Emotion object that can be used to display a series of emotions
 * It initiliazes the state with a given feeling (String) and comment (String).
 * The date is automatically intialized to the current time
 *
 * @author Muhammad Khan

 * @param feeling the emotional state
 * @param comment an associated comment with the feeling, must be less than 100 chars
 * @throws commentTooLongException if the comment is too long, throws an exception
 * @return an emotion object
 */
public class Emotion {
    private String comment;
    private String feeling;
    private String timeStamp;
    private static final Integer MAX_CHARS = 100;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());

    public Emotion(String feeling, String comment) throws commentTooLongException {
        this.setComment(comment);
        this.setFeeling(feeling);
        this.setDate(new Date());

    }

    public String getFeeling() {
        return this.feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) throws commentTooLongException {
        if (comment.length() <= MAX_CHARS) {
            this.comment = comment;
        } else {
            throw new commentTooLongException();
        }

    }
    public String toString(){
        return this.getFeeling();
    }
    public void setDate(Date date) {
        this.timeStamp = dateFormat.format(date);
    }
    public String getDate() {
        return this.timeStamp;
    }

    public Boolean matches(Emotion emotionToMatch) {
        return this.getFeeling().equals(emotionToMatch.getFeeling());
    }

}
