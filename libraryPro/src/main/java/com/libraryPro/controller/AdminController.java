package com.libraryPro.controller;

import com.libraryPro.entity.Books;
import com.libraryPro.service.BooksService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminController {
    final BooksService bookService;


    public AdminController(BooksService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/list")
    public String listBooks(Model theModel) {
        // get employees from db
        List<Books> theBooks = bookService.findAll();
        // add to the spring model
        theModel.addAttribute("books", theBooks);
        return "admin-list-books";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        Books theBook = new Books();
        theModel.addAttribute("books", theBook);
        return "admin-books-form";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theID, Model theModel) {
        //get the book from the service
        Optional<Books> theBook = bookService.findById(theID);
        //set book as a model attribute to pre-populate the form
        theModel.addAttribute("books", theBook);
        return "admin-books-form";
    }
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("books") Books theBook) {
        // save the book
        bookService.save(theBook);
        // use a redirect to prevent duplicate submissions
        return "redirect:/admin/books/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int theId) {
        // delete the book
        bookService.deleteById(theId);
        return "redirect:/admin/books/list";
    }


}
