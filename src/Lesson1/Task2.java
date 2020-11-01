package Lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task2 {

    public static <T> ArrayList<T> arrayToArrayListMethod(T[] array){
        int size = array.length;
        ArrayList<T> resultList= new ArrayList<>();

        if(size > 0) {
            //Manual variant
//            for (T element : array) {
//                resultList.add(element);
//            }
            //Variant with Collection
            resultList.addAll(Arrays.asList(array));
        }
        else {
            throw new IllegalArgumentException("Array argument have zero length!");
        }
        return resultList;
    }

    public static void main(String[] args) {
        String[] testArray = {"zero", "first", "second", "third"};
        ArrayList<String> stringListFromTestArray= arrayToArrayListMethod(testArray);
        for (Object o : stringListFromTestArray) {
            System.out.println(o);
        }
    }
}
