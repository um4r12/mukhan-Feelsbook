package com.example.mukha.mukhan_feelsbook;

import java.util.Date;

public abstract class Emotions {
    private String comment;
    private String feeling;
    private Date date;
    private static final Integer MAX_CHARS = 100;

    public Emotions(String feeling, String comment) throws commentTooLongException {
        this.setComment(comment);
        this.setFeeling(feeling);

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
        this.date = date;
    }

    public abstract int getCount();
    public abstract void removeObject();


}
