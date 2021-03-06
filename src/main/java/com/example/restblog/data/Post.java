package com.example.restblog.data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name="author_id")
    @JsonIgnoreProperties({"posts", "password"})
    private User author;


    //    private Collection<Category> categories;
@ManyToMany(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.DETACH, CascadeType.REFRESH},
        targetEntity = Post.class)
@JoinTable(
        name="post_category",
        joinColumns = {@JoinColumn(name = "category_id", nullable = false, updatable = false)},
        inverseJoinColumns = {@JoinColumn(name="post_id", nullable = false, updatable = false)},
        foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
        inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
)
@JsonIgnoreProperties("posts")
private Collection<Category> categories;
}
