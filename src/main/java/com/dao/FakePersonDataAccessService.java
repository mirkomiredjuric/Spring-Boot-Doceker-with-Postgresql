package com.dao;

import com.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakePersonDataAccess")
public class FakePersonDataAccessService implements PersonDAO {

    private List<Person> fakeDB = new ArrayList<>();

    @Override
    public int insertPerson(UUID uuid, Person person) {
        fakeDB.add(new Person(uuid, person.getName()));
        return 1;
    }

    @Override
    public List<Person> getAllPersons() {
        return fakeDB;
    }

    @Override
    public int deletePersonByUuid(UUID uuid) {
        Optional<Person> personMaybe = selectPersonByUuid(uuid);
        if (personMaybe.isPresent()){
            fakeDB.remove(personMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonByUuid(UUID uuid, Person person) {
        return selectPersonByUuid(uuid).map(p -> {
            int indexOfPersonToUpdate = fakeDB.indexOf(p);
            if (indexOfPersonToUpdate >= 0){
                fakeDB.set(indexOfPersonToUpdate, new Person(uuid, person.getName()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

    @Override
    public Optional<Person> selectPersonByUuid(UUID uuid) {
        return fakeDB.stream().filter(person -> person.getUuid().equals(uuid)).findFirst();
    }
}
