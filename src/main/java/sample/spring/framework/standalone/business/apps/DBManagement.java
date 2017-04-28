package sample.spring.framework.standalone.business.apps;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import sample.spring.framework.domain.Address;
import sample.spring.framework.domain.Person;
import sample.spring.framework.standalone.business.apps.dao.PersonDAO;

import java.util.List;

/**
 * Created by associate on 1/26/17.
 */


@Component
class Customer
{
    PersonDAO perDAO;


    @Autowired
    public Customer(PersonDAO pDAO)
    {
       this.perDAO = pDAO;
    }

    public void createPerson()
    {
        Person per1 = new Person();
        per1.setFirstName("CHARLES");
        per1.setLastName("FENZER");
        per1.setPhone("770111120");

        Address add = new Address();
        add.setAddressLine1("333 FOX STREET");
        add.setAddressLine2("SUITE 3B");
        add.setCity("DEERFIELD");
        add.setState("IL");
        add.setZipCode("60089");

        per1.setAddress(add);
        perDAO.create(per1);

    }

    public void printPerson(String phoneNumber)
    {
        List<Person> persons = perDAO.getRecord("770111116");

        if (CollectionUtils.isEmpty(persons))
        {
            System.out.println("No record is found");
        }

        for (Person person : persons)
        {
            System.out.println("Printing Person details...");
            System.out.println("First Name is: " + person.getFirstName());
            System.out.println("Last Name is: " + person.getLastName());
        }

    }

    public boolean delete(String phoneNbr)
    {
        return perDAO.delete(phoneNbr);
    }

}


@Component
public class DBManagement
{
    private static Customer cust;

    @Autowired
    public void DBManagement(Customer argCust)
    {
        DBManagement.cust = argCust;
    }

    public static void main(String args[])
    {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");


        //perDAO.insertPerson("KAREN","EICKMEYER","33433 HAZEL STREET,FL","770111116");

        System.out.println("Deleting entry from PERSON table..." + cust.delete("770111116"));

    }
}
