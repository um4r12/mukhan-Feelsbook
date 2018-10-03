package com.example.mukha.mukhan_feelsbook;

public class Anger extends Emotions {

    private static int numberOfOccurrences = 0;

    public Anger(String comment) throws commentTooLongException {
        super("Anger", comment);
        this.numberOfOccurrences++;
    }
    @Override
    public int getCount(){
        return this.numberOfOccurrences;
    }
    public void removeObject() {
        this.numberOfOccurrences--;
    }
}
