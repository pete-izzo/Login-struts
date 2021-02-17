package mine.formbeans;

import org.apache.struts.action.*;
import org.apache.struts.action.ActionForm;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

public class HomeForm extends ActionForm {
    private @Getter @Setter int customerID;
    private @Getter @Setter String customerName;
    private @Getter @Setter ArrayList<CustomerInfo> CustomerList;    
    private @Getter @Setter ArrayList<OrderInfo> newOrders;
}