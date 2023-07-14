package com.hus23.assignment.socialmediaplatform.repository;

import com.hus23.assignment.socialmediaplatform.entities.Post;
import com.hus23.assignment.socialmediaplatform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User_Repo extends JpaRepository<User, Long> {
    List<User> findByUserNameContaining(String userName);
//    List<User> findByFirstNameContaining(String firstName);
//    List<User> findByLastNameContaining(String lastName);

    List<User> findByUserNameIgnoreCaseContainingOrFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String userName, String firstName, String lastName);

}
