package controller;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/console/")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("people")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return new ResponseEntity<>(personService.createPerson(person), HttpStatus.CREATED);
    }

    @GetMapping("people")
    public ResponseEntity<List<Person>> getPersonList() {
        return new ResponseEntity<>(personService.getPersonList(), HttpStatus.OK);
    }

    @GetMapping("people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        Person person = personService.getPerson(id);

        if(person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else return new ResponseEntity<>(person, HttpStatus.NOT_FOUND);
    }

    @PutMapping("people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return new ResponseEntity<>(personService.updatePerson(id, person), HttpStatus.OK);
    }

    @DeleteMapping("people/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable Long id) {
        return new ResponseEntity<>(personService.deletePerson(id), HttpStatus.NO_CONTENT);
    }

}
