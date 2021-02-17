package mine.formbeans;

import org.apache.struts.action.*;
import org.apache.struts.action.ActionForm;
import java.util.*;
import lombok.Getter;
import lombok.Setter;



public class OrderEditForm extends ActionForm {

    private @Getter @Setter int orderID;
    private @Getter @Setter int customerID;
    private @Getter @Setter String customerName;
    private @Getter @Setter String orderDate;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<OrderInfo> newOrders;
    private @Getter @Setter ArrayList<CustomerInfo> CustomerList;    


}