package com.foosball.blog.model;

import java.util.List;

import org.apache.sling.commons.json.JSONArray;

/**
 * Created by tripotha on 6/26/2015.
 */
public class Post {

	private String title;
	private String screenName;
	private  String postArticle;
	private int likeCount;
	private List<Comment> comments;
	private String postId;
    private int commentCount;

    public Post() {
    	
    }
    
    public Post(String postId, String postArticle, int commentCount, int likeCount, String screenName, String title, List<Comment> comments) {
    	this.postId = postId;
    	this.postArticle = postArticle;
    	this.commentCount = commentCount;
    	this.likeCount = likeCount;
    	this.screenName = screenName;
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

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
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
    
    public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}
    
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("{");
    	sb.append("postId:");
    	sb.append(postId + ",");
    	sb.append("postArticle:");
    	sb.append(postArticle + ",");
    	sb.append("likeCount:");
    	sb.append(likeCount + ",");
    	sb.append("commentCount:");
    	sb.append(commentCount + ",");
    	sb.append("screenName:");
    	sb.append(screenName + ",");
    	sb.append("title:");
    	sb.append(title + ",");
    	sb.append("comments:");
    	sb.append(new JSONArray(comments));
    	sb.append("}");
    	return sb.toString();
    }
}
