//Java Order Info class file

package mine.formbeans;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.lang.*;
import java.sql.*;
import java.util.Date;
import static java.lang.System.*;
import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import lombok.Getter;
import lombok.Setter;


public class OrderInfo implements Serializable{
    private @Getter @Setter int orderID;
    private @Getter @Setter int customerID;
    private @Getter @Setter String customerName;
    private @Getter @Setter Date orderDate;
    private @Getter @Setter String description;
}