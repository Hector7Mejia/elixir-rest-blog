package com.example.restblog.web;


import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")



public class UsersController {

    @GetMapping
    private List<User> getAll() {
        ArrayList<User> posts = new ArrayList<>();
        posts.add(new User(1L,"User1", "email 1", "1234", null, User.Role.ADMIN));
        posts.add(new User(1L,"User1", "email 1", "1234", null, User.Role.USER));
        posts.add(new User(1L,"User1", "email 1", "1234", null, User.Role.USER));
        return posts;
    }

    @GetMapping("{userId}")
    public User getUserById(@PathVariable Long userId){
        User user = new User(userId,"User1", "email 1", "1234", null, User.Role.ADMIN);
        return user;

    }

    @GetMapping("/getByEmail")
    public void getByEmail(@RequestParam String email) {
        System.out.println("Users email is : " + email);
    }

    @GetMapping("/username")
    public User getByUserName(@RequestParam String userName){
        System.out.println("The username is " + userName);
        User user = new User(1L,userName, "email 1", "1234", null, User.Role.ADMIN);
        return user;
    }

    @PostMapping
    private void createUser(@RequestBody User newPost){
        System.out.println("Ready to add post" + newPost);
    }


    @PutMapping("{userId}")
    private void updateUser(@PathVariable Long userId, @RequestBody User newUser){
        System.out.println("Ready to update post" + userId + newUser );
    }

    @PutMapping("{id}/updatePassword")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword) {
        User user = new User(id,"user 1", "email", "1111", null, User.Role.USER);
        System.out.println("Changing password to " + newPassword);
    }

    @DeleteMapping("{userId}")
    private void deleteUser(@PathVariable Long userId){
        System.out.println("Deleted post" + userId);
    }

}
