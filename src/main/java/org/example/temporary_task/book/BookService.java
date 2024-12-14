package org.example.temporary_task.book;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.temporary_task.book.dto.BookCreateDto;
import org.example.temporary_task.book.dto.BookUpdateDto;
import org.example.temporary_task.user.User;
import org.example.temporary_task.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public Book create(BookCreateDto createDto) {
        Book book = new Book();

        book.setTitle(createDto.getTitle());
        book.setAuthor(createDto.getAuthor());
        book.setDescription(createDto.getDescription());
        book.setQuantity(createDto.getQuantity());
        book.setPrice(createDto.getPrice());

        UUID currentUserId = getCurrentUserId();

        User user = userRepository.findById(currentUserId).orElseThrow(EntityNotFoundException::new);
        book.setUser(user);

        return bookRepository.save(book);
    }

    public Book update(BookUpdateDto updateDto, Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));

        UUID currentUserId = getCurrentUserId();
        if (!book.getUser().getId().equals(currentUserId)) {
            throw new SecurityException("You are not authorized to update this book.");
        }

        book.setTitle(updateDto.getTitle());
        book.setAuthor(updateDto.getAuthor());
        book.setPrice(updateDto.getPrice());
        book.setQuantity(updateDto.getQuantity());
        book.setDescription(updateDto.getDescription());

        return bookRepository.save(book);
    }

    private UUID getCurrentUserId() {
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user.getId();
    }

    public void delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));

        UUID currentUserId = getCurrentUserId();
        if (!book.getUser().getId().equals(currentUserId)) {
            throw new SecurityException("You are not authorized to delete this book.");
        }
        bookRepository.delete(book);
    }

    public List<Book> findAllMyBooks() {
        UUID currentUserId = getCurrentUserId();
        return bookRepository.findByUserId(currentUserId);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
