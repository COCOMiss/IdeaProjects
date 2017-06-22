package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 * Created by COCOMiss on 2017/5/25.
 */
public class HelloWorld extends ActionSupport {
    @Override
    public String execute ()throws Exception{
        System.out.print("index");
        return SUCCESS;
    }
    public String add(){
        System.out.print("add");
        return "add";
    }


}
