package com.foosball.blog.wcm.components;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.foosball.blog.model.Post;
import com.foosball.blog.service.PostService;

public class AddPostWcmUse extends WCMUse {
	
	private final Logger logger = LoggerFactory.getLogger(AddPostWcmUse.class);
	private boolean flag; 
	
	@Override
	public void activate() throws Exception {
		SlingScriptHelper sling = getSlingScriptHelper();
		SlingHttpServletRequest slingRequest = getRequest();
		PostService postService = sling.getService(PostService.class);
		
		String screenName = slingRequest.getParameter("screenName");
		String postTitle = slingRequest.getParameter("postTitle");
		String postContent = slingRequest.getParameter("postContent");
		
		Post newPost = new Post(postContent, 0, 0, screenName, postTitle, null);
		logger.info("Screen Name :" + screenName);
		logger.info("Post Content :" + postContent);
		logger.info("Post Service");
		flag = postService.addPost(newPost);
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
