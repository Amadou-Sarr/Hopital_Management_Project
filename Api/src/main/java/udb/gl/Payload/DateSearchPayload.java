package udb.gl.payload;

import java.util.Date;

public class DateSearchPayload {

    private  Date firstDate;

    private  Date  lastDate;

    public DateSearchPayload() {}

    public DateSearchPayload(Date firstDate, Date lastDate) {
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
