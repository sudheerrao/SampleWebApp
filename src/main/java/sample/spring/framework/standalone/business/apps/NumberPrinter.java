package sample.spring.framework.standalone.business.apps;

import org.springframework.stereotype.Component;

import java.lang.*;
import java.util.Random;

/**
 * Created by associate on 8/25/16.
 */
@Component
@sample.spring.framework.standalone.business.apps.Number
public class NumberPrinter implements IText {

    public String returnText() {

        return "Next number is: "+new Random().nextInt();
    }
}
