<%@include file="/apps/foosball/champs/global.jsp"%>
<%@page import="javax.jcr.Node,
com.day.cq.commons.jcr.*"%>
<%@page pageEncoding="UTF-8" %>
<h2>Catalog Update Form</h2>
<form id="updateCatalog" name="updateCatalog" action="<%=currentNode.getPath()%>.updateCatalog.html" method="post">
    <b>All Products JSON *</b>
    <textarea id="allProducts" name="allProducts" rows="20" cols="20" style="width:640px" required>
    </textarea>
    <br>
	<input id="submitButton" name="submitButton" type="submit" value="Update Catalog"/>

</form>