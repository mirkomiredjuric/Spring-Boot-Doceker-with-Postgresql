package com.dao;

import com.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresDataAccessService")
public class PostgresDataAccessService implements PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID uuid, Person person) {
        return 0;
    }

    @Override
    public List<Person> getAllPersons() {
        final String sql = "SELECT uuid, name FROM person";
        List<Person> listOfPeople = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID uuid = UUID.fromString(resultSet.getString("uuid"));
            String name = resultSet.getString("name");
            return new Person(uuid, name);
        });
        return listOfPeople;
    }

    @Override
    public int deletePersonByUuid(UUID uuid) {
        return 0;
    }

    @Override
    public int updatePersonByUuid(UUID uuid, Person person) {
        return 0;
    }

    @Override
    public Optional<Person> selectPersonByUuid(UUID uuid) {
        final String sql = "SELECT uuid, name FROM person WHERE uuid = ?";
        Person person = jdbcTemplate.queryForObject(sql, new Object[]{uuid}, ((resultSet, i) -> {
                    UUID uuidFromDb = UUID.fromString(resultSet.getString("uuid"));
                    String name = resultSet.getString("name");
                    return new Person(uuidFromDb, name);
                })
        );

        return Optional.ofNullable(person);
    }
}
