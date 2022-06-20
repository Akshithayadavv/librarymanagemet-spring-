package com.libraryPro.controller;

import com.libraryPro.entity.Books;
import com.libraryPro.service.BooksService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookController {
    final BooksService bookService;
    public BookController(BooksService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/access-denied")
    public String accessdenied(){
        return "access-denied";
    }
    @GetMapping("/list")
    public String listBooks(@ModelAttribute DummyBinder params, Model theModel) {
        // get employees from db
        String userId = params.getParam1();
        System.out.println("UserId in bookscontroller is:"+ userId);
        List<Books> theBooks = bookService.findByUserId(userId);
        System.out.println("Books List is: ");
        System.out.println(theBooks);
        // add to the spring model
        theModel.addAttribute("books", theBooks);
        return "list-books";
    }

    class DummyBinder {
        private String param1;

        public String getParam1() {
            return param1;
        }

        public void setParam1(String param1) {
            this.param1 = param1;
        }
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        Books theBook = new Books();
        theModel.addAttribute("books", theBook);
        return "books-form";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theID, Model theModel) {
        //get the book from the service
        //Books theBook = bookService.findById(theID);
        //set book as a model attribute to pre-populate the form
       // theModel.addAttribute("books", theBook);
        return "books-form";
    }
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("books") Books theBook) {
        // save the book
        bookService.save(theBook);
        // use a redirect to prevent duplicate submissions
        return "redirect:/books/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int theId) {
        // delete the book
        bookService.deleteById(theId);
        return "redirect:/books/list";
    }


}
