package com.hus23.assignment.socialmediaplatform.services;

import com.hus23.assignment.socialmediaplatform.entities.Post;
import com.hus23.assignment.socialmediaplatform.entities.User;
import com.hus23.assignment.socialmediaplatform.repository.User_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class User_Service {
    @Autowired
    private User_Repo userRepo;

    public ResponseEntity<List<User>> getallUser(){
        try{
            List<User> users = new ArrayList<>();
            userRepo.findAll().forEach(users::add);

            if(users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> postAddUser(User user){
        User obj = userRepo.save(user);
        try{
            return new ResponseEntity<>(obj, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> getUserById(Long id){
        Optional<User> userObj = userRepo.findById(id);
        try{
            if(userObj.isPresent()){
                return new ResponseEntity<>(userObj.get(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> updateUserById(Long id, User newData){
        Optional<User> oldData = userRepo.findById(id);
        try {
            if (oldData.isPresent()){
                User updatedData = oldData.get();
                updatedData.setUserName(newData.getUserName());
                updatedData.setFirstName(newData.getFirstName());
                updatedData.setLastName(newData.getLastName());
                updatedData.setBio(newData.getBio());
                updatedData.setPassword(newData.getPassword());

                User userData = userRepo.save(updatedData);
                return new ResponseEntity<>(userData, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> deleteUserById(Long id){
        try {
            Optional<User> userid = userRepo.findById(id);
            if (userid.isPresent()) {
                userRepo.delete(userid.get());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<User> findUsersBySearch(String userName){
        return userRepo.findByUserNameContaining(userName);
    }
//    public List<User> getUserByFirstName(String firstName){
//        return userRepo.findByFirstNameContaining(firstName);
//    }
//
//    public List<User> getUserByLastName(String lastName){
//        return userRepo.findByLastNameContaining(lastName);
//    }

    public List<User> getUserByAnyName(String userName, String firstName, String lastName){
        return userRepo.findByUserNameIgnoreCaseContainingOrFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(userName, firstName, lastName);
    }

    public void followById (Long userId, Long follow_Id){
        User user = userRepo.getById(userId);
        User follow = userRepo.getById(follow_Id);
        user.getFollow_following().add(follow);

        userRepo.save(user);
    }

    public void unfollowById(Long userId, Long unfollow_Id){
        User user = userRepo.getById(userId);
        User unfollow = userRepo.getById(unfollow_Id);
        user.getFollow_following().remove(unfollow);

        userRepo.save(user);
    }

//    public ResponseEntity<List<User>> findFollowersByUserId(Long userId){
//        Optional<User> objPost = userRepo.findById(userId);
//        return new ResponseEntity<>(objPost, HttpStatus.OK);
//    }
}
