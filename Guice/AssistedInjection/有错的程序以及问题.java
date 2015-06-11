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
import java.util.Date;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
interface Payment {
}
interface CreditService {
    void run();
}
interface AuthService {
    void run();
}
class ChassCreditService implements CreditService {
    @Override
    public void run() { System.out.println("Chass credit running"); }
}
class ChassAuthService implements AuthService {
    @Override
    public void run() { System.out.println("Chass auth running"); }
}
class RealPayment implements Payment{
    CreditService creditService;
    AuthService authService;
    Date day;
    Double amount;
    @Inject
    RealPayment(
        @Named("credit") CreditService creditService, 
        @Named("auth") AuthService authService,
        Date day,//这里是可以的，因为有default constructor
        Double amount//没有default constructor， 报错！！！！
        ){
            this.creditService = creditService;
            this.authService = authService;
            this.day = day;
            this.amount = amount;
        };
    void runCredit() {
        creditService.run();
    }
    void runAuth() {
        authService.run();
    }
    void runday() {
        System.out.println(day);
    }
}
class SomeModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CreditService.class).annotatedWith(Names.named("credit")).to(ChassCreditService.class);
        bind(AuthService.class).annotatedWith(Names.named("auth")).to(ChassAuthService.class);
    }
}

public class GuiceTest {

    public static void main(String[] args) {
        Injector i = Guice.createInjector(new SomeModule());
        RealPayment mod = i.getInstance(RealPayment.class);
        mod.runCredit();
        mod.runday();
    }
}
