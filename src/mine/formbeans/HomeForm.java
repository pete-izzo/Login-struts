package mine.formbeans;

import org.apache.struts.action.*;
import org.apache.struts.action.ActionForm;
import java.util.*;

public class HomeForm extends ActionForm {
    private ArrayList<CustomerInfo> CustomerList;
	public ArrayList<CustomerInfo> getCustomerList()
	{
		return CustomerList;
	}

	public void setCustomerList(ArrayList<CustomerInfo> CustomerList)
	{
		this.CustomerList = CustomerList;
    }
    
    private ArrayList<OrderInfo> newOrders;
	public ArrayList<OrderInfo> getNewOrders()
	{
		return newOrders;
	}

	public void setNewOrders(ArrayList<OrderInfo> newOrders)
	{
		this.newOrders = newOrders;
	}
}