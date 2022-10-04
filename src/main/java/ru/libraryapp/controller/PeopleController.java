package ru.libraryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.libraryapp.dao.PersonDAO;
import ru.libraryapp.model.Person;
import ru.libraryapp.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String listOfPeople(Model model) {
        model.addAttribute("listOfPeople", personDAO.getListOfPeople());
        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") long id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        model.addAttribute("listOfBooks", personDAO.getListOfReceivedBooks(id));
        return "people/person";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") long id,
                         @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        Person oldPerson = personDAO.getPerson(id);

        if (!oldPerson.getName().equals(person.getName())) {
            personValidator.validate(person, bindingResult);
        }

        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
