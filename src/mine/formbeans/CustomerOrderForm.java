package mine.formbeans;

import org.apache.struts.action.ActionForm;
import java.util.*;
import lombok.Getter;
import lombok.Setter;


public class CustomerOrderForm extends ActionForm
{

	private @Getter @Setter ArrayList<CustomerInfo> CustomerList;
    private @Getter @Setter ArrayList<OrderInfo> newOrders;
}
