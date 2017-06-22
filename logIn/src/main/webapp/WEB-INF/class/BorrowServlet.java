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
import java.io.Writer;
import java.sql.SQLException;

/**
 * Created by COCOMiss on 2017/5/29.
 */
public class BorrowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session=req.getSession();
        PrintWriter out=resp.getWriter();
        try {
            if(ReaderManager.isLogin(session)){

                //out.print(session.getId());
                //管理与的reader实例
                Reader reader= (Reader) session.getAttribute("reader");
                BorrowDao borrowDao=new BorrowDao();
                //验证reader是否为管理员
                if(!reader.getStyle().equals("admin")){
                    resp.setStatus(400);
                    out.print("该用户非管理员");
                    return;
                }

                //
                //reader_id为用户的id
                int reader_id=Integer.parseInt(req.getParameter("reader_id"));
                int book_id= Integer.parseInt(req.getParameter("book_id"));
                boolean borrowSuccess=borrowDao.borrow(reader_id,book_id);
                if(borrowSuccess==false){
                    out.print("借书不成功");
                    resp.setStatus(400);
                    return;
                }else {
                    resp.setStatus(200);
                    return;
                }


            }else {
                out.print("未登录");
                resp.setStatus(400);
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
