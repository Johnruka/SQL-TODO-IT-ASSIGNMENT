package se.lexicon.Impl;

import com.sun.tools.javac.comp.Todo;
import se.lexicon.DB.Connection;
import se.lexicon.Dao.TodoItemDao;
import se.lexicon.exception.MySQLException;

import se.lexicon.model.TodoItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


import static se.lexicon.model.Person.getId;

public class todoItemDaoImpl implements TodoItemDao {

    private TodoItem todoItem;

    private se.lexicon.model.Person Person;
    private Connection connection;

    public todoItemDaoImpl(Connection connection) {
        this.connection = connection;

    }
    @Override
    public TodoItem create() {
        String insertQuery = "INSERT INTO TodoItem (title, description) VALUES (?, ?)";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, todoItem.getTitle());
            preparedStatement.setString(2, todoItem.getDescription() );

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                String errorMessage = "Creating calendar failed, no rows affected.";
                throw new MySQLException(errorMessage);
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int TodoitemId = ((ResultSet) generatedKeys).getInt(1);
                    return new TodoItem(TodoitemId, todoItem.getTitle(), todoItem.getDescription());
                } else {
                    String errorMessage = "Creating calendar failed, no ID obtained.";
                    throw new MySQLException(errorMessage);
                }
            }

        } catch (SQLException e) {
            String errorMessage = "An error occurred while creating a calendar.";
            throw new MySQLException(errorMessage, e);
        }
    }

    @Override
    public Optional<Object> findAll() {
        String selectQuery = "SELECT * FROM todo_item";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            List<TodoItem> TodoItemList = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from todo_item");


            while (resultSet.next()) {
                int TodoItemId = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);

                TodoItem todoItem = new TodoItem(TodoItemList.lastIndexOf(TodoItemId), title, description);
                TodoItemList.add(todoItem);
            }

           TodoItemList.forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println("SQL Exception: ");
            e.printStackTrace();
        }


        return Optional.empty();
    }



    @Override
    public Optional<TodoItem> findById() {
        String selectQuery = "SELECT * FROM todo_item WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, todoItem.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");

                String description = resultSet.getString("_description");



                TodoItem todoItem = new TodoItem(id, title, description);

                return Optional.of(todoItem);
            }
        } catch (SQLException e) {
            String errorMessage = "Error occurred while finding a todo_item by ID " + todoItem.getId();
            throw new MySQLException(errorMessage, e);
        }
        return Optional.empty();
    }

    @Override
    public Collection<Todo> findByDoneStatus() {
        return List.of();
    }

    @Override
    public Optional<Integer> findByAssignee() {
        String query = "select * from users where username = ?";

        try (
                //Connection connection = OracleDBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);

        ) {
            preparedStatement.setInt(1, todoItem.getAssigneeId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int foundAssigneeId = resultSet.getInt("AssigneeId");

                return Optional.of(foundAssigneeId);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding foundAssignee by assigneeId: " + todoItem.getAssigneeId(), e);
        }
    }

    @Override
    public Collection<Todo> findByUnassignedTodoItems() {
        return List.of();
    }

    @Override
    public Todo update() {
        return null;
    }

    @Override
    public boolean deleteById() {
        String query = "DELETE FROM Todo_item WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, getId());

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            String errorMessage = "Error occurred while deleting todoItem by ID: " + getId() ;
            throw new MySQLException(errorMessage, e);
        }
    }
    }

