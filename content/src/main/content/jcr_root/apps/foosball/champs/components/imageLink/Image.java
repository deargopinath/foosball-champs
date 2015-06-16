// This class provides input to Sightly template from the corresponding dialog for the "Image" component.
// @Author: Srinivas Gopinath Parimi (srinivas.gopinath@razorfish.com) 

package apps.foosball.champs.components.imageLink;

import com.adobe.cq.sightly.WCMUse;
import org.apache.sling.api.resource.ValueMap;

public class Image extends WCMUse {

    private ValueMap properties;

    @Override
    public void activate() throws Exception {
        this.properties = getProperties();
    }
    public String getLinkUrl() {
        return properties.get("linkUrl", String.class);
    }
    public String getLinkTarget() {
        return properties.get("linkTarget", String.class);
    }
    public String getImageSource() {
        return properties.get("imageSource", String.class);
    }
    public String getHoverText() {
        return properties.get("hoverText", String.class);
    }
    public String getAltText() {
        return properties.get("altText", String.class);
    }

}


