package org.example.temporary_task.user;

import lombok.RequiredArgsConstructor;
import org.example.temporary_task.security.JwtService;
import org.example.temporary_task.user.dto.UserCreateDto;
import org.example.temporary_task.user.dto.UserSignInDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/auth/sign-up")
    public ResponseEntity<User> signUp(@RequestBody UserCreateDto userCreateDto){
        User userResponseDto = userService.create(userCreateDto);
        String token = jwtService.generateToken(userResponseDto.getPhoneNumber());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(userResponseDto);
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<User> signIn(@RequestBody UserSignInDto userSignInDto){
        User userResponseDto = userService.signIn(userSignInDto);
        String token = jwtService.generateToken(userResponseDto.getPhoneNumber());

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(userResponseDto);
    }
}
