package sample.spring.framework.generated.request.response;

import javax.xml.bind.annotation.*;

/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="address" type="{}address"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "", propOrder = { "personId", "firstName", "lastName", "phone",
                "address" }) @XmlRootElement(name = "Person") public class Person
{

    @XmlElement(required = true, nillable = true) protected String  personId;
    @XmlElement(required = true) protected                  String  firstName;
    @XmlElement(required = true, nillable = true) protected String  lastName;
    @XmlElement(required = true) protected                  String  phone;
    @XmlElement(required = true, nillable = true) protected Address address;

    /**
     * Gets the value of the personId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPersonId()
    {
        return personId;
    }

    /**
     * Sets the value of the personId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPersonId(String value)
    {
        this.personId = value;
    }

    /**
     * Gets the value of the firstName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFirstName(String value)
    {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLastName(String value)
    {
        this.lastName = value;
    }

    /**
     * Gets the value of the phone property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPhone(String value)
    {
        this.phone = value;
    }

    /**
     * Gets the value of the address property.
     *
     * @return possible object is
     * {@link Address }
     */
    public Address getAddress()
    {
        return address;
    }

    /**
     * Sets the value of the address property.
     *
     * @param value allowed object is
     *              {@link Address }
     */
    public void setAddress(Address value)
    {
        this.address = value;
    }

}
