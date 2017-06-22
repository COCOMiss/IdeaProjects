package Dao;

import entity.Book;
import util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by COCOMiss on 2017/5/28.
 */
public class BookDao {
    DBUtil db=new DBUtil();
    Connection con=db.getCon();
    int totalNumber;

    protected Book getBook(ResultSet rSet) throws SQLException {
        Book book =new Book();
        book.setId(rSet.getInt("book_id"));
        book.setName(rSet.getString("book_name"));
        book.setAuthor(rSet.getString("author"));
        book.setBook_style(rSet.getString("book_style"));
        book.setBorrowed(rSet.getInt("borrowed"));
        book.setIBSN(rSet.getString("IBSN"));
        book.setPub_date(rSet.getDate("pub_date"));
        book.setPublisher(rSet.getString("publisher"));
        return book;
    }
    public boolean addBook(Book book){
        String sql="insert into book(book_name,author,book_style,IBSN,pub_date,publisher) values (?,?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,book.getName());
            ps.setString(2,book.getAuthor());
            ps.setString(3,book.getBook_style());
            ps.setString(4,book.getIBSN());
            ps.setString(5, String.valueOf(book.getPub_date()));
            ps.setString(6,book.getPublisher());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean removeBook(String book_id){
        String sql="delete from book where book_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,book_id);
            ps.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public List<Book> getBookByStyle(String book_style){
        String sql="select * from book where book_style=? limit 0,10";
        ArrayList<Book> list=new ArrayList<Book>();
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,book_style);
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()){
                Book book=getBook(resultSet);
                list.add(book);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private boolean isValid(String attr){
        if(attr!=null&&!attr.trim().equals("")){
            return true;
        }
        return false;
    }
    public List<Book> QueryBook(String book_id,String book_name,String author,String publisher,
                                String book_style,String IBSN,int startPos,int num){
        StringBuilder sql =new StringBuilder("select * from book where 1=1");
        List<Book> list=new ArrayList<Book>();
        StringBuilder sqlCount=new StringBuilder("select count(*) from book where 1=1");


        List<String> parmas = new ArrayList<String>();

        if(isValid(book_id)){
            sql.append(" and book_id like ?");
            sqlCount.append(" and book_id like ?");
            parmas.add("%"+book_id+"%");

        }
        if(isValid(book_name)){
            sql.append(" and book_name like ?");
            sqlCount.append(" and book_name like ?");
            parmas.add("%"+book_name+"%");
        }

        if(isValid(author)){
            sql.append(" and author like ?");
            sqlCount.append(" and author like ?");
            parmas.add("%"+author+"%");
        }
        if(isValid(publisher)){
            sql.append(" and publisher like ?");
           sqlCount.append(" and publisher like ?");
            parmas.add("%"+publisher+"%");
        }
        if(isValid(book_style)){
            sql.append(" and book_style like ?");
            sqlCount.append(" and book_style like ?");
            parmas.add("%"+book_style+"%");
        }
        if(isValid(IBSN)){
            sql.append(" and IBSN like ?");
            sqlCount.append(" and IBSN like ?");
            parmas.add("%"+IBSN+"%");
        }
        //查询从第几条开始
        sql.append(" limit "+startPos+","+num);

        PreparedStatement ps= null;
        PreparedStatement psCount =null;
        try {
            int index=1;
            ps = con.prepareStatement(sql.toString());
            psCount=con.prepareStatement(sqlCount.toString());
            for(Object param:parmas){
                ps.setString(index,param.toString());
                psCount.setString(index,param.toString());
                System.out.println(param.toString());
                index++;
            }
            System.out.println(sql);
            System.out.println(ps);
            ResultSet rSet=ps.executeQuery();
            ResultSet rCount=psCount.executeQuery();
            if(rCount.next()){
                totalNumber=rCount.getInt(1);
            }

            //将list<Book>赋值
            while (rSet.next()){
                Book book=getBook(rSet);
                list.add(book);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    public List<Book> getBookByName(String name) throws SQLException {
        con=db.getCon();
        ArrayList<Book> list=new ArrayList<Book>();
        String sql="SELECT * FROM book WHERE book_name=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,name); // 第一个问号就是1，依次类推
        ResultSet rSet = ps.executeQuery();
        while(rSet.next()){
            Book book=getBook(rSet);
            list.add(book);
        }
        ps.close();
        rSet.close();
        return list;
    }
//``````````
    public Book getBookById(int id) throws SQLException {
        Book book=null;
        String sql="SElECT * FROM book WHERE book_id=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,id+"");
        ResultSet rSet=ps.executeQuery();
        if(rSet.next()){
            book=getBook(rSet);
        }
        ps.close();
        rSet.close();
        return book;

    }

    public List<Book> getBookByIBSN(String IBSN) throws SQLException {
        ArrayList<Book> list=new ArrayList<Book>();
        String sql="SElECT * FROM book WHERE IBSN=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,IBSN);
        ResultSet rSet=ps.executeQuery();
        while(rSet.next()){
            Book book=getBook(rSet);
            list.add(book);
        }
        ps.close();
        rSet.close();
        return list;
    }
    public boolean borrowAndReyurnBook(int id,int vlaue) throws SQLException {
        String sql="UPDATE book SET borrowed=? where book_id=?";
        PreparedStatement ps= null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,vlaue+"");
            ps.setString(2,id+"");
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ps.close();
        return false;

    }

    protected void finalize() throws java.lang.Throwable {
        con.close();
    }






    }
