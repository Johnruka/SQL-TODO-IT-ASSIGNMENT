package se.lexicon.Impl;

import se.lexicon.DB.Connection;
import se.lexicon.Dao.PersonDao;
import se.lexicon.exception.MySQLException;
import se.lexicon.model.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;



import static se.lexicon.model.Person.getFirstName;
import static se.lexicon.model.Person.getId;

public class PersonDaoImpl implements PersonDao {

  private Person Person;
    private Connection connection;

    public PersonDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Person create(Person person) {
        String query = "insert into person(first-name, _last-name) values(?,?)";
        try (

                PreparedStatement preparedStatement = connection.prepareStatement(query);

        ) {

            Person Person = new Person(getFirstName(),getFirstName());
            preparedStatement.setString(1, se.lexicon.model.Person.getFirstName());
            preparedStatement.setString(2, Person.getLastName());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new MySQLException("Creating Person failed, no rows affected.");
            }
            return person;
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while creating person: " + getFirstName());
        }
    }

    @Override
    public Collection<Person> findAll() {
        return List.of();
    }

    @Override
    public Optional<Person> findById() {
        String selectQuery = "SELECT * FROM Person WHERE id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setInt(1,2);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    //getting username and title from result set and set it to a new Calendar object
                    String firstName= ((ResultSet) resultSet).getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    return Optional.of(new Person(Person.getFirstName(),Person.getFirstName()));
                }
            }

        } catch (SQLException e) {
            String errorMessage = "Error occurred while finding Person by id: " + findById();
            throw new MySQLException(errorMessage, e);
        }

        return Optional.empty();
    }

    @Override
    public Collection<Person> findByName() {
        return List.of();
    }

    @Override
    public Person update() {
        return null;
    }

    @Override
    public Boolean deleteById() {
        String query = "DELETE FROM Person WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1,2);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            String errorMessage = "Error occurred while deleting MeetingCalendar by ID: " + deleteById();
            throw new MySQLException(errorMessage, e);
        }
    }
}
