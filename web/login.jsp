<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>



<html:html>
    <head>
      <title>Login</title>
    </head>
  
    <body bgcolor=white>
  
    <html:form action="Login.do">

        <h3>Please enter your credentials then click login:</h3>
        UserName:<form:text property="un"/><br>
        <input type="text" required placeholder="Your Username" name="username" id="username">
        <br> 
        enter password:<form:password property="password"/><br>
        <input type="password" required placeholder="Your Password" name="password" id="password">
        
        <form:submit value="Login"/>

    </html:form>
        <!--PLACE MESSAGE HERE IF LOGIN INFO IS INCORRECT-->

        <div style="color:red">
            <html:errors />
        </div>
        <h3 style="color:red"><em><c:out value="${notInvalid}"/></em></h3>
        <h3 style="color:red"><em><c:out value="${errorMessage}"/></em></h3>

    </body>
</html:html>