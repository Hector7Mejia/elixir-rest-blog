package com.example.restblog.web;

import com.example.restblog.data.Category;
import com.example.restblog.data.Post;
import com.example.restblog.data.PostsRepository;
import com.example.restblog.data.UsersRepository;
import com.example.restblog.services.EmailService;
import lombok.AllArgsConstructor;
import lombok.ToString;
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
    private void createPost(@RequestBody Post newPost) {
        newPost.setAuthor(userRepository.getById(2L));
        System.out.println(newPost);
        postsRepository.save(newPost);
        emailService.prepareAndSend(newPost,"subject","test body");
        System.out.println("Ready to add post." + newPost);
    }

    @PutMapping("{id}")
    private void updatePost(@PathVariable Long id, @RequestBody Post newPost) {
        System.out.println("Ready to update post." + id + newPost);
//        newPost.setTitle(id);
    }

    @DeleteMapping("{postId}")
    private void deletePost(@PathVariable Long postId) {
        System.out.println("ready to delete post." + postId);
    }


}
