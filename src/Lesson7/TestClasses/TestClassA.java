package Lesson7.TestClasses;

import Lesson7.Annotations.AfterSuite;
import Lesson7.Annotations.BeforeSuite;
import Lesson7.Annotations.Test;

public class TestClassA {
    private  int id;
    private  String name;

    public TestClassA(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Test(description = "TestClassA getId method test result:", priority = 7)
    public void getId() {
        System.out.println("TestClassA getId method test result: " + id);
    }

    @Test(description = "TestClassA getName method test result:", priority = 4)
    public  void getName() {
        System.out.println("TestClassA getName method test result: " + name);
    }

    @BeforeSuite
    public  void beforeSuiteMethod(){
        System.out.println("@BeforeSuite TestClassA");
    }

    @AfterSuite
    public void afterSuiteMethod(){
        System.out.println("@AfterSuite TestClassA");
    }

}
