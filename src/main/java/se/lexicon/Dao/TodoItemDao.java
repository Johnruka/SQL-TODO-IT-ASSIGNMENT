package se.lexicon.Dao;

import com.sun.tools.javac.comp.Todo;
import se.lexicon.model.TodoItem;

import java.util.Collection;
import java.util.Optional;

public interface TodoItemDao {

    TodoItem create();
    Collection<Todo> findAll();
    Optional<TodoItem> findById();
    Collection<Todo>findByDoneStatus();
    Collection<Todo>findByAssignee();
    Collection<Todo> findByUnassignedTodoItems();
    Todo update();
    boolean deleteById();
}
