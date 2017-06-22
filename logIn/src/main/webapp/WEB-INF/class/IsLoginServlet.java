import com.alibaba.fastjson.JSON;
import entity.Reader;
import util.ReaderManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by COCOMiss on 2017/5/31.
 */
public class IsLoginServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();
        class ResponseData{
            String user_style;

            public String getReader_name() {
                return reader_name;
            }

            public void setReader_name(String reader_name) {
                this.reader_name = reader_name;
            }

            String reader_name;



            public String getUser_style() {
                return user_style;
            }

            public void setUser_style(String user_style) {
                this.user_style = user_style;
            }
        }
        ResponseData responseData=new ResponseData();
        try {


            if(ReaderManager.isLogin(session)){
                Reader reader= (Reader) session.getAttribute("reader");
                responseData.setUser_style(reader.getStyle());
                responseData.setReader_name(reader.getName());
            }else {
                responseData.setUser_style("");
                responseData.setReader_name("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.print(JSON.toJSONString(responseData));




    }
}
