package com.hus23.assignment.socialmediaplatform.services;

import com.hus23.assignment.socialmediaplatform.entities.Post;
import com.hus23.assignment.socialmediaplatform.entities.User;
import com.hus23.assignment.socialmediaplatform.repository.Post_Repo;
import com.hus23.assignment.socialmediaplatform.repository.User_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class Post_Service {
    @Autowired
    User_Repo userRepo;
    @Autowired
    Post_Repo postRepo;

    public ResponseEntity<Post> createPosts(Post post, Long userId){
        Optional<User> data = userRepo.findById(userId);
        User user = data.get();
        post.setUser(user);
        post.setDate(LocalDateTime.now());
        Post postData = postRepo.save(post);
        try{
            return new ResponseEntity<>(postData, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Post> getPostsById(Long id){
        Optional<Post> postObj = postRepo.findById(id);
        try{
            if(postObj.isPresent()){
                return new ResponseEntity<>(postObj.get(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Post> updatePostsById(Long id, Post newData){
        Optional<Post> postOldData = postRepo.findById(id);
        try {
            if (postOldData.isPresent()){
                Post updatedData = postOldData.get();
                updatedData.setDate(LocalDateTime.now());
                updatedData.setCaption(newData.getCaption());
                updatedData.setLocation(newData.getLocation());
                Post postsData = postRepo.save(updatedData);
                return new ResponseEntity<>(postsData, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Post> DeleteById(Long id){
        postRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deletePostsOfUser(Long userId){
        Optional<User> data = userRepo.findById(userId);
        User user = data.get();

        List<Post> postDelete = postRepo.findByUser(user);
        postRepo.deleteAll(postDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Post>> findPostsByUserId(Long userId){
        List<Post> objPost = postRepo.findByUserId(userId);
        return new ResponseEntity<>(objPost, HttpStatus.OK);
    }

    public List<Post> findPostByLocation(String location){
        return postRepo.findByLocation(location);
    }
}
