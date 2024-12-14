package org.example.temporary_task.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDto {
    private String title;
    private String author;
    private String description;
    private double price;
    private int quantity;
}
