package phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import phonebook.entity.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/edit{name}")
    private ModelAndView editView(@PathVariable String name, ModelAndView modelAndView) {
        Contact contactToEdit = getContactToEdit(name);
        modelAndView.setViewName("edit");
        modelAndView.addObject(contactToEdit);
        return modelAndView;
    }

    @PutMapping("/edit{name}")
    private String edit(@PathVariable String name, Contact contact) {
        Contact editedContact = getContactToEdit(name);
        editedContact.setName(contact.getName());
        editedContact.setNumber(contact.getNumber());
        return "redirect:/";
    }

    @PostMapping("/")
    private String addContact(Contact contact) {
        this.contacts.add(contact);
        return "redirect:/";
    }

    private Contact getContactToEdit(String name) {
        return this.contacts.stream().filter(c -> c.getName().equals(name)).collect(Collectors.toList()).get(0);
    }
}