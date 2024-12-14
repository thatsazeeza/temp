package org.example.temporary_task.book;

import lombok.RequiredArgsConstructor;
import org.example.temporary_task.book.dto.BookCreateDto;
import org.example.temporary_task.book.dto.BookUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody BookCreateDto createDto) {
        Book bookResponse = bookService.create(createDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookUpdateDto updateDto) {
        Book updatedBook = bookService.update(updateDto, id);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }

    @GetMapping("/my-books")
    public ResponseEntity<List<Book>> findAllMyBooks() {
        List<Book> books = bookService.findAllMyBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAllBooks() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }
}
