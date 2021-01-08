package Lesson7.TestClasses;

import Lesson7.Annotations.AfterSuite;
import Lesson7.Annotations.BeforeSuite;
import Lesson7.Annotations.Test;

public class TestClassB {
    private int id;
    private String name;

    public TestClassB(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Test(description = "TestClassB getId method test result:", priority = 1)
    public void getId() {
        System.out.println("TestClassB getId method test result: " + id);
    }

    @Test(description = "TestClassB getName method test result:", priority = 4)
    public  void getName() {
        System.out.println("TestClassB getName method test result: " + name);
    }

    @BeforeSuite
    public  void beforeSuiteMethod(){
        System.out.println("@BeforeSuite TestClassB method result.");
    }

    //@AfterSuite
    public void afterSuiteMethod(){
        System.out.println("@AfterSuite TestClassB method result.");
    }
}
