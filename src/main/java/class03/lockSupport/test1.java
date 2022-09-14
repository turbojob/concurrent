package class03.lockSupport;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class test1 {
    public static void main(String[] args) {
      Thread t = new Thread(){
          @Override
          public void run() {
              log.debug("begin");
              LockSupport.park();
              log.debug("恢复回来了");

          }
      };
      t.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LockSupport.unpark(t);
        log.debug("Main over");
    }
}
