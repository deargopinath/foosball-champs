package com.foosball.services.common.utilities;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import org.apache.felix.scr.annotations.*;
import org.apache.felix.scr.annotations.Property;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.LoggerFactory;


import org.osgi.framework.Constants;

import javax.jcr.*;


/**
 * Created by Mohan on 6/30/2015.
 */
@Component(immediate=true)
@Service(value=WorkflowProcess.class)
@Properties({
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "Mohan Workflow"),
        @Property(name = Constants.SERVICE_VENDOR, value = "Razorfish"),
        @Property(name = "process.label", value = "Mohan Workflow")
})
public class WorkflowUrl implements WorkflowProcess {
    private org.slf4j.Logger log = LoggerFactory.getLogger(WorkflowUrl.class);

    @Reference
    ResourceResolverFactory resourceResolverFactory;


    public void execute(WorkItem item, WorkflowSession wfsession,MetaDataMap args) throws WorkflowException {
        try
        {
            log.info("Mohan Workflow");
            ResourceResolver resolver=null;
            resolver = resourceResolverFactory.getAdministrativeResourceResolver(null);
            Resource res=null;
            String componentPath = "/content/"; //path to component
            res= resolver.getResource(componentPath);

            Node nodes = res.adaptTo(Node.class);
            NodeIterator nodeIter = nodes.getNodes();
            while(nodeIter.hasNext()) {
                Node next = nodeIter.nextNode();
                PropertyIterator properties = next.getProperties();
                String propertyValue = next.getProperty("text").getValue().toString();
                propertyValue = propertyValue.replace("com", "co.in");
                log.info("Property value :" + propertyValue);
                next.setProperty("text", propertyValue);
                next.save();
                log.info("After Setting value :" + next.getProperty("text").getValue().toString());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace()  ;
        }
    }

}

