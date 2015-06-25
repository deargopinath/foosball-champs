package com.foosball.services.common.utilities;

import java.io.Serializable;

public class Product implements Serializable {
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -5472375045578459080L;
	private String productName;
	private double price;
	private String url;
	private boolean newtab;
	private String imageloc;
	private String description;
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isNewtab() {
		return newtab;
	}

	public void setNewtab(boolean newtab) {
		this.newtab = newtab;
	}

	public String getImageloc() {
		return imageloc;
	}

	public void setImageloc(String imageloc) {
		this.imageloc = imageloc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.productName);
        sb.append(this.url);
        sb.append(this.imageloc);
        sb.append(this.description);
        sb.append(this.price);
        sb.append(this.newtab);
        return sb.toString();
    }

}