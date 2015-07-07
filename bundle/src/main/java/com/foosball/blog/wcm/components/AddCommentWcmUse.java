package com.foosball.blog.wcm.components;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.foosball.blog.model.Comment;
import com.foosball.blog.service.PostService;

public class AddCommentWcmUse extends WCMUse {

	private final Logger logger = LoggerFactory.getLogger(AddCommentWcmUse.class);
	private boolean flag;
	
	@Override
	public void activate() throws Exception {
		// Code to save the comment in JCR
		SlingHttpServletRequest slingRequest = getRequest();
		SlingScriptHelper sling = getSlingScriptHelper();
		PostService postService = sling.getService(PostService.class);
		
		String postId = slingRequest.getParameter("postId");
		String screenName = slingRequest.getParameter("screenName");
		String commentText = slingRequest.getParameter("comment");
		Comment comment = new Comment();
		comment.setMessage(commentText);
		comment.setScreenName(screenName);
		flag = postService.addComment(postId, comment);
		// By Using the Service we have to implement write the code to save the comment in JCR
		logger.info("Post ID: " + postId + " Screen Name :" + screenName + " Comment Text :" + commentText);
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
