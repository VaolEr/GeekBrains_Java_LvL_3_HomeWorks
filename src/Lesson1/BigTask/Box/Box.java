package Lesson1.BigTask.Box;

import Lesson1.BigTask.Fruits.Fruit;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Box<T extends Fruit>  {

    ArrayList<T> fruitBox = new ArrayList<>();

    public Box() {

    }

    public ArrayList<T> getFruitBox() {
        return fruitBox;
    }

    public void addFruit(T fruit){
        fruitBox.add(fruit);
    }

    public float getWeight(){
        float weight = 0.0f;
        for (T fruit:fruitBox) {
            weight += fruit.getFruitWeight();
        }
        return weight;
    }

    public int getCountOfElements() {
        return fruitBox.size();
    }

    public boolean compare(@NotNull Box<?> otherBox){
        //System.out.printf("\n%f vs %f\n",this.getWeight(),otherBox.getWeight());
        if((this.getWeight() - otherBox.getWeight()) < 0.0f){
            return false;
        }
        else if((this.getWeight() - otherBox.getWeight()) > 0.0f){
            return true;
        }
        else {
            return Math.abs(otherBox.getWeight() - this.getWeight()) < 0.00000000001f;
        }
    }

    public Box<T> pourOver(){
        Box<T> box = new Box<>();
        for (T fruit : fruitBox) {
            box.addFruit(fruit);
        }
        if (fruitBox.size() > 0) {
            fruitBox.subList(0, fruitBox.size()).clear();
        }
        return box;
    }
}
