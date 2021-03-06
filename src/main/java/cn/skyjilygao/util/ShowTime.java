package cn.skyjilygao.util;

import com.ocpsoft.pretty.time.PrettyTime;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class ShowTime extends PrettyTime {
    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 1天毫秒数
     */
    private static long dayTimeMillis = 24 * 60 * 60 * 1000L;
    private static int default_days = 90;
    /**
     * 90天毫秒数（默认）
     */
    private static long threeMonth = dayTimeMillis * default_days;

    /**
     * 返回多久以前：几分钟前，几小时前，几天前等等.
     * 默认：如果超过90天以前，就直接显示时间：yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String showTimeStr(Date date){
        return showTimeStr(date, default_days);
    }
    /**
     * 返回多久以前：几分钟前，几小时前，几天前等等.
     * 默认：如果超过90天以前，就直接显示时间：yyyy-MM-dd HH:mm:ss
     * @param date
     * @param days 多少天：表示如果date早于指定days以前，就直接显示时间字符串
     * @return
     */
    public static String showTimeStr(Date date, int days){
        long time = date.getTime();
        if (System.currentTimeMillis() - time > days * dayTimeMillis) {
            SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_PATTERN);
            return df.format(date);
        }
        return prettyTime(date);
    }
    /**
     * 返回多久以前：几分钟前，几小时前，几天前等等
     * @param date
     * @return 几分钟前，几小时前，几天前等等。时间越长，精度可能不准
     */
    public static String prettyTime(Date date){
        Locale.setDefault(Locale.CHINA);
        PrettyTime p = new PrettyTime();
        return StringUtils.deleteWhitespace(p.format(date));
    }
}
