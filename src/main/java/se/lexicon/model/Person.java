package se.lexicon.model;

import java.util.Objects;

public class Person {

    private static Integer id;
    private static String firstName;
    public static String lastName;


    public Person(String firstName, String lastName) {
        Person.firstName = firstName;
        Person.lastName = lastName;
    }

    public Person(Integer id, String firstName, String lastName) {
        Person.firstName = firstName;
        Person.lastName = lastName;
        if (id == null) throw new RuntimeException("id is null");
        Person.id = id;
    }

    public static Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Person.id = id;
    }

    public static String getFirstName() {
        return firstName;
    }

    public String setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty())
            throw new IllegalArgumentException("firstName is null or empty.");
        Person.firstName = firstName;
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty())
            throw new IllegalArgumentException("lastName is empty.");
        Person.lastName = lastName;
        return lastName;
    }

    public String getSummary() {
        return "Person ID: " + getId() + " Name: " + getFirstName() + " " + getLastName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
