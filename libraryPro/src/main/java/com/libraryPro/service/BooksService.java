package com.libraryPro.service;



import com.libraryPro.entity.Books;

import java.util.List;
import java.util.Optional;

public interface BooksService {

    public List<Books> findAll();

    public Optional<Books> findById(int theId);

    public void save(Books theBooks);

    public void deleteById(int theId);

    public List<Books> findByUserId(String theId);



}