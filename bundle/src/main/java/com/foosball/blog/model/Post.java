package com.foosball.blog.model;

import java.util.List;

/**
 * Created by tripotha on 6/26/2015.
 */
public class Post {

    private  String postArticle;
    private int commentCount;
    private int likeCount;
    private String bloggerName;
    private String title;
    private List<Comment> comments;

    public Post() {
    	
    }
    
    public Post(String postArticle, int commentCount, int likeCount, String bloggerName, String title, List<Comment> comments) {
    	this.postArticle = postArticle;
    	this.commentCount = commentCount;
    	this.likeCount = likeCount;
    	this.bloggerName = bloggerName;
    	this.title = title;
    	this.comments = comments;
    }

    public String getPostArticle() {
		return postArticle;
	}

	public void setPostArticle(String postArticle) {
		this.postArticle = postArticle;
	}

	public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getBloggerName() {
        return bloggerName;
    }

    public void setBloggerName(String bloggerName) {
        this.bloggerName = bloggerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
