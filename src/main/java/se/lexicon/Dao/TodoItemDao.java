package se.lexicon.Dao;

import com.sun.tools.javac.comp.Todo;

import java.util.Collection;

public interface TodoItemDao {

    Todo create();
    Collection<Todo> findAll();
    Todo findById();
    Collection<Todo>findByDoneStatus();
    Collection<Todo>findByAssignee();
    Collection<Todo> findByUnassignedTodoItems();
    Todo update();
    boolean deleteById();
}
