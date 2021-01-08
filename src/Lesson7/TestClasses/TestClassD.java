package Lesson7.TestClasses;

import Lesson7.Annotations.AfterSuite;
import Lesson7.Annotations.BeforeSuite;
import Lesson7.Annotations.Test;

public class TestClassD {
    private int id;
    private String name;

    public TestClassD(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Test(description = "TestClassD getId method test result:", priority = 1)
    public void getId() {
        System.out.println("TestClassD getId method test result: " + id);
    }

    @Test(description = "TestClassD getName method test result:", priority = 4)
    public  void getName() {
        System.out.println("TestClassD getName method test result: " + name);
    }

    @AfterSuite
    public  void beforeSuiteMethod(){
        System.out.println("@AfterSuiteSuite TestClassD Error Method");
    }

    @AfterSuite
    public void afterSuiteMethod(){
        System.out.println("@AfterSuite TestClassD Error Method");
    }
}
