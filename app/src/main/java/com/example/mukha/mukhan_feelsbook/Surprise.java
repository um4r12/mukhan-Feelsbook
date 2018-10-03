package com.example.mukha.mukhan_feelsbook;

public class Surprise extends Emotions {

    private static int numberOfOccurrences = 0;

    public Surprise(String comment) throws commentTooLongException {
        super("Surprise", comment);
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
