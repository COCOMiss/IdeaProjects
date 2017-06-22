import Dao.ReaderDao;
import entity.Reader;
import util.ReaderManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by COCOMiss on 2017/5/31.
 */
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session=req.getSession();
        PrintWriter out=resp.getWriter();
        try {
            if(ReaderManager.isLogin(session)){
                String password=req.getParameter("password");
                String newPassword=req.getParameter("newPassword");
                Reader reader= (Reader) session.getAttribute("reader");
                if(!reader.getPassword().equals(password)){
                    out.print("原来密码输入错误");
                    resp.setStatus(400);
                }else{
                    ReaderDao readerDao=new ReaderDao();
                    boolean modify=readerDao.updatePassword(reader.getName(),newPassword);
                    if(!modify){
                        out.print("修改密码失败");
                        resp.setStatus(400);
                    }

                }
            }
            else{
                out.print("未登录");
                resp.setStatus(400);
            }
        } catch (SQLException e) {


        }
    }
}
