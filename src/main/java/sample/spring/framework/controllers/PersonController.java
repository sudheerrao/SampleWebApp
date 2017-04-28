package sample.spring.framework.controllers;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sample.spring.framework.domain.Person;
import sample.spring.framework.standalone.business.apps.dao.PersonDAO;
import sample.spring.framework.transformer.PersonRequestTransformer;
import sample.spring.framework.transformer.PersonResponseTransformer;

import java.util.List;

/**
 * Created by associate on 3/7/17.
 */

@RestController
@RequestMapping("/person")
public class PersonController
{
    @Autowired
    PersonDAO perDAO;

    @Autowired
    PersonRequestTransformer personRequestTransformer;

    @Autowired
    PersonResponseTransformer personResponseTransformer;

    @RequestMapping(value="/{phoneNbr}", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
    public @ResponseBody sample.spring.framework.generated.request.response.Person getPersonDetails(@PathVariable("phoneNbr") String phoneNbr)
    {
        List<Person> personList=null;
        Person per=null;
        sample.spring.framework.generated.request.response.Person response=null;

        if(StringUtils.isNotBlank(phoneNbr))
        {
            personList = perDAO.getRecord(phoneNbr);
        }

        if(CollectionUtils.isNotEmpty(personList))
        {
            per = personList.get(0);
            response = personResponseTransformer.convertFromDomain(per);
        }
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces =MediaType.ALL_VALUE,
                    consumes = MediaType.ALL_VALUE)
    public @ResponseBody String createPerson(@RequestBody sample.spring.framework.generated.request.response.Person request)
    {
        boolean retVal= false;
        String response = "FAILRUE";
        Person per = new Person();

        if(null != request)
        {
            per = personRequestTransformer.convertToDomain(request);
            retVal = perDAO.create(per);
        }

        return (retVal == true) ? "SUCCESS" : "FAILURE";
    }
}
