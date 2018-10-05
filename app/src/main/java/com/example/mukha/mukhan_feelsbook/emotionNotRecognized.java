package com.example.mukha.mukhan_feelsbook;

public class emotionNotRecognized extends Exception {
    emotionNotRecognized() {
        /** The super takes advantage of everything in the exception class,
         by calling the constructor of the Exception class with the message created
         */
        super("This emotion is not recognized.");
    }
}
