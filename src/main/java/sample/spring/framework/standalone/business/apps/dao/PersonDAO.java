package sample.spring.framework.standalone.business.apps.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import sample.spring.framework.domain.Address;
import sample.spring.framework.domain.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by associate on 1/26/17.
 */
@Component
public class PersonDAO
{

    @Autowired
    protected DataSourceTransactionManager txnManager;
    protected JdbcTemplate               jdbcTemplate;
    protected NamedParameterJdbcTemplate namedJdbcTemplate;
    private Logger log = Logger.getLogger(PersonDAO.class);



    private String INSERT_PERSON_SQL  = "insert into PERSON values(?,?,?,?,?)";
    private String INSERT_PERSON_SQL1 = "insert into PERSON (PERSON_ID,FIRST_NAME,LAST_NAME,PHONE)"
                    + " values (:PERSON_ID,:FIRST_NAME,:LAST_NAME,:PHONE)";
    private String INSERT_ADDRESS_SQL =
                    "insert into PERSON_ADDRESS (PERSON_ID,ADDRESS_LINE_1,ADDRESS_LINE_2,CITY,STATE,ZIPCODE)"
                                    + "values (:PERSON_ID,:ADDRESS_LINE_1,:ADDRESS_LINE_2,:CITY,:STATE,:ZIPCODE)";


    private String SELECT_PERSON_SQL     = "select PERSON_ID, FIRST_NAME, LAST_NAME, PHONE from PERSON where PHONE=?";
    private String SELECT_PERSON_SQL1    = "select PERSON_ID, FIRST_NAME, LAST_NAME, PHONE from PERSON where PHONE=:phoneNumber";
    private String SELECT_ALL_PERSON_SQL = "select PERSON_ID, FIRST_NAME, LAST_NAME, PHONE from PERSON";

    private String DELETE_PERSON_SQL = "delete from PERSON where PHONE=?";


    @Bean
    protected boolean setDataSource()
    {
        log.debug("Data source is: " + txnManager.getDataSource());
        this.jdbcTemplate = new JdbcTemplate(txnManager.getDataSource());
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(txnManager.getDataSource());

        //requires a return value to be passed by Spring
        return true;
    }

    private String generatePersonId()
    {
        int rand = new Random().nextInt();
        String pid = "P" + rand;
        return pid;
    }

    @Transactional(rollbackFor = { Exception.class })
    public boolean create(Person argPer)
    {
        String id = generatePersonId();
        int retVal=0;

        if(argPer != null)
        {
             retVal = insertPerson(id,argPer);

            if(argPer.getAddress() != null )
            {
                retVal = insertAddress(id, argPer.getAddress());
            }
        }

        return (retVal > 0) ? true : false;
    }


    private int insertPerson(String id,Person argPer)
    {
        int rVal=0;

        if (argPer != null)
        {
            Map<String, Object> personMap = new HashMap<String, Object>();
            personMap.put("PERSON_ID", id);
            personMap.put("FIRST_NAME", argPer.getFirstName());
            personMap.put("LAST_NAME", argPer.getLastName());
            personMap.put("PHONE", argPer.getPhone());

            rVal =  namedJdbcTemplate.update(INSERT_PERSON_SQL1, personMap);
        }
        return rVal;
    }

    private int insertAddress(String id, Address add)
    {
        int rVal=0;
        Map<String, Object> addMap = new HashMap<String, Object>();
        addMap.put("PERSON_ID", id);
        addMap.put("ADDRESS_LINE_1", add.getAddressLine1());
        addMap.put("ADDRESS_LINE_2", add.getAddressLine2());
        addMap.put("CITY", add.getCity());
        addMap.put("STATE", add.getState());
        addMap.put("ZIPCODE", add.getZipCode());

        rVal =  namedJdbcTemplate.update(INSERT_ADDRESS_SQL, addMap);
        return rVal;

    }

    public List<Person> getRecord(String phoneNumber)
    {
        SqlParameterSource queryParam = new MapSqlParameterSource("phoneNumber", phoneNumber);
        return this.namedJdbcTemplate.query(SELECT_PERSON_SQL1, queryParam, new RowMapper<Person>()
        {
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                Person per = new Person();
                per.setFirstName(rs.getString("FIRST_NAME"));
                per.setLastName(rs.getString("LAST_NAME"));
                per.setPhone(rs.getString("PHONE"));

                return per;
            }
        });
    }

    @Transactional(isolation = Isolation.DEFAULT, readOnly = false)
    public boolean delete(String phoneNumber)
    {
        boolean isDeleted = false;
        int retVal = jdbcTemplate.update(DELETE_PERSON_SQL, phoneNumber);

        if (retVal > 0)
            isDeleted = true;

        return isDeleted;
    }


    public int insertPerson(String fName, String lName, String address, String phone)
    {
        int rand = new Random().nextInt();
        String pid = "P" + rand;
        return jdbcTemplate.update(INSERT_PERSON_SQL, pid, fName, lName, phone);

    }

}
