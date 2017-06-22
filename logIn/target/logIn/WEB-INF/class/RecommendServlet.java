import Dao.BookDao;
import com.alibaba.fastjson.JSON;
import entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by COCOMiss on 2017/5/31.
 */
public class RecommendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String book_style=req.getParameter("book_style");
        BookDao bookDao=new BookDao();
        ArrayList<Book> list= (ArrayList<Book>) bookDao.getBookByStyle(book_style);
        PrintWriter out=resp.getWriter();
        out.print(JSON.toJSON(list));
    }
}
