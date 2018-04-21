package Chen.Listener;

import Chen.Class.UpdateDailyTask;

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

        System.out.print("定时器启动");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 1); // 凌晨1点
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime(); // 第一次执行定时任务的时间
        System.out.print(date.toString());
        // 如果第一次执行定时任务的时间 小于当前的时间
        // 此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
        if (date.before(new Date())) {
            System.out.print(this.toString());
            date = this.addDay(date, 1);
        }
        timer.schedule(new UpdateDailyTask(), date, PERIOD_DAY);// 隔一天执行一次

    }

    // 增加或减少天数
    private Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }
}
