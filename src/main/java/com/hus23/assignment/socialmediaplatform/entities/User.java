package com.hus23.assignment.socialmediaplatform.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_table")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String userName;

    private String firstName;

    private String lastName;

    private String bio;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Post> posts = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "follower_following", joinColumns = @JoinColumn(name = "follower"), inverseJoinColumns = @JoinColumn(name = "following"))
    private Set<User> follow_following;
}
