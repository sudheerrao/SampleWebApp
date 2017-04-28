package sample.spring.framework.transformer;

import com.google.common.base.Function;
import org.springframework.stereotype.Component;
import sample.spring.framework.generated.request.response.Address;
import sample.spring.framework.generated.request.response.Person;

/**
 * Created by associate on 3/15/17.
 */

@Component
public class PersonRequestTransformer
{

    private static final PersonTransformer PERSON_TRANSFORMER = new PersonTransformer();
    private static final AddressTransformer ADDRESS_TRANSFORMER = new AddressTransformer();


    public sample.spring.framework.domain.Person convertToDomain(Person person)
    {
        return PERSON_TRANSFORMER.apply(person);
    }

    private static class PersonTransformer implements Function<Person, sample.spring.framework.domain.Person>
    {
        public sample.spring.framework.domain.Person apply(Person input)
        {
            sample.spring.framework.domain.Person person  = null;

            if(input !=null)
            {
                person = new sample.spring.framework.domain.Person();
                person.setFirstName(input.getFirstName());
                person.setLastName(input.getLastName());
                person.setPhone(input.getPhone());
                person.setAddress(ADDRESS_TRANSFORMER.apply(input.getAddress()));
            }
            return person;
        }
    }


    private static class AddressTransformer implements Function<Address, sample.spring.framework.domain.Address>
    {
        public sample.spring.framework.domain.Address apply(Address input)
        {
            sample.spring.framework.domain.Address address = null;

            if(input !=null)
            {
                address = new sample.spring.framework.domain.Address();
                address.setAddressLine1(input.getAddressLine1());
                address.setAddressLine2(input.getAddressLine1());
                address.setCity(input.getCity());
                address.setState(input.getState());
                address.setZipCode(input.getZipCode());
            }
            return address;
        }
    }
}
