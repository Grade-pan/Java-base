package TEST;


public class Cpu extends Thread {
    public static void main(String[] args) {
        Cpu myThread1 = new Cpu();
        Cpu myThread2 = new Cpu();
        Cpu myThread3 = new Cpu();
        Cpu myThread4 = new Cpu();
        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
    }

    public void run() {
        int i = 0;
        while(true){
            i++;
        }
    }
}
