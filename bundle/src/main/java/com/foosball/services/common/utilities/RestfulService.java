package com.foosball.services.common.utilities;

public interface RestfulService {

    /**
     * Returns the entire product catalog
     * Example:  http://localhost:4502/bin/products?category=all
     */
    public String getAllProducts();


    /**
     * Show all Foosball Tables
     * Example:  http://localhost:4502/bin/products?category=tables
     */
    public String getTables();

    /**
     * Show all Accessories
     * Example:  http://localhost:4502/bin/products?category=accessories
     */
    public String getAccessories();

}

