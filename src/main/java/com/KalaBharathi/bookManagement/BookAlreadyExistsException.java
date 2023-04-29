package com.KalaBharathi.bookManagement;

public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(int id){
        super("Book for id: "+id+"already exists" );
    }
}
