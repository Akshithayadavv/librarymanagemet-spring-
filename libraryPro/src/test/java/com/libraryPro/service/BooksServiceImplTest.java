package com.libraryPro.service;

import com.libraryPro.entity.Books;
import com.libraryPro.entity.Users;
import com.libraryPro.repository.BooksRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BooksServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BooksRepository booksRepository;

    @Test
    public void whenFindById_thenReturnBook() {
        // given
        Users user = new Users(1, "Harry", "qwer", "STUDENT");
        Books book = new Books(1, "Harry Potter", user);
        entityManager.flush();
        entityManager.persist(book);
        entityManager.flush();

        // when
        Optional<Books> found = booksRepository.findById(book.getId());

        // then
        assertThat(found.get().getName())
                .isEqualTo(book.getName());
    }
    @Test
    public void whenFindByUserId_thenReturnBooks() {
        // given
        Users user = new Users(1, "Harry", "qwer", "STUDENT");
        Books book = new Books(1, "Harry Potter", user);
        entityManager.flush();
        entityManager.persist(book);
        entityManager.flush();

        // when
        List<Books> found = booksRepository.findByUserId(String.valueOf(user.getId()));

        // then
        assertThat(found.size())
                .isEqualTo(1);
    }

}
