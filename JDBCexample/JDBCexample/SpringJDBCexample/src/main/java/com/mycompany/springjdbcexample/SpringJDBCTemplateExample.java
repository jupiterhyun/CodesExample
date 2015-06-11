/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springjdbcexample;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import com.mycompany.springjdbcexample.dao.JdbcDao;
import javax.sql.DataSource;

/**
 *
 * @author jupiter
 */
class SomeModule extends AbstractModule {
  @Override
  protected void configure() {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
    dataSource.setDriver(new SQLServerDriver());
    dataSource.setUrl("jdbc:sqlserver://localhost;databaseName=contactdb;");
    dataSource.setUsername("jupiter");
    dataSource.setPassword("12321");
    bind(DataSource.class)
        .annotatedWith(Names.named("data"))
        .toInstance(dataSource);
  }
}

public class SpringJDBCTemplateExample {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SomeModule());
        JdbcDao mod = injector.getInstance(JdbcDao.class);
        mod.run();
        mod.run2();
    }
    
}
