package class03.abcabcabc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Wrongabcabcabc_waitNotify {
    static boolean isA, isB, isC;
    static int timeA = 5, timeB = 5, timeC = 5;
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public synchronized void run() {
                while(!isA && timeA != 0){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(timeA != 0){
                    log.debug("a");
                    timeA--;
                }
                isA = false;
                isB = true;
                notifyAll();
            }
        };
        Thread t2 = new Thread(){
            @Override
            public synchronized void run() {
                while(!isB  && timeB != 0){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(timeB != 0){
                    log.debug("b");
                    timeB--;
                }
                isB = false;
                isC = true;
                notifyAll();
            }
        };
        Thread t3 = new Thread(){
            @Override
            public synchronized void run() {
                while(!isC  && timeC != 0){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(timeC != 0){
                    log.debug("c");
                    timeC--;
                    isC = false;
                    isA = true;
                    notifyAll();
                }
            }

        };
        isA = true;
        t2.start();t3.start();t1.start();
    }
}