package mine.formbeans;

import org.apache.struts.action.*;
import org.apache.struts.action.ActionForm;
import java.util.*;


public class OrderEditForm extends ActionForm {

    private int orderID;
    private int customerID;
    private String customerName = null;
    private String orderDate = null;
    private String description = null;

    public int getOrderID() {
        return orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getDescription() {
        return description;
    }

    // public String getDel(){
    //     return del;
    // }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setDescription(String description) {
        this.description = description;
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