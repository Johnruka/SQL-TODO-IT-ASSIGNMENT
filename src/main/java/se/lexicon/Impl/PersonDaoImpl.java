package se.lexicon.Impl;


import se.lexicon.DB.Connection;
import se.lexicon.Dao.PersonDao;
import se.lexicon.exception.MySQLException;
import se.lexicon.model.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static se.lexicon.model.Person.getFirstName;


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

            Person Person = new Person(getFirstName(), person.getLastName());
            preparedStatement.setString(1, Person.setFirstName(getFirstName()));
            preparedStatement.setString(2, Person.setLastName(person.getLastName()));
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
    public Optional<Object> findAll() throws SQLException {
        String selectQuery = "SELECT * FROM Person";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            List<Person> PersonList = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Person");


            while (resultSet.next()) {
                int personId = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);

                Person person = new Person(PersonList.lastIndexOf(personId), firstName, lastName);
                PersonList.add(Person);
            }

            PersonList.forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println("SQL Exception: ");
            e.printStackTrace();
        }


        return Optional.empty();
    }

    @Override
    public Optional<Person> findById() {
        String selectQuery = "SELECT * FROM Person WHERE id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setInt(1, Person.getId());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String firstName = ((ResultSet) resultSet).getString("firstName");
                    String lastName = ((ResultSet) resultSet).getString("lastName");
                    return Optional.of(new Person(getFirstName(), update().getLastName()));
                }
            }

        } catch (SQLException e) {
            String errorMessage = "Error occurred while finding Person by id: " + findById();
            throw new MySQLException(errorMessage, e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<String> findByName() {
        String query = "select * from person where (first_name, last_name) VALUES (?,?)";

        try (

                PreparedStatement preparedStatement = connection.prepareStatement(query);

        ) {
            preparedStatement.setString(1, getFirstName());
            preparedStatement.setString(2, Person.getLastName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String foundFirstName = resultSet.getString("firstName");
                String foundLastName = resultSet.getString("_LastName");

                return Optional.of(foundLastName);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding person by firstName: " + getFirstName(), e);
        }
    }

    @Override
    public Person update() {
        return null;
    }

    @Override
    public Boolean deleteById() {
        String query = "DELETE FROM Person WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, 2);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            String errorMessage = "Error occurred while deleting person by ID: " + deleteById();
            throw new MySQLException(errorMessage, e);
        }
    }
}
