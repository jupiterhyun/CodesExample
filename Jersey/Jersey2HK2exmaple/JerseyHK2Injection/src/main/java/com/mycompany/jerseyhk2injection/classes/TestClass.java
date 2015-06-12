package com.mycompany.jerseyhk2injection.classes;

public class TestClass implements Test{
    String a;
    public void setA(String a) {
        this.a = a;
    }
    @Override
    public String run() {
        return a;
    }
}