package com.libraryPro.service;

import com.libraryPro.entity.Books;
//import com.libraryPro.exception.NotFoundException;
import com.libraryPro.repository.BooksRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BooksServiceImpl implements BooksService {
    final BooksRepository bookRepository;

    public BooksServiceImpl(BooksRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Books> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Books> findById(int theId) {
        return bookRepository.findById(theId);
               // .orElseThrow(() -> new NotFoundException(String.format(" not found  with ID %d", theId)));
    }

    @Override
    public void save(Books theBook) {
        bookRepository.save(theBook);
    }

    @Override
    public void deleteById(int theId) {
        bookRepository.deleteById(theId);
    }

    @Override
    public List<Books> findByUserId(String theId) { return bookRepository.findByUserId(theId); }

}
