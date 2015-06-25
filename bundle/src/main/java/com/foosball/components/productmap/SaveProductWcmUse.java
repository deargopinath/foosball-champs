package com.foosball.components.productmap;

import javax.jcr.Node;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;

/**
 * To save the products in /content/products location
 * 
 * @author rammoole
 *
 */
public class SaveProductWcmUse extends WCMUse {

	private final Logger logger = LoggerFactory
			.getLogger(SaveProductWcmUse.class);

	private String productName;

	@Override
	public void activate() throws Exception {
		SlingHttpServletRequest request = getRequest();
		productName = request.getParameter("productName");
		//save details
		saveProduct(request);
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void saveProduct(SlingHttpServletRequest request) {
		logger.info("Enter into the Save product");
		String dataPath = "/content";
		String productName = request.getParameter("productName");
		String productPrice = request.getParameter("productPrice");
		String productDescription = request.getParameter("productDescription");
		String productImage = request.getParameter("productImage");
		try {

			Resource dataResource = request.getResourceResolver().getResource(
					dataPath);
			logger.info("dataResource  : " + dataResource.getName());
			Node rootNode = dataResource.adaptTo(Node.class);
			if (!rootNode.hasNode("FoosBallItems")) {

				rootNode.addNode("FoosBallItems");
				rootNode.save();
				logger.info("Created parent node");
			}

			dataPath += "/FoosBallItems";
			dataResource = request.getResourceResolver().getResource(dataPath);
			rootNode = dataResource.adaptTo(Node.class);
			if (!rootNode.hasNode("jcr:content")) {

				rootNode.addNode("jcr:content", "nt:unstructured");
				rootNode.save();
				logger.info("Created jcr node");
			}

			dataPath += "/jcr:content";
			dataResource = request.getResourceResolver().getResource(dataPath);
			rootNode = dataResource.adaptTo(Node.class);
			if (!rootNode.hasNode(productName)) {

				rootNode.addNode(productName, "nt:unstructured");
				rootNode.save();
				logger.info("Created productName node");
			}
			dataPath += "/" + productName;
			dataResource = request.getResourceResolver().getResource(dataPath);
			rootNode = dataResource.adaptTo(Node.class);
			rootNode.setProperty("title", productName);
			rootNode.setProperty("price", productPrice);
			rootNode.setProperty("description", productDescription);
			rootNode.setProperty("productImage",productImage);
			rootNode.save();
			logger.info("Property saved");
			logger.info("property name "
					+ rootNode.getProperty("title").getValue().toString());

		} catch (Exception ex) {
			logger.info("Exception in save product " + ex.getMessage());
		}
	}
}