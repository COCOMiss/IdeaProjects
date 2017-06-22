package entity;

import java.sql.Date;

/**
 * Created by COCOMiss on 2017/5/31.
 */
public class Borrowed {
    String IBSN;
    String name;
    String author;
    Date Borrowed_date;
    Date return_date;

    public String getIBSN() {
        return IBSN;
    }

    public void setIBSN(String IBSN) {
        this.IBSN = IBSN;
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

    public Date getBorrowed_date() {
        return Borrowed_date;
    }

    public void setBorrowed_date(Date borrowed_date) {
        Borrowed_date = borrowed_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }
}
