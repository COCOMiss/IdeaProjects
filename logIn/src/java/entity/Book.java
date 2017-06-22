package entity;

import java.sql.Date;

/**
 * Created by COCOMiss on 2017/5/28.
 */
public class Book {
    public int id;
    public String name;
    public String author;
    public String publisher;
    public Date pub_date;
    public String book_style;
    public int borrowed;
    public String IBSN;


    public int getId() {
        return id;
    }

    public String getBook_style() {
        return book_style;
    }

    public void setBook_style(String book_style) {
        this.book_style = book_style;
    }

    public int getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(int borrowed) {
        this.borrowed = borrowed;
    }

    public String getIBSN() {
        return IBSN;
    }

    public void setIBSN(String IBSN) {
        this.IBSN = IBSN;
    }

    public void setId(int id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPub_date() {
        return pub_date;
    }

    public void setPub_date(Date pub_date) {
        this.pub_date = pub_date;
    }
}
