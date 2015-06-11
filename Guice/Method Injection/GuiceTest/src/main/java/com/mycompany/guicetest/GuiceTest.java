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
    bindConstant().annotatedWith(Names.named("JDBC URL")).to(jdbcUrl);
  }
}
public class GuiceTest {

    public static void main(String[] args) {
        Injector i = Guice.createInjector(new SomeModule());
        SomeClass mod = i.getInstance(SomeClass.class);
        System.out.println(mod.getUrl());
    }
}
