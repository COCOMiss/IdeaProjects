package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by COCOMiss on 2017/5/27.
 */
public class DBUtil {

    //private static final String URL="jdbc:mysql://localhost:3306/BookSysytem?useUnicode=true&characterEncoding=utf-8";
    private static final String URL="jdbc:mysql://localhost:3306/BookSystem?useUnicode=true&characterEncoding=utf-8";
    private static final String USER="root";
    //private static final String PASSWORD="123456";
    private static final String PASSWORD="3271905";
    private static final String driver="com.mysql.jdbc.Driver";
    Connection connection=null;

    public Connection getCon(){
        try{
            Class.forName(driver);
            connection= DriverManager.getConnection(URL,USER,PASSWORD);
        }catch(Exception e){
            e.printStackTrace();
        }
        return connection;

    }

}
