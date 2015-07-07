package com.foosball.blog.wcm.components;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foosball.blog.service.PostService;

@SlingServlet(paths="/bin/likeCount", methods="POST", label="Like Count Servlet")
//@SlingServlet(paths = "/eLearning/pages/course", methods = "POST")
public class LikeCountServlet extends SlingAllMethodsServlet {
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -8670536820903855120L;

	private final Logger logger = LoggerFactory.getLogger(LikeCountServlet.class);
	
	@Reference
	PostService service;
	
	@Override
	public void doPost(SlingHttpServletRequest slingRequest, SlingHttpServletResponse slingResponse) {
		String postId = slingRequest.getParameter("postId");
		String screenName = slingRequest.getParameter("screenName");
		logger.info("doPost method incrementing the likes of the post :" + postId + screenName);
		long likeCount = service.increaseLikeCount(postId, screenName);
		PrintWriter out;
		try {
			out = slingResponse.getWriter();
			out.println(likeCount);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
