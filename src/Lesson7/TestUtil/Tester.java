package Lesson7.TestUtil;

import Lesson7.Annotations.AfterSuite;
import Lesson7.Annotations.BeforeSuite;
import Lesson7.Annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

// Создать класс, который может выполнять «тесты», в качестве тестов выступают классы с наборами методов с аннотациями @Test.
// Для этого у него должен быть статический метод start(), которому в качестве параметра передается или объект типа Class,
// или имя класса. Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется,
// далее запущены методы с аннотациями @Test, а по завершению всех тестов – метод с аннотацией @AfterSuite.
// К каждому тесту необходимо также добавить приоритеты (int числа от 1 до 10), в соответствии с которыми будет выбираться
// порядок их выполнения, если приоритет одинаковый, то порядок не имеет значения.
// Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре,
// иначе необходимо бросить RuntimeException при запуске «тестирования».

public class Tester {

    private int countOfBeforeSuiteMethods = 0;
    private int countOfAfterSuiteMethods = 0;

    Constructor<?> constructor;

    public void start(Object obj)  {
        countOfBeforeSuiteMethods = 0;
        countOfAfterSuiteMethods = 0;

        Class testClass = obj.getClass();

        ArrayList<Method> allTestClassMethods = new ArrayList<>(Arrays.asList(testClass.getMethods()));
        System.out.println("\nAll test class [" + testClass.getName() + "] methods.");
        System.out.println(Arrays.toString(allTestClassMethods.toArray()));

        ArrayList<Method> testClassMethods = new ArrayList<>();
        for (Method classMethod:allTestClassMethods) {
            if(classMethod.isAnnotationPresent(BeforeSuite.class)||
               classMethod.isAnnotationPresent(AfterSuite.class)||
               classMethod.isAnnotationPresent(Test.class)){
               testClassMethods.add(classMethod);
            }
        }
        System.out.println("\n@Annotated test class [" + testClass.getName() + "] methods.");
        System.out.println(Arrays.toString(testClassMethods.toArray()));

        Method first = null;
        int indexFirst = 0;
        Method last = null;
        int indexLast = 0;

        for (int i = 0; i < testClassMethods.size(); i++) {
            //If we have @BeforeSuite, then place it first and chek array for other @BeforeSuite
            if (testClassMethods.get(i).isAnnotationPresent(BeforeSuite.class)) {
                countOfBeforeSuiteMethods++;
                if (countOfBeforeSuiteMethods > 1) {
                    throw new RuntimeException("Count of @BeforeSuite annotated methods more than 1!");
                }
                first = testClassMethods.get(i);
                indexFirst = i;
            }
            //If we have @AfterSuite, then place it last and chek array for other @AfterSuite
            if (testClassMethods.get(i).isAnnotationPresent(AfterSuite.class)) {
                countOfAfterSuiteMethods++;
                if (countOfAfterSuiteMethods > 1) {
                    throw new RuntimeException("Count of @AfterSuite annotated methods more than 1!");
                }
                last = testClassMethods.get(i);
                indexLast = i;
            }
        }

        if(countOfBeforeSuiteMethods == 1){
            testClassMethods.remove(indexFirst);
            testClassMethods.add(0, first);
        }

        if(countOfAfterSuiteMethods == 1){
            testClassMethods.remove(indexLast);
            testClassMethods.add(last);
        }

        int startIndex = countOfBeforeSuiteMethods;
        int stopIndex = testClassMethods.size();

        sortMethodsByPriority(testClassMethods, startIndex, stopIndex);

        System.out.println("\nSorted by priority test class [" + testClass.getName() + "] methods.");
        System.out.println(Arrays.toString(testClassMethods.toArray()));
        System.out.println("\n");

        for (Method testMethod : testClassMethods) {
            try {
                testMethod.invoke(obj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        System.out.println("//--------------------------------------------------------------------------------------//");
    }

    public void start(Class testClass)  {
        countOfBeforeSuiteMethods = 0;
        countOfAfterSuiteMethods = 0;

        //Class testClass = obj.getClass();
        try {
             constructor = testClass.getConstructor(int.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        ArrayList<Method> allTestClassMethods = new ArrayList<>(Arrays.asList(testClass.getMethods()));
        System.out.println("\nAll test class [" + testClass.getName() + "] methods.");
        System.out.println(Arrays.toString(allTestClassMethods.toArray()));

        ArrayList<Method> testClassMethods = new ArrayList<>();
        for (Method classMethod:allTestClassMethods) {
            if(classMethod.isAnnotationPresent(BeforeSuite.class)||
                    classMethod.isAnnotationPresent(AfterSuite.class)||
                    classMethod.isAnnotationPresent(Test.class)){
                testClassMethods.add(classMethod);
            }
        }
        System.out.println("\n@Annotated test class [" + testClass.getName() + "] methods.");
        System.out.println(Arrays.toString(testClassMethods.toArray()));

        Method first = null;
        int indexFirst = 0;
        Method last = null;
        int indexLast = 0;

        for (int i = 0; i < testClassMethods.size(); i++) {
            //If we have @BeforeSuite, then place it first and chek array for other @BeforeSuite
            if (testClassMethods.get(i).isAnnotationPresent(BeforeSuite.class)) {
                countOfBeforeSuiteMethods++;
                if (countOfBeforeSuiteMethods > 1) {
                    throw new RuntimeException("Count of @BeforeSuite annotated methods more than 1!");
                }
                first = testClassMethods.get(i);
                indexFirst = i;
            }
            //If we have @AfterSuite, then place it last and chek array for other @AfterSuite
            if (testClassMethods.get(i).isAnnotationPresent(AfterSuite.class)) {
                countOfAfterSuiteMethods++;
                if (countOfAfterSuiteMethods > 1) {
                    throw new RuntimeException("Count of @AfterSuite annotated methods more than 1!");
                }
                last = testClassMethods.get(i);
                indexLast = i;
            }
        }

        if(countOfBeforeSuiteMethods == 1){
            testClassMethods.remove(indexFirst);
            testClassMethods.add(0, first);
        }

        if(countOfAfterSuiteMethods == 1){
            testClassMethods.remove(indexLast);
            testClassMethods.add(last);
        }

        int startIndex = countOfBeforeSuiteMethods;
        int stopIndex = testClassMethods.size();

        sortMethodsByPriority(testClassMethods, startIndex, stopIndex);

        System.out.println("\nSorted by priority test class [" + testClass.getName() + "] methods.");
        System.out.println(Arrays.toString(testClassMethods.toArray()));
        System.out.println("\n");

        for (Method testMethod : testClassMethods) {

            try {
                testMethod.invoke(constructor.newInstance(1,"Object from method start by Class object"));
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        System.out.println("//--------------------------------------------------------------------------------------//");
    }

    public void start(String className)  {
        countOfBeforeSuiteMethods = 0;
        countOfAfterSuiteMethods = 0;

        Class testClass = null;
        try {
             testClass = Class.forName(className);
             constructor = testClass.getConstructor(int.class, String.class);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        assert testClass != null;
        ArrayList<Method> allTestClassMethods = new ArrayList<>(Arrays.asList(testClass.getMethods()));
        System.out.println("\nAll test class [" + testClass.getName() + "] methods.");
        System.out.println(Arrays.toString(allTestClassMethods.toArray()));

        ArrayList<Method> testClassMethods = new ArrayList<>();
        for (Method classMethod:allTestClassMethods) {
            if(classMethod.isAnnotationPresent(BeforeSuite.class)||
                    classMethod.isAnnotationPresent(AfterSuite.class)||
                    classMethod.isAnnotationPresent(Test.class)){
                testClassMethods.add(classMethod);
            }
        }
        System.out.println("\n@Annotated test class [" + testClass.getName() + "] methods.");
        System.out.println(Arrays.toString(testClassMethods.toArray()));

        Method first = null;
        int indexFirst = 0;
        Method last = null;
        int indexLast = 0;

        for (int i = 0; i < testClassMethods.size(); i++) {
            //If we have @BeforeSuite, then place it first and chek array for other @BeforeSuite
            if (testClassMethods.get(i).isAnnotationPresent(BeforeSuite.class)) {
                countOfBeforeSuiteMethods++;
                if (countOfBeforeSuiteMethods > 1) {
                    throw new RuntimeException("Count of @BeforeSuite annotated methods more than 1!");
                }
                first = testClassMethods.get(i);
                indexFirst = i;
            }
            //If we have @AfterSuite, then place it last and chek array for other @AfterSuite
            if (testClassMethods.get(i).isAnnotationPresent(AfterSuite.class)) {
                countOfAfterSuiteMethods++;
                if (countOfAfterSuiteMethods > 1) {
                    throw new RuntimeException("Count of @AfterSuite annotated methods more than 1!");
                }
                last = testClassMethods.get(i);
                indexLast = i;
            }
        }

        if(countOfBeforeSuiteMethods == 1){
            testClassMethods.remove(indexFirst);
            testClassMethods.add(0, first);
        }

        if(countOfAfterSuiteMethods == 1){
            testClassMethods.remove(indexLast);
            testClassMethods.add(last);
        }

        int startIndex = countOfBeforeSuiteMethods;
        int stopIndex = testClassMethods.size();

        sortMethodsByPriority(testClassMethods, startIndex, stopIndex);

        System.out.println("\nSorted by priority test class [" + testClass.getName() + "] methods.");
        System.out.println(Arrays.toString(testClassMethods.toArray()));
        System.out.println("\n");

        for (Method testMethod : testClassMethods) {

            try {
                testMethod.invoke(constructor.newInstance(1,"Object from method start by name"));
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        System.out.println("//--------------------------------------------------------------------------------------//");
    }

    private void swap(ArrayList<Method> array, int ind1, int ind2) {
        Method tmp = array.get(ind1);
        array.remove(ind1);
        array.add(ind1, array.get(ind2));
        array.remove(ind2);
        array.add(ind2, tmp);
    }

    private void sortMethodsByPriority(ArrayList<Method> methodsArray, int startIndex, int stopIndex) {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = startIndex + 1; i < stopIndex; i++) {
                if (methodsArray.get(i).isAnnotationPresent(Test.class) && methodsArray.get(i-1).isAnnotationPresent(Test.class)) {
                    if (methodsArray.get(i).getAnnotation(Test.class).priority() < methodsArray.get(i - 1).getAnnotation(Test.class).priority()) {
                        swap(methodsArray, i, i - 1);
                        needIteration = true;
                    }
                }
            }
        }
    }


}
