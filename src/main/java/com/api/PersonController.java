package com.api;

import com.exception.ApiRequestException;
import com.model.Person;
import com.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //insert Person kroz body
    //localhost:8080/api/v1/person POST
    //u body ide JSON
    //{
    //   "name": "Mesa Selimovic"
    //}
    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person){
        personService.addPerson(person);
    }

    //getting all persons
    //localhost:8080/api/v1/person
    @GetMapping
    public List<Person> getAllPersons(){
        return personService.selectAllPersons();
    }

    //getting person by uuid
    //localhost:8080/api/v1/person/uuid osobe koja vec postoji
    @GetMapping(path = "/{uuid}")
    public Person selectPersonByUuid(@PathVariable("uuid") UUID uuid){
        return personService.selectPersonByUuid(uuid).orElse(null);
    }

    @GetMapping(path = "/error")
    public List<Person> getAllPersonsError(){
        throw new ApiRequestException("Cannot get all persons");
    }

    //delete person by uuid
    //localhost:8080/api/v1/person/uuid osobe koja vec postoji DELETE
    @DeleteMapping(path = "/{uuid}")
    public void deletePersonByUuid(@PathVariable("uuid") UUID uuid){
        personService.deletePerson(uuid);
    }

    //update person by uuid
    //localhost:8080/api/v1/person/uuid osobe koja vec postoji PUT
    @PutMapping(path = "/{uuid}")
    public void updatePersonByUuid(@PathVariable("uuid") UUID uuid, @Valid @NotNull @RequestBody Person person){
        personService.updatePerson(uuid, person);
    }
}
