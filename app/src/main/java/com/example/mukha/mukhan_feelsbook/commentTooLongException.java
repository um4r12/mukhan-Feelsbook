package com.example.mukha.mukhan_feelsbook;

public class commentTooLongException extends Exception {
    commentTooLongException() {
        /** The super takes advantage of everything in the exception class,
         by calling the constructor of the Exception class with the message created
         */
        super("The message is too long! Please keep your comment within 140 characters.");
    }
}
