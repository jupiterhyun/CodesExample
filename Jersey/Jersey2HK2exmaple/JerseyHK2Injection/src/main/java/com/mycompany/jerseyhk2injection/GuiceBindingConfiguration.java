package com.mycompany.jerseyhk2injection;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.mycompany.jerseyhk2injection.classes.Test;
import com.mycompany.jerseyhk2injection.classes.Test2;
import com.mycompany.jerseyhk2injection.classes.TestClass;
import com.mycompany.jerseyhk2injection.classes.TestClass2;

public class GuiceBindingConfiguration implements Module{
    @Override
    public void configure(Binder binder) {
        TestClass t = new TestClass();
        t.setA("I am the king!");
        binder.bind(Test.class).toInstance(t);
        TestClass2 t2 = new TestClass2();
        t2.setA("I am the queen!");
        binder.bind(Test2.class).toInstance(t2);
    }
}
