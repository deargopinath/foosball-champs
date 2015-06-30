package com.foosball.blog.model;

/**
 * Created by tripotha on 6/26/2015.
 */
public class Comment {

    private String commentID;
    private String message;
    private String screenName;

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenName() {
        return screenName;
    }
    
    @Override
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("{");
    	sb.append("\"commentID\"");
    	sb.append("\"" + commentID + "\"");
    	sb.append("\"message\"");
    	sb.append("\"" + message + "\"");
    	sb.append("\"screenName\"");
    	sb.append("\"" + screenName + "\"");
    	return sb.toString();
    }
}
