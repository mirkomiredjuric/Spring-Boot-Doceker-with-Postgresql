package com.dao;

import com.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDAO {

    int insertPerson(UUID uuid, Person person);

    default int insertPerson(Person person){
        UUID uuid = UUID.randomUUID();
        return insertPerson(uuid, person);
    }

    List<Person> getAllPersons();

    int deletePersonByUuid(UUID uuid);

    int updatePersonByUuid(UUID uuid, Person person);

    Optional<Person> selectPersonByUuid(UUID uuid);
}
