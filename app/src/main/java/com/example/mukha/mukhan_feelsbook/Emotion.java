package com.example.mukha.mukhan_feelsbook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Emotions {
    private String comment;
    private String feeling;
    private String date;
    private static final Integer MAX_CHARS = 100;

    public Emotions(String feeling, String comment) throws commentTooLongException {
        this.setComment(comment);
        this.setFeeling(feeling);
       // this.dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        this.setDate("01/01/1996");

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
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return this.date;
    }


}
