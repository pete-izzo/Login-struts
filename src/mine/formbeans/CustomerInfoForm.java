package mine.formbeans;

import org.apache.struts.action.ActionForm;
import java.util.*;
import lombok.Getter;
import lombok.Setter;


public class CustomerInfoForm extends ActionForm
{

	private @Getter @Setter ArrayList<CustomerInfo> CustomerList;
}

