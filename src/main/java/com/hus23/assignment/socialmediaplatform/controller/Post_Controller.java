package com.hus23.assignment.socialmediaplatform.controller;

import com.hus23.assignment.socialmediaplatform.entities.Post;
import com.hus23.assignment.socialmediaplatform.entities.User;
import com.hus23.assignment.socialmediaplatform.repository.Post_Repo;
import com.hus23.assignment.socialmediaplatform.repository.User_Repo;
import com.hus23.assignment.socialmediaplatform.services.Post_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Post_Controller {
    @Autowired
    private User_Repo userRepo;

    @Autowired
    private Post_Repo postRepo;
    @Autowired
    private Post_Service postService;

    @PostMapping("/posts/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Long userId) {
        return postService.createPosts(post, userId);
    }

    @GetMapping("/getPostById/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id){
        return postService.getPostsById(id);
    }

    @PutMapping("/updatePost/{id}")
    public ResponseEntity<Post> updatePostById(@PathVariable Long id, @RequestBody Post newData){
        return postService.updatePostsById(id, newData);
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<Post> deleteById(@PathVariable Long id){
        return postService.DeleteById(id);
    }

    @DeleteMapping("/deleteAllPostOfUser/{userId}")
    public ResponseEntity<HttpStatus> deletePostOfUser(@PathVariable Long userId){
        return postService.deletePostsOfUser(userId);
    }

    @GetMapping("/findByUserId/{userId}")
    public ResponseEntity<List<Post>> findPostByUserId(@PathVariable Long userId){
        return postService.findPostsByUserId(userId);
    }

    @GetMapping("/postByLocation")
    public List<Post> seacrhPostByLocation(@RequestParam(value = "location", required = false) String location){
        return postService.findPostByLocation(location);
    }
}
