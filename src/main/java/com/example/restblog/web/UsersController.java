package com.example.restblog.web;


import com.example.restblog.data.PostsRepository;
import com.example.restblog.data.User;
import com.example.restblog.data.UsersRepository;
import com.example.restblog.security.OAuthConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/users", headers = "Accept=application/json")


public class UsersController {

    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

//    public UsersController(PostsRepository postsRepository, UsersRepository usersRepository) {
//        this.postsRepository = postsRepository;
//        this.usersRepository = usersRepository;
//    }

    @GetMapping
    private List<User> getAll() {

        return usersRepository.findAll();
    }

    @GetMapping("{userId}")
    public Optional<User> getUserById(@PathVariable Long userId) {
        return usersRepository.findById(userId);


    }

    @GetMapping("email")
    public User getByEmail(@RequestParam String email) {
return usersRepository.findByEmail(email);
    }

//    @GetMapping("/username")
//    public User getByUserName(@RequestParam String userName){
//        System.out.println("The username is " + userName);
//        User user = new User(1L,userName, "email 1", "1234", null, User.Role.ADMIN);
//        return user;
//    }

    @PostMapping("create")
    private void createUser(@RequestBody User newUser) {
        newUser.setCreatedAt(LocalDate.now());
        newUser.setRole(User.Role.USER);
        String encryptedPassword = newUser.getPassword();
        encryptedPassword = passwordEncoder.encode(encryptedPassword);
        newUser.setPassword(encryptedPassword);
        usersRepository.save(newUser);

//        System.out.println("Ready to add post" + newPost);
    }
@GetMapping("me")
private User getMyInfo(OAuth2Authentication auth) {
        String email = auth.getName();
        return usersRepository.findByEmail(email);
}


//    @PutMapping("{userId}")
//    private void updateUser(@PathVariable Long userId, @RequestBody User newUser) {
//        System.out.println("Ready to update post" + userId + newUser);
//    }

    @PutMapping("{id}/updatePassword")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword) {
       User userToUpdatePassword = usersRepository.findById(id).get();
        String encryptedPassword = userToUpdatePassword.getPassword();
        encryptedPassword = passwordEncoder.encode(encryptedPassword);
        userToUpdatePassword.setPassword(encryptedPassword);
        usersRepository.save(userToUpdatePassword);
        System.out.println("Changing password to " + newPassword);
    }

//    @DeleteMapping("{userId}")
//    private void deleteUser(@PathVariable Long userId) {
//        System.out.println("Deleted post" + userId);
//    }

}
