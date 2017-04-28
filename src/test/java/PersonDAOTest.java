import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.powermock.reflect.internal.WhiteboxImpl;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import sample.spring.framework.domain.Address;
import sample.spring.framework.domain.Person;
import sample.spring.framework.standalone.business.apps.dao.PersonDAO;

import static org.junit.Assert.assertThat;


/**
 * Created by associate on 3/14/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(PersonDAO.class)
public class PersonDAOTest
{
    private PersonDAO pDAO;

    @Mock
    private Person testPerson;

    @Mock
    private Address testAddress;

    @Mock
    private DataSourceTransactionManager testDataSource;

    @Mock
    private NamedParameterJdbcTemplate testNamedJdbcTemplate;

    @Before
    public void setupMock() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        pDAO = PowerMockito.spy(new PersonDAO());
        String testId = "P123";

        testPerson.setFirstName("JOHN");
        testPerson.setLastName("DAVIS");
        testPerson.setPhone("6781121212");

        testAddress.setAddressLine1("123 HAZEL STREET");
        testAddress.setAddressLine2("B");
        testAddress.setCity("CEDAR RAPIDS");
        testAddress.setState("MN");
        testAddress.setZipCode("24443");

        PowerMockito.doReturn(testId).when(pDAO,"generatePersonId");
        PowerMockito.doReturn(1).when(pDAO,"insertPerson",testId,testPerson);
        PowerMockito.doReturn(1).when(pDAO,"insertAddress",testId,testAddress);
        PowerMockito.doReturn(0).when(pDAO,"insertPerson",null,testPerson);


        Whitebox.setInternalStateFromContext(pDAO,testDataSource);
        Whitebox.setInternalStateFromContext(pDAO,testNamedJdbcTemplate);

    }


    @Test
    public void testSuccessfulCreatePerson()
    {
        boolean expectedResult=true;
        boolean actualResult = pDAO.create(testPerson);
        assertThat(expectedResult, CoreMatchers.is(actualResult));
    }

    @Test
    public void testUnSuccessfulCreatePerson()
    {
        boolean expectedResult = false;
        boolean actualResult = pDAO.create(null);
        assertThat(expectedResult, CoreMatchers.is(actualResult));
    }

    @Test
    public void testSuccessfulPersonIdGeneration() throws Exception
    {
        String expectedResult = "P123";
        String actualResult = WhiteboxImpl.invokeMethod(pDAO,"generatePersonId");
        assertThat(expectedResult,CoreMatchers.is(actualResult));

    }

    @Test
    public void testSuccessfulInsertPerson() throws Exception
    {
        int expectedResult = 1;
        String testId="P123";

        int actualResult = WhiteboxImpl.invokeMethod(pDAO,"insertPerson",testId,testPerson);
        assertThat(expectedResult, CoreMatchers.is(actualResult));
    }

    @Test
    public void testInsertPersonWithoutValidId() throws Exception
    {
        int expectedResult = 0;
        String testId=null;

        int actualResult = WhiteboxImpl.invokeMethod(pDAO,"insertPerson",testId,testPerson);
        assertThat(expectedResult, CoreMatchers.is(actualResult));

    }

    @Test
    public void testInsertPersonWithoutValidPerson() throws Exception
    {
        int expectedResult = 0;
        String testId="P123";

        int actualResult = WhiteboxImpl.invokeMethod(pDAO,"insertPerson",testId,null);
        assertThat(expectedResult, CoreMatchers.is(actualResult));

    }




}
