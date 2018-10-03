package com.example.mukha.mukhan_feelsbook;

public class Joy extends Emotions {

    private static int numberOfOccurrences = 0;

    public Joy(String comment) throws commentTooLongException {
        super("Joy", comment);
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
