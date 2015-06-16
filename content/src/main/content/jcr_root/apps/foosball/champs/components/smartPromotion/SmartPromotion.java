// This class provides input to Sightly template from the corresponding dialog for the "SmartPromotion" component.
// @Author: Srinivas Gopinath Parimi (srinivas.gopinath@razorfish.com) 

package apps.foosball.champs.components.smartPromotion;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.ValueMap;
import java.util.*;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.commons.json.JSONException;
import javax.jcr.*;
import com.day.cq.commons.jcr.*;

public class SmartPromotion extends WCMUse {

    private ValueMap properties;
    private Page page;
    private PageManager pageManager;
    private String path;
    private String locale = "en-us";
    String pageName;

    HashMap<String,String> promoMap = new HashMap<String,String>();

    @Override
    public void activate() throws Exception {
        this.properties = getProperties();
        page = super.getCurrentPage();
        pageManager = super.getPageManager();
        path = page.getPath().toLowerCase();
        pageName = page.getName().toLowerCase();
    }

    public String getLocale() {
        String pageLocale = "EN-US";
        if(path.contains("/us/es/")) {
            return "ES-US";
        }
        if(path.contains("/ca/en/")) {
            return "EN-CA";
        }
        if(path.contains("/ca/fr/")) {
            return "FR-CA";
        }
        return pageLocale;
    }

    public String getSubBrand() {
        String subBrand = "green";
        if(path.contains("blue")) {
            return "blue";
        }
        return subBrand;
    }

    public String getPromoJson() {
        String json = null;
        String promoName = (getLocale() + "-" + getSubBrand()).toLowerCase();
        Page dataPage = pageManager.getContainingPage("/content/foosball/champs/products");
        ValueMap promotions = dataPage.getProperties("promotions");
        if(promotions.containsKey(promoName)) {
            json = promotions.get(promoName).toString();
            try{
                JSONObject smartPromotion = new JSONObject(json);
                promoMap.put("headlineText", smartPromotion.getString("headlineText"));
                promoMap.put("bodyText", smartPromotion.getString("bodyText"));
                promoMap.put("colorTheme", smartPromotion.getString("colorTheme"));
                promoMap.put("buttonText", smartPromotion.getString("buttonLabel"));
                promoMap.put("buttonClass", smartPromotion.getString("buttonClass"));
                promoMap.put("linkUrl", smartPromotion.getString("linkUrl"));
                promoMap.put("linkTarget", smartPromotion.getString("linkTarget"));
                promoMap.put("brandingImage", smartPromotion.getString("brandingImage"));
                promoMap.put("backgroundImage", smartPromotion.getString("backgroundImage"));
            }catch(Exception ex){
                return null;
            }
        }
        return json;
    }

    public String getHeadlineText() {
        getPromoJson();
        return promoMap.get("headlineText");
    }

public String getBackgroundImage() {
        return promoMap.get("backgroundImage");
    }
    public String getColorTheme() {
        return promoMap.get("colorTheme");
    }
    public String getBodyText() {
        return promoMap.get("bodyText");
    }
    public String getButtonText() {
        return promoMap.get("buttonText");
    }
    public String getLinkUrl() {
        return promoMap.get("linkUrl");
    }
    public String getLinkTarget() {
        return promoMap.get("linkTarget");
    }
    public String getButtonClass() {
        return promoMap.get("buttonClass");
    }
    public String getBrandingImage() {
        return promoMap.get("brandingImage");
    }
    public String getBrandingImageAlt() {
        return promoMap.get("brandingImageAlt");
    }

}


