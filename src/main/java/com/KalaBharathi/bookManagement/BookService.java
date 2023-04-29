package com.KalaBharathi.bookManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Objects;
import java.util.Optional;
@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public Boolean addBook(Book book) throws BookAlreadyExistsException{
        Optional<Book> bookOpt=bookRepository.getById(book.getBookId());
        if(bookOpt.isPresent()) {
            throw new BookAlreadyExistsException(book.getBookId());
        }
        return bookRepository.addBook(book);
    }

    public Book getBook(Integer id) throws BookNotFoundException{
        Optional<Book> bookOpt=bookRepository.getById(id);
        if(bookOpt.isEmpty()){
            throw new BookNotFoundException(id);
        }
            return bookOpt.get();
    }

    public String updateBook(int id, String title, String author, Integer pages) {
        try {
            Book book = getBook(id);
            if (Objects.nonNull(title)) {
                book.setTitle(title);
            }
            if (Objects.nonNull(author)) {
                book.setAuthor(author);
            }
            if (Objects.nonNull(pages)) {
                book.setPages(pages);
            }
            bookRepository.addBook(book);
            return "Book Updated";
        }catch(BookNotFoundException ex){
            Book book=new Book(id,title,author,pages);
            bookRepository.addBook(book);
            return "Book Created";
        }
    }
    public Boolean deleteBook(int id) throws BookNotFoundException{
        Optional<Book> bookOpt=bookRepository.getById(id);
        if(bookOpt.isEmpty()){
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
        return true;
    }

}
