package org.example.temporary_task.user.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
}
