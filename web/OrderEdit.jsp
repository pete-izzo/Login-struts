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


<html>
  <style type="text/css">
    table, th, td {
      border: 1px solid black;
    }
  </style>
    <head>
      <title>Add an Order</title>  

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
    
        <h1>Welcome <c:out value="${name}" /></h1>
        <h2>del:<c:out value="${del}"/> orderID: <c:out value="${orderIDInt}"/></h2>
        <h2>addOrder: <c:out value="${addOrder}"/> </h2>

        <!--orderID set in home.jsp saved in servlet from doGet and passed to this page-->
        <c:if test="${sessionScope.orderIDInt != null && sessionScope.del != 'foo'}">
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
                      <form action="OrderServlet" method="POST">

                        <td></td>
                        <td></td>
                        <td><input type="date" name="newOrderDate" id="orderDate" required></td>
                        <td><input type="text" name="editOrderDescription" value="${item.description}"></td>
                        <td><input type="submit" value="Save"/></td>

                      </form>
                      
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
                <th><c:out value="${orders.getOrderID()}"/></th>
                <th><c:out value="${orders.getCustomerName()}"/></th>
                <th><c:out value="${orders.getOrderDate()}"/></th>
                <th><c:out value="${orders.getDescription()}"/></th>
              </tr>
            </tbody>
          </table>  

          <br>
          <html:form action="Orders.do?parameter=delete">
            <td><input type="hidden" name="delete" value="delete"></td>

            <html:submit value="Yes"/>

          </html:form>
          <a href="Login.do">Back Home</a>



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
              <input type="date" required name="order_date" id="order_date">
              <br>
              <label for="description">Order Description:</label><br>
              <input type="text" required placeholder="What was ordered?" name="orderDescription" id="orderDescription">
              <br> 
  
              <input type="submit" value="Submit" />
              <a href="Login.do">Back Home</a>

          </html:form>
  
        </c:if>
        <!--END ADD ORDER-->

  
      </body>



</html>
  