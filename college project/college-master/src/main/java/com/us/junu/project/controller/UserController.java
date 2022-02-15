package com.us.junu.project.controller;

import com.us.junu.project.model.Contact;
import com.us.junu.project.model.User;
import com.us.junu.project.service.ContactService;
import com.us.junu.project.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class UserController {

    private final UserService userService;
    private final ContactService contactService;

    public UserController(UserService userService, ContactService contactService) {
        this.userService = userService;
        this.contactService = contactService;
    }


    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.setViewName("user/login");
        return model;
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("user/signup");
        return model;
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if (bindingResult.hasErrors()) {
            model.setViewName("user/signup");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("user/signup");
        }

        return model;
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public ModelAndView admin() {

        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("user", new User());

        model.setViewName("home/admin");
        return model;
    }

    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public ModelAndView about() {

        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.setViewName("home/about");
        return model;
    }


    @RequestMapping(value = {"/contact"}, method = RequestMethod.GET)
    public ModelAndView contact() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("contact", new Contact());
        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.setViewName("home/contact");
        return model;
    }

    @RequestMapping(value = {"/contact"}, method = RequestMethod.POST)
    public ModelAndView createDonor(@Valid Contact contact, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        if (bindingResult.hasErrors()) {
            model.setViewName("");
        } else {
            contactService.save(contact);
            model.addObject("msg", "Thanks for your feedback");
            model.addObject("contact", new Contact());
            model.addObject("userName", user.getFirstname() + " " + user.getLastname());
            model.setViewName("home/contact");
        }
        return model;
    }

    @RequestMapping(value = {"/access_denied"}, method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }
}
