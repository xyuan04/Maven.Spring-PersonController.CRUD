package service;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person getPerson(Long id) {
        return personRepository.findById(id).get();
    }

    public List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();

        personRepository.findAll().forEach(personList::add);

        return personList;
    }

    public Person updatePerson(Long id, Person person) {
        Person ogPerson = personRepository.findById(id).get();
        ogPerson.setFirstName(person.getFirstName());
        ogPerson.setLastName(person.getLastName());
        return personRepository.save(ogPerson);
    }

    public Boolean deletePerson(Long id) {
        personRepository.deleteById(id);
        return true;
    }
}
