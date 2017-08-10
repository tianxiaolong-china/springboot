import com.txl.demo.version.ApiVersion;
import com.txl.demo.version.ApiVesrsionCondition;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;

/**
 * Created by xiaolong.tian on 2017/8/10.
 */
public class test {
    @Test
    public void testdDate(){
        String dateString = "22:34:45";
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(dateString);

            SimpleDateFormat newsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(newsdf.format(date));
        }
        catch (ParseException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testDate(){
        String version1 = "2.3";
        String version2 = "2.3.2";
        String version3 = "2.";
        String version4 = "2";


        Matcher matcher1 = ApiVesrsionCondition.VERSION_PATTERN.matcher(version1);
        Matcher matcher2 = ApiVesrsionCondition.VERSION_PATTERN.matcher(version2);
        Matcher matcher3 = ApiVesrsionCondition.VERSION_PATTERN.matcher(version3);
        Matcher matcher4 = ApiVesrsionCondition.VERSION_PATTERN.matcher(version4);

        Assert.assertTrue(matcher1.find());
        Assert.assertFalse(matcher2.find());
        Assert.assertFalse(matcher3.find());
        Assert.assertFalse(matcher4.find());

    }

    @Test
    public void testDateCompare(){
        String expireDateStr = "2008-10-10 0:0:0";

        checkDateAfterNow(expireDateStr);
    }

    public boolean checkDateAfterNow(String expireDateStr){
        Date nowDate = new Date();
        Date expireDate;
        try {
            expireDate = ApiVesrsionCondition.DATE_FORMAT.parse(expireDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }

        if (nowDate.after(expireDate)){
            return false;
        }

        return true;
    }
}
