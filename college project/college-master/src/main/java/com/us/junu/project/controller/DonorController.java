
package com.us.junu.project.controller;

import com.us.junu.project.model.Donor;
import com.us.junu.project.model.User;
import com.us.junu.project.resource.DonorDto;
import com.us.junu.project.service.DonorService;
import com.us.junu.project.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class DonorController {
    private final UserService userService;
    private final DonorService donorService;

    public DonorController(UserService userService, DonorService donorService) {
        this.userService = userService;
        this.donorService = donorService;
    }

    @RequestMapping(value = {"/donor"}, method = RequestMethod.GET)
    public ModelAndView donor() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("user", new User());

        List<Donor> allDonor = donorService.findAllDonor();
        model.addObject("donorList", allDonor);

        model.setViewName("home/donor");
        return model;
    }

    @RequestMapping(value = {"/donate"}, method = RequestMethod.GET)
    public ModelAndView donate() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("donor", new Donor());
        model.setViewName("home/donate");
        return model;
    }

    @RequestMapping(value = {"/donate"}, method = RequestMethod.POST)
    public ModelAndView createDonor(@Valid DonorDto donorDto, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Optional<Donor> byId = donorService.findByEmail(user.getEmail());

        if (bindingResult.hasErrors()) {
            model.setViewName("");
        } else if (byId.isPresent()) {
            model.addObject("errMsg", "Donor already exists");
            model.addObject("donor", new DonorDto());
            model.addObject("userName", user.getFirstname() + " " + user.getLastname());
            model.setViewName("home/donate");
        } else {
            donorService.addDonor(donorDto);
            model.addObject("msg", "Donor has been added successfully!");
            model.addObject("donor", new DonorDto());
            model.addObject("userName", user.getFirstname() + " " + user.getLastname());
            model.setViewName("home/donate");
        }
        return model;
    }
}
