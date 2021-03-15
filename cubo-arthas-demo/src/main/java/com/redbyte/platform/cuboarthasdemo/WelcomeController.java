package com.redbyte.platform.cuboarthasdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
@RestController
public class WelcomeController {

    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Autowired
    WelcomeService welcomeService;

    /**
     * 调用链
     */
    @RequestMapping("/mockRequestChain")
    public Map mockRequestChain() {
        Map map = new HashMap();
        String welcome = welcomeService.getWelcome();
        map.put("code", "200");
        map.put("data", welcome);
        return map;
    }

    /**
     * cpu高
     */
    @RequestMapping("/mockHighCpu")
    public void mockHighCpu() {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("cpu start high...");
            }
        });
        executorService.submit(thread);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    System.out.println("cpu start");

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * deadlock
     */
    @RequestMapping("/mockDeadLock")
    public void deadLock() {
        Object obj1 = new Object();
        Object obj2 = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (obj1) {

                System.out.println(Thread.currentThread() + "get obj1");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (obj2) {
                    System.out.println(Thread.currentThread() + "get obj2");
                }

            }

        });

        Thread thread2 = new Thread(() -> {
            synchronized (obj2) {

                System.out.println(Thread.currentThread() + "get obj2");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (obj1) {
                    System.out.println(Thread.currentThread() + "get obj1");
                }

            }
        });

        thread1.start();
        thread2.start();
    }
}
