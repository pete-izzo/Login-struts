package mine.formbeans;

import org.apache.struts.action.ActionForm;
import java.util.*;

public class CustomerInfoForm extends ActionForm
{

	private List<CustomerInfo> CustomerList;
	public List<CustomerInfo> getCustomerList()
	{
		return CustomerList;
	}

	public void setCustomerList(List<CustomerInfo> CustomerList)
	{
		this.CustomerList = CustomerList;
	}
}

