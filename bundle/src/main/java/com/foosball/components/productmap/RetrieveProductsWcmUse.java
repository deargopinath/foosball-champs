package com.foosball.components.productmap;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;
import com.foosball.services.common.utilities.Product;

public class RetrieveProductsWcmUse extends WCMUse {

	private final Logger logger = LoggerFactory.getLogger(RetrieveProductsWcmUse.class);
	private List<Product> productList;

	Session session;

	@Override
	public void activate() throws Exception {
		//logger.info("Inside activate method");
		/*SlingScriptHelper sling = getSlingScriptHelper();
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
		}*/

		// hard coding the products data here

		logger.info("inside activate method RetrieveProductWcmUse");
	}

	public List<Product> getProductList() {

		logger.info("Enter into getProductList");

		productList = new ArrayList<Product>();
		logger.info("Enter into getProductList");
		//List<Product> productList = new ArrayList<Product>();
		try {
			String searchQuery = " ";
			Page page = getCurrentPage();
			String pageName = page.getName();

			if(pageName.contains("ball")){
				searchQuery = "ball";
			}
			else if(pageName.contains("table")){
				searchQuery = "table";
			} else if(pageName.contains("gift")) {
				searchQuery = "gift";
			}

			logger.info("searchQuery :" + searchQuery );

			/*String componentPath = "/content/FoosBallItems/jcr:content"; //path to component
			Node node = request.getResourceResolver().getResource(componentPath).adaptTo(Node.class);*/

			Session session = getRequest().getResourceResolver().adaptTo(Session.class);

			QueryManager queryManager = session.getWorkspace().getQueryManager();

			Query query = null;
			if(searchQuery.equals("gift")) {
				query = queryManager.createQuery("SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/FoosBallItems/jcr:content') AND s.price < 15", Query.JCR_SQL2);
			} else {
				query =  queryManager.createQuery("SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/FoosBallItems/jcr:content') AND s.title like '%"+ searchQuery + "%'", Query.JCR_SQL2);
			}

			QueryResult result = query.execute();

			// 	logger.info("Node Path ::D> " + node.getPath());
			NodeIterator nodes = result.getNodes();
			//	logger.info("NOde size : " +result.getRows().getSize());
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
		logger.info("Size of Product list" + productList.size());
		return productList;

	}
}
