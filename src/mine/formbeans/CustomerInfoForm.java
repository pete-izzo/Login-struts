package mine.formbeans;

import org.apache.struts.action.ActionForm;
import java.util.*;

public class CustomerInfoForm extends ActionForm
{

	private ArrayList<CustomerInfo> CustomerList;
	public ArrayList<CustomerInfo> getCustomerList()
	{
		return CustomerList;
	}

	public void setCustomerList(ArrayList<CustomerInfo> CustomerList)
	{
		this.CustomerList = CustomerList;
	}
}

