package org.example.temporary_task.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByUserId(UUID currentUserId);
}
