import Dao.BookDao;
import Dao.BorrowDao;
import entity.Book;
import entity.Reader;
import util.ReaderManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by COCOMiss on 2017/5/31.
 */
public class AddRemoveBookServlet extends HttpServlet {
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
                String option=req.getParameter("option");
                //test
                //String option="removeBook";
                if(option=="addBook"){

                    BookDao bookDao=new BookDao();
                    Book book=new Book();
                    book.setName(req.getParameter("book_name"));
                    book.setAuthor(req.getParameter("author"));
                    book.setBook_style(req.getParameter("book_style"));
                    book.setIBSN(req.getParameter("IBSN"));
                    book.setPub_date(Date.valueOf(req.getParameter("pub_date")));
                    book.setPublisher(req.getParameter("publisher"));
                    boolean add=bookDao.addBook(book);

                    //test
                        /*Book book=new Book();
                        book.setName("经济");
                        book.setAuthor("林");
                        book.setPub_date(new Date(System.currentTimeMillis()));
                        BookDao bookDao=new BookDao();
                        boolean add=bookDao.addBook(book);*/
                    if(!add){
                        out.print("书籍未添加成功");
                        resp.setStatus(400);
                    }

                } else if(option=="removeBook"){
                    String book_id=req.getParameter("book_id");
                    BookDao bookDao=new BookDao();
                    boolean remove=bookDao.removeBook(book_id);
                    if(remove==false){
                        out.print("删除失败");
                        resp.setStatus(400);
                    }


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
