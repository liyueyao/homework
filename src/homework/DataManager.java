package homework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yyLi
 * @since 2022-07-31 13:16
 */
public class DataManager {

    public static long getPeriod(Date start, Date end) {
        if(start != null && end != null){
            long period = end.getTime() - start.getTime();

            TimeUnit day = TimeUnit.DAYS;

            long diff = day.convert(period, TimeUnit.MILLISECONDS);

            return diff;
        }else {
            return -1L;
        }
    }

    public static long getInterest(long money, float rate, Date start, Date end){
        long period = getPeriod(start, end);
        if(money <= 0 || period <= 0){
            return 0;
        }

        double result = money * rate * period * 100;

        return (long)Math.ceil(result);
    }

    public static void writeFile(String filePath, List<String> lineText, String formate)  {
        File file = new File(filePath);

        try {
            if (!file.isFile()){
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), formate));

            for (String line: lineText){
                writer.write(line + "\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
