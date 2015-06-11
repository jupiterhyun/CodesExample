package com.mycompany.guicetest;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.google.inject.BindingAnnotation;
import com.google.inject.Provider;
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
class AbstructClassProvider implements Provider<AbstructClass> {
    public AbstructClass get() {
    return new hiClass();
  }
}
class SomeModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AbstructClass.class).toProvider(AbstructClassProvider.class);
    }
}
public class GuiceTest {
    public static void main(String[] args) {
        Injector i = Guice.createInjector(new SomeModule());
        AbstructClass mod = i.getInstance(AbstructClass.class);
        mod.say();
    }
}
