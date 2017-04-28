package sample.spring.framework.standalone.business.apps;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by associate on 8/19/16.
 */

@Component
@Text

public class TextPrinter implements IText {

    public String returnText() {

        return "Some random text: " + new Random().nextInt();
    }
}
