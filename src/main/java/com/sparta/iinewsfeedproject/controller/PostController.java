package com.sparta.iinewsfeedproject.controller;

import com.sparta.iinewsfeedproject.dto.PostRequestDto;
import com.sparta.iinewsfeedproject.dto.PostResponseDto;
import com.sparta.iinewsfeedproject.entity.User;
import com.sparta.iinewsfeedproject.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody @Valid PostRequestDto postRequestDto, HttpServletRequest request) {
        User user = (User)request.getAttribute("user");
        PostResponseDto post = postService.createPost(postRequestDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}
