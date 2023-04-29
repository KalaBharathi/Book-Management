package com.KalaBharathi.bookManagement;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(int id){
        super("Book does not exist with id :"+ id);
    }
}
