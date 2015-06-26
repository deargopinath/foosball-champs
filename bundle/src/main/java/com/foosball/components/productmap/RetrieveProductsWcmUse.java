package com.foosball.components.productmap;

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

import org.apache.sling.api.scripting.SlingScriptHelper;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.foosball.services.common.utilities.Product;

public class RetrieveProductsWcmUse extends WCMUse {

	private final Logger logger = LoggerFactory.getLogger(RetrieveProductsWcmUse.class);
	private List<Product> productList;

	Session session;

	@Override
	public void activate() throws Exception {
		logger.info("Inside activate method");
		SlingScriptHelper sling = getSlingScriptHelper();
		SlingRepository repository = sling.getService(SlingRepository.class);

		session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
		
		String searchQuery = getCurrentPage().getName().toLowerCase();
		QueryManager queryManager = session.getWorkspace().getQueryManager();
		Query query = null;
		if(searchQuery.contains("gift")) {
			query = queryManager.createQuery("SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/FoosBallItems/jcr:content') AND s.price < 15", Query.JCR_SQL2);
		} else {
			query = queryManager.createQuery("SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/FoosBallItems/jcr:content') AND CONTAINS(s.title, '" + searchQuery + "')", Query.JCR_SQL2);
		}
		logger.info(query.getStatement());
		QueryResult result = query.execute();
		NodeIterator nodeIter = result.getNodes();
		productList = new ArrayList<Product>();
		while(nodeIter.hasNext()) {
			Node node = nodeIter.nextNode();
			Product product = new Product();
			PropertyIterator propertyIter = node.getProperties();
			while(propertyIter.hasNext()) {
				Property prop = propertyIter.nextProperty();
				if(!prop.getName().startsWith("jcr")) {
					if(prop.getName().equals("title")) { 
						product.setProductName(prop.getString());
					} else if(prop.getName().equals("price")) {
						product.setPrice(prop.getDecimal().doubleValue());
					} else if(prop.getName().equals("description")) {
						product.setDescription(prop.getString());
					} else {
						product.setImageloc(prop.getString());
					}
				}
			}
			productList.add(product);
		}

		// hard coding the products data here
		
		logger.info("inside activate method RetrieveProductWcmUse");
		
		/*productList = new ArrayList<Product>();
		/*logger.info("inside activate method RetrieveProductWcmUse");
		productList = new ArrayList<Product>();
		Product product1 = new Product();
		product1.setProductName("New ITSF B-ball");
		product1.setPrice(100.34);
		product1.setUrl("http://shop.foosball.com/newitsfbball.html");
		product1.setImageloc("/content/dam/foosball-champs/new_itsf_b_ball.gif");

		Product product2 = new Product();
		product2.setProductName("Tornado Tournament 3000 Table");
		product2.setPrice(2099.00);
		product2.setUrl("http://shop.foosball.com/toto30ta.html");
		product2.setImageloc("/content/dam/foosball-champs/tornado-tournament-3000-table-11.jpg");

		Product product3 = new Product();
		product3.setProductName("Red Soccer ball");
		product3.setPrice(100.34);
		product3.setUrl("http://shop.foosball.com/redsoccer.html");
		product3.setImageloc("/content/dam/foosball-champs/red-soccer-3.jpg");

		Product product4 = new Product();
		product4.setProductName("FoosGirl");
		product4.setPrice(5.00);
		product4.setUrl("http://shop.foosball.com/foosgirl.html");
		product4.setImageloc("/content/dam/foosball-champs/foosgirl-4.jpg");

		productList.add(product1);
		productList.add(product2);
		productList.add(product3);
		productList.add(product4);*/
	}

	public List<Product> getProductList() {

			logger.info("Enter into getProductList");
			productList = new ArrayList<Product>();
		/*logger.info("Enter into getProductList");
			List<Product> productList = new ArrayList<Product>();
			try {
				String searchQuery = " ";
				Page page = getCurrentPage();
				  String pageName = page.getName();		  
				  
				  if(pageName.contains("ball")){
					  searchQuery = "ball";
					 
				  }
				  else if(pageName.contains("table")){				
					  searchQuery = "table"; 
				  }
				  
				  logger.info("searchQuery :" + searchQuery );
				SlingHttpServletRequest request = getRequest();

				/*String componentPath = "/content/FoosBallItems/jcr:content"; //path to component
				Node node = request.getResourceResolver().getResource(componentPath).adaptTo(Node.class);*/
				/*Session session = request.getResourceResolver().adaptTo(Session.class);
				
				QueryManager queryManager = session.getWorkspace().getQueryManager();
				
				Query que =  queryManager.createQuery("SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/FoosBallItems/jcr:content') AND s.title like '%"+ searchQuery + "%'", Query.JCR_SQL2);
				QueryResult result = que.execute();
				
				//logger.info("Node Path ::D> " + node.getPath());
				NodeIterator nodes = result.getNodes();
//				logger.info("NOde size : " +result.getRows().getSize());
				while (nodes.hasNext()) {
					logger.info("Node Iteration : ");
					Product p = new Product();
					Node next = nodes.nextNode();
					PropertyIterator properties = next.getProperties();
					while (properties.hasNext()) {
						Property prop = properties.nextProperty();
						//Value[] values = prop.getValues();
						if (prop.getName().equals("title")) {
							p.setProductName(prop.getValue().toString());
						} else if (prop.getName().equals("price")) {
							p.setPrice(prop.getValue().getDouble());
						} else if (prop.getName().equals("description")) {
							p.setDescription(prop.getValue().toString());
						} else if (prop.getName().equals("productImage")) {
							p.setImageloc(prop.getValue().toString());
						}
					}
					productList.add(p);
				}
			} catch (Exception e) {
				logger.error("Error :::D>>" + e.getMessage());
			}
			logger.info("Size of Product list" + productList.size());*/
		return productList;

	}
}
