package phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import phonebook.entity.Contact;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactController {

    List<Contact> contacts;

    public ContactController() {
        this.contacts = new ArrayList<>();
    }

    @GetMapping("/")
    private ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        modelAndView.addObject("contactList", contacts);
        return modelAndView;
    }

    @PostMapping("/")
    private String addContact(Contact contact) {
        this.contacts.add(contact);
        return "redirect:/";
    }
}