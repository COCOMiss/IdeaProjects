import Dao.BorrowDao;
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
 * Created by COCOMiss on 2017/5/29.
 */
public class ReturnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session=req.getSession();
        PrintWriter out=resp.getWriter();
        try {
            if(ReaderManager.isLogin(session)){

                //out.print(session.getId());
                Reader reader= (Reader) session.getAttribute("reader");
                BorrowDao borrowDao=new BorrowDao();
                //验证reader是否为管理员
                if(!reader.getStyle().equals("admin")){
                    resp.setStatus(400);
                    out.print("此用户不是管理员");
                    return;
                }

                int reader_id= Integer.parseInt(req.getParameter("reader_id"));
                int book_id= Integer.parseInt(req.getParameter("book_id"));
                //此处book_id 用于测试
                boolean returnSuccess=borrowDao.borrowDelete(reader.getId(),book_id);
                if(returnSuccess==false){
                    out.print("还书不成功");
                    resp.setStatus(400);
                    return;
                }else {
                    out.print("还书成功");
                    resp.setStatus(200);
                    return;
                }


            }else {
                out.print("未登录");
                return;
            }
        } catch (SQLException e) {
            out.print("未知错误");
            resp.setStatus(400);
            e.printStackTrace();
        }finally {
            out.close();
        }
    }
}
