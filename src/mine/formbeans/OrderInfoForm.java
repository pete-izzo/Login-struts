package mine.formbeans;

import org.apache.struts.action.ActionForm;
import java.util.*;

public class OrderInfoForm extends ActionForm
{

	private List<OrderInfo> newOrders;
	public List<OrderInfo> getNewOrders()
	{
		return newOrders;
	}

	public void setNewOrders(List<OrderInfo> newOrders)
	{
		this.newOrders = newOrders;
	}
}

