package com.hus23.assignment.socialmediaplatform.controller;


import com.hus23.assignment.socialmediaplatform.entities.Post;
import com.hus23.assignment.socialmediaplatform.entities.User;
import com.hus23.assignment.socialmediaplatform.repository.User_Repo;
import com.hus23.assignment.socialmediaplatform.services.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class User_Controller {
    @Autowired
    private User_Repo userRepo;

    @Autowired
    private User_Service userService;

    @GetMapping("/allUser")
    public ResponseEntity<List<User>> All_user(){
        return userService.getallUser();
    }

    @PostMapping("/postUser")
    public ResponseEntity<User> add_User(@RequestBody User user){
        return userService.postAddUser(user);
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<User> userById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateById(@PathVariable Long id, @RequestBody User newData){
       return userService.updateUserById(id, newData);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id){
        return userService.deleteUserById(id);
    }

    @GetMapping("/searchUser")
    public List<User> findUserBySearch(@RequestParam(value = "userName", required = false) String userName){
        return userService.findUsersBySearch(userName);
    }
//
//    @GetMapping("/searchUser/FirstName")
//    public List<User> findUserByFirstName(@RequestParam(value = "firstName", required = false) String firstName){
//        return userService.getUserByFirstName(firstName);
//    }
//
//    @GetMapping("/searchUser/LastName")
//    public List<User> findUserByLastName(@RequestParam(value = "lastName", required = false) String lastName){
//        return userService.getUserByLastName(lastName);
//    }

    @GetMapping("/searchUser/any")
    public List<User> searchByAnyName(@RequestParam(value = "userName", required = false) String userName,
                                      @RequestParam(value = "firstName", required = false) String firstName,
                                      @RequestParam(value = "lastName", required = false) String lastName){
        return userService.getUserByAnyName(userName, firstName, lastName);
    }

    @PostMapping("/{userId}/follows")
    public void followId(@PathVariable Long userId, @RequestParam(value = "follow_Id") Long follow_Id){
        userService.followById(userId, follow_Id);
    }

    @PostMapping("/{userId}/unfollows")
    public void unfollowId(@PathVariable Long userId, @RequestParam(value = "unfollow_Id") Long unfollow_Id){
        userService.unfollowById(userId, unfollow_Id);
    }

//    @GetMapping("/followerByUserId/{userId}")
//    public ResponseEntity<List<User>> findPostByUserId(@PathVariable Long userId){
//        return userService.findFollowersByUserId(userId);
//    }
}
