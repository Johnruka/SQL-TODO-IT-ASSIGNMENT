package se.lexicon.Dao;

import com.sun.tools.javac.comp.Todo;
import se.lexicon.model.TodoItem;

import java.util.Collection;
import java.util.Optional;

public interface TodoItemDao {

    TodoItem create();

    Optional<Object> findAll();

    Optional<TodoItem> findById();

    Collection<Todo> findByDoneStatus();

    Optional<Integer> findByAssignee();

    Collection<Todo> findByUnassignedTodoItems();

    boolean deleteById();
}
