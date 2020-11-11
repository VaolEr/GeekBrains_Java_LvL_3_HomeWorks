package Lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static AtomicInteger PLACE ;
    static {
        CARS_COUNT = 0;
        PLACE = new AtomicInteger(0);
    }
    private Race race;
    private int speed;
    private String name;

    private CountDownLatch latch;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CountDownLatch latch) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;

        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");

        } catch (Exception e) {
            e.printStackTrace();
        }

        latch.countDown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime-startTime;
        String raceTime = "Time: " + totalTime + "ms.";

        if(PLACE.incrementAndGet() == 1){
            System.out.println(this.name + " <<<------------------------------------->>> is WINNER!!! " + raceTime);
        }
        else{
            System.out.println(this.name + " <<<------------------------------------->>> place is " + PLACE.get() + ". " + raceTime);
        }
    }
}
