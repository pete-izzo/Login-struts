package mine.formbeans;

// import org.apache.struts.action.*;
// import org.apache.struts.action.ActionForm;

/*
* //////////////////
* THIS WASNT WORKING NEEDS TO BE WORKED ON
* //////////////////
*/
import org.apache.struts.validator.*;
import org.apache.struts.validator.ValidatorForm;

import java.util.*;


public class LoginForm extends ValidatorForm {
    protected String username;
    protected String password;

    public String getUsername() {
        return this.username;
    };

    public String getPassword() {
        return this.password;
    };

    public void setUsername(String username) {
        this.username = username;
    };
    public void setPassword(String password) {
        this.password = password;
    };
    
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

