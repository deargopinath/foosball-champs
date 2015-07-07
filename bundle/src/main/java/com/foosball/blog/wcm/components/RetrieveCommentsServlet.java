package com.foosball.blog.wcm.components;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foosball.blog.model.Comment;
import com.foosball.blog.service.PostService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@SlingServlet(paths="/bin/fetchComments", methods="GET", label="Servlet to get comments of a post.")
public class RetrieveCommentsServlet extends SlingSafeMethodsServlet {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -8491564263679343349L;
	private final Logger logger = LoggerFactory.getLogger(RetrieveCommentsServlet.class);
	
	@Reference
	PostService service;
	
	@Override
	public void doGet(SlingHttpServletRequest slingRequest, SlingHttpServletResponse slingResponse) {
		String postId = slingRequest.getParameter("postId");
		logger.info("Inside the doGet Method :" + postId);
		List<Comment> commentList = service.getCommentList(postId);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String commentListJson = gson.toJson(commentList);
		PrintWriter out = null;
		try {
			out = slingResponse.getWriter();
			out.println(commentListJson);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
