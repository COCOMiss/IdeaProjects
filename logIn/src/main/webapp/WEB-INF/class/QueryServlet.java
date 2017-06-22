import Dao.BookDao;
import com.alibaba.fastjson.JSON;
import entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by COCOMiss on 2017/5/29.
 */
public class QueryServlet extends HttpServlet {

    class ResponseData{
        int totalNum;
        List<Book> list;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public List<Book> getList() {
            return list;
        }

        public void setList(List<Book> list) {
            this.list = list;
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        BookDao bookDao=new BookDao();
        String book_id=req.getParameter("book_id");
        String book_name=req.getParameter("book_name");
        String author=req.getParameter("author");
        String publisher=req.getParameter("publisher");
        String book_style=req.getParameter("book_style");
        String IBSN=req.getParameter("IBSN");
        int startPos= Integer.parseInt(req.getParameter("startPos"));
        int num= Integer.parseInt(req.getParameter("num"));
        List<Book> bookList=bookDao.QueryBook(book_id,book_name,author,publisher,book_style,IBSN,startPos,num);
        //List<Book> bookList=bookDao.QueryBook(null,"活着","","","","",0,2);
        PrintWriter out=resp.getWriter();
        ResponseData data=new ResponseData();
        data.setTotalNum(bookDao.getTotalNumber());
        data.setList(bookList);
        //out.print(book_name);
        out.print(JSON.toJSONString(data));
    }

}
