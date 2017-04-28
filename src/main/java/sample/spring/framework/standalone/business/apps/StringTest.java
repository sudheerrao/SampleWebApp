package sample.spring.framework.standalone.business.apps;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by associate on 2/7/17.
 */
public class StringTest {

    public static void main(String arggs[])
    {
        List<String> stringList = null;
        stringList = new ArrayList<String>();

        if(CollectionUtils.isEmpty(stringList))
        {
            System.out.println("List is null or empty...");
        }
    }

}
