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
public class PersonController {
    private final PersonDao personDao;

    @Autowired
    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String index(Model model) {
//        model.addAttribute("people", personDao.index());
        model.addAttribute("people", List.of(new Person("joe", 122), new Person("biden", 39)));
        return "/people/index";
    }

}
