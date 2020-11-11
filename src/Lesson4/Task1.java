package Lesson4;

// 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C)
// 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.

public class Task1 {
    static final Object monitor = new Object();
    static volatile int letterIndex = 1;
    static int countOfRepeats = 5;

    public static void main(String[] args) {
        new Thread(()-> {
           try{
               for (int i = 0; i < countOfRepeats; i++){
                   synchronized (monitor){
                       while(letterIndex !=1){
                           monitor.wait();
                       }
                       System.out.print("A");
                       letterIndex = 2;
                       monitor.notifyAll();
                   }
               }
           } catch (InterruptedException e){
               e.printStackTrace();
           }
        }).start();

        new Thread(()-> {
            try{
                for (int i = 0; i < countOfRepeats; i++){
                    synchronized (monitor){
                        while(letterIndex !=2){
                            monitor.wait();
                        }
                        System.out.print("B");
                        letterIndex = 3;
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {
            try{
                for (int i = 0; i < countOfRepeats; i++){
                    synchronized (monitor){
                        while(letterIndex !=3){
                            monitor.wait();
                        }
                        System.out.print("C");
                        letterIndex = 1;
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }
}
