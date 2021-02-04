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
import java.util.*;

import org.apache.struts.action.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.io.*;

import mine.formbeans.LoginForm;
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
        LoginForm loginForm = (LoginForm) form;
        HttpSession session = request.getSession(false);

        // loginForm.setMessage("Inside add user method.");
        //add orders and send back to home

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
        LoginForm loginForm = (LoginForm) form;
        HttpSession session = request.getSession(false);

        // loginForm.setMessage("Inside update user method.");
        //do updating and send back to home
        request.setAttribute("message", "ORder edited successfully");
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
        LoginForm loginForm = (LoginForm) form;
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




        // loginForm.setMessage("Inside delete user method.");
        //deleting orders and send back to home
        request.setAttribute("message", "Order deleted successfully");
        return mapping.findForward(SUCCESS);
    }

    //mapping and variable setting for orderEdit.jsp page
    public ActionForward orderEdit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginForm loginForm = (LoginForm) form;
        HttpSession session = request.getSession(false);


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
                orders.setDel(" ");
                System.out.println("-----GET DEL-----");

                System.out.println("Del = " + orders.getDel());


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

                        System.out.println("-----SET DEL-----");

                        orders.setDel("foo");
                        System.out.println("Del = " + orders.getDel());


                        session.setAttribute("orders", orders);

                }


            };
            session.setAttribute("orderIDInt", orderIDInt);
            System.out.println("orderIDInt = " + orderIDInt);



        }

        session.setAttribute("orderIDString", orderIDString);


        // System.out.println("del = " + del);
        System.out.println("orderIDString = " + orderIDString);




        // loginForm.setMessage("Inside delete user method.");
        //probably just send back to home
        request.setAttribute("message", "Showing all orders successfully");

        return mapping.findForward(ORDERS);
    }
}