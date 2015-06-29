package com.foosball.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foosball.blog.model.Comment;
import com.foosball.blog.model.Post;
import com.foosball.blog.service.PostService;

/**
 * Created by tripotha on 6/26/2015.
 */
@Service(PostService.class)
@Component(immediate=true)
public class PostSeviceImpl implements PostService {

	private final Logger logger = LoggerFactory.getLogger(PostSeviceImpl.class);
	Session session;
	@Reference
	SlingRepository repository;

	public List<Post> getPostList(String mode) {

		List<Post> postList = new ArrayList<Post>();

		try {

			Session session = this.repository.login(new SimpleCredentials(
					"admin", "admin".toCharArray()));

			QueryManager queryManager = session.getWorkspace()
					.getQueryManager();

			Query query = null;
			query = queryManager.createQuery(
							"SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/BlogDB/jcr:content') order by  s.commentCount DESC, s.likeCount DESC, s.postArticle ASC",
							Query.JCR_SQL2);
			if(mode.equals("trendingPosts")){
				query.setLimit(3);
			}

			QueryResult result = query.execute();

			// logger.info("Node Path ::D> " + node.getPath());
			NodeIterator nodes = result.getNodes();
			// logger.info("NOde size : " +result.getRows().getSize());
			while (nodes.hasNext()) {
				logger.info("Node Iteration : ");
				Post p = new Post();
				Node next = nodes.nextNode();
				PropertyIterator properties = next.getProperties();
				while (properties.hasNext()) {
					Property prop = properties.nextProperty();
					// Value[] values = prop.getValues();
					if (prop.getName().equals("title")) {
						p.setTitle(prop.getValue().toString());
					} else if (prop.getName().equals("bloggerName")) {
						p.setBloggerName(prop.getValue().toString());
					} else if (prop.getName().equals("postArticle")) {
						p.setPostArticle(prop.getValue().toString());
					} else if (prop.getName().equals("commentCount")) {
						p.setCommentCount((int) prop.getValue().getLong());
					} else if (prop.getName().equals("likeCount")) {
						p.setLikeCount((int) prop.getValue().getLong());
					}
				}
				postList.add(p);
			}
		} catch (Exception e) {
			logger.error("Error :::" + e.getMessage());
		}
		logger.info("Size of Post list" + postList.size());

		return postList;

	}

	public Boolean addPost(Post post) {
		
		logger.info("enter the addpost() ****");

		try {
			Session session = this.repository.login(new SimpleCredentials(
					"admin", "admin".toCharArray()));
			
			QueryManager queryManager = session.getWorkspace()
					.getQueryManager();

			Query query = queryManager
					.createQuery("SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/BlogDB/jcr:content') AND NAME() LIKE 'post#%'",
							Query.JCR_SQL2);
			
			QueryResult result = query.execute();
			long postCount = result.getRows().getSize();
			logger.info("Post Count :" + postCount);

			Node root = session.getRootNode();
			Node content = root.getNode("content");

			if (!content.hasNode("BlogDB")) {
				content.addNode("BlogDB", "sling:OrderedFolder");
				logger.info("Created parent node");
			}
			Node blogNode = content.getNode("BlogDB");

			if (!blogNode.hasNode("jcr:content")) {
				blogNode.addNode("jcr:content", "nt:unstructured");
				logger.info("Created jcr node");
			}
			Node postNode = blogNode.getNode("BlogDB/jcr:content");
			
			postNode.addNode("post#" + postCount++, "nt:unstructured");
			logger.info("Created post#1 node");
			
			postNode.setProperty("title",post.getTitle());
			postNode.setProperty("postArticle", post.getPostArticle());
			postNode.setProperty("commentCount", post.getCommentCount());
			postNode.setProperty("likeCount", post.getLikeCount());
			postNode.setProperty("bloggerName", post.getBloggerName());
			
			
			/*postNode.setProperty("title", "AEM Test");
			postNode.setProperty("postArticle", "postArticle");
			postNode.setProperty("commentCount", "commentCount");
			postNode.setProperty("likeCount", "likeCount");
			postNode.setProperty("bloggerName", "bloggerName");*/
			
			session.save();
			logger.info("Property saved");
			logger.info("property name :" + postNode.getProperty("title").getValue().toString());

		} catch (Exception ex) {
			logger.info("Exception in save product " + ex.getMessage());
		}

		return true;
	}

	//comment will be added to the post node if it doesn't exists. postID is the node name
	public Boolean addComment(String postID, Comment comment){

		String commentPath = "content/BlogDB/jcr:content/"+postID+"/" + comment.getCommentID();

		try {
			Session session = this.repository.login(new SimpleCredentials(
					"admin", "admin".toCharArray()));

			Node root = session.getRootNode();

			if ( !root.hasNode(commentPath) ) {
				Node commentNode = root.addNode(commentPath);

				commentNode.setProperty("message", comment.getMessage());
				commentNode.setProperty("screenName", comment.getScreenName());

				session.save();
				logger.info(comment.getCommentID() + " comment node is created ");
			}


		} catch (Exception ex) {
			logger.info("Exception in save product " + ex.getMessage());
		}

		return null;
	}


}
