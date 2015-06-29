package com.foosball.blog.service;

import com.foosball.blog.model.Post;
import com.foosball.blog.model.Comment;

import java.util.List;

/**
 * Created by tripotha on 6/26/2015.
 */
public interface PostService {

    public List<Post>  getPostList(String mode);
    
    public Boolean addPost(Post post);

    public Boolean addComment(String postID, Comment comment);
}
