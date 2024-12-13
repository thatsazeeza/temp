package org.example.temporary_task.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.temporary_task.user.dto.UserCreateDto;
import org.example.temporary_task.user.dto.UserSignInDto;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(UserCreateDto createDto){
        User user = new User();
        user.setFirstName(createDto.getFirstName());
        user.setLastName(createDto.getLastName());
        user.setPhoneNumber(createDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(createDto.getPassword()));
        user.setId(UUID.randomUUID());
        userRepository.save(user);
        return user;
    }

    public User signIn(UserSignInDto userSignInDto) {
        User user = userRepository.findByPhoneNumber(userSignInDto.getPhoneNumber())
                .orElseThrow(() -> new BadCredentialsException("Username or password is not correct"));

        if (!passwordEncoder.matches(userSignInDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Username or password is not correct");
        }

        return user;
    }
}
