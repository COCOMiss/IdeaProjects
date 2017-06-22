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
 * Created by COCOMiss on 2017/5/27.
 */
public class RegistServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        System.out.println(name);
        String style="user";
        String password=req.getParameter("password");
        if(name==null||name.equals("")){
            resp.setStatus(400);
            out.print("用户名为空");
            return;
        }
        if(password==null||password.equals("")){
            resp.setStatus(400);
            out.print("密码为空");
            return;
        }
        if(style==null||style.equals("")){
            resp.setStatus(400);
            out.print("未选择用户类别");
            return;
        }

        Reader reader=new Reader();
        reader.setName(name);
        reader.setPassword(password);
        reader.setStyle(style);
        ReaderDao readerDao=new ReaderDao();
        boolean registerSuccess= false;
        try {
            registerSuccess = readerDao.registReader(reader);
            if(registerSuccess){
                //out.print("注册成功");
                Reader reader2=readerDao.getReader(name);
                HttpSession session=req.getSession();
                session.setAttribute("reader" ,reader2);
                ReaderManager.login(session);
                out.print(session.getId());
            }else{
                resp.setStatus(400);
                out.print("注册失败");
            }
        } catch (SQLException e) {
            try {
                Reader readerQuery=readerDao.getReader(name);

                if(readerQuery!=null){
                    resp.setStatus(400);
                    out.print("用户名存在");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }


            e.printStackTrace();
        }


    }
}
