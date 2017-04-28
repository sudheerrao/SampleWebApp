package sample.spring.framework.standalone.business.apps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by associate on 8/17/16.
 */


@Component
public class MainApp {

    private static HelloWorld msgObj;

    @Autowired
    public MainApp(HelloWorld argObj) {

        System.out.println("MainApp constructor initalizing...");
        MainApp.msgObj = argObj;
    }

    public static void main(String args[]) {

        String file = "applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(file);

        msgObj.sayHello();
        System.out.println();
        msgObj.printSomething();

        int count=332;

        if(count <0) {
            System.out.println("Count is less than zero");
        } else if (count > 0) {
            System.out.println("Count is greater than zero");
        } else if (count == 332) {
            System.out.println("Count is equal to the value");
        }

    }

    public void doNothing() {
        //do nothing..
    }
}
