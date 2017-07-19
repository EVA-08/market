package entiry;

import java.sql.Date;

/**
 * Created by EVA-08 on 2017/7/16.
 */
public class Action {
    private Date date;
    private String action;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
