package com.foosball.blog.wcm.components;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.foosball.blog.model.Post;
import com.foosball.blog.service.PostService;

public class AddPostBlogWcmUse extends WCMUse {

	private final Logger logger = LoggerFactory.getLogger(AddPostBlogWcmUse.class);

	private String productName;
	private String flag;

	@Override
	public void activate() throws Exception {
		SlingHttpServletRequest request = getRequest();
		productName = "Adobe Experience Manager";
		System.out.println("postTitle :" + request.getParameter("postTitle"));
		logger.info("postTitle :" + request.getParameter("postTitle"));
		logger.info("postContent :" + request.getParameter("postContent"));
		logger.info("screenName :" + request.getParameter("screenName"));
		
		SlingScriptHelper sling = getSlingScriptHelper();
		SlingHttpServletRequest slingRequest = getRequest();
		PostService postService = sling.getService(PostService.class);
		
		String screenName = slingRequest.getParameter("screenName");
		String postTitle = slingRequest.getParameter("postTitle");
		String postContent = slingRequest.getParameter("postContent");
		
		Post newPost = new Post(null, postContent, 0, 0, screenName, postTitle, null);
		logger.info("Screen Name :" + screenName);
		logger.info("Post Content :" + postContent);
		logger.info("Post Service");
		boolean flag = postService.addPost(newPost);
		if(flag) {
			this.flag = "true";
		} else {
			this.flag = "false";
		}
		//save details
		logger.info("Inside activate method AddPotBlogWcmUse component");
		System.out.println("Inside activate method AddPotBlogWcmUse component");
	}
	
	public String getProductName() {
		logger.info("Inside getProductName method");
		System.out.println("Inside activate method AddPotBlogWcmUse component");
		return productName;
	}

	public String getFlag() {
		return flag;
	}
}
