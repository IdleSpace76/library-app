package ru.libraryapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.libraryapp.model.Book;
import ru.libraryapp.model.Person;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getListOfBooks() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, year) values (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public Book getBook(long id) {
        return jdbcTemplate.query("SELECT * from book where book_id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream().findAny().orElse(null);
    }

    public void update(long id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book set title=?, author=?, year=? where book_id=?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public Person getBookOwner(long id) {
        return jdbcTemplate.query("SELECT Person.name FROM Book JOIN Person ON Book.person_id = Person.person_id " +
                        "WHERE book_id = ?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findAny().orElse(null);
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM book where book_id=?", id);
    }

    public void release(long id) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE book_id=?", id);
    }

    public void assign(long id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", selectedPerson.getPerson_id(), id);
    }
}
