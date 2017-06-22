package util;

import Dao.ReaderDao;
import entity.Reader;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by COCOMiss on 2017/5/29.
 */
public class ReaderManager {
    public static HttpSession login(HttpSession session){
        //一天有效期
        session.setMaxInactiveInterval(3600*24);
        return session;

    }
    public static boolean isLogin(HttpSession session) throws SQLException {
        Reader readerTest= (Reader) session.getAttribute("reader");
        if(readerTest==null){
            return false;
        }
        ReaderDao readerDao=new ReaderDao();
        Reader readerTrue=readerDao.getReader(readerTest.getName());
        if(readerTest.getPassword().equals(readerTrue.getPassword())){
            return true;
        }
        return false;

    }
    public static String getUserStyle(HttpSession session){
        Reader reader= (Reader) session.getAttribute("reader");
        String reader_style=reader.getStyle();
        return reader_style;
    }

    public static boolean logout(HttpSession session){
        session.invalidate();
        return true;
    }

}
