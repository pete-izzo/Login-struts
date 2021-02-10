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
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<html>
  <style type="text/css">
    table, th, td {
      border: 1px solid black;
    }
  </style>
    <head>
      <title>Add an Order</title>  

      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
      <link rel="stylesheet" href="/resources/demos/style.css">
      <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
      <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
      <script>
      $( function() {
        $( "#datepicker" ).datepicker();
      } );
      </script>

    </head>

    <%
    String uname = (String) session.getAttribute("name");
    String isLogged = (String) session.getAttribute("logged");

    if (null == uname || isLogged == "false") {
    session.setAttribute("errorMessage", "Login Failed ");
    response.sendRedirect("login.jsp");
    }
    %>
      
      <body bgcolor=white>

        <!--orderID set in home.jsp saved in servlet from doGet and passed to this page-->
        <c:if test="${sessionScope.orderIDString != null && sessionScope.del != 'foo'}">
          <h1>Choose a new date and type a new description to edit the order below</h1>

          <table>
            <thead>
              <tr>
                <th colspan="4">Edit Order Info</th>
              </tr>
            </thead>

            <tbody>

              <tr>
                <th><c:out value="${orders.getOrderID()}"/></th>
                <th><c:out value="${orders.getCustomerName()}"/></th>
                <th><c:out value="${orders.getOrderDate()}"/></th>
                <th><c:out value="${orders.getDescription()}"/></th>
              </tr>
                  <tr>
                      <html:form action="Orders.do?parameter=update">

                        <td></td>
                        <td></td>
                        <td><nested:text styleId="datepicker" property="orderDate" /></td>
                        <td><nested:text property="description" /></td>
                        <td><html:submit value="Save" /></td>

                      </html:form>
                      
                  </tr>

            </tbody>
          </table>  



        </c:if>

        <!--END EDIT ORDER SECTION-->

        <!--DELETE SECTION-->
        <c:if test="${sessionScope.del == 'foo'}">

          <h1>Are you sure you would like to delete:</h1>
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


              <tr>
                <th><c:out value="${orders.getOrderID()}"/></th>
                <th><c:out value="${orders.getCustomerName()}"/></th>
                <th><c:out value="${orders.getOrderDate()}"/></th>
                <th><c:out value="${orders.getDescription()}"/></th>
              </tr>
            </tbody>
          </table>  

          <br>
          <html:form action="Orders.do?parameter=delete">
            <!-- <td><html type="hidden" name="delete" value="delete"></td> -->

            <html:submit value="Yes"/>

          </html:form>
          <html:link href="Login.do">Back Home</html:link>



        </c:if>

        <!--END DELETE SECTION-->



        <!--ADD ORDER SECTION-->
        <c:if test="${addOrder=='1'}">
          <h2>Add an order for...</h2>

          <html:form action="Orders.do?parameter=add">
              <select name="customerChoice">
                  <c:forEach items="${customerList}" var="items">
                      <option value="${items.customerID}">${items.customerName} ${items.customerID}</option>
                  </c:forEach>
              </select>
  
              <br>
              <br>
                <label for="date">Order Date: </label><br>
                <nested:text styleId="datepicker" property="orderDate" />
                <!-- <input type="date" required name="order_date" id="order_date"> -->
                <br>
                <label for="description">Order Description:</label><br>
                <nested:text property="description" />
                <br> 
              
  
              <html:submit value="Submit New Order" />
              <html:link href="Login.do">Back Home</html:link>

          </html:form>
  
        </c:if>
        <!--END ADD ORDER-->

  
      </body>



</html>
  