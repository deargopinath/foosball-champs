// This class provides input to Sightly template for the "Bread Crumb" component.
// @Author: Srinivas Gopinath Parimi (srinivas.gopinath@razorfish.com)

package com.foosball.components.champs.breadcrumb;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

public class Breadcrumb extends WCMUse {

    private Page page;
    private PageManager pageManager;
    private String path;
    private String locale = "/us/en/";

    @Override
    public void activate() throws Exception {
        page = super.getCurrentPage();
        pageManager = super.getPageManager();
        path = page.getPath();
        if(path.contains("/us/es/")) {
            locale = "/us/es/";
        }
        if(path.contains("/ca/en/")) {
            locale = "/ca/en/";
        }
        if(path.contains("/ca/fr/")) {
            locale = "/ca/fr/";
        }
    }

    public LinkedHashMap<String,String> getLinks() {

        LinkedHashMap<String,String> links = new LinkedHashMap<String,String>();
        String prefix = "/content/foosball/champs" + locale;

        String[] crumbs = path.replaceAll(prefix, "").split("/");
        String currentPath = prefix;
        for(int i = 0; i < crumbs.length-1; i++) {
            currentPath += crumbs[i];
                Page p = pageManager.getContainingPage(currentPath);
                // Do not show if page is deactivated or not to be shown
                if(p != null && !p.isHideInNav()) {
                	String title = p.getNavigationTitle();
                	if (StringUtils.isEmpty(title)) {
                		title = p.getTitle();
                	}
                    links.put(title, p.getPath() + ".html");
                }
            currentPath += ("/");
        }
        return links;
    }

    public String getTail() {
    	String title = page.getNavigationTitle();
    	if (StringUtils.isEmpty(title)) {
    		title = page.getTitle();
    	}
        return title;
    }
}
