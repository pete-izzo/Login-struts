package mine.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import javax.sql.DataSource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.lang.*;
import static java.lang.System.*;
import java.lang.Object;
import java.util.Collections;
import static java.util.Comparator.comparing;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.apache.struts.action.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import org.apache.struts.action.*;
import javax.servlet.http.*;

import mine.formbeans.OrderEditForm;
import mine.formbeans.CustomerInfo;
import mine.formbeans.OrderInfo;


public class OrderAction extends DispatchAction {

    private final static String SUCCESS = "success";
    private final static String ORDERS = "orders";
    private final static String dbURL ="java:comp/env/jdbc/firstDB";

    private final static String driver = "org.apache.derby.jdbc.EmbeddedDriver";

    /**
     * /////////////////////
     * ORDER ADD ACTION
     * /////////////////////
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
                System.out.println("-----BEGIN ADD-----");

        OrderEditForm oef = (OrderEditForm) form;
        HttpSession session = request.getSession(false);

        // String customer = request.getParameter("customerChoice");
        // session.setAttribute("customerChoice", customer);

        String customer =  String.valueOf(oef.getCustomerID());

        System.out.println("----NEW CUSTOMER CHOICE----");
        System.out.println(customer);


        //formats date from string so it can be put in db
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date date = sdf.parse(oef.getOrderDate());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        System.out.println("customer: " + customer);
        System.out.println("SQL Date: " + sqlDate);

        Context ctx = null;
        Connection con = null;
        Statement stmt = null;
        String addOrder = null;

        try {

            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(dbURL);

            con = ds.getConnection();

            stmt = con.createStatement();

            /**
             *  THIS IS WHERE YOU LEFT OFF
             *  CHANGE ALL CUST ID VARIABLES TO THAT OF THE
             *  SELECTED CUST ID FROM THE DROP DOWN
             */

            System.out.println("-----Inside New Order Function-------");

            int custID = Integer.parseInt(customer);
            System.out.println("custID: " + custID);

            /**
             *  
             * //////////////////
             * PREPARED STATEMENT
             * to insert new orders into orders
             * table
             * //////////////////
             * 
             */
            addOrder =  "INSERT INTO orders (cust_id, order_date, order_desc) VALUES (?, ?, ?)";
            PreparedStatement insertOrder = con.prepareStatement(addOrder);
            System.out.println("insertOrder: " + insertOrder);

            insertOrder.setInt(1, custID);
            insertOrder.setDate(2, sqlDate);
            insertOrder.setString(3, oef.getDescription());

            insertOrder.executeUpdate();
            insertOrder.close();             

