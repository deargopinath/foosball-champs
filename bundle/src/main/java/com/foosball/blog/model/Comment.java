package com.foosball.blog.model;

/**
 * Created by tripotha on 6/26/2015.
 */
public class Comment {

    private String message;
    private String screenName;

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
}
