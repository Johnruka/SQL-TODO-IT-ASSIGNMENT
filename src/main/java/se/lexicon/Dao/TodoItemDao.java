package se.lexicon.Dao;

import com.sun.tools.javac.comp.Todo;
import se.lexicon.model.TodoItem;

import java.util.Collection;
import java.util.Optional;

public interface TodoItemDao {

    TodoItem create();

    Optional<Object> findAll();

    Optional<TodoItem> findById();


    Optional<Integer> findByAssignee();


    boolean deleteById();
}
