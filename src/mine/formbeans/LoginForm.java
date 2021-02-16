package mine.formbeans;

import org.apache.struts.action.*;
import org.apache.struts.action.ActionForm;
import lombok.Getter;
import lombok.Setter;


/*
* ////////////////////
* Got everything coded correctly
* missing deprecated class(I think) and getting error:
* java.lang.NoClassDefFoundError: org/apache/commons/validator/ValidatorResourcesInitializer
* //////////////////
import org.apache.struts.validator.*;
import org.apache.struts.validator.ValidatorForm;
*/
import java.util.*;

//change to ValidatorForm to try again
public class LoginForm extends ActionForm {
    protected @Getter @Setter String username;
    protected @Getter @Setter String password;
   }

