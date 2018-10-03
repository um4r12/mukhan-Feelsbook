package com.example.mukha.mukhan_feelsbook;

public class Sadness extends Emotions {

    private static int numberOfOccurrences = 0;

    public Sadness(String comment) throws commentTooLongException {
        super("Sadness", comment);
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
