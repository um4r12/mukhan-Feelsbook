package com.example.mukha.mukhan_feelsbook;

public class Love extends Emotions {

    private static int numberOfOccurrences = 0;

    public Love(String comment) throws commentTooLongException {
        super("Love", comment);

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
