package com.sparta.iinewsfeedproject.controller;

import com.sparta.iinewsfeedproject.dto.ErrorResponseDto;
import com.sparta.iinewsfeedproject.exception.FriendNotFoundException;
import com.sparta.iinewsfeedproject.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friends")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @DeleteMapping ("/{fromUserId}/friend/{userId}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long fromUserId, @PathVariable Long userId) {
        friendService.deleteFriend(fromUserId, userId);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(FriendNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleFriendNotFoundException(FriendNotFoundException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(404, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
