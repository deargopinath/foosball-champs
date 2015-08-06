<%@include file="/apps/foosball/champs/global.jsp"%>
<%@page import="javax.jcr.Node,
                com.day.cq.commons.jcr.*"%>
<%@page pageEncoding="UTF-8" %>
<h2>Update Catalog : Result</h2>
<%
   int promotionCount = 0;
   String dataPath="/content/foosball/en/us/jcr:content";
   try {
         Resource dataResource = slingRequest.getResourceResolver().getResource(dataPath);
         Node rootNode = dataResource.adaptTo(Node.class);
         if(!rootNode.hasNode("catalog")) {
            rootNode.addNode("catalog");
            rootNode.save();
            out.println("Created catalog node");
         }
         dataPath += "/catalog";
         dataResource = slingRequest.getResourceResolver().getResource(dataPath);
         rootNode = dataResource.adaptTo(Node.class);
         String allProducts = slingRequest.getParameter("allProducts").trim();
         rootNode.setProperty("allProducts", allProducts, javax.jcr.PropertyType.STRING);
         rootNode.save();
         out.println("<h2>Updated the catalog with this data:</h2><br>" + allProducts);
   } catch (Exception ex) {
         out.println(ex.toString());
   }
%>

<FORM>
    <input Type="button" VALUE="Back" onClick="history.go(-1);return true;">
</FORM>
