<%@ page import="org.apache.sling.commons.json.JSONObject" %>
<%@ page import="java.io.PrintWriter" %>
<%@include file="/libs/foundation/global.jsp" %>
<%@page session="false" %>

<h3>Product Map - MultiField Panel</h3>
<hr>
<%
    try {
        Property prop = null;
        if(currentNode.hasProperty("map")){
            prop = currentNode.getProperty("map");
        }
        if (prop != null) {
            JSONObject product = null;
            Value[] values = null;
            if(prop.isMultiple()){
                values = prop.getValues();
            }else{
                values = new Value[1];
                values[0] = prop.getValue();
            }
            for (Value val : values) {
                product = new JSONObject(val.getString());
                out.println("Product = " + product.get("productName") + ", ");
                out.println("Price = " + product.get("price") + ", ");
                out.println("Link = " + product.get("url") + ", ");
                out.println("New Window = " + product.get("newTab") + "<br>");
            }
        } else {
            out.println("Map is empty");
        }
    } catch (Exception ex) {
        out.println(ex.toString());
    }
%>
