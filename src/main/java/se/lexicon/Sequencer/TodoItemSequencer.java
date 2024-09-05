package se.lexicon.Sequencer;

public class TodoItemSequencer {

    private static int sequencer = 0;

    public static int nextId() {
        return ++sequencer;
    }
}
