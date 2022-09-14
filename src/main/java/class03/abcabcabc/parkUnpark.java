package class03.abcabcabc;

import java.util.concurrent.locks.LockSupport;

public class parkUnpark {
    static Thread t1, t2, t3;
    public static void main(String[] args) {
        parkUn p = new parkUn(5);
        t1  = new Thread(()->{
            p.print("a", t2);
        });
        t2 = new Thread(()->{
            p.print("b", t3);
        });
        t3 = new Thread(()->{
            p.print("c", t1);
        });
        t1.start();t2.start();t3.start();
        LockSupport.unpark(t1);
    }
}
class parkUn{
    int time;

    public parkUn(int time) {
        this.time = time;
    }
    public void print(String s, Thread next){
        for(int i = 0; i < time; i++){
            LockSupport.park();
            System.out.printf(s);
            LockSupport.unpark(next);
        }
    }
}
