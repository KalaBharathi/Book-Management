package com.KalaBharathi.bookManagement;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookRepository {
    Map<Integer,Book> data=new HashMap<>();

    public Boolean addBook(Book book) {
        data.put(book.getBookId(),book);
        return true;
    }
    public Optional<Book> getById(int id){
        if(data.containsKey(id)){
            return Optional.of(data.get(id));
        }
        else
            return Optional.empty();
    }

    public void deleteById(int id) {
        data.remove(id);
    }
}
