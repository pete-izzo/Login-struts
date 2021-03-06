<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%@ taglib uri="./WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="./WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="./WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="./WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="./WEB-INF/struts-nested.tld" prefix="nested" %>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<html:html>
  <style type="text/css">
    table, th, td {
      border: 1px solid black;
    }
  </style>
    <head>
      <title>Welcome Home</title>  

    </head>

    <%
    String uname = (String) session.getAttribute("un");
    String isLogged = (String) session.getAttribute("logged");

    if (null == uname || isLogged == "false") {
    session.setAttribute("errorMessage", "Login Failed ");
    response.sendRedirect("login.jsp");
    }
    %>
      
      <body bgcolor=white>
    
        <h1>Welcome <c:out value="${name}" /></h1>
  
        <h2>Brought to you by: <c:out value="${DBProductInfo}" /></h2>

        <html:form action="Home.do">

          <html:select name="HomeForm" property="customerID">

              <html:option value="all">All Orders</html:option>
              <html:optionsCollection name="HomeForm" property="customerList"
              label="customerName" value="customerID" />


          </html:select>

          <html:submit value="Submit" />

        </html:form>
        
        

        <table>
            <thead>
              <tr>
                <th colspan="4">Order Info</th>
              </tr>
            </thead>

            <tbody>

              <tr>
                <th>Order ID</th>
                <th>Customer Name</th>
                <th>Order Date</th>
                <th>Description</th>
              </tr>
              
                <c:forEach items="${cooldata}" var="item">
                  <tr>

                        <td>${item.orderID}</td>
                  
                        <td>${item.customerName}</td>

                        <td>${item.orderDate}</td>

                        <td>${item.description}</td>

                        <!--EDIT ITEMS-->
                        <td>
                          <a href="Orders.do?parameter=orderEdit&orderID=${item.orderID}">
                                Edit
                          </a>
                        </td>

                        <!--DELETE ITEMS-->
                        <td>
                          <a href="Orders.do?parameter=orderEdit&orderID=${item.orderID}&delOrderID=foo">

                          Delete
                          </a>
                        </td>

                      
                  </tr>
                </c:forEach>                

            </tbody>
        </table> 
        <br>
        <br>          
        <a href="Orders.do?parameter=orderEdit&addNew=1">
          <button>Submit New Order</button>
        </a>
        <br>

        <h2><c:out value="${message}" /></h2>
  
      </body>



</html:html>
  