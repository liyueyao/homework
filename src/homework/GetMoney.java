package homework;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author yyLi
 * @since 2022-07-31 13:45
 */
public class GetMoney {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        System.out.print("请输入本金：");
        long money = in.nextLong();
        while(money <= 0 || String.valueOf(money).length() > 13){
            System.out.print("本金不合法，请重新输入13位以内整数：");
            money = in.nextLong();
        }

        System.out.print("请输入利率：");
        float rate = in.nextFloat();
        while(rate < 0 || rate > 1) {
            System.out.print("利率不正确，请重新输入：");
            rate = in.nextFloat();
        }

        System.out.print("请输入开始日期：");
        Date start = getDate(in);

        System.out.print("请输入结束日期：");
        Date end = getDate(in);

        Data data = new Data(money, rate, start, end);
        System.out.print("利息为 " + data.getInterest() + "分");

        writeOutput(data);
    }

    private static void writeOutput(Data data){
        List<String> outList = new ArrayList<>();

        outList.add("本金：" + data.getMoney());
        outList.add("利率：" + getFloat(data.getRate()));
        outList.add("开始日期：" + data.getStart().toString());
        outList.add("结束时间：" + data.getEnd().toString());

        long period = DataManager.getPeriod(data.getStart(), data.getEnd());
        outList.add("存款时长：" + period + " 天");
        outList.add("利息 = " + data.getMoney() + " * " + getFloat(data.getRate()) + " * " + period + " = " + data.getInterest() + " 分");

        DataManager.writeFile("/Users/bytedance/IdeaProjects/homework/src/test_utf.txt",
                outList, "UTF-8");

        DataManager.writeFile("/Users/bytedance/IdeaProjects/homework/src/test_gbk.txt",
                outList, "GBK");
    }

    private static Date getDate(Scanner in){
        Date temp = null;
        String tmp = in.next();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        while(!checkDate(tmp)){
            System.out.print("日期格式不正确，请重新输入：");
            tmp = in.next();
        }
        try {
            temp = format.parse(tmp);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return temp;
    }

    private static boolean checkDate(String date){
        String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

        return date.matches(regex);
    }

    private static String getFloat(float value){
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(5, RoundingMode.HALF_UP);
        return bd.toString();
    }
}
