package ru.libraryapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.libraryapp.model.Book;
import ru.libraryapp.model.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getListOfPeople() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPerson(long id) {
        return jdbcTemplate.query("SELECT * FROM person where person_id=?",
                new BeanPropertyRowMapper<>(Person.class), id).stream().findAny().orElse(null);
    }

    public Person getPerson(String name) {
        return jdbcTemplate.query("SELECT * FROM person where name=?",
                new BeanPropertyRowMapper<>(Person.class), name).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, age) VALUES (?, ?)", person.getName(), person.getAge());
    }

    public void update(long id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?, age=? WHERE person_id=?", updatedPerson.getName(),
                updatedPerson.getAge(), id);
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM person where person_id=?", id);
    }

    public List<Book> getListOfReceivedBooks(long id) {
        return jdbcTemplate.query("SELECT * FROM book where person_id=?", new BeanPropertyRowMapper<>(Book.class), id);
    }
}
