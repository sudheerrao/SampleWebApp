package sample.spring.framework.standalone.business.apps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by associate on 8/17/16.
 */

@Component
public class HelloWorld implements IPrinter {

    @Autowired
    @Text

   private IText text;

    public HelloWorld() {

        System.out.println("Hello World constructor being called...");
    }

    public void sayHello() {

        System.out.println("Hello World");
    }

    public void printSomething() {

        System.out.println("Printing: " +text.returnText());
    }

}
