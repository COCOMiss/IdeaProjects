package Dao;

import entity.Book;
import entity.Borrowed;
import entity.Reader;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


/**
 * Created by COCOMiss on 2017/5/28.
 */
public class BorrowDao {
    DBUtil db=new DBUtil();
    Connection con=db.getCon();


    public boolean borrow(int reader_id, int book_id) throws SQLException {
        ReaderDao readerDao=new ReaderDao();
        Reader reader=readerDao.getReaderById(reader_id);
        BookDao bookDao=new BookDao();
        Book book=bookDao.getBookById(book_id);
        if(book.getBorrowed()==1||reader==null){
            return false;
        }else{
            book.setBorrowed(1);
            boolean bookUpdate=bookDao.borrowAndReyurnBook(book_id,1);
            boolean borrowUpdate=borrowInsert(reader_id,book_id);
            if(bookUpdate==true&&bookUpdate==true){
                return true;
            }
        }
        return false;
    }

    //获取某个用户已经借的书
    public List<Borrowed> getBorrowedBook(String reader_id){

        String sql="select * from borrow inner join book on borrow.book_id=book.book_id and borrow.reader_id=?";
        ArrayList<Borrowed> list=new ArrayList<Borrowed>();
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,reader_id);
            ResultSet resultSet=ps.executeQuery();

            while (resultSet.next()){
                Borrowed borrowed=new Borrowed();
                borrowed.setBorrowed_date(Date.valueOf(resultSet.getString("borrow_date")));
                borrowed.setReturn_date(Date.valueOf(resultSet.getString("return_date")));
                borrowed.setAuthor(resultSet.getString("author"));
                borrowed.setIBSN(resultSet.getString("IBSN"));
                borrowed.setName(resultSet.getString("book_name"));
                list.add(borrowed);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean borrowDelete(int reader_id,int book_id) {
        //验证borrow表中是否有这本书
        String TestBorrowsql="SELECT * FROM borrow WHERE reader_id=? and book_id=?";
        int count=0;
        try {
            PreparedStatement psBorrow=con.prepareStatement(TestBorrowsql);
            psBorrow.setString(1,reader_id+"");
            psBorrow.setString(2,book_id+"");
            ResultSet resultSet=psBorrow.executeQuery();
            if(resultSet.next()){
                count++;
            }
            if(count==0){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        String sql="DELETE FROM borrow WHERE reader_id=? and book_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,reader_id+"");
            ps.setString(2,book_id+"");
            ps.execute();
            BookDao bookDao=new BookDao();
            boolean returnInBook=bookDao.borrowAndReyurnBook(book_id,0);
            return returnInBook;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean borrowInsert(int reader_id,int book_id) {
        java.util.Date borrow_date=new java.util.Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrow_date);
        calendar.add(Calendar.DATE, 30);
        java.util.Date return_date=calendar.getTime();
        java.sql.Date borrow_date_sql=new java.sql.Date(borrow_date.getTime());
        java.sql.Date return_date_sql=new java.sql.Date(return_date.getTime());
        String sql="INSERT INTO borrow (reader_id, book_id,borrow_date,return_date) VALUES (?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,reader_id+""); // 第一个问号就是1，依次类推
            ps.setString(2,book_id+"");
            ps.setString(3,borrow_date_sql.toString());
            ps.setString(4,return_date_sql.toString());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    protected void finalize() throws java.lang.Throwable {
        con.close();
    }


}
