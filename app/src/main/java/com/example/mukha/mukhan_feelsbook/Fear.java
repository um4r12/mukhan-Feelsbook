package com.example.mukha.mukhan_feelsbook;

public class Fear extends Emotions {

    private static int numberOfOccurrences = 0;

    public Fear(String comment) throws commentTooLongException {
        super("Fear", comment);
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
