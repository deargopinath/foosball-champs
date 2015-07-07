package com.foosball.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.Value;
import javax.jcr.ValueFactory;
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
		List<Post> postList = null;
		try {
			Session session = this.repository.login(new SimpleCredentials(
					"admin", "admin".toCharArray()));

			QueryManager queryManager = session.getWorkspace().getQueryManager();

			Query query = null;
			query = queryManager.createQuery("SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/BlogDB/jcr:content') AND NAME() LIKE 'post#%' order by  s.commentCount DESC, s.likeCount DESC, s.postArticle ASC",
					Query.JCR_SQL2);
			if(mode.equals("trendingPosts")){
				query.setLimit(3);
			}

			QueryResult result = query.execute();

			// logger.info("Node Path ::D> " + node.getPath());
			NodeIterator nodes = result.getNodes();
			// logger.info("NOde size : " +result.getRows().getSize());

			postList = new ArrayList<Post>();
			List<Comment> commentList = new ArrayList<Comment>();
			Post p = null;
			Comment c = null;

			while (nodes.hasNext()) {
				logger.info("Node Iteration : ");
				p = new Post();
				Node nextNode = nodes.nextNode();
				PropertyIterator properties = nextNode.getProperties();
				while (properties.hasNext()) {
					Property prop = properties.nextProperty();
					// Value[] values = prop.getValues();
					if (prop.getName().equals("title")) {
						p.setTitle(prop.getValue().toString());
					} else if (prop.getName().equals("screenName")) {
						p.setScreenName(prop.getValue().toString());
					} else if (prop.getName().equals("postArticle")) {
						p.setPostArticle(prop.getValue().toString());
					} else if (prop.getName().equals("commentCount")) {
						p.setCommentCount((int) prop.getValue().getLong());
					} else if (prop.getName().equals("likeCount")) {
						p.setLikeCount((int) prop.getValue().getLong());
					} 
				}
				p.setPostId(nextNode.getName());
				//Below code is used to set array of comments values
				NodeIterator commentNodes = nextNode.getNodes();

				while(commentNodes.hasNext()){
					Node commentNode = commentNodes.nextNode();

					PropertyIterator commentProperties = commentNode.getProperties();
					c = new Comment();
					c.setCommentID(commentNode.getName());
					while(commentProperties.hasNext()){
						Property prop = commentProperties.nextProperty();
						if (prop.getName().equals("message")) {
							c.setMessage(prop.getValue().toString());
						} else if (prop.getName().equals("screenName")) {
							c.setScreenName(prop.getValue().toString());
						}
					}
					commentList.add(c);
				}
				p.setComments(commentList);
				postList.add(p);
			}
		} catch (Exception e) {
			logger.error("Error :::" + e.getMessage());
		}
		logger.info("Size of Post list" + postList.size());

		return postList;

	}

	public boolean addPost(Post post) {
		logger.info("enter the addpost() ****");
		System.out.println("enter the addpost() ****");

		try {
			Session session = this.repository.login(new SimpleCredentials(
					"admin", "admin".toCharArray()));

			QueryManager queryManager = session.getWorkspace()
					.getQueryManager();

			Query query = queryManager.createQuery("SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/BlogDB/jcr:content') AND NAME() LIKE 'post#%'", Query.JCR_SQL2);

			QueryResult result = query.execute();
			long postCount = result.getRows().getSize();
			logger.info("Post Count :" + postCount);
			System.out.println("Post Count :" + postCount);

			Node root = session.getRootNode();
			Node content = root.getNode("content");

			if (!content.hasNode("BlogDB")) {
				content.addNode("BlogDB", "sling:OrderedFolder");
				logger.info("Created parent node");
				System.out.println("Created parent node");
			}
			Node blogNode = content.getNode("BlogDB");

			if (!blogNode.hasNode("jcr:content")) {
				blogNode.addNode("jcr:content", "nt:unstructured");
				logger.info("Created jcr node");
				System.out.println("Created jcr node");
			}

			Node jcrContentNode = blogNode.getNode("jcr:content");

			Node postNode = jcrContentNode.addNode("post#" + (postCount + 1), "nt:unstructured");

			logger.info("Created post node :" + postNode.getName());
			System.out.println("Created post node :" + postNode.getName());

			postNode.setProperty("title", post.getTitle());
			postNode.setProperty("postArticle", post.getPostArticle());
			postNode.setProperty("commentCount", post.getCommentCount());
			postNode.setProperty("likeCount", post.getLikeCount());
			postNode.setProperty("screenName", post.getScreenName());

			/*postNode.setProperty("title", "AEM Test");
			postNode.setProperty("postArticle", "postArticle");
			postNode.setProperty("commentCount", "commentCount");
			postNode.setProperty("likeCount", "likeCount");
			postNode.setProperty("bloggerName", "bloggerName");*/

			session.save();
			logger.info("Property saved");
			System.out.println("Property saved");
			logger.info("property name :" + postNode.getProperty("title").getValue().toString());

		} catch (Exception ex) {
			logger.info("Exception in save product " + ex.getMessage());
			ex.printStackTrace();
		}

		return true;
	}

	//comment will be added to the post node if it doesn't exists. postID is the node name
	public boolean addComment(String postID, Comment comment){
		logger.info("Inside addCommment method :" + postID);
		String commentPath = "content/BlogDB/jcr:content/"+postID+"/" + getCommentID(postID);
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
				return true;
			}
		} catch (Exception ex) {
			logger.info("Exception in save product " + ex.getMessage());
		}

		return false;
	}


	private String getCommentID(String postID){
		try{
			Session session = this.repository.login(new SimpleCredentials(
					"admin", "admin".toCharArray()));

			QueryManager queryManager = session.getWorkspace()
					.getQueryManager();

			Query query = queryManager
					.createQuery("SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/BlogDB/jcr:content" + postID + "') AND NAME() LIKE 'comment#%'",
							Query.JCR_SQL2);

			QueryResult result = query.execute();
			long commentCount = result.getRows().getSize();
			logger.info("Comment Count :" + commentCount);

			String commentName = "comment#"+commentCount;
			return commentName;
		} catch (Exception ex) {
			logger.info("Exception in save product " + ex.getMessage());
		}

		return null;
	}

	public long increaseLikeCount(String postId, String screenName) {
		long likeCount = 0;
		try {
			Session session = this.repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
			Node postNode = session.getNode("/content/BlogDB/jcr:content/" + postId);
			logger.info("Post Node :" + postNode);
			ValueFactory vf = session.getValueFactory();
			Value screenNameValue = vf.createValue(screenName);
			logger.info("ScreenName :" + screenName);
			if(postNode.hasProperty("likedBy")) {
				Property likedByProp = postNode.getProperty("likedBy");
				Value[] likedBy = likedByProp.getValues();
				logger.info("LikedBy :" + likedBy);
				List<String> values = new ArrayList<String>();
				for(Value val: likedBy) {
					values.add(val.toString());
				}
				if(values.contains(screenName)) {
					logger.info("this screenName is already liked");
					likeCount = -1;
				} else {
					Value[] likedValues = new Value[values.size() + 1];
					for(int i = 0; i < values.size(); i++) {
						likedValues[i] = vf.createValue(values.get(i));
					}
					likedValues[likedValues.length - 1] = screenNameValue;
					logger.info("The LikedBy Values:" + likedValues);
					postNode.setProperty("likedBy", likedValues);
					Property likeCountProp = postNode.getProperty("likeCount");
					postNode.setProperty("likeCount", likeCountProp.getLong() + 1);
					logger.info("LikeCountProp :" + likeCountProp);
					likeCount = likeCountProp.getLong();
				}
			} else {
				// likedBy property is not available
				postNode.setProperty("likedBy", new Value[] {screenNameValue});
				likeCount = 1;
			}
			session.save();
			return likeCount;
		} catch (LoginException e) {
			e.printStackTrace();
			logger.error("LoginException :", e);
		} catch (RepositoryException e) {
			e.printStackTrace();
			logger.error("RepositoryException :", e);
		}
		return likeCount;
	}

	public List<Comment> getCommentList(String postId) {
		logger.info("Fetching the comments of Post :" + postId);
		List<Comment> commentList = null;
		try {
			commentList = new ArrayList<Comment>();
			Session session = this.repository.login(new SimpleCredentials("admin", "admin".toCharArray()));

			QueryManager queryManager = session.getWorkspace().getQueryManager();

			Query query = null;
			query = queryManager.createQuery("SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/BlogDB/jcr:content/" + postId + "') AND NAME() LIKE 'comment#%'", Query.JCR_SQL2);
			QueryResult result = query.execute();

			// logger.info("Node Path ::D> " + node.getPath());
			NodeIterator nodes = result.getNodes();
			Comment comment = null;

			while (nodes.hasNext()) {
				Node commentNode = nodes.nextNode();
				logger.info("Node Iteration :" + commentNode.getName());
				PropertyIterator commentProperties = commentNode.getProperties();
				comment = new Comment();
				comment.setCommentID(commentNode.getName());
				while(commentProperties.hasNext()){
					Property prop = commentProperties.nextProperty();
					if (prop.getName().equals("message")) {
						comment.setMessage(prop.getValue().toString());
					} else if (prop.getName().equals("screenName")) {
						comment.setScreenName(prop.getValue().toString());
					}
				}
				commentList.add(comment);
			}
		} catch (Exception e) {
			logger.error("Error :::" + e.getMessage());
		}
		return commentList;
	}
}
