package com.foosball.blog.wcm.components;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import com.foosball.blog.service.PostService;


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
	
	@Reference
	PostService postService;

	@Override
	public void doGet(SlingHttpServletRequest slingRequest, SlingHttpServletResponse slingResponse) {
		String action = slingRequest.getParameter("action");
		if(action.equals("trendingPosts")) {
			// Code to fetch trending Posts from JCR
			
		} else {
			// Code to fetch all posts from JCR
		}
	}
}
