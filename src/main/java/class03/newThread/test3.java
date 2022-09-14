package class03.newThread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.FutureTask;

@Slf4j
public class test3 {
    public static void main(String[] args) throws Exception {
        FutureTask<Integer> task = new FutureTask<>(()->{
            log.debug("running");
            Thread.sleep(1000);
            return 100;
        });
        Thread t = new Thread(task, "t1");
        t.run();

        int a= task.get();
        log.debug("{}", a);
    }
}
