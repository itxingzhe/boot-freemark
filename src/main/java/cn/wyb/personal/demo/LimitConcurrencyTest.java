package cn.wyb.personal.demo;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

import com.google.common.util.concurrent.RateLimiter;

/**
 * LimitConcurrencyTest: (请描述这个类).
 *
 * @author wangyibin
 * @date 2018/12/6 10:55
 * @see
 */
public class LimitConcurrencyTest {

    public static final SimpleDateFormat sdf       = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    public static final RateLimiter      limiter   = RateLimiter.create(50);                         // 允许每秒最多50个任务

    public static final Semaphore        semaphore = new Semaphore(50, true);                        // 允许并发的任务量限制为50个，公平的分配资源

    public void rateLimiterTest() {
        double acquire = limiter.acquire();// 请求令牌,超过许可会被阻塞
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(10);
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        String s = numberFormat.format(acquire);
        System.out.println("等待时间：" + s + "秒");
        System.out.println(Thread.currentThread().getName() + " start at :" + sdf.format(new Date()));
    }

    public void rateLimiterTest(RateLimiter limiter) {
        double acquire = limiter.acquire();// 请求令牌,超过许可会被阻塞
        System.out.println("等待时间：" + acquire + "秒");
        System.out.println(Thread.currentThread().getName() + " start at :" + sdf.format(new Date()));
    }

    public void semaphoreTest() {
        try {
            semaphore.acquire(); // 获取信号量,不足会阻塞
            if (semaphore.tryAcquire()) {
                System.out.println(
                        Thread.currentThread().getName() + " start at :" + sdf.format(new Date()) + "," + semaphore.availablePermits());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // semaphore.release();
        }
    }

}

class TestSemaphore implements Runnable {
    public static final SimpleDateFormat sdf       = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    public static final Semaphore        semaphore = new Semaphore(10, true);                        // 允许并发的任务量限制为5个

    public static void main(String[] arg) {
        for (int i = 0; i < 200; i++) {
            Thread t = new Thread(new TestSemaphore());
            t.start();
        }
    }

    public void run() {
        try {
            // semaphore.acquire(); // 获取信号量,不足会阻塞
            if (semaphore.tryAcquire()) {
                System.out.println(sdf.format(new Date()) + " Task Start..");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // semaphore.release(); // 释放信号量
        }
    }
}
