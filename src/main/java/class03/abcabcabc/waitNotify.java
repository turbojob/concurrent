package class03.abcabcabc;

public class waitNotify {
    public static void main(String[] args) {
        wn w = new wn(1, 5);
        Thread t1 = new Thread(()->{
            w.print('a', 1, 2);
        });
        Thread t2 = new Thread(()->{
            w.print('b', 2, 3);
        });
        Thread t3 = new Thread(()->{
            w.print('c', 3, 1);
        });
        t1.start();t2.start();t3.start();
    }
}
class wn{
    private int flag;
    private int loopTime;
    public wn(int a, int b){
        flag = a; loopTime = b;
    }

    public void print(char c, int curFlag, int hope){
        synchronized (this){
            for(int i = 0; i < loopTime; i++){
                while(flag != curFlag){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(c);
                flag = hope;
                notifyAll();
            }
        }
    }
}
