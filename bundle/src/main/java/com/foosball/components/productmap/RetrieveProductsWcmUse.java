package com.foosball.components.productmap;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.foosball.services.common.utilities.Product;

public class RetrieveProductsWcmUse extends WCMUse {

	private final Logger logger = LoggerFactory.getLogger(RetrieveProductsWcmUse.class);
	private List<Product> productList;
	
	@Override
	public void activate() throws Exception {
		
		// hard coding the products data here
		logger.info("inside activate method RetrieveProductWcmUse");
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
		productList.add(product4);
	}

	public List<Product> getProductList() {
		return productList;
	}
}
