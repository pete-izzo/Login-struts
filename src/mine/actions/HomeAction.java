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

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.io.*;

import mine.formbeans.HomeForm;
import mine.formbeans.CustomerInfo;
import mine.formbeans.OrderInfo;


public class HomeAction extends Action{
    
    public ActionForward execute(ActionMapping mapping,ActionForm form,
        HttpServletRequest request,HttpServletResponse response) 
        throws Exception {
        
        HomeForm hf = (HomeForm) form;
        HttpSession session = request.getSession(false);

        // Dropdown choice
        String choice =  request.getParameter("action");

        String newChoice =  String.valueOf(hf.getCustomerID());

        System.out.println("----NEW CHOICE----");
        System.out.println(newChoice);



        //list of customers for dropdown
        // List<CustomerInfo> customerList = hf.getCustomerList();

        ArrayList<CustomerInfo> customerList = hf.getCustomerList();
        if(hf.getCustomerList() == null) {
            customerList = new ArrayList<>();
        }

        // ArrayList<CustomerInfo> customerList = new ArrayList<CustomerInfo>();
        //order info
        ArrayList<OrderInfo> newOrders = hf.getNewOrders();
        if(hf.getNewOrders() == null) {
            newOrders = new ArrayList<>();
        }

        // ArrayList<OrderInfo> newOrders = new ArrayList<OrderInfo>();

        // JDBC Stuff
        String dbURL ="java:comp/env/jdbc/NewDBTest";

        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        Context ctx = null;
        Connection con = null;
        Connection connection = null;
        Statement stmt = null;
        Statement statement =null;
        ResultSet rs = null;
        ResultSet resultset = null;
        String sql;
        String customerQuery;

        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/firstDB");

            con = ds.getConnection();
            connection = ds.getConnection();
            newOrders.clear();
            customerList.clear();
            System.out.println("-----NEW ORDERS AND CUSTOMER LIST CLEARED-----");


            stmt = con.createStatement();
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
            hf.setCustomerList(customerList);

            if (newChoice == null || newChoice.equals("0")) {

                /**
                 * The Query
                 * connects both tables at cust_id so customers names 
                 * aren't duplicated for each order
                 */

                sql = "SELECT o.*, c.cust_name" +
                " FROM orders o, customers c" +
                " WHERE o.cust_id = c.cust_id";

                rs = stmt.executeQuery(sql);

            } else {

                sql = "SELECT o.*, c.cust_name" +
                " FROM orders o, customers c" +
                " WHERE o.cust_id = c.cust_id" +
                " AND c.cust_id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, newChoice);
                rs = ps.executeQuery();

            }
            while(rs.next()) {
    
                OrderInfo orders = new OrderInfo();

                orders.setOrderID(rs.getInt("order_id"));

                orders.setCustomerName(rs.getString("cust_name"));
                
                orders.setOrderDate(rs.getDate("order_date"));

                orders.setDescription(rs.getString("order_desc")); 
                // System.out.println("----SET DEL----");
 
                // orders.setDel(" ");  
                // System.out.println("Del = " + orders.getDel());
          

                newOrders.add(orders);

                System.out.println("newOrders contains: " + orders.getOrderID() + 
                                    " " + orders.getCustomerName() +
                                    " " + orders.getOrderDate() +
                                    " " + orders.getDescription());

            }

            

            /**
             * Sorts all order array lists by date ascending
             */
            Collections.sort(newOrders, new Comparator<OrderInfo>() {
                @Override
                public int compare(OrderInfo o1, OrderInfo o2) {
                    return o1.getOrderDate().compareTo(o2.getOrderDate());
                }
            });
            hf.setNewOrders(newOrders);

            session.setAttribute("cooldata", newOrders);
            session.setAttribute("customerList", customerList);

            String productInfo = con.getMetaData().getDatabaseProductName();

            session.setAttribute("DBProductInfo", productInfo);
            
        } catch (NamingException ex) {
    
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                resultset.close();
                stmt.close();
                con.close();
                ctx.close();



    
            }catch (SQLException error) {
                error.printStackTrace();
            }catch (NamingException error) {
                error.printStackTrace();
            }
        }

        
        return mapping.findForward("success");
    }
    
}