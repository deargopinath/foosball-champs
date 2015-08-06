package com.foosball.services.common.utilities;

        import com.day.cq.commons.jcr.JcrUtil;
        import com.day.cq.commons.jcr.JcrConstants;
        import com.google.gson.Gson;

        import java.io.InputStream;
        import java.io.StringWriter;
        import java.lang.StringBuilder;
        import java.nio.charset.Charset;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Dictionary;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
        import java.util.Set;
        import java.util.TreeSet;

        import javax.jcr.Node;
        import javax.jcr.RepositoryException;
        import javax.jcr.Session;
        import javax.jcr.query.*;

        import org.apache.commons.io.IOUtils;
        import org.apache.felix.scr.annotations.Activate;
        import org.apache.felix.scr.annotations.Component;
        import org.apache.felix.scr.annotations.Property;
        import org.apache.felix.scr.annotations.Reference;
        import org.apache.felix.scr.annotations.Service;
        import org.apache.jackrabbit.commons.JcrUtils;
        import org.apache.sling.api.resource.ResourceResolver;
        import org.apache.sling.api.resource.ResourceResolverFactory;
        import org.apache.sling.commons.json.JSONException;
        import org.apache.sling.commons.osgi.PropertiesUtil;
        import org.apache.sling.jcr.api.SlingRepository;
        import org.json.simple.JSONArray;
        import org.json.simple.JSONObject;
        import org.json.simple.JSONValue;
        import org.json.simple.parser.JSONParser;
        import org.osgi.service.component.ComponentContext;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;


@Component(immediate = true, metatype = true, label = "MMY Data Service")
@Service(MMYDataService.class)

public class RestfulServiceImpl implements RestfulService {

    private static Logger LOGGER = LoggerFactory
            .getLogger(RestfulServiceImpl.class);

    private ResourceResolver resourceResolver;

    @Reference
    private SlingRepository repository;

    @Reference
    private ResourceResolverFactory resolverFactory;


    private Session getSession() throws RepositoryException {
        return repository.loginAdministrative(null);
    }

    public String getAllProducts() {

        String json = "{\"products\":\"none\"}";
        Session session = null;
        String path = "content/foosball/en/us/jcr:content/catalog";
        try{
            session = getSession();
            Node rootNode = session.getRootNode();
            Node sourceNode = rootNode.getNode(path);
            if(sourceNode.hasProperty("allProducts")) {
                json = sourceNode.getProperty("allProducts").getString();
            }
        } catch (RepositoryException e) {
            LOGGER.error("Data source " + path + "could not be read.", e);
        } finally {
            if (session != null && session.isLive()) {
                session.logout();
            }
        }
        return json;
    }


    public String getTables() {

        String json = "{\"products\":\"none\"}";
        Session session = null;
        String path = "content/foosball/en/us/jcr:content/catalog";
        try{
            session = getSession();
            Node rootNode = session.getRootNode();
            Node sourceNode = rootNode.getNode(path);
            if(sourceNode.hasProperty("tables")) {
                json = sourceNode.getProperty("tables").getString();
            }
        } catch (RepositoryException e) {
            LOGGER.error("Data source " + path + "could not be read.", e);
        } finally {
            if (session != null && session.isLive()) {
                session.logout();
            }
        }
        return json;
    }

    public String getAccessories() {

        String json = "{\"products\":\"none\"}";
        Session session = null;
        String path = "content/foosball/en/us/jcr:content/catalog";
        try{
            session = getSession();
            Node rootNode = session.getRootNode();
            Node sourceNode = rootNode.getNode(path);
            if(sourceNode.hasProperty("accessories")) {
                json = sourceNode.getProperty("accessories").getString();
            }
        } catch (RepositoryException e) {
            LOGGER.error("Data source " + path + "could not be read.", e);
        } finally {
            if (session != null && session.isLive()) {
                session.logout();
            }
        }
        return json;
    }
}
