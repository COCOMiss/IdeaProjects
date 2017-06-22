import Dao.BorrowDao;
import com.alibaba.fastjson.JSON;
import entity.Book;
import entity.Borrowed;
import entity.Reader;
import util.ReaderManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by COCOMiss on 2017/5/31.
 */
public class GetBorrowedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session=req.getSession();
        PrintWriter out=resp.getWriter();
        try {
            if(ReaderManager.isLogin(session)){
                Reader reader= (Reader) session.getAttribute("reader");
                if(!reader.getStyle().equals("user")){
                    resp.setStatus(400);
                    out.print("该用户非用户");
                    return;
                }

                BorrowDao borrowDao=new BorrowDao();
                ArrayList<Borrowed> list= (ArrayList<Borrowed>) borrowDao.getBorrowedBook(reader.getId()+"");
                out.print(JSON.toJSONString(list));

            }else {
                out.print("未登录");
                resp.setStatus(400);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
