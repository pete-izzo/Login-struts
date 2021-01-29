package mine.formbeans;

import org.apache.struts.action.*;
import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm {
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
   }

