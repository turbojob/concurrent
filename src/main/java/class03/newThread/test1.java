package class03.newThread;

import lombok.extern.slf4j.Slf4j;

import java.util.TreeMap;

@Slf4j
public class test1 {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                log.debug("running");
            }
        };
        t.start();
        log.debug("main run");
    }
}
