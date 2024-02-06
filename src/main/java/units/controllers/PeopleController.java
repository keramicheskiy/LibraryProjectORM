package units.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import units.dao.PersonDao;
import units.models.Person;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDao personDao;

    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String index(Model model) {
//        model.addAttribute("people", personDao.index());
        model.addAttribute("people", List.of(new Person("joe", 122), new Person("biden", 39)));
        return "/people/index";
    }

    @GetMapping("/new")
    public String createNewPerson(Model model) {
        model.addAttribute("person", new Person());
        return "/people/new";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@ModelAttribute("id") int id, Model model) {
        model.addAttribute("person", personDao.get(id));
        return "/person/edit";
    }

}
