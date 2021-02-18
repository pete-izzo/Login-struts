//Java Customer Info class file

package mine.formbeans;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.*;
import java.sql.*;
import java.util.Date;
import static java.lang.System.*;
import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import lombok.Getter;
import lombok.Setter;


public class CustomerInfo{
    private @Getter @Setter int customerID;
    private @Getter @Setter String customerName = null;

    public CustomerInfo(){
    }

}