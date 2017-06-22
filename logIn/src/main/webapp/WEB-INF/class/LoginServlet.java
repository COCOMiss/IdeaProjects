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

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String name = request.getParameter("reader_name");
        PrintWriter out=response.getWriter();
        String password = request.getParameter("reader_password");
        try {
            ReaderDao readerDao=new ReaderDao();
            Reader reader =readerDao.getReader(name);

            if(reader==null){
                request.setAttribute("msg", "没有这个用户！");
                response.setStatus(401);
                out.print("No User");
                return;
            }
            if(reader.getPassword()!=null&&!reader.getPassword().equals(password)){
                request.setAttribute("msg", "密码错误请重新输入！");
                out.print("密码输入错误");
                response.setStatus(401);
                return;
            }
            if(reader.getPassword().equals(password)){
                HttpSession session=request.getSession();
                session.setAttribute("reader",reader);
                session=ReaderManager.login(session);
                out.print(session.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.close();


    }

}