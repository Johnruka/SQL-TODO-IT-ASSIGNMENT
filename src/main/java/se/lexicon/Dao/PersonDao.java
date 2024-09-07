package se.lexicon.Dao;

import se.lexicon.model.Person;

import java.sql.SQLException;
import java.util.Optional;

public interface PersonDao {

    Person create(Person person);
    Optional<Object> findAll() throws SQLException;
    Optional<Person> findById();
    Optional<String> findByName();
    Person update();
    Boolean deleteById();
}
