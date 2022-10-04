package ru.libraryapp.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {
    private long person_id;

    @NotEmpty(message = "Empty name")
    @Size(max = 100, message = "Name should be between 3 and 100 characters")
    @Pattern(regexp = "[А-Я][а-я]{0,30} [А-Я][а-я]{0,30} [А-Я][а-я]{0,30}", message = "Wrong name format")
    private String name;

    @Min(value = 0, message = "Wrong age")
    private int age;

    public Person(Long id, String name, int age) {
        this.person_id = id;
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(long person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
