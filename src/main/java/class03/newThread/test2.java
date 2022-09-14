package class03.newThread;

import lombok.extern.slf4j.Slf4j;

import java.util.TreeMap;

@Slf4j
public class test2 {
    public static void main(String[] args) {
        Runnable runnable = () -> { log.debug("runnable"); };
        Thread t = new Thread(runnable, "t2");
        //t.start();
        t.run();
    }
}
