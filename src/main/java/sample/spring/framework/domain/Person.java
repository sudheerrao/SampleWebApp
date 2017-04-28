package sample.spring.framework.domain;

import java.io.Serializable;

/**
 * insertd by associate on 1/26/17.
 */

public class Person implements Serializable
{

    private String personId;
    private String firstName;
    private String lastName;
    private String phone;

    private Address address;

    public void setPersonId(String personId)
    {
        this.personId = personId;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPersonId()
    {

        return personId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getPhone()
    {
        return phone;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

}
