package com.libraryPro.controller;

import com.libraryPro.entity.Users;
import com.libraryPro.service.BooksService;
import com.libraryPro.service.UsersService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersController {

    public static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    final BooksService bookService;
    final UsersService usersService;

    public UsersController(BooksService bookService, UsersService usersService) {
        this.bookService = bookService;
        this.usersService = usersService;
    }


    @GetMapping("/list")
    public String listUsers(Model theModel) {
        // get users from db
        List<Users> theUsers = usersService.findAll();
        // add to the spring model
        theModel.addAttribute("users", theUsers);
        return "list-users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        Users theUsers = new Users();
        theModel.addAttribute("users", theUsers);
        return "users-form";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("usersId") int theID, Model theModel) {
        //get the users from the service
        Users theUsers = usersService.findById(theID);
        //set users as a model attribute to pre-populate the form
        theModel.addAttribute("users", theUsers);
        return "users-form";
    }
    @PostMapping("/save")
    public String saveUsers(@ModelAttribute("users") Users theUsers) {
        // save the users
        usersService.save(theUsers);
        // use a redirect to prevent duplicate submissions
        return "redirect:/users/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("usersId") int theId) {
        // delete the users
        usersService.deleteById(theId);
        // redirect to /users/list
        return "redirect:/users/list";
    }
}
