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


public class OrderAction extends DispatchAction {

    private final static String SUCCESS = "success";

    //add order
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginForm loginForm = (LoginForm) form;
        // loginForm.setMessage("Inside add user method.");
        //add orders and send back to home
        return mapping.findForward(SUCCESS);
    }

    //edit orders
    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginForm loginForm = (LoginForm) form;
        // loginForm.setMessage("Inside update user method.");
        //do updating and send back to home
        return mapping.findForward(SUCCESS);
    }

    //delete order
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginForm loginForm = (LoginForm) form;
        // loginForm.setMessage("Inside delete user method.");
        //deleting orders and send back to home
        return mapping.findForward(SUCCESS);
    }

    //may not need this
    public ActionForward showAll(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginForm loginForm = (LoginForm) form;
        // loginForm.setMessage("Inside delete user method.");
        //probably just send back to home
        return mapping.findForward(SUCCESS);
    }
}