package Lesson1.BigTask.Fruits;

public class Fruit {
    private float fruitWeight;

    public Fruit(float fruitWeight) {
        this.fruitWeight = Math.abs(fruitWeight); // for pass negative weight situations
    }

    public float getFruitWeight() {
        return fruitWeight;
    }
}
