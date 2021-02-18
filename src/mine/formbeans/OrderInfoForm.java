package mine.formbeans;

import org.apache.struts.action.ActionForm;
import java.util.*;
import lombok.Getter;
import lombok.Setter;


public class OrderInfoForm extends ActionForm
{
	private @Getter @Setter ArrayList<OrderInfo> newOrders;
}

