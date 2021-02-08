package mine.formbeans;

import org.apache.struts.action.ActionForm;
import java.util.*;

public class OrderInfoForm extends ActionForm
{

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