            System.out.println("-------Add Order Prepared Statement Complete--------");

        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
                ctx.close();

            }catch (SQLException error) {
                error.printStackTrace();
            }catch (NamingException error) {
                error.printStackTrace();
            }
        }

        request.setAttribute("message", "Order added successfully");
        return mapping.findForward(SUCCESS);
    }

    /**
     * /////////////////////
     * ORDER EDIT ACTION
     * /////////////////////
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OrderEditForm oef = (OrderEditForm) form;
        HttpSession session = request.getSession(false);

        System.out.println("-----BEGIN ORDER EDIT-----");

        int orderIDInt = (int)session.getAttribute("orderIDInt");
        //formats date from string so it can be put in db
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date date = sdf.parse(oef.getOrderDate());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        System.out.println("-----ORDER EDIT TEST VARIABLES-----");

        System.out.println("OrderDate: " + oef.getOrderDate());
        System.out.println("SQL Date: " + sqlDate);
        System.out.println("OrderIDInt = " + orderIDInt);
        System.out.println("Description: " + oef.getDescription());

        Context ctx = null;
        Connection con = null;
        Statement stmt = null;
        String addOrder = null;

        try {

            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(dbURL);

            con = ds.getConnection();

            stmt = con.createStatement();

            String updateOrder = "UPDATE orders" +
            " SET order_date = ?" +
            ", order_desc = ?" +
            " WHERE order_id = ?";

            PreparedStatement editOrder = con.prepareStatement(updateOrder);
            editOrder.setDate(1, sqlDate);
            editOrder.setString(2, oef.getDescription());
            editOrder.setInt(3, orderIDInt);

            editOrder.executeUpdate();
            editOrder.close();

            stmt = con.createStatement();
            stmt.execute(updateOrder);

        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
                ctx.close();

            }catch (SQLException error) {
                error.printStackTrace();
            }catch (NamingException error) {
                error.printStackTrace();
            }
        }




        // oef.setMessage("Inside update user method.");
        //do updating and send back to home
        request.setAttribute("message", "Order edited successfully");
        return mapping.findForward(SUCCESS);
    }

    /**
     * /////////////////////
     * ORDER DELETE ACTION
     * /////////////////////
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OrderEditForm oef = (OrderEditForm) form;
        HttpSession session = request.getSession(false);

        int orderIDInt = (int)session.getAttribute("orderIDInt");
        System.out.println("Delete Action OrderIDInt = " + orderIDInt);
        Context ctx = null;
        Connection con = null;
        Statement stmt = null;
        String addOrder = null;


        try {

        
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(dbURL);
        
            con = ds.getConnection();
        
            stmt = con.createStatement();
        

            String deleteOrder = "DELETE FROM orders" +
                                 " WHERE order_id = ?";
            PreparedStatement delOrder = con.prepareStatement(deleteOrder);

            delOrder.setInt(1, orderIDInt);
            delOrder.executeUpdate();
            delOrder.close();
            System.out.println("---ORDER DELETED---");


        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
                ctx.close();

            }catch (SQLException error) {
                error.printStackTrace();
            }catch (NamingException error) {
                error.printStackTrace();
            }
        }

        // oef.setMessage("Inside delete user method.");
        //deleting orders and send back to home
        request.setAttribute("message", "Order deleted successfully");
        return mapping.findForward(SUCCESS);
    }

    //mapping and variable setting for orderEdit.jsp page
    public ActionForward orderEdit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OrderEditForm oef = (OrderEditForm) form;
        HttpSession session = request.getSession(false);

        
        ArrayList<CustomerInfo> customerList = oef.getCustomerList();
        if(oef.getCustomerList() == null) {
            customerList = new ArrayList<>();
        }

        String dbURL ="java:comp/env/jdbc/NewDBTest";

        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        Context ctx = null;
        Connection con = null;
        Statement statement =null;
        ResultSet resultset = null;
        String customerQuery;

        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/firstDB");

            con = ds.getConnection();
            customerList.clear();
            System.out.println("-----NEW ORDERS AND CUSTOMER LIST CLEARED-----");


            statement=con.createStatement();

            customerQuery = "SELECT * FROM customers";

            resultset = statement.executeQuery(customerQuery);

            while(resultset.next()) {

                /**
                 * create customer list for drop down
                 */

                CustomerInfo customers = new CustomerInfo();

                customers.setCustomerName(resultset.getString("cust_name"));
                customers.setCustomerID(resultset.getInt("cust_id"));

                customerList.add(customers);

            }
            oef.setCustomerList(customerList);
        } catch (NamingException ex) {
    
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultset.close();
                statement.close();
                con.close();
                ctx.close();



    
            }catch (SQLException error) {
                error.printStackTrace();
            }catch (NamingException error) {
                error.printStackTrace();
            }
        }



        String orderIDString = request.getParameter("orderID");
        System.out.println("OrderIDString = " + orderIDString);
        String addOrder = request.getParameter("addNew");
        System.out.println("addOrder = " + addOrder);
        session.setAttribute("addOrder", addOrder);




        String del = request.getParameter("delOrderID");
        session.setAttribute("del", del);
        System.out.println("-----BEFORE IF STMT-----");

        if(orderIDString != null) {

            int orderIDInt = Integer.parseInt(orderIDString);
            System.out.println("-----ORDERidiNT SET-----");


            //Gets newOrders ArrayList from session to iterate over to get the specific order
            //for whichever edit is clicked
            ArrayList<OrderInfo> newOrders = (ArrayList)session.getAttribute("cooldata");
            System.out.println(newOrders);
            


            //Grabs only the "orders" obj inside "newOrders" that has a matching id as the
            //one of the clicked edit button
            for(OrderInfo orders : newOrders){
                System.out.println("-----FOR STMT WORK?-----");

                System.out.println("orders contains: " + orders.getOrderID() + 
                                        " " + orders.getCustomerName() +
                                        " " + orders.getOrderDate() +
                                        " " + orders.getDescription());


                if(orders.getOrderID() == orderIDInt) {
                    System.out.println("newOrders contains: " + orders.getOrderID() + 
                                        " " + orders.getCustomerName() +
                                        " " + orders.getOrderDate() +
                                        " " + orders.getDescription());

                        /*
                        * \\\\\\\\\\\\\\\\\\\
                        *   save this order to session so you can mess with it
                        *   or at least save its values to session to display them
                        *   in the event that more attributes are saved than displayed on the
                        *   home screen
                        * \\\\\\\\\\\\\\\\\\\\
                        */
                        session.setAttribute("orders", orders);

                }


            };
            session.setAttribute("orderIDInt", orderIDInt);
            System.out.println("orderIDInt = " + orderIDInt);

            System.out.println("----ALL EDIT VARIABLES----");

            System.out.println("orderIDInt = " + orderIDInt +
                               " orderIDString = " + orderIDString +
                               " del = " + del +
                               " addOrder = " + addOrder);
    



        }

        session.setAttribute("orderIDString", orderIDString);
        System.out.println("------end of edit reached------");



        // System.out.println("del = " + del);





        // oef.setMessage("Inside delete user method.");
        //probably just send back to home
        request.setAttribute("message", "Showing all orders successfully");

        return mapping.findForward(ORDERS);
    }
}