package com.mycompany.jerseyhk2injection.classes;

public class TestClass2 implements Test2{
    String a;
    public void setA(String a) {
        this.a = a;
    }
    @Override
    public String run() {
        return a;
    }
}

