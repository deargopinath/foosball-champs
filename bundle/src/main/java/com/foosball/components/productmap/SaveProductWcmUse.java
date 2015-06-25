package com.foosball.components.productmap;

import org.apache.sling.api.SlingHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;

/**
 * To save the products in /content/products location
 * @author rammoole
 *
 */
public class SaveProductWcmUse extends WCMUse {

	private final Logger logger = LoggerFactory.getLogger(SaveProductWcmUse.class);
	
	private String productName;
	
	@Override
	public void activate() throws Exception {
		SlingHttpServletRequest request = getRequest();
		String productName = request.getParameter("productName");
		String productPrice = request.getParameter("productPrice");
		logger.info(productName + "|" + productPrice);
		productName = "Foosball Table";
	}

	public String getProductName() {
		return productName;
	}
}
