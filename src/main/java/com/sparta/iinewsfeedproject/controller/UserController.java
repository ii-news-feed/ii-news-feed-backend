package com.sparta.iinewsfeedproject.controller;

import com.sparta.iinewsfeedproject.dto.*;
import com.sparta.iinewsfeedproject.exception.IncorrectPasswordException;
import com.sparta.iinewsfeedproject.exception.UserNotFoundException;
import com.sparta.iinewsfeedproject.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody SignupRequestDto reqDto) {
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.signUp(reqDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> loginUser(@RequestBody LoginRequestDto reqDto, HttpServletResponse res) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.login(reqDto, res));
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deactivateUser(@PathVariable Long userId, @RequestBody DeleteUserRequestDto deleteUserRequest) {
        userService.deactivateUser(userId, deleteUserRequest.getPassword());
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(404, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ErrorResponseDto> handleIncorrectPasswordException(IncorrectPasswordException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(400, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
