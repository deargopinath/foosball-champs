<%@include file="/apps/foosball/champs/global.jsp"%>
<c:choose>
    <c:when test="${isEditMode}">
        <h2>Smart Promotions</h2>
        <p>Form to create promotions</p>
        <form id="createPromotion" name="createPromotion" action="<%=currentNode.getPath()%>.createPromotion.html" method="post">
          <table style="width:600px">
            <tr>
              <td><b>Locale *</b></td>
              <td>
                  <select id="locale" name="locale">
                    <option value="en-us" selected="selected">USA English</option>
                    <option value="es-us">USA Espanol</option>
                    <option value="en-ca">Canada English</option>
                    <option value="fr-ca">Canada French</option>
                  </select>
              </td>
            </tr>
            <tr>
              <td><b>Applicable Sub-Brand*</b></td>
              <td>
                  <select id="subBrand" name="subBrand">
                      <option value="blizzak" selected="selected">Blizzak</option>
                      <option value="driveguard">DriveGuard</option>
                      <option value="dueler">Dueler</option>
                      <option value="duravis">Duravis</option>
                      <option value="ecopia">Ecopia</option>
                      <option value="potenza">Potenza</option>
                      <option value="turanza">Turanza</option>
                  </select>
              </td>
            </tr>
            <tr>
               <td><b>Heading *</b></td>
               <td>
                   <input id="headlineText"  name="headlineText"  type="text" style="width:250px"  required/>
               </td>
            </tr>
            <tr>
               <td><b>Body *</b></td>
               <td>
                   <textarea id="bodyText" name="bodyText" rows="3" cols="20" style="width:250px" required></textarea>
               </td>
            </tr>
            <tr>
               <td><b>Button Label *</b></td>
               <td>
                   <input id="buttonLabel"  name="buttonLabel"  type="text" style="width:250px" required/>
               </td>
            </tr>
            <tr>
               <td><b>Button URL *</b></td>
               <td><input id="linkUrl"  name="linkUrl"  type="text" style="width:250px" required/></td>
            </tr>
            <tr>
               <td><b>Open in a new Tab?</b></td>
               <td>
                    <select id="linkTarget" name="linkTarget">
                        <option value="false" selected="selected">No</option>
                        <option value="true">Yes</option>
                    </select>
               </td>
            </tr>
            <tr>
               <td><b>Button Type</b></td>
               <td>
                   <select id="buttonClass"  name="buttonClass">
                       <option value="button-medium-solid-champs-red">Red</option>
                       <option value="button-medium-solid-champs-green">Green</option>
                       <option value="button-medium-solid-champs-blue">Blue</option>
                       <option value="button-medium-solid-champs-black">Black</option>
                       <option value="button-medium-transparent-champs-black" >Black Outline</option>
                       <option value="button-medium-solid-champs-white" >White</option>
                       <option value="button-medium-transparent-champs-white" selected="selected">White Outline</option>
                   </select>
               </td>
            </tr>
            <tr>
                <td><b>Text Color</b></td>
                <td>
                    <select id="colorTheme" name="colorTheme">
                        <option value="light" selected="selected">White</option>
                        <option value="dark">Black</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><b>Foreground Image *</b></td>
                <td><input id="brandingImage"  name="brandingImage"  type="text" style="width:250px" required/></td>
            </tr>
            <tr>
                <td><b>Image Alt Text *</b></td>
                <td><input id="brandingImageAlt"  name="brandingImageAlt"  type="text" style="width:250px" required/></td>
            </tr>
            <tr>
                <td><b>Background Image *</b></td>
                <td><input id="backgroundImage"  name="backgroundImage"  type="text"  style="width:250px" required/></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input id="submitButton" name="submitButton" type="submit" value="Create Promotion"
                        style="background-color: #081E41; color: #ffffff; font-size:1.0em; height:30px"/>
                </td>
            </tr>
          </table>
        </form>
    </c:when>
    <c:otherwise>
        <cq:include script="smartPromotion.html"/>
    </c:otherwise>
</c:choose>

