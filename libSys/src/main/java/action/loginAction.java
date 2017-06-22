package action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by COCOMiss on 2017/5/23.
 */
public class loginAction extends ActionSupport {
    private String selfParam="param";

    public String getSelfParam() {
        return selfParam;
    }

    public void setSelfParam(String selfParam) {
        this.selfParam = selfParam;
    }


    @Override
    public String execute() throws Exception {
        System.out.println("action executed");
        return SUCCESS;
    }

    public String add(){
        return "add";
    }
}
