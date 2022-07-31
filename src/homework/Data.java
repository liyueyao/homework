package homework;

import java.util.Date;

/**
 * @author yyLi
 * @since 2022-07-31 13:10
 */
public class Data {

    private long money;
    private float rate;
    private Date start = null;
    private Date end = null;
    private long interest = 0;

    public Data(long money, float rate, Date start, Date end){
        this.money = money;
        this.rate = rate;
        this.start = start;
        this.end = end;

        this.interest = DataManager.getInterest(money, rate, start, end);
    }

    public long getMoney() {
        return money;
    }

    public float getRate() {
        return rate;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public long getInterest() {
        return interest;
    }
}
