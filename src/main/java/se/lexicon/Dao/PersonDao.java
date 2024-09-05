package se.lexicon.Dao;

import se.lexicon.model.Person;

import java.util.Collection;

public interface PersonDao {

    Person create(Person person);
    Collection<Person> findAll();
    Person findById();
    Collection<Person> findByName();
    Person update();
    Boolean deleteById();
}
