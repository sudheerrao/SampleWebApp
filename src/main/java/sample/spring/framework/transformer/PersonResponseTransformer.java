package sample.spring.framework.transformer;

import com.google.common.base.Function;
import org.springframework.stereotype.Component;
import sample.spring.framework.domain.Address;
import sample.spring.framework.domain.Person;

/**
 * Created by associate on 3/16/17.
 */

@Component
public class PersonResponseTransformer
{
    private static AddressTransformer ADDRESS_TRANSFORMER = new AddressTransformer();
    private static PersonTransformer  PERSON_TRANSFORMER  = new PersonTransformer();

    public sample.spring.framework.generated.request.response.Person convertFromDomain(Person per)
    {
        return PERSON_TRANSFORMER.apply(per);
    }

    private static class PersonTransformer
                    implements Function<Person, sample.spring.framework.generated.request.response.Person>
    {
        public sample.spring.framework.generated.request.response.Person apply(Person input)
        {
            sample.spring.framework.generated.request.response.Person response = null;

            if (input != null)
            {
                response = new sample.spring.framework.generated.request.response.Person();
                response.setFirstName(input.getFirstName());
                response.setLastName(input.getLastName());
                response.setPhone(input.getPhone());
                response.setPersonId(input.getPersonId());
                response.setAddress(ADDRESS_TRANSFORMER.apply(input.getAddress()));
            }

            return response;
        }
    }

    private static class AddressTransformer
                    implements Function<Address, sample.spring.framework.generated.request.response.Address>
    {
        public sample.spring.framework.generated.request.response.Address apply(Address input)
        {
            sample.spring.framework.generated.request.response.Address response = null;

            if (input != null)
            {
                response = new sample.spring.framework.generated.request.response.Address();

                response.setAddressLine1(input.getAddressLine1());
                response.setAddressLine2(input.getAddressLine2());
                response.setCity(input.getCity());
                response.setState(input.getState());
                response.setZipCode(input.getZipCode());
            }
            return response;
        }
    }

}
