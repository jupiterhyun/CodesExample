package com.mycompany.assistedinjection;

import java.util.List;
import java.util.ArrayList;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import java.util.Date;

/**
 *
 * @author jupiter
 */
class SomeClass {

    String jdbcUrl;
    List<String> names;

    @Inject
    SomeClass(@Named("JDBC URL") String jdbcUrl, List<String> list) {
        this.jdbcUrl = jdbcUrl;
        this.names = list;
    }

    String getUrl() {
        return jdbcUrl;
    }

    void print() {
        names
                .stream()
                .forEach(t -> System.out.println(t));
    }
}
//assisted injection
class Money {

    double money;

    Money(double money) {
        this.money = money;
    }

    double get() {
        return money;
    }

    void set(double money) {
        this.money = money;
    }
}
//这个是用来创建实例的
interface PaymentFactory {

    public Payment create(Date startDate, Money amount);
}
//返回的接口
interface Payment {

    public void runCredit();

    public void runAuth();

    public void run();
}

interface CreditService {

    public void run();
}

interface AuthService {

    public void run();
}

class ChassCreditService implements CreditService {

    @Override
    public void run() {
        System.out.println("Chass credit running");
    }
}

class ChassAuthService implements AuthService {

    @Override
    public void run() {
        System.out.println("Chass auth running");
    }
}
//返回接口的实现函数
class RealPayment implements Payment {

    CreditService creditService;
    AuthService authService;
    Date day;
    Money amount;
    //一定要有这个
    public RealPayment() {
    }
    //day amount 用的时候再创建
    @AssistedInject
    public RealPayment(
            CreditService creditService,
            AuthService authService,
            @Assisted Date day,
            @Assisted Money amount
    ) {
        this.creditService = creditService;
        this.authService = authService;
        this.day = day;
        this.amount = amount;
    }

    @Override
    public void runCredit() {
        creditService.run();
    }

    @Override
    public void runAuth() {
        authService.run();
    }

    @Override
    public void run() {
        System.out.println(day.toString() + " " + amount.get());
    }

}

class PaymentAction {
    //最后是用这个factory建一个payment，然后pay
    @Inject
    private PaymentFactory paymentFactory;

    public void doPayment(Money amount) {
        Payment payment = paymentFactory.create(new Date(), amount);
        payment.runAuth();
        payment.runCredit();
        payment.run();
        System.out.println(payment);
    }
}

class SomeModule extends AbstractModule {

    private final String jdbcUrl = "adsbadsfasdfasd"; // set in constructor

    @Override
    protected void configure() {
        List<String> list = new ArrayList<>();
        list.add("muhahah");
        list.add("hehhe");
        bindConstant().annotatedWith(Names.named("JDBC URL")).to(jdbcUrl);
        //TypeLiteral
        bind(new TypeLiteral<List<String>>() {
        }).toInstance(list);

        bind(CreditService.class).toInstance(new ChassCreditService());
        bind(AuthService.class).toInstance(new ChassAuthService());

        install(new FactoryModuleBuilder()
                .implement(Payment.class, RealPayment.class)
                .build(PaymentFactory.class));

        bind(PaymentAction.class).toInstance(new PaymentAction());
    }
}

public class GuiceTest {

    public static void main(String[] args) {
        Injector i = Guice.createInjector(new SomeModule());
        SomeClass mod = i.getInstance(SomeClass.class);
        System.out.println(mod.getUrl());
        mod.print();

        RealPayment r = i.getInstance(RealPayment.class);

        PaymentAction p = i.getInstance(PaymentAction.class);
        p.doPayment(new Money(100));

    }
}
