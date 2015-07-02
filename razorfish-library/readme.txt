razorfish developer library
===========================

Developed by:  Srinivas Gopinath Parimi (srinivas.gopinath@razorfish.com)a

This package provides several tags that can help developers build AEM components easily

Building
--------

Run the following command from the root directory to build and deploy the library.

$  mvn clean install -P autoInstallPackage


How to use the library in an existing AEM project:
-------------------------------------------------

1. Copy the razorfishLibrary.jsp to the existing AEM project
	
	cp razorfishLibrary.jsp ~/foosball-champs/content/src/main/content/jcr_root/apps/foosball/champs/razorfishLibrary.jsp

2. Include razorfishLibrary.jsp file in the .jsp file of the component to use the tags

	<%@include file="/apps/foosball/champs/razorfishLibrary.jsp"%>

