package com.foosball.blog.model;

import java.util.List;

import org.apache.sling.commons.json.JSONArray;

/**
 * Created by tripotha on 6/26/2015.
 */
public class Post {

    private  String postArticle;
    private int commentCount;
    private int likeCount;
    private String screenName;
    private String title;
    private List<Comment> comments;

    public Post() {
    	
    }
    
    public Post(String postArticle, int commentCount, int likeCount, String screenName, String title, List<Comment> comments) {
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
    
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("{");
    	sb.append("\"postArticle\":");
    	sb.append("\"" + postArticle + "\"");
    	sb.append("\"likeCount\":");
    	sb.append("\"" + likeCount + "\"");
    	sb.append("\"commentCount\":");
    	sb.append("\"" + commentCount + "\"");
    	sb.append("\"screenName\":");
    	sb.append("\"" + screenName + "\"");
    	sb.append("\"title\":");
    	sb.append("\"" + title + "\"");
    	sb.append("\"comments\":");
    	sb.append("\"" + new JSONArray(comments) + "\"");
    	sb.append("}");
    	return sb.toString();
    }
}
