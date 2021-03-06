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

import mine.formbeans.LoginForm;
import mine.formbeans.CustomerInfo;
import mine.formbeans.OrderInfo;


public class LoginAction extends Action{
    
    public ActionForward execute(ActionMapping mapping,ActionForm form,
        HttpServletRequest request,HttpServletResponse response) 
        throws Exception {
        
        LoginForm lf = (LoginForm) form;

        String un = lf.getUsername();
        String pw = lf.getPassword();

        String isLoggedIn = "false";
        HttpSession session = request.getSession(false);

        session.setAttribute("un", un);
        session.setAttribute("name", un);

        // JDBC Stuff
        String dbURL ="java:comp/env/jdbc/NewDBTest";

        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        Context ctx = null;
        Connection con = null;
        ResultSet loginSet = null;
        String sql;

        System.out.println("-----START ALL-----");

        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/firstDB");

            con = ds.getConnection();
            String query = "select * from users where userid = ? and passwd_digest = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, un);
            preparedStatement.setString(2, pw);

            loginSet = preparedStatement.executeQuery();

            String productInfo = con.getMetaData().getDatabaseProductName();

            session.setAttribute("DBProductInfo", productInfo);
    
            

            if (loginSet.next()) {
                isLoggedIn = "true";
                return mapping.findForward("success");
                // response.sendRedirect ("/login/HomeServlet");

            } else{
                String notValid = "Please enter a valid username or password";
                session.setAttribute("notValid", notValid);

                return mapping.findForward("failure");
                // response.sendRedirect ("login.jsp");

            }

            
        } catch (NamingException ex) {
    
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                loginSet.close();
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