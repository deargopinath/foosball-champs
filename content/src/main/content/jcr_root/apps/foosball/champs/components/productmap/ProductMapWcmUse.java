package apps.foosball.champs.components.productmap;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Value;

import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.foosball.services.common.utilities.Product;

public class ProductMapWcmUse extends WCMUse {

	private final Logger logger = LoggerFactory.getLogger(ProductMapWcmUse.class);

	private List<Product> products;

	@Override
	public void activate() throws Exception {
        System.out.println("Inside activate method WCMUse Component");
		logger.info("Inside activate method WCMUse Component");
		try {
			Property prop = null;
			Node currentNode = getResource().adaptTo(Node.class);
			if(currentNode.hasProperty("map")){
				prop = currentNode.getProperty("map");
			}
			if (prop != null) {
				JSONObject productJson = null;
				Value[] values = null;
				if(prop.isMultiple()) {
					values = prop.getValues();
				} else {
					values = new Value[1];
					values[0] = prop.getValue();
				}
                logger.info("The elements :" + values.length);
				products = new ArrayList<Product>();
				Product product = null; 
				for (Value val : values) {
                    logger.info("value :" + val);
					productJson = new JSONObject(val.getString());
                    logger.info("Product JSON :" + productJson.getString("productName"));
                    product = new Product();
					logger.info("product :" + product);

                    product.setProductName(productJson.getString("productName"));
                    product.setPrice(productJson.getString("price"));
                    product.setUrl(productJson.getString("url"));
                    product.setNewtab(productJson.getBoolean("newTab"));
                    product.setDescription(productJson.getString("description"));
                    product.setImageloc(productJson.getString("imageloc"));
					products.add(product);
				}

                logger.info("products List size :" + products.size());
			} else {
				logger.info("Map is empty");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Product> getProducts() {
		return products;
	}
}