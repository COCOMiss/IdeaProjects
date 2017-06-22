package Dao;

import com.sun.org.apache.regexp.internal.RE;
import entity.Reader;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by COCOMiss on 2017/5/27.
 */
public class ReaderDao {
    DBUtil db=new DBUtil();
    Connection con=db.getCon();
    public Reader getReader(ResultSet rSet) throws SQLException {
        Reader reader =new Reader();
        reader.setId(rSet.getInt("reader_id"));
        reader.setName(rSet.getString("reader_name"));
        reader.setPassword(rSet.getString("reader_password"));
        reader.setStyle(rSet.getString("reader_style"));
        return reader;
    }


    public Reader getReader(String name) throws SQLException {

        String sql="SELECT * FROM reader WHERE reader_name=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,name); // 第一个问号就是1，依次类推
        ResultSet rSet = ps.executeQuery();
        if(rSet.next()){
            Reader reader=getReader(rSet);
            rSet.close();
            ps.close();
            return reader;
        }
        rSet.close();
        ps.close();
        return null;
    }


    public Reader getReaderById(int id) throws SQLException {

        String sql="SELECT * FROM reader WHERE reader_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,id+""); // 第一个问号就是1，依次类推
        ResultSet rSet = ps.executeQuery();
        if(rSet.next())
        {
            Reader reader=getReader(rSet);
            rSet.close();
            ps.close();
            return reader;
        }
        rSet.close();
        ps.close();
        return null;
    }

    public boolean registReader(Reader reader) throws SQLException {
        String sql="INSERT INTO reader (reader_name,reader_password,reader_style) VALUES (?,?,?)";
        PreparedStatement ps= null;
        ps = con.prepareStatement(sql);
        ps.setString(1,reader.getName());
        ps.setString(2,reader.getPassword());
        ps.setString(3,reader.getStyle());
        ps.execute();
        return true;
    }
    public boolean deleteReader(String reader_name) {
        String sql="delete from reader where reader_name=?";
        PreparedStatement ps= null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,reader_name);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean updatePassword(String reader_name,String reader_password){
        String sql ="update reader set reader_password=? where reader_name=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,reader_password);
            ps.setString(2,reader_name);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    protected void finalize() throws java.lang.Throwable {
        con.close();
    }


}
