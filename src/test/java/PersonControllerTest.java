import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;
import sample.spring.framework.controllers.PersonController;
import sample.spring.framework.domain.Address;
import sample.spring.framework.domain.Person;
import sample.spring.framework.standalone.business.apps.dao.PersonDAO;
import sample.spring.framework.transformer.PersonResponseTransformer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by associate on 3/13/17.
 */

public class    PersonControllerTest
{
    @InjectMocks
    private PersonController personControllerService;

    @Mock
    private PersonDAO personDAO;

    @Mock
    private PersonResponseTransformer perRespTransformer;

    @Mock
    private Address address;


    String personFirstName="DAVID";
    String personLastName="MEYERS";
    String personPhone="6781111112";
    String personIdentifier="P123";
    String addressLine1 = "123 HAZEL STREET";
    String addressLine2 = "B";
    String city = "DURHAM";
    String state = "NC";
    String zipCode = "27231";

    @Before
    public void setupMock()
    {
        MockitoAnnotations.initMocks(this);

        sample.spring.framework.generated.request.response.Person response = null;

        Person personDomain = createTestPersonDomain();

        response = createTestRespnose();

        List<Person> domain = new ArrayList<Person>();
        domain.add(personDomain);

        when(personDAO.getRecord(Mockito.any(String.class))).thenReturn(domain);
        when(perRespTransformer.convertFromDomain(Mockito.any(Person.class))).thenReturn(response);

        Whitebox.setInternalStateFromContext(personControllerService, personDAO);
        Whitebox.setInternalStateFromContext(personControllerService,perRespTransformer);

    }
    
    


    @Test
    public void testGetValidPersonDetails()
    {
        String reqPhoneNumber = "6781111112";

        sample.spring.framework.generated.request.response.Person actualResponse = personControllerService
                        .getPersonDetails(reqPhoneNumber);

        assertThat(actualResponse.getFirstName(), CoreMatchers.notNullValue());
        assertThat(actualResponse.getFirstName(), CoreMatchers.is(personFirstName));
        assertThat(actualResponse.getLastName(), CoreMatchers.notNullValue());
        assertThat(actualResponse.getLastName(), CoreMatchers.is(personLastName));
        assertThat(actualResponse.getPhone(), CoreMatchers.notNullValue());
        assertThat(actualResponse.getPhone(), CoreMatchers.is(personPhone));

    }

    @Test
    public void testInvalidInputForPersonDetails()
    {
        sample.spring.framework.generated.request.response.Person actualResponse = personControllerService
                        .getPersonDetails(null);

        assertThat(actualResponse, CoreMatchers.nullValue());
    }



    private Person createTestPersonDomain()
    {
        Person testPerson = new Person();

        testPerson.setFirstName(personFirstName);
        testPerson.setLastName(personLastName);
        testPerson.setPhone(personPhone);
        testPerson.setPersonId(personIdentifier);
        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);
        testPerson.setAddress(address);

        return testPerson;
    }

    private sample.spring.framework.generated.request.response.Person createTestRespnose()
    {
        sample.spring.framework.generated.request.response.Person testResponse = new sample.spring.framework.generated.request.response.Person();
        sample.spring.framework.generated.request.response.Address add = new sample.spring.framework.generated.request.response.Address();

        testResponse.setFirstName(personFirstName);
        testResponse.setLastName(personLastName);
        testResponse.setPhone(personPhone);
        add.setAddressLine1(addressLine1);
        add.setAddressLine2(addressLine2);
        add.setCity(city);
        add.setState(state);
        add.setZipCode(zipCode);
        testResponse.setAddress(add);

        return testResponse;
    }

}
