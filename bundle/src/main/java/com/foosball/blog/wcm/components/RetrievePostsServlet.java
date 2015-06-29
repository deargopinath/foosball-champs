package com.foosball.blog.wcm.components;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foosball.blog.model.Post;
import com.foosball.blog.service.PostService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Component(immediate=true, label="Retrieve Posts Component", metatype=true, description="This is a servlet to retrieve posts from JCR")
@Service
@Properties({
	@Property(name="sling.servlet.paths", value={"/bin/fetchPosts"}, label="Servlet Path"),
	@Property(name="sling.servlet.methods", value="GET", label="Request Method")
})
public class RetrievePostsServlet extends SlingSafeMethodsServlet {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -8107238707819821221L;
	private final Logger logger = LoggerFactory.getLogger(RetrievePostsServlet.class);
	
	@Reference
	PostService postService;

	@Override
	public void doGet(SlingHttpServletRequest slingRequest, SlingHttpServletResponse slingResponse) {
		String action = slingRequest.getParameter("action");
		List<Post> postList = postService.getPostList(action);
		GsonBuilder gsonbuilder = new GsonBuilder();
		Gson gson = gsonbuilder.create();
		String postListJson = gson.toJson(postList);

		logger.info("Post List JSON :" + postListJson);
		PrintWriter out;
		try {
			out = slingResponse.getWriter();
			out.println(postListJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
