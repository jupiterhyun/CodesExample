/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guicetest;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.google.inject.BindingAnnotation;
import com.google.inject.Provides;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

interface AbstructClass{
    void say();
}
class hiClass implements AbstructClass{
    @Override
    public void say() { System.out.println("hi"); }
}
class hooClass implements AbstructClass{
    @Override
    public void say() { System.out.println("hoo"); }
}
class SomeModule extends AbstractModule {
    @Override
    protected void configure() {
    }
    @Provides
    AbstructClass provideAbstructClass() {
        return new hooClass();
    }
}

public class GuiceTest {
    public static void main(String[] args) {
        Injector i = Guice.createInjector(new SomeModule());
        AbstructClass mod = i.getInstance(AbstructClass.class);
        mod.say();
    }
}
