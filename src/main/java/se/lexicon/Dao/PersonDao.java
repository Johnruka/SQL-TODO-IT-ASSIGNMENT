package se.lexicon.Dao;

import se.lexicon.model.Person;

import java.util.Collection;
import java.util.Optional;

public interface PersonDao {

    Person create(Person person);
    Collection<Person> findAll();
    Optional<Person> findById();
    Collection<Person> findByName();
    Person update();
    Boolean deleteById();
}
