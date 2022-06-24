package com.example.restblog.web;

import com.example.restblog.data.*;
import com.example.restblog.security.OAuthConfiguration;
import com.example.restblog.services.EmailService;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ToString
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {
    private PostsRepository postsRepository;
    private UsersRepository userRepository;
    private EmailService emailService;

//    public PostsController(PostsRepository postsRepository, UsersRepository userRepository,EmailService emailService) {
//        this.postsRepository = postsRepository;
//        this.userRepository = userRepository;
//        this.emailService = emailService;
//
//    }

    @GetMapping
    private List<Post> getPosts() {
    return postsRepository.findAll();
    }

    @GetMapping("{postID}")
    public Optional<Post> getById(@PathVariable Long postID) {
        return postsRepository.findById(postID);
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost, OAuth2Authentication auth) {
//        newPost.setAuthor(userRepository.getById(2L));
        String email = auth.getName();
        User user = userRepository.findByEmail(email);
        newPost.setAuthor(user);
//        System.out.println(newPost);
        postsRepository.save(newPost);
        emailService.prepareAndSend(newPost,"subject","test body");
//        passwordEncoder
        System.out.println("Ready to add post." + newPost);
    }

//    @PostMapping
//    private void createPost(@RequestBody Post newPost, OAuth2Authentication auth){
//        String email = auth.getName(); // yes, the email is found under "getName()"
//        User user = userRepository.findByEmail(email).getId(2L); // use the email to get the user who made the request
//        newPost.setAuthor(user); //set the user to the post
//        postsRepository.save(newPost); //save the post!
//    }

    @PutMapping("{id}")
    private void updatePost(@PathVariable Long id, @RequestBody Post newPost) {
//        fetch the original post of the same id from the database using your postsRepository
//        copy the non-null fields from newPost to original post (i.e., title and content)
//        save the original post to the db using postsRepository
        System.out.println("Ready to update post." + id + newPost);
        Post originalPost = postsRepository.getById(id);
        originalPost.setTitle(newPost.getTitle());
        originalPost.setContent(newPost.getContent());
        postsRepository.save(originalPost);
//        newPost.setTitle(id);
    }

    @DeleteMapping("{postId}")
    private void deletePost(@PathVariable Long postId) {
        System.out.println("ready to delete post." + postId);
        Post postToDelete = postsRepository.getById(postId);
        postsRepository.delete(postToDelete);
    }


}
