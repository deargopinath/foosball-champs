<%@include file="/apps/foosball/common/global.jsp"%>
<%@page import="javax.jcr.Node,
                com.day.cq.commons.jcr.*"%>
<%@page pageEncoding="UTF-8" %>
<h2>Create Promotion : Result</h2>
<%
   int promotionCount = 0;
   String dataPath="/content/data/products/bridgestone/jcr:content";
   try {
         Resource dataResource = slingRequest.getResourceResolver().getResource(dataPath);
         Node rootNode = dataResource.adaptTo(Node.class);
         if(!rootNode.hasNode("promotions")) {
            rootNode.addNode("promotions");
            rootNode.save();
            out.println("Created promotions node");
         }
         dataPath += "/promotions";
         dataResource = slingRequest.getResourceResolver().getResource(dataPath);
         rootNode = dataResource.adaptTo(Node.class);
         String json = "{";
         String locale = slingRequest.getParameter("locale").trim();
         String subBrand = slingRequest.getParameter("subBrand").trim();
         String newPromotion = (locale + "-" + subBrand);
         for(String s : slingRequest.getParameterMap().keySet()) {
             if(s.equals("submitButton")) {
                 continue;
             }
             json += ("\"" + s + "\":\"" + slingRequest.getParameter(s).trim() + "\",");
         }
         json += ("}");
         json = (json.replaceAll(",}", "}"));
         rootNode.setProperty(newPromotion, json, javax.jcr.PropertyType.STRING);
         rootNode.save();
         PropertyIterator allPromotions = rootNode.getProperties();
         while (allPromotions.hasNext()) {
             Property p = allPromotions.nextProperty();
             if(p.getDefinition().isMultiple() || p.getName().contains("jcr:")) {
                 continue;
             }
             promotionCount++;
             out.println("<br> Promotion # " + promotionCount + " : " + p.getName());
             out.println("<br>" + p.getString() + "<hr>");
         }

   } catch (Exception ex) {
         out.println(ex.toString());
   }
   out.println("<br># Promotions in the database = " + promotionCount);
%>

<FORM>
    <input Type="button" VALUE="Back" onClick="history.go(-1);return true;">
</FORM>
