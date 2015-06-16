package com.foosball.components.champs.promotion;

import com.adobe.cq.sightly.WCMUse;
import org.apache.sling.api.resource.ValueMap;

public class Promotion extends WCMUse {

    private static final String HEADLINE = "headline";
    private static final String BODY = "body";
    private static final String CTA = "cta";
    private static final String CTA_URL = "ctaUrl";
    private static final String TARGET = "target";
    private static final String BRANDING_IMAGE = "fileReference";
    private static final String BRANDING_IMAGE_ALT = "brandingImageAlt";
    private static final String BACKGROUND_IMAGE = "fileReferenceBackground";
    private static final String BACKGROUND_COLOR = "backgroundColor";
    private static final String DATA_OVERLAY = "dataOverlay";
    private static final String COLOR_THEME = "colorTheme";
    private static final String CARET_OPTION = "caretOption";
    private static final String FULL_BLEED = "fullBleed";

    private ValueMap properties;

    /**
     * The initialization method (called by sightly).
     */
    @Override
    public void activate() throws Exception {
        this.properties = getProperties();
    }

    public String getHeadlineText() {
        return properties.get(HEADLINE, "-- Promotion Component Here --");
    }

    public String getBodyText() {
        return properties.get(BODY, String.class);
    }

    public String getButtonText() {
        return properties.get(CTA, String.class);
    }

    public String getLinkUrl() {
        return properties.get(CTA_URL, String.class);
    }

    public boolean getLinkTarget() {
        return properties.get(TARGET, false);
    }

    public String getBrandingImage() {
        return properties.get(BRANDING_IMAGE, String.class);
    }

    public String getBrandingImageAlt() {
        return properties.get(BRANDING_IMAGE_ALT, String.class);
    }

    public String getBackgroundImage() {
        return properties.get(BACKGROUND_IMAGE, String.class);
    }

    public String getBackgroundColor() {
        return properties.get(BACKGROUND_COLOR, String.class);
    }

    public String getDataOverlay() {
        return properties.get(DATA_OVERLAY, "false");
    }

    public String getColorTheme() {
        return properties.get(COLOR_THEME, String.class);
    }

    public String getCaretOption() {
        return properties.get(CARET_OPTION, String.class);
    }

    public boolean getFullBleed() {
        return properties.get(FULL_BLEED, false);
    }
}