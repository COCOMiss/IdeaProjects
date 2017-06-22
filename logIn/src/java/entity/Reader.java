package entity;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

/**
 * Created by COCOMiss on 2017/5/27.
 */
public class Reader {
    public int id;
    public String name;
    public String password;

    public int getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String style;
}
