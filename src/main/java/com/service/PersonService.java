package com.service;

import com.dao.PersonDAO;
import com.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDAO personDAO;

    @Autowired
    public PersonService(@Qualifier("postgresDataAccessService") PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public int addPerson(Person person){
        return personDAO.insertPerson(person);
    }

    public List<Person> selectAllPersons(){
        return personDAO.getAllPersons();
    }

    public Optional<Person> selectPersonByUuid(UUID uuid){
        return personDAO.selectPersonByUuid(uuid);
    }

    public int deletePerson(UUID uuid){
       return personDAO.deletePersonByUuid(uuid);
    }

    public int updatePerson(UUID uuid, Person updatePerson){
        return personDAO.updatePersonByUuid(uuid, updatePerson);
    }


}
