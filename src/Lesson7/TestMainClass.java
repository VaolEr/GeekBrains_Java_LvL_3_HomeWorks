package Lesson7;

import Lesson7.TestClasses.TestClassA;
import Lesson7.TestClasses.TestClassB;
import Lesson7.TestClasses.TestClassC;
import Lesson7.TestClasses.TestClassD;
import Lesson7.TestUtil.Tester;

public class TestMainClass {
    public static void main(String[] args) {
        Tester tester = new Tester();
        TestClassA testA = new TestClassA(0,"TestClassA");
        TestClassB testB = new TestClassB(1,"TestClassB");
        TestClassC testC = new TestClassC(2,"TestClassC");
        TestClassD testD = new TestClassD(3,"TestClassD");
        try {
            tester.start(testA);
            tester.start(testB.getClass());
            tester.start(testC.getClass().getName());
        } catch ( RuntimeException e) {
            e.printStackTrace();
        }

        try {
            tester.start(testD);
        }catch (RuntimeException e) {
            e.printStackTrace();
        }

    }
}
