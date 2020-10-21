package Lesson1;

//1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);

public class Task1 {

    public static <T> void changeElementsPosition( T[] array, int elementA, int elementB){

        int arraySize = array.length;

        if((elementA < 0) || (elementB < 0)){
            throw new IllegalArgumentException("Element position can't be less than 0!");
        }
        if(elementA == elementB){
            //I think it is not important exception, but I made it.
            throw new IllegalArgumentException("Elements have the same position!");
        }
        if((elementA >= arraySize) || (elementB >= arraySize)){
            throw new IllegalArgumentException("One of element's position greater than array length!");
        }

        T firstElement = array[elementA];
        T secondElement = array[elementB];
        array[elementA] = secondElement;
        array[elementB] = firstElement;

        for (T element:array) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        String[] testArray = {"zero", "first", "second", "third"};
        changeElementsPosition(testArray,0,3);
        //changeElementsPosition(testArray,4,3);
        //changeElementsPosition(testArray,2,4);
        //changeElementsPosition(testArray,-1,3);
        //changeElementsPosition(testArray,2,2);

        Integer[] testArrayInt = {0, 1, 2, 3};
        changeElementsPosition(testArrayInt,0,3);
        //changeElementsPosition(testArray,4,3);
        //changeElementsPosition(testArray,2,4);
        //changeElementsPosition(testArray,-1,3);
        //changeElementsPosition(testArray,2,2);
    }

}
