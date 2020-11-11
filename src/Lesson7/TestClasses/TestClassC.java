package Lesson7.TestClasses;

import Lesson7.Annotations.BeforeSuite;
import Lesson7.Annotations.Test;

public class TestClassC {
    private int id;
    private String name;

    public TestClassC(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Test(description = "TestClassC getId method test result:", priority = 1)
    public void getId() {
        System.out.println("TestClassC id: " + id);
    }

    @Test(description = "TestClassC getName method test result:", priority = 4)
    public  void getName() {
        System.out.println("TestClassC name: " + name);
    }

    @BeforeSuite
    public  void beforeSuiteMethod(){
        System.out.println("@BeforeSuite TestClassB");
    }

    @BeforeSuite
    public void afterSuiteMethod(){
        System.out.println("@AfterSuite TestClassB");
    }
}
