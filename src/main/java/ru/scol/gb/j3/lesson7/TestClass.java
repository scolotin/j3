package ru.scol.gb.j3.lesson7;

public class TestClass {

    @BeforeSuite
    public void welcome() {
        System.out.println("Welcome to " + this.getClass().getName());
    }

    @TestSuite(priority = 10)
    public void doingSomethingImportant() {
        System.out.println(this.getClass().getName() + " doing something important ...");
    }

    @TestSuite(priority = 0)
    public void doingSomethingUnimportant() {
        System.out.println(this.getClass().getName() + " doing something unimportant ...");
    }

    @TestSuite
    public void doingSomething() {
        System.out.println(this.getClass().getName() + " doing something ...");
    }

    @AfterSuite
    public void bye() {
        System.out.println("Bye from " + this.getClass().getName());
    }
}
