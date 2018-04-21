package Chen.Listener;

import Chen.Class.UpdateDailyTask;
import Chen.Class.UpdateMonthlyTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

public class DailyDBListener implements ServletContextListener {
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

    private Timer timer = null;

    public void contextDestroyed(ServletContextEvent arg0) {
        if (timer != null) {
            timer.cancel();
        }
    }

    public void contextInitialized(ServletContextEvent arg0) {
        timer = new Timer(true);

        System.out.print("Timer Start");

        Calendar calendar_d = Calendar.getInstance();
        Calendar calendar_m = Calendar.getInstance();
        calendar_d.set(Calendar.HOUR_OF_DAY, 17); // 凌晨1点
        calendar_d.set(Calendar.MINUTE, 7);
        calendar_d.set(Calendar.SECOND, 0);
        calendar_m.set(Calendar.DAY_OF_MONTH,1);//月的第一天
        calendar_m.set(Calendar.HOUR_OF_DAY, 1); // 凌晨1点
        calendar_m.set(Calendar.MINUTE, 0);
        calendar_m.set(Calendar.SECOND, 0);
        Date date_d = calendar_d.getTime(); // 第一次执行定时任务的时间
        Date date_m = calendar_m.getTime(); // 第一次执行定时任务的时间
        System.out.print(date_d.toString());
        // 如果第一次执行定时任务的时间 小于当前的时间
        // 此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
        if (date_d.before(new Date())) {
            //System.out.print(this.toString());
            date_d = this.addDay(date_d, 1);
        }
        if (date_m.before(new Date())) {
            date_m = this.addMonth(date_m, 1);
        }
        timer.schedule(new UpdateDailyTask(), date_d, PERIOD_DAY);// 隔一天执行一次
        timer.schedule(new UpdateMonthlyTask(), date_m, PERIOD_DAY);// 隔一天执行一次，但每个月第一天才成功

    }

    // 增加或减少天数
    private Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }
    private Date addMonth(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.MONTH, num);
        return startDT.getTime();
    }
}
