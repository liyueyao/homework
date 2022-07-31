package homework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static org.junit.Assert.*;

public class DataManagerTest {

    @org.junit.Test
    public void getPeriod() {

        Date start = getDate("2022-01-01");
        Date end = getDate("2022-02-01");

        long period = DataManager.getPeriod(start, end);

        assert period == 31;
    }

    @org.junit.Test
    public void getInterest() {
        Date start = getDate("2022-01-01");
        Date end = getDate("2022-02-01");

        long interest = DataManager.getInterest(10000, 0.0005f, start, end);
        assert interest == 15500;
    }

    private Date getDate(String in){
        Date temp = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            temp = format.parse(in);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return temp;
    }
}