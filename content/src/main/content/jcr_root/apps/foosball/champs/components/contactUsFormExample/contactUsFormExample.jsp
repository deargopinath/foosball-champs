<%@include file="/apps/foosball/champs/global.jsp"%>
<form id="contactUsForm" name="contactUsForm"
      action="<%=currentNode.getPath()%>.ThankYou.html"
      method="post">
    <p> Enter your details and click 'Contact Us'</p>
    <input id="submitButton" name="submitButton" type="submit" value="Contact Us"
           style="background-color: #081E41; color: #ffffff; font-size:1.0em; height:30px"/>
</form>