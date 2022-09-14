package class03.abcabcabc;

import com.sun.xml.internal.ws.api.server.ThreadLocalContainerResolver;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class reentrantLock {
    public static void main(String[] args) {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();
        new Thread(()->{
            awaitSignal.print("a", a, b);
        }).start();
        new Thread(()->{
            awaitSignal.print("b", b, c);
        }).start();
        new Thread(()->{
            awaitSignal.print("c", c, a);
        }).start();
        awaitSignal.lock();
        a.signal();
        awaitSignal.unlock();
    }
}

class AwaitSignal extends ReentrantLock{
    int loopTimes;

    public AwaitSignal(int loopTimes) {
        this.loopTimes = loopTimes;
    }

    public void print(String cur, Condition current, Condition next){
        for(int i = 0; i < loopTimes; i++){
            lock();
            try {
                current.await();
                System.out.print(cur);
                next.signal();
            }catch (Exception e){

            }finally {
                unlock();
            }
        }
    }
}