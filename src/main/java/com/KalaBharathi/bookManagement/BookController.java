package com.KalaBharathi.bookManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/add") // creating end point and  post api to update data
    public ResponseEntity addBook(@RequestBody Book book){//requestbody denotes what input this method is expecting
        try {
            Boolean added = bookService.addBook(book);
            return new ResponseEntity("Book with Id: "+book.getBookId()+" added successfully", HttpStatus.CREATED);
        }
        catch(BookAlreadyExistsException ex){
            return new ResponseEntity("Unable to add book as it already exists",HttpStatus.valueOf(400));
        }catch(Exception ex){
            return new ResponseEntity("Something went wrong",HttpStatus.valueOf(500));
        }
        //data.put(book.getBookId(),book);
    }
   @GetMapping("/find")
    public ResponseEntity findBook(@RequestParam("Bookid") Integer id){//if we specify name in brackets then that variable is used to access
        try{
            Book book=bookService.getBook(id);
            return new ResponseEntity(book,HttpStatus.OK);
        }catch(BookNotFoundException ex){
            return new ResponseEntity("Book not found",HttpStatus.valueOf(404));
       }catch(Exception ex){
            return new ResponseEntity("Something went wrong",HttpStatus.valueOf(500));
        }
        //return data.get(id);
    }
    /* @GetMapping("/all-books")
    public List<Book> getAllBooks(){
        return data.values().stream().toList();
    }
    @GetMapping("/find-books/{id}")
    public Book findBookbyPath(@PathVariable Integer id){//example like hotstar/sports/tennis
        return data.get(id);
    }*/

    @PutMapping("/update/{id}")
    public String updateBook(@PathVariable int id,@RequestParam(required = false) String title,@RequestParam(required = false) String author,@RequestParam(required = false) Integer pages){
        try {
            String response = bookService.updateBook(id, title, author, pages);
            return response;
        }catch(Exception ex){
            return "Exception Occured";
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable int id){
        try {
            bookService.deleteBook(id);
            return new ResponseEntity("removed successfully",HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity("Book not found",HttpStatus.NOT_FOUND);
        }

    }

}
