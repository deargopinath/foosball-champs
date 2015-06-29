package com.foosball.blog.service;

import com.foosball.blog.model.Post;

import java.util.List;

/**
 * Created by tripotha on 6/26/2015.
 */
public interface PostService {

    public List<Post>  getPostList();
    
    public Boolean addPost(Post post);
}
