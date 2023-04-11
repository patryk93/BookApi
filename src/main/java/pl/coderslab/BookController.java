package pl.coderslab;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }
    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.add(book);
    }
    @GetMapping("/{id}")
    public Book bookById(@PathVariable Long id) {
        return bookService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
    }
    @PutMapping("")
    public void update(@RequestBody Book book) {
        bookService.update(book);
    }
    @DeleteMapping("/{id}")
    public void removeBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}

