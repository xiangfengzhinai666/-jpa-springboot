package com.powernode.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 香风智乃
 * @className Tag
 * @date 2023/2/24 20:13
 * @desciption:
 */

@Entity(name = "t_tag")
@Table
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    private String name;


    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();
    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tag() {
    }
}
