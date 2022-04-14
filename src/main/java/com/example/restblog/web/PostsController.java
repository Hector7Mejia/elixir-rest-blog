package com.example.restblog.web;

import com.example.restblog.data.Post;
import lombok.ToString;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@ToString
@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    @GetMapping
    private List<Post> getAll() {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "Post 1", "Blah blah blah"));
        posts.add(new Post(2L, "Post 2", "Blah blah blah"));
        posts.add(new Post(3L, "Post 3", "Blah blah blah"));
        return posts;
    }

    @GetMapping("{postId}")
    public Post getById(@PathVariable Long postId) {
        Post post = new Post(postId, "Post " + postId, "Blah blah blah");
        return post;
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost) {
        System.out.println("Ready to add post." + newPost);
    }

    @PutMapping("{id}")
    private void updatePost(@PathVariable Long id, @RequestBody Post newPost) {
        System.out.println("Ready to update post." + id + newPost);
    }

    @DeleteMapping("{postId}")
    private void deletePost(@PathVariable Long postId) {
        System.out.println("Ready to add post." + postId);
    }
}
