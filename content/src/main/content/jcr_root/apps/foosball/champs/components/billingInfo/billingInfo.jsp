<%@include file="/apps/foosball/champs/global.jsp"%>
<%@include file="/apps/foosball/champs/razorfishLibrary.jsp"%>

<slingx:property var="heading" name="heading" resourcePath="."/>
<slingx:property var="customer" name="customerName" resourcePath="."/>
<slingx:property var="addr" name="address" resourcePath="."/>
<slingx:property var="zip" name="zip" resourcePath="."/>
<slingx:property var="phone" name="phone" resourcePath="."/>
<slingx:property var="email" name="email" resourcePath="."/>
<slingx:property var="account" name="account" resourcePath="."/>

<div class="fls-inner-block" style="border:1px solid black">
    <div class="fls-inner-content">
        <h3><rf:write value='${heading}' defaultValue='- Heading here -' editMode='${isEditMode}'/></h3>
        <div class="grid">
            <div class="col-1">
				<rf:write value='${customer}' defaultValue='- Customer name -' editMode='${isEditMode}'/><br>
                <rf:write value='${addr}' defaultValue='- Address here -' editMode='${isEditMode}'/><br>
                <rf:write value='${zip}' defaultValue='- ZIP Label here -' editMode='${isEditMode}'/><br>
                <rf:write value='${phone}' defaultValue='- Phone number -' editMode='${isEditMode}'/><br>
                <rf:write value='${email}' defaultValue='- Email address -' editMode='${isEditMode}'/><br>
            </div>
        </div>
    </div>
</div>

<hr>
<p>
    ${heading} <br> ${addr} &nbsp; ${zip} <br> ${phone} <br> ${email}
</p>
