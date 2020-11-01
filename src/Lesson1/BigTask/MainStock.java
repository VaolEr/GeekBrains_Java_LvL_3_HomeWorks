package Lesson1.BigTask;

//3. Большая задача:
//+a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
//+b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
//+c. Для хранения фруктов внутри коробки можете использовать ArrayList;
//+d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
//   (вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
//+e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра,
//   true - если их веса равны, false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
//+f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку фруктов,
//   нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается,
//   а в другую перекидываются объекты, которые были в этой коробке;
//+g. Не забываем про метод добавления фрукта в коробку.

import Lesson1.BigTask.Box.Box;
import Lesson1.BigTask.Fruits.Apple;
import Lesson1.BigTask.Fruits.Orange;

import java.util.Random;

public class MainStock {
    public static void main(String[] args) {

        Box<Apple> appleBox = new Box();
        Box<Orange> orangeBox = new Box();

        Random random = new Random();
        int fruitsCount = 50;
        for(int i = 0; i < fruitsCount; i++){
            appleBox.addFruit(new Apple(10.0f * random.nextFloat()));
            //appleBox.addFruit(new Orange(10.0f * random.nextFloat())); // demonstration that oranges cannot be put in a box with apples
            orangeBox.addFruit(new Orange(10.0f * random.nextFloat()));
            //orangeBox.addFruit(new Apple(10.0f * random.nextFloat())); // demonstration that apples cannot be put in a box with oranges
        }

        System.out.println("\n----- Calculate boxes weights -----\n");

        System.out.printf("Apple box contains %d apples with total weight %.2f;\nOrange box contains %d oranges with total weight %.2f\n", appleBox.getCountOfElements(),  appleBox.getWeight(), orangeBox.getCountOfElements(), orangeBox.getWeight());

        System.out.println("\n----- Compare boxes weights -----\n");

        System.out.println("Apple box greater than orange box? Answer: " + appleBox.compare(orangeBox));

        System.out.println("\n----- Pour over apples from old box to new box -----\n");
        Box<Apple> newAppleBox = new Box<>();
        System.out.printf("Before pull over:\nOld box elements count: %d; Weight: %.2f\nNew box elements count: %d; Weight: %.2f\n",
                appleBox.getCountOfElements(), appleBox.getWeight(),newAppleBox.getCountOfElements(),newAppleBox.getWeight());

        newAppleBox = appleBox.pourOver();

        System.out.printf("\nAfter pull over:\nOld box elements count: %d; Weight: %.2f\nNew box elements count: %d; Weight: %.2f\n",
                appleBox.getCountOfElements(), appleBox.getWeight(),newAppleBox.getCountOfElements(),newAppleBox.getWeight());

        //Box<Orange> newOrangeBox = appleBox.pourOver(); // demonstration that apples cannot be put in a box with oranges

    }
}
