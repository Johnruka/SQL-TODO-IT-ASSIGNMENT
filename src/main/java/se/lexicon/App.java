package se.lexicon;


import se.lexicon.DB.Connection;
import se.lexicon.Dao.PersonDao;
import se.lexicon.Dao.TodoItemDao;
import se.lexicon.Impl.PersonDaoImpl;
import se.lexicon.Impl.todoItemDaoImpl;
import se.lexicon.model.Person;

public class App {

    public static void main( String[] args ) {
        Connection connection = (Connection) Connection.getConnection();
        PersonDao PersonDao = new PersonDaoImpl(connection);
        TodoItemDao TodoItemDao = new todoItemDaoImpl(connection);

    }
}
