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
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
/**
 *
 * @author jupiter
 */
class SomeClass {
  String jdbcUrl;
  @Inject
  SomeClass(@Named("JDBC URL") String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }
  String getUrl() {
      return jdbcUrl;
  }
}
class SomeModule extends AbstractModule {
  private final String jdbcUrl = "adsbadsfasdfasd"; // set in constructor

  @Override
  protected void configure() {
     bind(String.class)
        .annotatedWith(Names.named("JDBC URL"))
        .toInstance("jdbc:mysql://localhost/pizza");
  }
}
public class GuiceTest {

    public static void main(String[] args) {
        Injector i = Guice.createInjector(new SomeModule());
        SomeClass mod = i.getInstance(SomeClass.class);
        System.out.println(mod.getUrl());
    }
}
