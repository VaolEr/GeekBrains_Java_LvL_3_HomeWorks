package Lesson5;

//Организуем гонки:
//Все участники должны стартовать одновременно, несмотря на то, что на подготовку у каждого из них уходит разное время.
//В туннель не может заехать одновременно больше половины участников (условность).
//Попробуйте всё это синхронизировать.
//Только после того как все завершат гонку, нужно выдать объявление об окончании.
//Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов из пакета util.concurrent.


// Семафор - 1.15.05
// Защёлка - 1.20.11

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    public static final int CARS_COUNT = 10;
    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(CARS_COUNT);
        Semaphore semaphore = new Semaphore(CARS_COUNT/2);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(semaphore), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), latch);
        }

        Thread[] carsThreads = new Thread[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
             carsThreads[i] = new Thread(cars[i]);
             carsThreads[i].start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nСТАРТ! --->>>");
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        for (int i = 0; i < cars.length; i++) {
            try {
                carsThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n<<<--- ВСЕ УЧАСТНИКИ ЗАВЕРШИЛИ СОСТЯЗАНИЕ --->>>");
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
